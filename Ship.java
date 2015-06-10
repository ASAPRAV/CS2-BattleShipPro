public class Ship
{
   public static spot[][] ships;
   private static int startX;
   private static int startY;
   private static int endX;
   private static int endY;
   
   public Ship(int startXe, int startYe, int endXe, int endYe)
   {
      startX = startXe;
      startY = startYe;
      endX = endXe;
      endY = endYe;
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
   }
}