import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;

public class SnakeGUI{
     private Board board;
     private JPanel menuPanel;
     private JPanel gameOverPanel;
     private JFrame containerJF;
     private Game game;

     public SnakeGUI(Snake snake, Game game){
          board = new Board(snake);
          this.game = game;
          setFrame();
     }

     public Board getBoard(){
          return board;
     }

     private void setFrame(){
          containerJF = new JFrame("snake");
          containerJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          containerJF.setSize(625, 675);
          containerJF.setResizable(false);
          containerJF.setLayout(new BorderLayout());
          containerJF.add(board, BorderLayout.CENTER);
          containerJF.add(new JLabel("score"), BorderLayout.NORTH);
          setFrameListener();
          containerJF.setVisible(true);
     }

     private void setFrameListener(){
          containerJF.addKeyListener(new KeyListener(){
               @Override
               public void keyPressed(KeyEvent e) {
                   game.steerSnake(e.getKeyCode());
              }
              @Override
              public void keyTyped(KeyEvent e) {}
              @Override
              public void keyReleased(KeyEvent e){}

          });
     }

}
