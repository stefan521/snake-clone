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
     private JPanel gamePanel;
     private JFrame containerJF;
     private Game game;

     public SnakeGUI(Game game){
          board = new Board();
          this.game = game;
          setFrame();
     }

     public Board getBoard(){
          return board;
     }

     private void setFrame(){
          containerJF = new JFrame("snake");
          setPanels();
          containerJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          containerJF.setMinimumSize(new Dimension(656, 715));
          containerJF.setResizable(false);
          containerJF.setLayout(new BorderLayout());
          containerJF.add(gamePanel, BorderLayout.CENTER);
          setFrameListener();
          containerJF.setVisible(true);
     }

     private void setPanels(){
          gamePanel = new JPanel();
          gamePanel.setLayout(new BorderLayout());
          gamePanel.add(board, BorderLayout.CENTER);

          gameOverPanel = new JPanel();
          menuPanel = new JPanel();
     }

     private void setFrameListener(){
          containerJF.addKeyListener(new KeyListener(){
               @Override
               public void keyPressed(KeyEvent e) {
                   game.addInputToBeExecuted(e.getKeyCode());
              }
              @Override
              public void keyTyped(KeyEvent e) {}
              @Override
              public void keyReleased(KeyEvent e){}

          });
     }

}
