import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;

public class Board extends JPanel{
     private JFrame containerJF;
     private int width;
     private int height;

     public Board(int width, int height){
          this.width = width;
          this.height = height;
          setFrame();
     }

     public int getWidth(){
          return width;
     }

     public int getHeight(){
          return height;
     }

     private void setFrame(){
          containerJF = new JFrame("snake");
          containerJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          containerJF.setMinimumSize(new Dimension(200,200));
          containerJF.setVisible(true);
     }

}
