// Bobby Kain and Akash Arora


public class Nim
{
  public int[] heaps = {1, 3, 5, 7};

  Nim()
  {
    System.out.println("Nim started!");
    System.out.println("Enter your first move!")
    System.out.println("row col.");
  }

  public String getRow(int num)
  {
    String row = "";
    row += num + ": "
    for (int i = 0; i < num; ++i)
      row += "X ";
    row += "\n";
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
    else
      System.out.println("Too many");
  }

  public boolean checkGameover()
  {
    if (Arrays.equals({0, 0, 0, 0}, heaps))
      return true;
    else
      return false;
  }
}
