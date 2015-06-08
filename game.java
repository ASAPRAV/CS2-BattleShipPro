import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

public class game extends Applet implements MouseListener
{
   private Image backbuffer;
   private Graphics backg;
   private static String screen;
   private static int xstart;//gets passed to the ship class
   private static int ystart;
   private static int xend;//gets passed to the ship class
   private static int yend;
   private static int xcord;//used by the mouseListener
   private static int ycord;
   private static Board player1;
   private static Board player2;
   private static int[] shipLengths;
   private int currentLength;
   private int lengthIndex;
   
   public void init()
   {
      //initializations
      Color p1 = Color.red;
      Color p2 = Color.blue;
      screen = "start";  
      xstart = -1;
      ystart = -1;
      xend = -1;
      yend = -1;
      xcord = -1;
      ycord = -1;
      shipLengths = new int[5];
      shipLengths[0] = 5;
      shipLengths[1] = 4;
      shipLengths[2] = 3;
      shipLengths[3] = 3;
      shipLengths[4] = 2;
      currentLength = shipLengths[0];
      lengthIndex = 0;
          
      //adds mouse listener
      addMouseListener(this);
      player1 = new Board();
      player2 = new Board();
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
      backg.setColor(Color.lightGray);
      backg.fillRect(300,0,200,600);
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
      for(int i = 0; i < 5; i++)
      {   
         if(xstart == -1 && ystart == -1)
         {
            backg.drawString("Choose the starting spot for", 305, 340);
            backg.drawString("your "+ shipLengths[i] + "long ship", 305, 350);
            
            repaint();
         }
         else if(xend == -1 && yend == -1)
         {
            backg.setColor(Color.lightGray);
            backg.fillRect(300,275,200,200);
            backg.setColor(Color.gray);
            backg.fillRect(25*(xstart+1)-25, 25*(ystart+1) +275, 25, 25);
            backg.setColor(Color.black);
            backg.drawString("Choose the ending spot for", 305, 340);
            backg.drawString("your ship", 305, 350);
                        
         }
         if(xend != -1)
         {
            player1.makeShip(shipLengths[i], xstart, ystart, xend, yend);
            backg.setColor(Color.gray);
            backg.fillRect(25*(xend+1)-25, 25*(yend+1) +275, 25, 25);
            if(xend == xstart)
            {
               if(ystart < yend)
               {
                  for(int j = 0; j < currentLength -1; j++)
                  {
                     backg.fillRect(25*(xstart+1)-25, 25*(ystart+1) +275 + (j * 25), 25, 25);
                  }
               }
               if(ystart > yend)
               {
                  for(int j = 0; j < currentLength -1; j++)
                  {
                     backg.fillRect(25*(xstart+1)-25, 25*(ystart+1) +275 - (j * 25), 25, 25);
                  }
               }
            }
            if(yend == ystart)
            {
               if(xstart < xend)
               {
                  for(int j = 0; j < currentLength -1; j++)
                  {
                     backg.fillRect(25*(xstart+1)-25 + (j * 25), 25*(ystart+1) +275, 25, 25);
                  }
               }
               if(xstart > xend)
               {
                  for(int j = 0; j < currentLength -1; j++)
                  {
                     backg.fillRect(25*(xstart+1)-25 - (j * 25), 25*(ystart+1) +275, 25, 25);
                  }
               }
            }
            xstart = -1;
            ystart = -1;
            xend = -1;
            yend = -1;
            lengthIndex++;
            if(lengthIndex < 4)
            {
               
               currentLength = shipLengths[lengthIndex];
            }
            
         }
      }
      repaint();
      if(lengthIndex == 5)
         screen = "player2Setup";
      
     
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
      backg.setColor(Color.black);
      for(int i = 0; i < 5; i++)
      {   
         if(xstart == -1 && ystart == -1)
         {
            backg.drawString("Choose the starting spot for", 305, 340);
            backg.drawString("your "+ shipLengths[i] + "long ship", 305, 350);
            
            repaint();
         }
         else if(xend == -1 && yend == -1)
         {
            backg.setColor(Color.lightGray);
            backg.fillRect(300,275,200,200);
            backg.setColor(Color.gray);
            backg.fillRect(25*(xstart+1)-25, 25*(ystart+1) +275, 25, 25);
            backg.setColor(Color.black);
            backg.drawString("Choose the ending spot for", 305, 340);
            backg.drawString("your ship", 305, 350);
                        
         }
         if(xend != -1)
         {
            player1.makeShip(shipLengths[i], xstart, ystart, xend, yend);
            backg.setColor(Color.gray);
            backg.fillRect(25*(xend+1)-25, 25*(yend+1) +275, 25, 25);
            if(xend == xstart)
            {
               if(ystart < yend)
               {
                  for(int j = 0; j < currentLength -1; j++)
                  {
                     backg.fillRect(25*(xstart+1)-25, 25*(ystart+1) +275 + (j * 25), 25, 25);
                  }
               }
               if(ystart > yend)
               {
                  for(int j = 0; j < currentLength -1; j++)
                  {
                     backg.fillRect(25*(xstart+1)-25, 25*(ystart+1) +275 - (j * 25), 25, 25);
                  }
               }
            }
            if(yend == ystart)
            {
               if(xstart < xend)
               {
                  for(int j = 0; j < currentLength -1; j++)
                  {
                     backg.fillRect(25*(xstart+1)-25 + (j * 25), 25*(ystart+1) +275, 25, 25);
                  }
               }
               if(xstart > xend)
               {
                  for(int j = 0; j < currentLength -1; j++)
                  {
                     backg.fillRect(25*(xstart+1)-25 - (j * 25), 25*(ystart+1) +275, 25, 25);
                  }
               }
            }
            xstart = -1;
            ystart = -1;
            xend = -1;
            yend = -1;
            if(lengthIndex < 4)
            {
               lengthIndex++;
               currentLength = shipLengths[lengthIndex];
            }
            
         }
      }
         
         
         repaint();
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
      else if(screen.equals("player1setup"))
      {
         
         xcord = (int)(x+25)/25;
         ycord = (int)(y-275)/25;
         if((xcord > 1 && xcord < 12 && ycord > 1 && ycord < 12) && xstart == -1)//makes sure the selected spot is in the grid
         {
            xstart = xcord -1;
            ystart = ycord -1;
         }
         else if(xstart != -1 && ystart != -1)//checks if the start points have already been given coordinates
         {
            if(xcord == xstart + (currentLength) && ycord == ystart +1 || xcord == xstart - (currentLength- 2) && ycord == ystart + 1 || xcord == xstart + 1 && ycord == ystart + (currentLength)|| xcord == xstart + 1&& ycord == ystart -(currentLength-2))
            {
               boolean check = true;
               if(xcord - 1 == xstart)
               {
                  if(ycord > ystart)
                  {
                     for(int i = 0; i < currentLength - 1; i++)
                     {
                        if(player1.board[xstart][ystart+i].isShip())
                           check = false;
                     }
                  }
                  else
                  {
                     for(int i = 0; i < currentLength - 1; i++)
                     {
                        if(player1.board[xstart][ystart-i].isShip())
                           check = false;
                     }
                  }
               }
               
               
               
               xend = xcord - 1;
               yend = ycord - 1;
            }
            else
            {
                           
            }
         }
      }
      else if(screen.equals("player2setup"))
      {
      
      }
      else if(screen.equals("player1"))
      {
         
         xcord = (int)(x+25)/25;
         ycord = (int)(y+25)/25;
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
