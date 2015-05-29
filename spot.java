import java.awt.*;

public class spot{
   private boolean isShip;
   private boolean isGuessed;
   public spot(){
      isShip = false;
      isGuessed = false;
   }
   
   public void makeShip()
   {
      isShip = true;
   }
   
   public void guessed()
   {
      isGuessed = true;
   }
   
   public boolean isShip()
   {
      return isShip;
   }
   
   public boolean getGuess()
   {
      return isGuessed;
   }
   
   public Color getColor()
   {
      if(isGuessed && isShip)
         return Color.red;
      else if(isGuessed && !(isShip))
         return Color.black;
      else
         return Color.blue;
   }
}