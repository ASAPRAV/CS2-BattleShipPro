public class Ship
{
<<<<<<< HEAD
<<<<<<< HEAD
   private int size;
   private boolean sunk;
   public int[][] locs;
   public Ship(int len)
=======
=======
>>>>>>> master
   public static int size;
   public static spot[][] ships;
   private static boolean sunk;
   private static int startX;
   private static int startY;
   private static int endX;
   private static int endY;
   
   public Ship(int len, int startXe, int startYe, int endXe, int endYe)
<<<<<<< HEAD
>>>>>>> master
=======
>>>>>>> master
   {
      startX = startXe;
      startY = startYe;
      endX = endXe;
      endY = endYe;
      
      sunk = false;
<<<<<<< HEAD
      locs = new int[len][2];
=======
      ships = new spot[10][10];
      for(int r = 0; r < 10; r++)
      {
         for(int c = 0; c < 10; c++)
         {
            ships[r][c] = new spot();
         }
      }
      
      size = len;
      
      for(int r = startX; r < endX; r++)
      {
         for(int c = startY; c < endY; c++)
         {
            ships[r][c].makeShip();
         }
      } 
   }
      
   public boolean isSunk()
   {
      for(int r = startX; r < endX; r++)
      {
         for(int c = startY; c <endY; c++)
         {
            if(ships[r][c].isShip() && !ships[r][c].getGuess())
            {
               return false;
            }
         }
      }
      return true;
<<<<<<< HEAD
>>>>>>> master
=======
>>>>>>> master
   }
}