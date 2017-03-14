// Bobby Kain and Akash Arora


public class Nim
{
  public int[] heaps = {1, 3, 5, 7};

  public String getRow(int num)
  {
    String row = "";
    for (int i = 0; i < num; ++i)
      row += "X ";
    return row;
  }

  public String getBoard()
  {
    String board = "";
    for (int i = 0; i < 4; ++i)
      board += getRow(heaps[i]) + "\n";
    return board;
  }

  public void updateHeap(int numTaken, int heap)
  {
    if (numTaken <= heaps[heap])
      heaps[heap] -= numTaken;
  }

  public boolean checkGameover()
  {
    boolean gameOver = false;

    int numEmpty = 0;
    int numOne = 0;

    for (int i = 0; i < 4; ++i)
    {
      if (heaps[i] == 0)
        numOne++;
      else if (heaps[i] == 1)
        numOne++;
    }

    if (numEmpty == 3 && numOne == 1)
      gameOver = true;

    return gameOver;
  }
}
