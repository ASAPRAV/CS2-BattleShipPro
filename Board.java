public class Board
{
   public static spot[][] board = new spot[10][10];
   private static Ship patrolBoat;
   private static Ship destroyer;
   private static Ship destroyer2;
   private static Ship carrier;
   private static Ship battleship;
   
   public Board()
   {
      for(int r = 0; r<10; r++)
      {
         for(int c = 0; c<10; c++)
         {
            board[r][c] = new spot();
         }
      }
   }
   public static void makeShip(int len, int startX, int startY, int endX, int endY)
   {
      for(int r = startX; r<endX; r++)
      {
         for(int c = startY; c<endY; c++)
         {
            if(board[r][c].isShip())
               return;
         }
      }
      
      for(int r = startX; r<=endX; r++)
      {
         for(int c = startY; c<=endY; c++)
         {
            board[r][c].makeShip();
            if(len == 2)
            {
               patrolBoat = new Ship(startX, startY, endX, endY);
            }
            if(len == 4)
            {
               battleship = new Ship(startX, startY, endX, endY);
            }
            if(len == 5)
            {
               carrier = new Ship(startX, startY, endX, endY);
            }
            if(len == 3 && destroyer == null)
            {
               destroyer = new Ship(startX, startY, endX, endY);
            }
            else if(len == 3)
            {
               destroyer2 = new Ship(startX, startY, endX, endY);
            }
         }
      }
   }
}