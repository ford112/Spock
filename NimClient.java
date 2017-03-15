// Bobby Kain and Akash
// edited version of Dr Fahys Client.java

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;

public class NimClient
{
	public static void main(String[] args)
	{
		try
		{
			String hostname = "localhost";
			int port = 7654;

			System.out.println("Connecting to server on port " + port);
			Socket connectionSock = new Socket(hostname, port);

			DataOutputStream serverOutput = new DataOutputStream(connectionSock.getOutputStream());

			System.out.println("Connection made.");

			// Start a thread to listen and display data sent by the server
			NimClientListener listener = new NimClientListener(connectionSock);
			Thread theThread = new Thread(listener);
			theThread.start();

			// Read input from the keyboard and send it to everyone else.
			// The only way to quit is to hit control-c, but a quit command
			// could easily be added.
			Scanner keyboard = new Scanner(System.in);
			while (true)
			{
				String data = keyboard.nextLine();
				serverOutput.writeBytes(data + "\n");
			}
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
} // MTClient
