import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;

public class Board extends JPanel{
   
   private int boardWidth;
   private int boardHeight;
   private int squareSize;
   private int snakeThickness;
   private Snake snake;
   private Target target;
   private Font scoreFont;
   private BufferedImage targetImage;
   private BufferedImage snakeImage;
   private BufferedImage grassImage;
   private BufferedImage borderImage;

   public Board(){
      this.target = target;
      initialiseBoard();
   }

   public void setSnake(Snake snake){
      this.snake = snake;
   }

   public void setTarget(Target target){
      this.target = target;
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      //paint background and border
      g.drawImage(grassImage, 0 ,0 ,null);
      g.drawImage(borderImage, 0, 25, null);
      //paint target
      if(target != null){
         int targetX = (int)target.getPosition().getX()*squareSize;
         int targetY = (int)target.getPosition().getY()*squareSize;
         g.drawImage(targetImage, targetX, targetY, null);
      }
      //paint snake
      for(Point segment : snake.getSegments()){
         int snakeX = (int)segment.getX()*squareSize;
         int snakeY = (int)segment.getY()*squareSize;
         g.drawImage(snakeImage, snakeX, snakeY , null);
      }
      //paint score at the top
      g.setFont(scoreFont);
      g.setColor(Color.BLACK);
      g.drawString("Size "+ snake.getSize(), 310, 25);
   }

   private void initialiseBoard(){
      setImages();
      boardWidth = 25;
      boardHeight = 25;
      squareSize = 25;
      snakeThickness = squareSize-2;
      scoreFont = new Font("Tahoma", Font.BOLD, 20);
   }
   //read images from the resource file
   private void setImages(){
      targetImage = readBfiFromPath("./resources/originalTarget.png");
      borderImage = readBfiFromPath("./resources/originalBorder.png");
      snakeImage = readBfiFromPath("./resources/originalSnake.png");
      grassImage = readBfiFromPath("./resources/originalBG.png");
   }
   //convenient method for reading images
   private BufferedImage readBfiFromPath(String path){
      BufferedImage bfi;
      try{
         bfi = ImageIO.read(new File(path));
         return bfi;
      } catch (IOException e) {
         System.out.println("error reading bfi");
      }
      return null;
   }

   public int getBoardWidth(){
      return boardWidth;
   }

   public int getBoardHeight(){
      return boardHeight;
   }

}
