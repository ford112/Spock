// Bobby Kain and Akash
// edited version of Dr Fahys ClientHandler.java

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class SpockClientHandler implements Runnable
{
	private Socket connectionSock = null;
	private ArrayList<Socket> socketList;
	private int player;
	public Spock game;
	private String nL = System.getProperty("line.separator");
	SpockClientHandler(Socket sock, ArrayList<Socket> socketList, Spock game)
	{
		this.connectionSock = sock;
		this.socketList = socketList;
		this.game = game;	// Keep reference to master list
		player = socketList.size(); // let server know what player is sending inputs
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
				if (clientText != null)	{
					System.out.println("Received: " + clientText);
					// Turn around and output this data
					// to all other clients except the one
					// that sent us this information
					if (game.isValidInput(player, clientText)) {				
						game.assign(player, clientText);	
						for (Socket s : socketList)
						{
							DataOutputStream clientOutput = new DataOutputStream(s.getOutputStream());
							clientOutput.writeBytes("Player " + player + " has locked in. Type [GO] once all players have locked in: ");	
							clientOutput.writeBytes(game.displayChoices());
						}
 					} else if (game.isLocked[player] == 0) {
		                                DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
						clientOutput.writeBytes("Invalid Input. Try again: ");
				 		clientOutput.writeBytes(nL);
                                        } else if (clientText.equals("Display")) {
						DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
                                                clientOutput.writeBytes(game.displayChoices());
				     	} else if (clientText.equals("GO")) {
						DataOutputStream clientOutput = new DataOutputStream(connectionSock.getOutputStream());
                                                clientOutput.writeBytes(game.displayWinners());
					}
				} else {
				  // Connection was lost
				  System.out.println("Closing connection for socket " + connectionSock);
				  // Remove from arraylist
				  socketList.remove(connectionSock);
				  connectionSock.close();
				  //game.reset();
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
}
