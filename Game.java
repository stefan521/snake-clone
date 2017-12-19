import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class Game {
     private Snake snake;
     private Board board;
     private SnakeGUI gui;
     private Target target;
     private Timer timer;
     private boolean keyPressedTisTurn;

     public Game(){
          initialise();
     }

    public void steerSnake(int keyCode) {
            if (keyCode == KeyEvent.VK_UP && !keyPressedTisTurn) {
                 snake.changeDirection(snake.DOWN);
                 keyPressedTisTurn = true;
            }
            else if (keyCode == KeyEvent.VK_DOWN && !keyPressedTisTurn) {
                 snake.changeDirection(snake.UP);
                 keyPressedTisTurn = true;
            }
            else if (keyCode == KeyEvent.VK_LEFT && !keyPressedTisTurn) {
                 snake.changeDirection(snake.LEFT);
                 keyPressedTisTurn = true;
            }
            else if (keyCode == KeyEvent.VK_RIGHT && !keyPressedTisTurn) {
                 snake.changeDirection(snake.RIGHT);
                 keyPressedTisTurn = true;
            }
    }

     private void initialise(){
          snake = new Snake();
          gui = new SnakeGUI(snake, this);
          board = gui.getBoard();
          target = new Target(board.getBoardWidth()-2, board.getBoardHeight()-2, 35);
          target.setRestrictedPoints(snake.getSegments());
          board.setTarget(target);
          setTimer(150);
     }

     private void progress(){
          keyPressedTisTurn = false;
          if(snakeCollectedTarget()){
               snake.grow();
               target.spawn();
               gui.showScore(snake.getSize());
          }
          if(isGameOver()){
               timer.stop();
          } else {
               snake.move();
               target.increaseTimeLived();
          }
          board.updateGui();
     }

     private boolean isGameOver(){
          if(snake.isCoiled() || snakeIsOutOfBound()){
               return true;
          }
          return false;
     }

     private boolean snakeIsOutOfBound(){
          Point head = snake.getHead();
          if(head.getX()>=board.getBoardWidth() || head.getY()>=board.getBoardHeight() ||
             head.getX()<1 || head.getY()<1){
                  return true;
             }
          return false;
     }

     private boolean snakeCollectedTarget(){
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
          timer.start();
     }

     public static void main(String[] args){
          Game game = new Game();
     }
}
