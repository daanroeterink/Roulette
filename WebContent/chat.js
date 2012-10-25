﻿var Chat = {};

Chat.socket = null;

Chat.connect = (function (host)
{
    if ('WebSocket' in window)
    {
        Chat.socket = new WebSocket(host);
    }
    else
    {
        return;
    }

    Chat.socket.onopen = function ()
    {

    };

    Chat.socket.onclose = function ()
    {


    };

    Chat.socket.onmessage = function (message)
    {
        $("#console").html(message);
    };
});

Chat.initialize = function ()
{
    if (window.location.protocol == 'http:')
    {
        Chat.connect('ws://' + window.location.host + '/Roulette/chat');
    } else
    {
        Chat.connect('wss://' + window.location.host + '/Roulette/chat');
    }
};

Chat.sendMessage = (function ()
{
    Chat.socket.send("Test");
});

Chat.initialize();

        