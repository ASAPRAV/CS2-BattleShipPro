public class spot{
   private boolean isShip;
   private boolean isGuessed;
   public class spot{
      isShip = false;
      isGuessed = false;
   }
   
   public void makeShip()
   {
      isShip = true;
   }
   
   public void guessed()
   {
      guessed = true;
   }
   
   public boolean getShipStatus()
   {
      return isShip;
   }
   
   public boolean getGuess()
   {
      return isGuessed;
   }
}