/**
 * ClientHandler.java
 *
 * This class handles communication between the client
 * and the server.  It runs in a separate thread but has a
 * link to a common list of sockets to handle broadcast.
 *
 */
import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class NimClientHandler implements Runnable
{
	private Socket connectionSock = null;
	private ArrayList<Socket> socketList;
	Nim game = new Nim();

	NimClientHandler(Socket sock, ArrayList<Socket> socketList, Nim game)
	{
		this.connectionSock = sock;
		this.socketList = socketList;	// Keep reference to master list
		this.game = game;
	}

	public void run()
	{
    // Get data from a client and send it to everyone else
		try
		{
			System.out.println("Connection made with socket " + connectionSock);
			BufferedReader clientInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));
			while (true)
			{
				// Get data sent from a client
				String clientText = clientInput.readLine();
				if (clientText != null)
				{
					System.out.println("Received: " + clientText);
					// Turn around and output this data
					// to all other clients except the one
					// that sent us this information
					for (Socket s : socketList)
					{
						if (s != connectionSock)
						{
							DataOutputStream clientOutput = new DataOutputStream(s.getOutputStream());
							//clientOutput.writeBytes(clientText + "\n");
							String[] str = clientText.split(" ");
							int index = Integer.parseInt(str[0]);
							int value = Integer.parseInt(str[1]);
							clientOutput.writeBytes("Take " + value + " from row " + index + "\n");
							game.updateHeap(value, (index - 1));
							clientOutput.writeBytes(game.getBoard());
							if (game.checkGameover() == true)
								clientOutput.writeBytes("You lose! :(");

						}
					}
				}
				else
				{
				  // Connection was lost
				  System.out.println("Closing connection for socket " + connectionSock);
				  // Remove from arraylist
				  socketList.remove(connectionSock);
				  connectionSock.close();
				  break;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
			// Remove from arraylist
			socketList.remove(connectionSock);
		}
	}
} // ClientHandler for MTServer.java
