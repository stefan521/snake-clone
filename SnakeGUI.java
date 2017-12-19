import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

public class SnakeGUI{
     private Board board;
     private JPanel menuPanel;
     private JPanel gameOverPanel;
     private JPanel gamePanel;
     private JLabel scoreLabel;
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

     public void showScore(int score){
          scoreLabel.setText("size: "+score);
     }

     private void setFrame(){
          containerJF = new JFrame("snake");
          setPanels();
          containerJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          containerJF.setMinimumSize(new Dimension(625, 685));
          containerJF.setResizable(false);
          containerJF.setLayout(new BorderLayout());
          containerJF.add(gamePanel, BorderLayout.CENTER);
          setFrameListener();
          containerJF.setVisible(true);
     }

     private void setPanels(){
          gamePanel = new JPanel();
          Font scoreFont = new Font("Tahoma", Font.BOLD, 20);
          scoreLabel = new JLabel("size: 0", JLabel.CENTER);
          scoreLabel.setForeground(new Color(131, 211, 97));
          scoreLabel.setFont(scoreFont);
          JPanel scorePanel = new JPanel();
          scorePanel.setBackground(Color.BLACK);
          scorePanel.setLayout(new BorderLayout());
          scorePanel.add(scoreLabel, BorderLayout.CENTER);
          gamePanel.setLayout(new BorderLayout());
          gamePanel.add(board, BorderLayout.CENTER);
          gamePanel.add(scorePanel, BorderLayout.NORTH);

          gameOverPanel = new JPanel();
          menuPanel = new JPanel();
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
