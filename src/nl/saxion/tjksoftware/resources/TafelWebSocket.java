/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.saxion.tjksoftware.resources;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import nl.saxion.tjksoftware.models.Log;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

import util.HTMLFilter;

/**
 * Example web socket servlet for chat.
 */
@WebServlet("/bordnumber")
public class TafelWebSocket extends WebSocketServlet
{
	private static ChatWebSocketServlet chatWeb;

	public static ChatWebSocketServlet getInstance()
	{
		if (chatWeb == null)
		{
			chatWeb = new ChatWebSocketServlet();
		}
		return chatWeb;
	}

	private static final long serialVersionUID = 1L;

	private static final String GUEST_PREFIX = "Guest";

	private final AtomicInteger connectionIds = new AtomicInteger(0);

	private final Set<ChatMessageInbound> connections = new CopyOnWriteArraySet<ChatMessageInbound>();

	public void broadcast(String message)
	{
		for (ChatMessageInbound connection : connections)
		{
			try
			{
				CharBuffer buffer = CharBuffer.wrap(message);
				connection.getWsOutbound().writeTextMessage(buffer);
			}
			catch (IOException ignore)
			{

			}
		}
	}

	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol,
		HttpServletRequest request)
	{
		return new ChatMessageInbound(connectionIds.incrementAndGet());
	}

	private final class ChatMessageInbound extends MessageInbound
	{

		private final String nickname;

		private ChatMessageInbound(int id)
		{
			this.nickname = GUEST_PREFIX + id;
		}

		@Override
		protected void onOpen(WsOutbound outbound)
		{
			connections.add(this);
			Log.I("websocketconnected");
		}

		@Override
		protected void onClose(int status)
		{
			connections.remove(this);
		}

		@Override
		protected void onBinaryMessage(ByteBuffer message) throws IOException
		{
			throw new UnsupportedOperationException(
				"Binary message not supported.");
		}

		@Override
		protected void onTextMessage(CharBuffer message) throws IOException
		{
			// Never trust the client
			String filteredMessage = String.format("%s: %s", nickname,
				HTMLFilter.filter(message.toString()));
			System.out.println(filteredMessage);
		}

		private void broadcast(String message)
		{
			for (ChatMessageInbound connection : connections)
			{
				try
				{
					CharBuffer buffer = CharBuffer.wrap(message);
					connection.getWsOutbound().writeTextMessage(buffer);
				}
				catch (IOException ignore)
				{
					// Ignore
				}
			}
		}
	}
}
