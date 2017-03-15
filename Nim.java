// Bobby Kain and Akash Arora

import java.util.*;

public class Nim
{
  public int[] heaps = {1, 3, 5, 7};

  Nim()
  {
    System.out.println("Nim started!");
    System.out.println("Enter your first move!");
    System.out.println("row col.");
  }

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

  public void updateHeap(int heap, int numTaken)
  {
    if (numTaken <= heaps[heap])
      heaps[heap] -= numTaken;
    else
      System.out.println("Too many");
  }

  public boolean isOver()
  {
    int emptyHeaps[] = {0, 0, 0, 0};
    if (Arrays.equals(emptyHeaps, heaps))
      return true;
    else
      return false;
  }
}
