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
   public void minesweeper()
   {
      int yspot1;
      int xspot1;
      do{
         xspot1 = (int)(Math.random() * 10.0);
         yspot1 = (int)(Math.random() * 10.0);
         if(xspot1 >= 9)
            xspot1 -= 1;
         
      }while(temp[xspot1][yspot1].isShip == true || temp[xspot1 + 1][yspot1].isShip == true);
      ships[xspot1][yspot1].becomeShip();
      ships[xspot1 + 1][yspot1].becomeShip();
      
   }
   public void frigate()
   {
      int yspot1;
      int xspot1;
      do{
         xspot1 = (int)(Math.random() * 10.0);
         yspot1 = (int)(Math.random() * 10.0);
         while(yspot1 >= 8)
            yspot1 -= 1;
         
      }while(temp[xspot1][yspot1].isShip == true || temp[xspot1][yspot1 + 1].isShip == true || temp[xspot1][yspot1 + 2].isShip == true);
      ships[xspot1][yspot1].becomeShip();
      ships[xspot1][yspot1 + 1].becomeShip();
      ships[xspot1][yspot1 + 2].becomeShip();
   }
  public void cruiser()
   {
      int yspot1;
      int xspot1;
      do{
         xspot1 = (int)(Math.random() * 10.0);
         yspot1 = (int)(Math.random() * 10.0);
         while(xspot1 >= 7)
            xspot1 -= 1;
         
      }while(temp[xspot1][yspot1].isShip == true || temp[xspot1 + 1][yspot1].isShip == true || temp[xspot1 + 2][yspot1].isShip == true || temp[xspot1 + 3][yspot1].isShip == true);
      ships[xspot1][yspot1].becomeShip();
      ships[xspot1 + 1][yspot1].becomeShip();
      ships[xspot1 + 2][yspot1].becomeShip();
      ships[xspot1 + 3][yspot1].becomeShip();
      
   }
  public void battleship()
   {
      int yspot1;
      int xspot1;
      do{
         xspot1 = (int)(Math.random() * 10.0);
         yspot1 = (int)(Math.random() * 10.0);
         while(yspot1 >= 6)
            yspot1 -= 1;
         
      }while(temp[xspot1][yspot1].isShip == true || temp[xspot1][yspot1 + 1].isShip == true || temp[xspot1][yspot1 + 2].isShip == true || temp[xspot1][yspot1 + 3].isShip == true || temp[xspot1][yspot1 + 4].isShip == true);
      ships[xspot1][yspot1].becomeShip();
      ships[xspot1][yspot1 + 1].becomeShip();
      ships[xspot1][yspot1 + 2].becomeShip();
      ships[xspot1][yspot1 + 3].becomeShip();
      ships[xspot1][yspot1 + 4].becomeShip();
   }
}