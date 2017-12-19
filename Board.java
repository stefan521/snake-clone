import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Board extends JPanel{
     private int boardWidth;
     private int boardHeight;
     private int squareSize;
     private Snake snake;
     private Target target;
     private Color bgColor;
     private Color snakeBodyColor;
     private Color snakeHeadColor;
     private Color targetColor;
     private Color borderColor;

     public Board(Snake snake){
          initialiseBoard();
          this.snake = snake;
     }

     public int getBoardWidth(){
          return boardWidth;
     }

     public int getBoardHeight(){
          return boardHeight;
     }

     public void setTarget(Target target){
          this.target = target;
     }

     public void updateGui(){
          repaint();
     }

     public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int snakeThickness = squareSize-2;
        drawBorders(g);

        g.setColor(targetColor);
        if(target != null){
             int targetX = target.getX()*squareSize;
             int targetY = target.getY()*squareSize;
             g.fillRect(targetX, targetY, snakeThickness, snakeThickness);
        }

        for(Point segment : snake.getSegments()){
             g.setColor(snakeBodyColor);
             int snakeX = (int)segment.getX()*squareSize;
             int snakeY = (int)segment.getY()*squareSize;
             if(snake.getHead().equals(segment))
                  g.setColor(snakeHeadColor);
             g.fillRect(snakeX, snakeY , snakeThickness, snakeThickness);
        }
    }

    private void drawBorders(Graphics g){
         g.setColor(borderColor);
         for(int i = 0; i <=boardWidth; i++){
              g.fillRect(i*squareSize, (boardHeight)*squareSize, squareSize, squareSize);
              g.fillRect((boardWidth)*squareSize, i*squareSize, squareSize, squareSize);
              g.fillRect(i*squareSize, 0, squareSize, squareSize);
              g.fillRect(0, i*squareSize, squareSize, squareSize);
         }
    }

     private void initialiseBoard(){
          boardWidth = 30;
          boardHeight = 30;
          squareSize = 20;
          bgColor = new Color(0, 0, 0);
          borderColor = new Color(122, 131, 145);
          snakeBodyColor = new Color(27, 104, 229);
          snakeHeadColor = new Color(117,117,247);
          targetColor = new Color(131, 211, 97);
          setBackground(bgColor);
     }

}
