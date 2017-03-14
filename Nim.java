// Bobby Kain and Akash Arora


public class Nim
{
  public int[] heaps = {1, 3, 5, 7};

  public String getRow(int num)
  {
    String row = "";
    for (int i = 0; i < num; ++i)
    {
      row += "X ";
    }
    return row;
  }

  public String getBoard()
  {
    String board = "";
    for (int i = 0; i < 4; ++i)
    {
      board += getRow(heaps[i]) + "\n";
    }
    return board;
  }

  
}
