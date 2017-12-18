import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;

public class Board extends JPanel{
     private JFrame containerJF;

     public Board(){
          setFrame();
     }

     private void setFrame(){
          containerJF = new JFrame("snake");
          containerJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          containerJF.setMinimumSize(new Dimension(200,200));
          containerJF.setVisible(true);
     }

}
