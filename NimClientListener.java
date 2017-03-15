// Bobby Kain and Akash
// edited version of Dr Fahys ClientListener.java

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class NimClientListener implements Runnable
{
	private Socket connectionSock = null;

	//boolean firstTurn = 1;

	NimClientListener(Socket sock)
	{
		this.connectionSock = sock;
	}

	public void run()
	{
    // Wait for data from the server.  If received, output it.
		try
		{
			Nim game = new Nim();
			System.out.println(game.getBoard());
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(connectionSock.getInputStream()));
			while (true)
			{
				// Get data sent from the server
				String serverText = serverInput.readLine();
				if (serverInput != null)
				{
					String[] serverTextArr = serverText.split(" ");
					int heap = Integer.parseInt(serverTextArr[0]);
					int num = Integer.parseInt(serverTextArr[1]);
					game.updateHeap((heap - 1), num);
					System.out.println(game.getBoard());
					if (game.isOver())
					{
						char won = '\0';
						if (serverText.charAt(4) == '1')
							won = '2';
						else
							won = '1';
						System.out.println("Game over my guys. Player " + won + " wins.");
						connectionSock.close();
						break;
					}
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
