// Chinmai Raman and Akash Arora

import java.util.*;

public class Spock
{
  //Game Manager - Indicies correspond to specific weapons. At anyime, the weapon is either knocked out (== 1) or alive (==0)
  //(rock == isAlive[0], paper == isAlive[1], scissors == isAlive[2], lizard = isAlive[3], spock = isAlive[4])
  public int isAlive[] = new int[5];
  public String player1;
  public String player2;
  public String player3;
  public String player4;
  public String player5;
  public String displayChoices;
  Spock() {}

  //Error handler for user input
  public boolean isValidInput(int playerNumber, String input) {
        input = input.toLowerCase();
        if ((input.equals("rock") || input.equals("paper") || input.equals("scissors") || input.equals("lizard") || input.equals("spock")) && playerNumber <= 5) {
                return true;
        } else {
                return false;
        }
  }

  //Resets player choices, and sets all weapons to "alive",
  public void reset() {
	isAlive[0] = 0;
	isAlive[1] = 0;
	isAlive[2] = 0;
	isAlive[3] = 0;
	isAlive[4] = 0;
	player1 = "";
	player2 = "";
	player3 = "";
	player4 = "";
	player5 = "";
	displayChoices = "";
  }

  //Stores player input (if Valid)
  public void assign(int player, String input) {
	if (isValidInput(player, input)) {
		if(player == 1) {
			player1 = input;
		} else if (player == 2) {
			player2 = input;
		} else if (player == 3) {
			player3 = input;
		} else if (player == 4) {
			player4 = input;
		} else if (player == 5) {
			player5 = input;
		}
		displayChoices += "/nPlayer " + player + ": " + input;
	}  else {
		System.out.print("Invalid User Input");
	}
  }
		

  //Determines the weapons killed by this specific weapon and consequently updates the game manager
  public void kill(String input) {
	input = input.toLowerCase();
	if (input.equals("rock")) { //Rock crushes lizard - and as it always has, rock crushes scissors
		isAlive[3] = 1;
		isAlive[2] = 1;
	} else if (input.equals("paper")) { //Paper covers rock and disproves spocks
		isAlive[0] = 1;
                isAlive[4] = 1;
	} else if (input.equals("scissors")) { //Scissors cuts paper and decapitates lizard
		isAlive[1] = 1;
                isAlive[3] = 1;
	} else if (input.equals("lizard")) { //Lizard poisons spock and eats paper
		isAlive[4] = 1;
                isAlive[1] = 1;
	} else if (input.equals("spock")) { //Spock smashes scissors and vaporizes rock
		isAlive[2] = 1;
                isAlive[0] = 1;
	}
  }

  //Displays which players chose which weapons
  public String displayChoices() {
	return displayChoices;
  }

  // check win conditions
  public boolean isOver()
  {
  	return false;
  }
}
