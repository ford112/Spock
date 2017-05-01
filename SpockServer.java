// Chinmai Raman and Akash Arora
// edited version of Dr Fahys Server.java

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class SpockServer
{
	// Maintain list of all client sockets for broadcast
	private ArrayList<Socket> socketList;

	Spock game;

	public SpockServer()
	{
		socketList = new ArrayList<Socket>();
		game = new Spock();
	}

	private void getConnection()
	{
		// Wait for a connection from the client
		try
		{
			System.out.println("Waiting for client connections on port 7654.");
			ServerSocket serverSock = new ServerSocket(7654);
			// This is an infinite loop, the user will have to shut it down
			// using control-c
			while (true)
			{
				Socket connectionSock = serverSock.accept();
				// Add this socket to the list
				socketList.add(connectionSock);
				// Send to ClientHandler the socket and arraylist of all sockets
				SpockClientHandler handler = new SpockClientHandler(connectionSock, this.socketList, this.game);
				Thread theThread = new Thread(handler);
				theThread.start();
			}
			//serverSock.close();
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args)
	{
		SpockServer server = new SpockServer();
		server.getConnection();
	}
} // SpockServer
