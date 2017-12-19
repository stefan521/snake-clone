import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;

public class Game{
     private Snake snake;
     private Board board;
     private Target target;
     private Timer timer;

     public Game(){
          initialise();
     }

     private void initialise(){
          snake = new Snake();
          board = new Board(snake);
          target = new Target(board.getBoardWidth(), board.getBoardHeight(), 34);
          board.setTarget(target);
          setTimer(150);
     }

     private void progress(){
          if(snakeCollectedTarget()){
               snake.grow();
               target = new Target(board.getBoardWidth(),  board.getBoardHeight(), 34);
               board.setTarget(target);
          }
          if(isGameOver()){
               timer.stop();
          } else {
               snake.move();
               if(target.isExpired()){
                    target = new Target(board.getBoardWidth(),  board.getBoardHeight(), 34);
                    board.setTarget(target);
               }
               target.decreaseTimeToLive();
          }
          board.repaint();
     }

     private boolean isGameOver(){
          if(snakeCoiled()){
               return true;
          }
          if(snakeIsOutOfBound()){
               return true;
          }
          return false;
     }

     private boolean snakeCoiled(){
          Point head = snake.getHead();
          for(Point eachSegment : snake.getSegments()){
               if(head.equals(eachSegment) && head != eachSegment){
                    return true;
               }
          }
          return false;
     }

     private boolean snakeIsOutOfBound(){
          Point head = snake.getHead();
          if(head.getX()>board.getBoardWidth() || head.getY()>board.getBoardHeight() ||
             head.getX()<0 || head.getY()<0){
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
