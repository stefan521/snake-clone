import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;

public class Game{
     private Snake snake;
     private Board board;
     private Target target;
     private Timer timer;
     private int boardHeight;
     private int boardWidth;

     public Game(){
          boardWidth = 30;
          boardHeight = 30;
          initialise();
     }

     private void initialise(){
          snake = new Snake();
          board = new Board(boardWidth, boardHeight);
          target = new Target(boardWidth, boardHeight, 34);
          setTimer(150);
     }

     private void progress(){
          System.out.println(snake);
          if(snakeCollectedTarget()){
               snake.grow();
          }
          if(isGameOver()){
               timer.stop();
          } else {
               snake.move();
               if(target.isExpired()){
                    target = new Target(boardWidth, boardHeight, 34);
               }
               target.decreaseTimeToLive();
               System.out.println(snake);
          }
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
          if(head.getX()>boardWidth || head.getY()>boardHeight ||
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
