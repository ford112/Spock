// Chinmai Raman and Akash Arora
// edited version of Dr Fahys ClientListener.java

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class SpockClientListener implements Runnable
{
	private Socket connectionSock = null;

//	int playerTurn = 2;

	SpockClientListener(Socket sock)
	{
		this.connectionSock = sock;
	}

	public void run()
	{
    // Wait for data from the server.  If received, output it.
		try
		{
		//	Spock game = new Spock();	
			// Welcome Speech
			System.out.println("\nWelcome to Rock, Paper, Scissors, Lizard, Spock!\n");
			System.out.println("Incase you forgot how to spell your options, check out the title.");
			System.out.println("After both players have chosen their weapon we will tell you who won.");
			System.out.println("Thanks and have fun!\n");
			System.out.println("\nSelect Weapon: ");
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));
			while (true)
			{
				//System.out.println("Select Weapon: ");

			// Turn handling
			//	if (playerTurn == 2)
			//		playerTurn = 1;
			//	else
			//		playerTurn = 2;
			//	System.out.println("It is player " + playerTurn + "'s turn:");
			//	System.out.println(game.getBoard());


				// Get data sent from the server
				String serverText = serverInput.readLine();
				if (serverInput != null)
				{
			                System.out.print(serverText);
                         		// get ints from string input, to be used in Nim's updateHeap()
					//game.update(serverText);
					//End game here
				}
				else
				{
					// Connection was lost
					System.out.println("Closing connection for socket " + connectionSock);
					connectionSock.close();
					break;
				}
			}
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.toString());
		}
	}
} // ClientListener for MTClient
