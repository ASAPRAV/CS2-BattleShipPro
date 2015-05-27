import java.util.*;

public class player
{
   private static ArrayList <Ship> ships = new ArrayList <Ship> ();
   private static spot[][] grid = new spot[10][10];
   
   public player()
   {
      initSpots();
      makeShips();
   }
   
   private static void makeShips()
   {
      //Josh: TO DO:
      //feature each ship & then have them place it
      //once they place it, make sure to alter those spots to have a ship present
   }
   
   private static void initSpots()
   {
      //go through 2D array & initialize ships
      for(int i = 0; i<grid.length; i++)
      {
         for(int j = 0; j<grid[i].length; j++)
         {
            grid[i][j] = new spot();
         }
      }
   }
   
   private static void play()
   {
      //TO DO:
      //find spot to click on
      //launch at that spot
      //reveal what's underneath (ship/water)
   }
   
   private static boolean checkWin()
   {
      //TO DO:
      //see if all the ships are sunk
      return false;
   }
}