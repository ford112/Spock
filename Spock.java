// Bobby Kain and Akash Arora

import java.util.*;

public class Spock 
{
  public int[] heaps = {1, 3, 5, 7};

  Spock()
  {}

  // return a visual of the stacks
  public String getBoard()
  {
    String board = "";
    for (int i = 0; i < 4; ++i)
    {
      board += (i + 1) + ": ";
      for (int j = 0; j < heaps[i]; ++j)
      {
        board += "X ";
      }
      board += "\n";
    }
    return board;
  }

  // update values of the array based on user input
  public void updateHeap(int heap, int numTaken)
  {
    if (numTaken <= heaps[heap])
      heaps[heap] -= numTaken;
    else
      System.out.println("Too many");
  }

  // check win conditions
  public boolean isOver()
  {
    int end1[] = {1, 0, 0, 0};
    int end2[] = {0, 1, 0, 0};
    int end3[] = {0, 0, 1, 0};
    int end4[] = {0, 0, 0, 1};
    if (Arrays.equals(end1, heaps) || Arrays.equals(end2, heaps) || Arrays.equals(end3, heaps) || Arrays.equals(end4, heaps))
      return true;
    else
      return false;
  }
}
