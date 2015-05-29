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
   
   public void init()
   {
      //initializations
      player1 = new player();
      Color p1 = Color.red;
      player2 = new player();
      Color p2 = Color.blue;
            
      //adds mouse listener
      addMouseListener(this);
      
      //does backbuffering
      backbuffer = createImage(getSize().width, getSize().height);
      backg = backbuffer.getGraphics();
   }
   
   public void update(Graphics g)
   {
      //draws the grid
      drawSite();
      g.drawImage(backbuffer, 0,0, this); //only g
   }
   
   public void paint(Graphics g)
   { update(g); }
   
   public void drawSite()
   {
      //draws the grid
      //traverses through the 2D array
      for(int i = 0; i<ground.length; i++)
      {
         for(int j = 0; j<ground[i].length; j++)
         {
            backg.setColor(ground[i][j].getColor());//sets color
            backg.fillRect(50+(i*100),50+(j*100),95,95);//fills the spot
            backg.setColor(Color.black);
         }
      }
      backg.setColor(Color.gray);//covers old statement if any
      backg.fillRect(30, 545, 400, 200);
      repaint();
   }
   
   public void dig(int row, int col)
   {
      //preform the dig
      ground[row][col].dig();
      
      checkWin(row, col);
   }
   
   public void checkWin(int row, int col)
   {
      if(ground[row][col].isShip())//if the spot is a bone
      {
         int k = 0;//create a temp variable
         //traverse through the ground 2D array
         for(int r = 0; r<ground.length; r++)
         {
            for(int c = 0; c<ground[0].length; c++)
            {
               if(ground[r][c].isBone == true && ground[r][c].dugUp == true)
               {
                  k++;//add to k each time there is a dug up bone
               }
            }
         }
         if(k>=5)//if all five bones are found
         {
            backg.setColor(Color.black);
            backg.fillRect(0,0,600,600);
            backg.setColor(Color.white);
            backg.drawString("All the fossils were found. You win!", 30, 30);
            repaint();
         }
      }
   }
   
   public void mouseClicked(MouseEvent e)
   {
      //get mouse coordinates
      int x = e.getX();
      int y = e.getY();
      //make sure its within the bounds
      if(x>=50 && x<= 550 && y>=50 && y<=550)
      {
         //modify it
         int xBox = (x-50)/100;
         int yBox = (y-50)/100;
         //check if its within a valid box
         if(xBox>=0 && xBox<=5 && yBox>=0 && yBox<=5)
         {
            dig(xBox, yBox);//call dig upon that spot
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