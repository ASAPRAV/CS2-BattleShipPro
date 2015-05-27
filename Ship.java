public class Ship
{
   private int size;
   private boolean sunk;
   public int[][] locs;
   public Ship(int len)
   {
      sunk = false;
      locs = new int[len][2];
   }
}