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
   private JPanel gamePanel;
   private JFrame containerJF;
   private Game game;
   private Snake snake;
   private Target target;

   public SnakeGUI(Game game){

      this.target = target;
      board = new Board();
      this.game = game;
      setFrame();
   }

   public void setSnake(Snake snake){
      this.snake = snake;
      board.setSnake(snake);
   }

   public void setTarget(Target target){
      this.target = target;
      board.setTarget(target);
   }

   public Board getBoard(){
      return board;
   }
   //initialise the frame
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
   }
   //add key listener to the frame
   private void setFrameListener(){
      containerJF.addKeyListener(new KeyListener(){
         @Override
         public void keyPressed(KeyEvent e) {
            sendInputToGame(e.getKeyCode());
         }
         @Override
         public void keyTyped(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e){}
            });
         }

         private void sendInputToGame(int keyCode){
            int direction = snake.getDirectionHeading();
            switch(keyCode){
               /*UP and DOWN are reversed because the (0,0) point is
               the upper left corner when representing the game on a panel and
               the lower left corner in the usual system of coordinates.
               */
               case KeyEvent.VK_UP : direction = snake.DOWN;
               break;
               case KeyEvent.VK_DOWN : direction = snake.UP;
               break;
               case KeyEvent.VK_LEFT : direction = snake.LEFT;
               break;
               case KeyEvent.VK_RIGHT : direction = snake.RIGHT;
               break;
            }
            game.addInputToBeExecuted(direction);
         }

      }
