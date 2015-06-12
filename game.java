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
   private Board currentPlayer;
   private String tempPlayer;
   private Color currentColor;
   private Color p1;
   private Color p2;
   private boolean setupDone;
   
   public void init()
   {
      //initializations
      p1 = Color.red;
      p2 = Color.blue;
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
      setupDone = false;
          
      //adds mouse listener
      addMouseListener(this);
      player1 = new Board();
      player2 = new Board();
      //does backbuffering
      backbuffer = createImage(500, 600);
      backg = backbuffer.getGraphics();
      currentPlayer = player1;
      tempPlayer = "player1";
      currentColor = p1;
   }
   
   public void update(Graphics g)
   {
      //draws the grid
      if(screen.equals("start"))
         startScreen();
      else if(screen.equals("playersetup"))
         playerSetupScreen();
      else if(screen.equals("playerScreen"))
         playerScreen();
      else if(screen.equals("transition"))
         transition();

      
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
   public void playerSetupScreen()
   {
   if(lengthIndex != 5)
   {   
      backg.setColor(currentColor);
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
            backg.setColor(Color.lightGray);
            backg.fillRect(300,275,200,200);
            backg.setColor(Color.black);
            backg.drawString("Player :" + tempPlayer,305,330);
            backg.drawString("Choose the starting spot for", 305, 340);
            backg.drawString("your "+ currentLength + " long ship", 305, 350);
            repaint();
         }
         else if(xend == -1 && yend == -1)
         {
            backg.setColor(Color.lightGray);
            backg.fillRect(300,275,200,200);
            backg.setColor(Color.gray);
            backg.fillRect(25*(xstart+1)-25, 25*(ystart+1) +275, 25, 25);
            backg.setColor(Color.black);
            backg.drawString("Player :" + tempPlayer,305,330);
            backg.drawString("Choose the ending spot for", 305, 340);
            backg.drawString("your ship", 305, 350);          
         }
         if(xend != -1)
         {
            currentPlayer.makeShip(currentLength, xstart, ystart, xend, yend);
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
            if(lengthIndex < 5)
            {
               currentLength = shipLengths[lengthIndex];
            }
         }
      }
      repaint();
   }
      else if(tempPlayer.equals("player2"))
      {
         backg.setColor(Color.lightGray);
         backg.fillRect(300,0,200, 600);
         backg.setColor(Color.black);
         backg.drawString("Ready to move on?" ,305, 340);
         backg.setColor(Color.green);
         backg.fillRect(305,360,190,100);
         setupDone = true;
         switchPlayer();
      }
      else if(lengthIndex == 5)
      {
         backg.setColor(Color.lightGray);
         backg.fillRect(300,0,200, 600);
         backg.setColor(Color.black);
         backg.drawString("Ready to move on?" ,305, 340);
         backg.setColor(Color.green);
         backg.fillRect(305,360,190,100);
      }
   }
   public void playerScreen()
   {
      backg.setColor(Color.lightGray);
      backg.fillRect(0,0,500,600);
      Board other = new Board();
      if(tempPlayer.equals("player1"))
      {
         other = player2;
      }
      else
      {
         other = player1;
      }
      for(int r = 1; r < 11; r++)
      {
         for(int c = 1; c < 11; c++)
         {
            if(currentPlayer.board[r-1][c-1].val == 0)
            {
               backg.setColor(Color.cyan);
               backg.fillRect(r * 25, c * 25 + 300, 25, 25);
            }
            if(currentPlayer.board[r-1][c-1].val == 1)
            {
               backg.setColor(Color.black);
               backg.fillRect(r*25, c*25 + 300, 25, 25);
            }
            else if(currentPlayer.board[r-1][c-1].val == 2)
            {
               backg.setColor(Color.white);
               backg.fillRect(r * 25, c * 25 + 300, 25, 25);
            }
            else if(currentPlayer.board[r-1][c-1].val == 3)
            {
               backg.setColor(Color.red);
               backg.fillRect(r * 25, c * 25 + 300, 25, 25);
            }
         }
      }
      for(int r = 1; r < 11; r++)
      {
         for(int c = 1; c < 11; c++)
         {
            if(other.board[r-1][c-1].val == 0)
            {
               backg.setColor(Color.cyan);
               backg.fillRect(r * 25, c * 25, 25, 25);
            }
            if(other.board[r-1][c-1].val == 1)
            {
               backg.setColor(Color.black);
               backg.fillRect(r*25, c*25, 25, 25);
            }
            else if(other.board[r-1][c-1].val == 2)
            {
               backg.setColor(Color.white);
               backg.fillRect(r * 25, c * 25, 25, 25);
            }
            else if(other.board[r-1][c-1].val == 3)
            {
               backg.setColor(Color.red);
               backg.fillRect(r * 25, c * 25, 25, 25);
            }
         }
      }

      
      backg.setColor(currentColor);
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
      backg.setColor(Color.lightGray);
      backg.fillRect(300,0,200, 600);
      backg.setColor(Color.black);
      backg.drawString("Ready to move on?" ,305, 340);
      backg.setColor(Color.green);
      backg.fillRect(305,360,190,100);
      repaint();

   }
   public void switchPlayer()
   {
      
      if(tempPlayer.equals("player1"))
      {
         for(int r = 0; r < 10; r++)
         {
            for(int c = 0; c < 10; c++)
            {
               System.out.print(currentPlayer.board[r][c].val);
            }
            System.out.println();
         }
         player1.board = currentPlayer.board;
         currentPlayer = new Board();
         currentPlayer = player2;
         currentColor = p2;
         tempPlayer = "player2";
         for(int r = 0; r < 10; r++)
         {
            for(int c = 0; c < 10; c++)
            {
               System.out.print(player1.board[r][c].val);
            }
            System.out.println();
         }
      }
      else if(tempPlayer.equals("player2"))
      {
         player2 = currentPlayer;
         currentPlayer = new Board();
         currentPlayer = player1;
         currentColor = p1;
         tempPlayer = "player1";
      }
      xstart = -1;
      ystart = -1;
      xend = -1;
      yend = -1;
      xcord = -1;
      ycord = -1;
      if(!setupDone)
      {
         currentLength = shipLengths[0];
         lengthIndex = 0;
         backg.setColor(Color.lightGray);
         backg.fillRect(0,0,300, 600);
         
         playerSetupScreen();
      }
      else if(setupDone)
      {
         screen = "transition";
         transition();
      }
   }
   public void transition()
   {
      backg.setColor(Color.lightGray);
      backg.fillRect(0,0,500,600);
      backg.setColor(Color.green);
      backg.fillRect(150,250,200,100);
      repaint();
   }
   public void guess(int x, int y)
   {
      Board other = new Board();
      if(tempPlayer.equals("player1"))
      {
         other = player2;
      }
      else
      {
         other = player1;
      }
      other.board[x][y].guessed();
      if(tempPlayer.equals("player1"))
      {
         player2 = other;
         other = new Board();
      }
      else
      {
         player1 = other;
         other = new Board();
      }
      
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
         screen = "playersetup";
         repaint();
      }
      else if(screen.equals("transition"))
      {
         if(x > 150 && x < 350 && y > 250 && y < 350)
         {
            playerScreen();
            screen = "playerScreen";
         }
      }
      else if(screen.equals("playersetup"))
      {
         if(lengthIndex == 5 && x > 305 && x < 495 && y > 360 && y < 460)
         {
            switchPlayer();
         }
         else{
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
                     if(ycord > ystart + 1)
                     {
                        for(int i = 0; i < currentLength; i++)
                        {
                           if(currentPlayer.board[xstart][ystart+i].isShip())
                              check = false;
                        }
                     }
                     else
                     {
                        for(int i = 0; i < currentLength; i++)
                        {
                           if(currentPlayer.board[xstart][ystart-i].isShip())
                              check = false;
                        }
                     }
                  }
                  else if(ycord - 1 == ystart)
                  {
                     if(xcord > xstart + 1)
                     {
                        for(int i = 0; i < currentLength; i++)
                        {
                           if(currentPlayer.board[xstart+i][ystart].isShip())
                              check = false;
                        }
                     }
                     else
                     {
                        for(int i = 0; i < currentLength; i++)
                        {
                           if(currentPlayer.board[xstart-i][ystart].isShip())
                              check = false;
                        }
                     }
                  }
                  if(check == true)
                  {
                     xend = xcord - 1;
                     yend = ycord - 1;
                  }
               }
               else
               {
                              
               }
            }
         }
      }
      else if(screen.equals("playerScreen"))
      {
         
         xcord = (int)(x+25)/25;
         ycord = (int)(y+25)/25;
         if(xcord > 1 && xcord < 12 && ycord > 1 && ycord < 12)
            guess(xcord -1, ycord -1);
         if(x > 305 && x < 495 && y > 360 && y < 460)
         {
            switchPlayer();
         }

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