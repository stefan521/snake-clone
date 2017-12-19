import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;

public class Board extends JPanel{
     private JFrame containerJF;
     private int boardWidth;
     private int boardHeight;
     private int squareSize;
     private Snake snake;
     private Target target;

     public Board(Snake snake){
          boardWidth = 30;
          boardHeight = 30;
          squareSize = 20;
          this.snake = snake;
          setFrame();
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

     public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.RED);
        if(target != null){
             g.fillRect(target.getX(), target.getY(), squareSize, squareSize);
        }

        for(Point segment : snake.getSegments()){
             g.setColor(new Color(243, 0, 187));
             if(snake.getHead().equals(segment))
                  g.setColor(new Color(117,117,247));
             g.fillRect((int)segment.getX()*squareSize, (int)segment.getY()*squareSize, squareSize, squareSize);
        }

    }

     private void setFrame(){
          containerJF = new JFrame("snake");
          containerJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          containerJF.setSize(600, 600);
          containerJF.setResizable(false);
          containerJF.setLocation(50, 50);
          containerJF.add(this);
          setBackground(Color.BLACK);
          containerJF.setVisible(true);
     }

}
