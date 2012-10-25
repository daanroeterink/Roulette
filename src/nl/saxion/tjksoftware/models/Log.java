package nl.saxion.tjksoftware.models;

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
		System.out.println("INFO " + message);
	}

	/**
	 * Console log message with DEBUG
	 * 
	 * @param message
	 */
	public static void D(String message)
	{
		System.out.println("DEBUG " + message);
	}

	/**
	 * Console log message with ERROR
	 * 
	 * @param message
	 */
	public static void E(String message)
	{
		System.out.println("ERROR " + message);
	}

	/**
	 * Console log message with FATAL
	 * 
	 * @param message
	 */
	public static void F(String message)
	{
		System.out.println("FATAL " + message);
	}

}
