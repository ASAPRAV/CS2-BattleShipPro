public class Ship
{
   public int size;
   public spot[][] temp;
   public spot[][] ships;
   public boolean sunk;
   public Ship(int len, spot[][] spaces)
   {
      sunk = false;
      ships = new spot[10][10];
      for(int r = 0; r < 10; r++)
      {
         for(int c = 0; c < 10; c++)
         {
            ships[r][c] = new spot();
         }
      }
      temp = spaces;
      size = len;
      if(len == 2)
         minesweeper();
      else if(len == 3)
         frigate();
      else if(len == 4)
         cruiser();
      else
         battleship();
      for(int r = 0; r < 10; r++)
      {
         for(int c = 0; c < 10; c++)
         {
            if(ships[r][c].isShip)
            {
               spaces[r][c].becomeShip();
            }
         }
      } 
   }
}