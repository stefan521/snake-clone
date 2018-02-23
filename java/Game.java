import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Game {
     private Snake snake;
     private Board board;
     private SnakeGUI gui;
     private Target target;
     private Timer timer;
     private boolean keyPressedTisTurn;
     private ArrayList<Integer> inputs;

     public Game(){
          initialise();
          startGame();
     }

     public void startGame(){
          snake.resetSnake();
          timer.restart();
     }

     public void pause(){
          timer.stop();
     }

     public void resume(){
          if(isGameOver() == false){
               timer.restart();
          }
     }

     public void addInputToBeExecuted(int keyCode){
          if(inputs.size() < 2){
               if(keyPressedTisTurn) {
                    inputs.add(keyCode);
               } else{
                    inputs.clear();
                    inputs.add(keyCode);
                    keyPressedTisTurn = true;
               }
          }
     }

     private void steerSnake(int keyCode) {
          switch(keyCode){
               /*UP and DOWN are reversed because the (0,0) point is
               the upper left corner when representing the game on a panel and
               the lower left corner in the usual system of coordinates.
               */
               case KeyEvent.VK_UP : snake.changeDirection(snake.DOWN);
                                     break;
               case KeyEvent.VK_DOWN : snake.changeDirection(snake.UP);
                                       break;
               case KeyEvent.VK_LEFT : snake.changeDirection(snake.LEFT);
                                       break;
               case KeyEvent.VK_RIGHT : snake.changeDirection(snake.RIGHT);
                                        break;
          }
            inputs.remove(0);
    }

     private void initialise(){
          snake = new Snake();
          inputs = new ArrayList<Integer>();
          gui = new SnakeGUI(this);
          board = gui.getBoard();
          target = new Target(board.getBoardWidth()-1, board.getBoardHeight()-1, 30);
          target.setRestrictedPoints(snake.getSegments());
          board.setSnake(snake);
          board.setTarget(target);
          setTimer(100);
     }

     private void progress(){
          keyPressedTisTurn = false;
          if(inputs.size() > 0){
               steerSnake(inputs.get(0));
          }
          if(collectsTarget()){
               snake.grow();
               target.spawn();
          }

          if(isGameOver()){
               timer.stop();
          } else {
               snake.move();
               target.increaseTimeLived();
          }
          board.repaint();
     }

     private boolean isGameOver(){
          if(snake.isCoiled() || snakeIsOutOfBound()){
               return true;
          }
          return false;
     }

     private boolean snakeIsOutOfBound(){
          Point head = snake.getHead();
          if(head.getX()>board.getBoardWidth()-1 || head.getY()>board.getBoardHeight() ||
             head.getX()<1 || head.getY()<2){
                  return true;
             }
          return false;
     }

     private boolean collectsTarget(){
          if(snake.getHead().equals(target.getPosition())){
               return true;
          }
          return false;
     }

     private void setTimer(int delay){
          timer = new Timer(delay, new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                    progress();
               }
          });
     }

     public static void main(String[] args){
          Game game = new Game();
     }
}
