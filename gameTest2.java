import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

public class gameTest2 extends Applet implements MouseListener
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
   private static int[][] player1;
   private static int[][] player2;
   private static int[] shipLengths;
   private int currentLength;
   private int lengthIndex;
   private Color p1;
   private Color p2;
   private boolean setupDone;
   private boolean turnOver;
   private boolean transitions;
   
   
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
      turnOver = false;
      transitions = false;
          
      //adds mouse listener
      addMouseListener(this);
      player1 = new int[10][10];
      player2 = new int[10][10];
      for(int r = 0; r < 10; r++)
      {
         for(int c = 0; c < 10; c++)
         {
            player1[r][c] = 0;
            player2[r][c] = 0;
         }
      }
      //does backbuffering
      backbuffer = createImage(500, 600);
      backg = backbuffer.getGraphics();
   }
   
   public void update(Graphics g)
   {
      //draws the grid
      //System.out.println(screen);
      if(screen.equals("start"))
         startScreen();
      else if(screen.equals("player1Setup"))
         player1SetupScreen();
      else if(screen.equals("player2Setup"))
         player2SetupScreen();
      else if(screen.equals("player1Screen"))
         player1Screen();
      else if(screen.equals("player2Screen"))
         player2Screen();
      else if(screen.equals("transition"))
         transition();
      else if(screen.equals("winPlayer1"))
         winPlayer1();
      else if(screen.equals("winPlayer2"))
         winPlayer2();

      
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
      if(lengthIndex != 5)
      {   
         backg.setColor(p1);
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
               backg.drawString("Player 1",305,330);
               backg.drawString("Choose the starting spot for", 305, 340);
               backg.drawString("your "+ currentLength + " long ship", 305, 350);
               repaint();
            }
            else if(xend == -1 && yend == -1)
            {
               backg.setColor(Color.lightGray);
               backg.fillRect(300,275,200,200);
               backg.setColor(Color.gray);
               backg.fillRect(25*(xstart+2)-25, 25*(ystart+2) +275, 25, 25);
               backg.setColor(Color.black);
               backg.drawString("Player : 1",305,330);
               backg.drawString("Choose the ending spot for", 305, 340);
               backg.drawString("your ship", 305, 350);          
            }
            if(xend != -1)
            { 
               for(int r = xstart; r <= xend; r++)
               {
                  for(int c = ystart; c <= yend; c++)
                  {
                     player1[r][c] = 1;
                  
                  }
               }
               backg.setColor(Color.gray);
               backg.fillRect(25*(xend+2)-25, 25*(yend+2) +275, 25, 25);
               if(xend == xstart)
               {
                  if(ystart < yend)
                  {
                     for(int j = 0; j < currentLength -1; j++)
                     {
                        backg.fillRect(25*(xstart+2)-25, 25*(ystart+2) +275 + (j * 25), 25, 25);
                     }
                  }
                  if(ystart > yend)
                  {
                     for(int j = 0; j < currentLength -1; j++)
                     {
                        backg.fillRect(25*(xstart+2)-25, 25*(ystart+2) +275 - (j * 25), 25, 25);
                     }
                  }
               }
               if(yend == ystart)
               {
                  if(xstart < xend)
                  {
                     for(int j = 0; j < currentLength -1; j++)
                     {
                        backg.fillRect(25*(xstart+2)-25 + (j * 25), 25*(ystart+2) +275, 25, 25);
                     }
                  }
                  if(xstart > xend)
                  {
                     for(int j = 0; j < currentLength -1; j++)
                     {
                        backg.fillRect(25*(xstart+2)-25 - (j * 25), 25*(ystart+2) +275, 25, 25);
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
   public void player2SetupScreen()
   {
      if(transitions == true)
      {
         transition();
      }
      else
      {   
         if(lengthIndex != 5)
         {   
            
            backg.setColor(p2);
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
                  backg.drawString("Player 2",305,330);
                  backg.drawString("Choose the starting spot for", 305, 340);
                  backg.drawString("your "+ currentLength + " long ship", 305, 350);
                  repaint();
               }
               else if(xend == -1 && yend == -1)
               {
                  backg.setColor(Color.lightGray);
                  backg.fillRect(300,275,200,200);
                  backg.setColor(Color.gray);
                  backg.fillRect(25*(xstart+2)-25, 25*(ystart+2) +275, 25, 25);
                  backg.setColor(Color.black);
                  backg.drawString("Player : 2",305,330);
                  backg.drawString("Choose the ending spot for", 305, 340);
                  backg.drawString("your ship", 305, 350);          
               }
               if(xend != -1)
               {
                   
                  for(int r = xstart; r <= xend; r++)
                  {
                     for(int c = ystart; c <= yend; c++)
                     {
                        player2[r][c] = 1;
                     
                     }
                  }
                  
                  backg.setColor(Color.gray);
                  backg.fillRect(25*(xend+2)-25, 25*(yend+2) +275, 25, 25);
                  if(xend == xstart)
                  {
                     if(ystart < yend)
                     {
                        for(int j = 0; j < currentLength -1; j++)
                        {
                           backg.fillRect(25*(xstart+2)-25, 25*(ystart+2) +275 + (j * 25), 25, 25);
                        }
                     }
                     if(ystart > yend)
                     {
                        for(int j = 0; j < currentLength -1; j++)
                        {
                           backg.fillRect(25*(xstart+2)-25, 25*(ystart+2) +275 - (j * 25), 25, 25);
                        }
                     }
                  }
                  if(yend == ystart)
                  {
                     if(xstart < xend)
                     {
                        for(int j = 0; j < currentLength -1; j++)
                        {
                           backg.fillRect(25*(xstart+2)-25 + (j * 25), 25*(ystart+2) +275, 25, 25);
                        }
                     }
                     if(xstart > xend)
                     {
                        for(int j = 0; j < currentLength -1; j++)
                        {
                           backg.fillRect(25*(xstart+2)-25 - (j * 25), 25*(ystart+2) +275, 25, 25);
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
   }
   public void player1Screen()
   {
      if(transitions == true)
      {
         transition();
      }
      else
      {
         /*
         System.out.println("Player 1");
         for(int r = 1; r < 11; r++)
         {
            for(int c = 1; c < 11; c++)
            {
               System.out.print(player1[c-1][r-1]);
            }
            System.out.println();
         }
         System.out.println("Player 2");
         for(int r = 1; r < 11; r++)
         {
            for(int c = 1; c < 11; c++)
            {
               System.out.print(player2[c-1][r-1]);
            }
            System.out.println();
         }
         */
         backg.setColor(Color.lightGray);
         backg.fillRect(0,0,500,600);
         for(int r = 1; r < 11; r++)
         {
            for(int c = 1; c < 11; c++)
            {
               if(player1[r-1][c-1] == 0)
               {
                  backg.setColor(Color.cyan);
                  backg.fillRect(r * 25, c * 25 + 300, 25, 25);
               }
               else if(player1[r-1][c-1] == 2)
               {
                  backg.setColor(Color.white);
                  backg.fillRect(r * 25, c * 25 + 300, 25, 25);
               }
               else if(player1[r-1][c-1] == 3)
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
               if(player2[r-1][c-1] == 0 || player2[r-1][c-1] == 1)
               {
                  backg.setColor(Color.cyan);
                  backg.fillRect(r * 25, c * 25, 25, 25);
               }
               /*
               if(player2[r-1][c-1] == 1)
               {
                  backg.setColor(Color.black);
                  backg.fillRect(r*25, c*25, 25, 25);
               }
               */
               else if(player2[r-1][c-1] == 2)
               {
                  backg.setColor(Color.white);
                  backg.fillRect(r * 25, c * 25, 25, 25);
               }
               else if(player2[r-1][c-1] == 3)
               {
                  backg.setColor(Color.red);
                  backg.fillRect(r * 25, c * 25, 25, 25);
               }
            }
         }
         backg.setColor(p1);
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
         if(turnOver == true)
         {
            backg.setColor(Color.lightGray);
            backg.fillRect(300,0,200, 600);
            backg.setColor(Color.black);
            backg.drawString("Ready to move on?" ,305, 340);
            backg.setColor(Color.green);
            backg.fillRect(305,360,190,100);
         }
         repaint();
      }
   }
   public void player2Screen()
   {
      if(transitions == true)
      {
         transition();
      }
      else
      {
         backg.setColor(Color.lightGray);
         backg.fillRect(0,0,500,600);
         for(int r = 1; r < 11; r++)
         {
            for(int c = 1; c < 11; c++)
            {
               if(player2[r-1][c-1] == 0)
               {
                  backg.setColor(Color.cyan);
                  backg.fillRect(r * 25, c * 25 + 300, 25, 25);
               }
               else if(player2[r-1][c-1] == 2)
               {
                  backg.setColor(Color.white);
                  backg.fillRect(r * 25, c * 25 + 300, 25, 25);
               }
               else if(player2[r-1][c-1] == 3)
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
               if(player1[r-1][c-1] == 0 || player1[r-1][c-1] == 1)
               {
                  backg.setColor(Color.cyan);
                  backg.fillRect(r * 25, c * 25, 25, 25);
               }
               /*
               if(player1[r-1][c-1] == 1)
               {
                  backg.setColor(Color.black);
                  backg.fillRect(r*25, c*25, 25, 25);
               }
               */
               else if(player1[r-1][c-1] == 2)
               {
                  backg.setColor(Color.white);
                  backg.fillRect(r * 25, c * 25, 25, 25);
               }
               else if(player1[r-1][c-1] == 3)
               {
                  backg.setColor(Color.red);
                  backg.fillRect(r * 25, c * 25, 25, 25);
               }
            }
         }
         backg.setColor(p2);
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
         if(turnOver == true)
         {
            backg.setColor(Color.lightGray);
            backg.fillRect(300,0,200, 600);
            backg.setColor(Color.black);
            backg.drawString("Ready to move on?" ,305, 340);
            backg.setColor(Color.green);
            backg.fillRect(305,360,190,100);
         }
         repaint();
      }
   }
   public void switchPlayer()
   {
      if(screen.equals("player1Setup"))
      {
         screen = "player2Setup";
      }
      else if(screen.equals("player2Setup"))
      {
         screen = "player1Screen";
      }
      else if(screen.equals("player1Screen"))
      {
         screen = "player2Screen";
      }
      else if(screen.equals("player2Screen"))
      {
         screen = "player1Screen";
      }
      turnOver = false;
      transitions = true;
      xstart = -1;
      ystart = -1;
      xend = -1;
      yend = -1;
      xcord = -1;
      ycord = -1;
      currentLength = shipLengths[0];
      lengthIndex = 0;
      repaint();
   }
   public void transition()
   {
      backg.setColor(Color.lightGray);
      backg.fillRect(0,0,500,600);
      backg.setColor(Color.green);
      backg.fillRect(150,250,200,100);
      repaint();
   }
   public void guessPlayer1(int x, int y)
   {
      if(player2[x-1][y-1] == 1)
      {
         player2[x-1][y-1] = 3;
         turnOver = true;
      }
      else if(player2[x-1][y-1] == 0)
      {
         player2[x-1][y-1] = 2;
         turnOver = true;
      }
      else if(player2[x-1][y-1] == 2)
      {
         backg.setColor(Color.black);
         backg.drawString("Already guessed",305, 370);
      }
      else if(player2[x-1][y-1] == 3)
      {
         backg.setColor(Color.black);
         backg.drawString("Already guessed",305, 370);
      }
      repaint(); 
   }
   public void guessPlayer2(int x, int y)
   {
      if(player1[x-1][y-1] == 1)
      {
         player1[x-1][y-1] = 3;
         turnOver = true;
      }
      else if(player1[x-1][y-1] == 0)
      {
         player1[x-1][y-1] = 2;
         turnOver = true;
      }
      else if(player1[x-1][y-1] == 2)
      {
         backg.setColor(Color.black);
         backg.drawString("Already guessed",305, 370);
      }
      else if(player1[x-1][y-1] == 3)
      {
         backg.setColor(Color.black);
         backg.drawString("Already guessed",305, 370);
      }
      repaint(); 
   }
   public void checkWin()
   {
      boolean player1Win = true;
      boolean player2Win = true;
      for(int r = 0; r < 10; r++)
      {
         for(int c = 0; c < 10; c++)
         {
            if(player1[r][c] == 1)
            {
               player2Win = false;
            }
            if(player2[r][c] == 1)
            {
               player1Win = false;
            }
         }
      }
      if(player1Win)
         winPlayer1();
      if(player2Win)
         winPlayer2();
   }
   public void winPlayer1()
   {
      screen = "winPlayer1";
      backg.setColor(Color.lightGray);
      backg.fillRect(0,0,500,600);
      backg.setColor(Color.black);
      backg.drawString("U win",240,30);
   }
   public void winPlayer2()
   {
      screen = "winPlayer2";
      backg.setColor(Color.lightGray);
      backg.fillRect(0,0,500,600);
      backg.setColor(Color.black);
      backg.drawString("U win",240,30);
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
         screen = "player1Setup";
         repaint();
      }
      else if(transitions == true)
      {
         if(x > 150 && x < 350 && y > 250 && y < 350)
         {
            transitions = false;
            backg.setColor(Color.lightGray);
            backg.fillRect(0,0,500,600);
            repaint();
         }
      }
      else if(screen.equals("player1Setup"))
      {
         if(lengthIndex == 5 && x > 305 && x < 495 && y > 360 && y < 460)
         {
            switchPlayer();
         }
         else{
            xcord = (int)(x+25)/25 - 1;
            ycord = (int)(y-275)/25 - 1 ;
            if((xcord > 0 && xcord < 11 && ycord > 0 && ycord < 11) && xstart == -1)//makes sure the selected spot is in the grid
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
                           if(player1[xstart][ystart+i]== 1)
                              check = false;
                        }
                     }
                     else
                     {
                        for(int i = 0; i < currentLength; i++)
                        {
                           if(player1[xstart][ystart-i] == 1)
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
                           if(player1[xstart+i][ystart] == 1)
                              check = false;
                        }
                     }
                     else
                     {
                        for(int i = 0; i < currentLength; i++)
                        {
                           if(player1[xstart-i][ystart] == 1)
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
      else if(screen.equals("player2Setup"))
      {
         if(lengthIndex == 5 && x > 305 && x < 495 && y > 360 && y < 460)
         {
            switchPlayer();
         }
         else
         {
            xcord = (int)(x+25)/25 - 1;
            ycord = (int)(y-275)/25 - 1 ;
            if((xcord > 0 && xcord < 11 && ycord > 0 && ycord < 11) && xstart == -1)//makes sure the selected spot is in the grid
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
                           if(player2[xstart][ystart+i] == 1)
                              check = false;
                        }
                     }
                     else
                     {
                        for(int i = 0; i < currentLength; i++)
                        {
                           if(player2[xstart][ystart-i] == 1)
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
                           if(player2[xstart+i][ystart] == 1)
                              check = false;
                        }
                     }
                     else
                     {
                        for(int i = 0; i < currentLength; i++)
                        {
                           if(player2[xstart-i][ystart] == 1)
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
      
      else if(screen.equals("player1Screen"))
      {
         
         xcord = (int)(x+25)/25;
         ycord = (int)(y+25)/25;
         if(xcord > 1 && xcord < 12 && ycord > 1 && ycord < 12 && turnOver != true)
            guessPlayer1(xcord -1, ycord -1);
         if(turnOver == true && x > 305 && x < 495 && y > 360 && y < 460)
         {
            switchPlayer();
         }

      }
      else if(screen.equals("player2Screen"))
      {
         xcord = (int)(x+25)/25;
         ycord = (int)(y+25)/25;
         if(xcord > 1 && xcord < 12 && ycord > 1 && ycord < 12 && turnOver != true)
            guessPlayer2(xcord -1, ycord -1);
         if(turnOver == true && x > 305 && x < 495 && y > 360 && y < 460)
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