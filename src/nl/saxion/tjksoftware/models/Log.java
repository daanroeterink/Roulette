package nl.saxion.tjksoftware.models;

import java.util.GregorianCalendar;

/**
 * @author Tom Kostense
 * 
 */
public class Log
{

	/**
	 * Console log message with INFO
	 * 
	 * @param message
	 */
	public static void I(String message)
	{
		GregorianCalendar g = new GregorianCalendar();
		System.out.println("[" + g.getTime() + "] INFO " + message);
	}

	/**
	 * Console log message with WARN
	 * 
	 * @param message
	 */
	public static void W(String message)
	{
		GregorianCalendar g = new GregorianCalendar();
		System.out.println("[" + g.getTime() + "] WARN " + message);
	}

	/**
	 * Console log message with DEBUG
	 * 
	 * @param message
	 */
	public static void D(String message)
	{
		GregorianCalendar g = new GregorianCalendar();
		System.out.println("[" + g.getTime() + "] DEBUG " + message);
	}

	/**
	 * Console log message with ERROR
	 * 
	 * @param message
	 */
	public static void E(String message)
	{
		GregorianCalendar g = new GregorianCalendar();
		System.out.println("[" + g.getTime() + "] ERROR " + message);
	}

	/**
	 * Console log message with FATAL
	 * 
	 * @param message
	 */
	public static void F(String message)
	{
		GregorianCalendar g = new GregorianCalendar();
		System.out.println("[" + g.getTime() + "] FATAL " + message);
	}

}
