import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

public class game extends Applet implements MouseListener
{
   private Image backbuffer;
   private Graphics backg;
   private static player player1;
   private static player player2;
   private static String screen;
   private static player currentPlayer;
   private static int xstart;
   private static int ystart;
   private static int xend;
   private static int yend;
   private static int xcord;
   private static int ycord;
   
   public void init()
   {
      //initializations
      player1 = new player();
      Color p1 = Color.red;
      player2 = new player();
      Color p2 = Color.blue;
      screen = "start";  
      xstart = -1;
      ystart = -1;
      xend = -1;
      yend = -1;
      xcord = -1;
      ycord = -1;    
      //adds mouse listener
      addMouseListener(this);
      
      //does backbuffering
      backbuffer = createImage(500, 600);
      backg = backbuffer.getGraphics();
   }
   
   public void update(Graphics g)
   {
      //draws the grid
      if(screen.equals("start"))
         startScreen();
      else if(screen.equals("player1setup"))
         player1SetupScreen();
      else if(screen.equals("player2setup"))
         player2SetupScreen();
      else if(screen.equals("player1"))
         player1Screen();
      else if(screen.equals("player2"))
         player2Screen();
      g.drawImage(backbuffer, 0,0, this); //only g
   }
   
   public void paint(Graphics g)
   { 
      this.setSize(500, 600);
      
      update(g); 
   }
   
   public void startScreen()
   {
      backg.setColor(Color.lightGray);
      backg.fillRect(0,0,300, 600);
      backg.setColor(Color.blue);
      backg.fillRoundRect(100, 275, 100, 50, 10, 5);
      backg.setColor(Color.black);
      backg.drawString("START",130, 305);
   }
   public void player1SetupScreen()
   {
      backg.setColor(Color.red);
      backg.fillRect(0,0,300,25);
      backg.fillRect(0,275,300,50);
      backg.fillRect(0,575,300,25);
      backg.fillRect(0,0,25,600);
      backg.fillRect(275,0,25,600);
      backg.setColor(Color.black);
      for(int r = 0; r < 9; r++)
      {
         backg.drawLine(50 + 25*r, 25, 50 + 25*r, 275);
         backg.drawLine(50 + 25*r, 325, 50 + 25*r, 575);
      }
      for(int c =0; c <9; c++)
      {
         backg.drawLine(25, 50 + 25*c, 275, 50 + 25*c);
         backg.drawLine(25, 350 + 25*c, 275, 350 + 25*c);
      }
      backg.setColor(Color.black);
      if(xstart == -1 && ystart == -1)
      {
         backg.drawString("Choose the starting spot for", 305, 340);
         backg.drawString("your battleship", 305, 350);
         repaint();
      }
      else
      {
         backg.drawString("Choose the ending spot for", 305, 340);
         backg.drawString("your battleship", 305, 350);
         repaint();
      }
      
   }
   public void player2SetupScreen()
   {
      backg.setColor(Color.blue);
      backg.fillRect(0,0,300,25);
      backg.fillRect(0,275,300,50);
      backg.fillRect(0,575,300,25);
      backg.fillRect(0,0,25,600);
      backg.fillRect(275,0,25,600);
      backg.setColor(Color.black);
      for(int r = 0; r < 9; r++)
      {
         backg.drawLine(50 + 25*r, 25, 50 + 25*r, 275);
         backg.drawLine(50 + 25*r, 325, 50 + 25*r, 575);
      }
      for(int c =0; c <9; c++)
      {
         backg.drawLine(25, 50 + 25*c, 275, 50 + 25*c);
         backg.drawLine(25, 350 + 25*c, 275, 350 + 25*c);
      }
   }
   public void player1Screen()
   {
   
   }
   public void player2Screen()
   {
   
   }
   public void guess(int x, int y)
   {
      
   }
   
   public void mouseClicked(MouseEvent e)
   {
      //get mouse coordinates
      int x = e.getX();
      int y = e.getY();
      //make sure its within the bounds
      
      if(screen.equals("start"))
      {
         if(x >= 100 && x <= 200 && y >=275 && y< 325)
         screen = "player1setup";
         repaint();
      }
      else if(screen.equals("player1Setup"))
      {
         
         xcord = (int)x/25;
         ycord = (int)(y-300)/25;
         if(xcord > 1 && xcord < 12 && ycord > 1 && ycord < 12)
         {
            xstart = xcord -1;
            ystart = ycord -1;
         }
      }
      else if(screen.equals("player2Setup"))
      {
      
      }
      else if(screen.equals("player1"))
      {
         
         xcord = (int)x/25;
         ycord = (int)y/25;
         if(xcord > 1 && xcord < 12 && ycord > 1 && ycord < 12)
            guess(xcord, ycord);
      }
      else if(screen.equals("player2"))
      {
         
         xcord = (int)x/25;
         ycord = (int)y/25;
         //if(xcord > 1 && xcord < 12 && ycord > 1 && ycord < 12)
         //   guess(xcord, ycord);
      }
   }
   
   public void mouseEntered(MouseEvent e)
   {}
   
   public void mouseExited(MouseEvent e)
   {}
   
   public void mousePressed(MouseEvent e)
   {}
   
   public void mouseReleased(MouseEvent e)
   {}
}