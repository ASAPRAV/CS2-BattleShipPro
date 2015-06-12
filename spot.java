import java.awt.*;

public class spot{
   public int val;
   
   public spot()
   {
      val = 0;
   }
   
   public void makeShip()
   {
      val = 1;
   }
   
   public void guessed()
   {
      if(val == 0)
         val = 2;
      else if(val == 1)
         val = 3;
   }
   
   public boolean isShip()
   {
      if(val == 1 || val == 3)
         return true;
      return false;
   }
   
   public boolean getGuess()
   {
      if(val == 2 || val == 3)
         return true;
      return false;
   }
}