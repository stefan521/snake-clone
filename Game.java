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
            if (keyCode == KeyEvent.VK_UP) {
                 snake.changeDirection(snake.DOWN);
            }
            else if (keyCode == KeyEvent.VK_DOWN) {
                 snake.changeDirection(snake.UP);
            }
            else if (keyCode == KeyEvent.VK_LEFT) {
                 snake.changeDirection(snake.LEFT);
            }
            else if (keyCode == KeyEvent.VK_RIGHT) {
                 snake.changeDirection(snake.RIGHT);
            }
            inputs.remove(0);
    }

     private void initialise(){
          snake = new Snake();
          inputs = new ArrayList<Integer>();
          gui = new SnakeGUI(snake, this);
          board = gui.getBoard();
          target = new Target(board.getBoardWidth()-2, board.getBoardHeight()-2, 30);
          target.setRestrictedPoints(snake.getSegments());
          board.setTarget(target);
          setTimer(100);
     }

     private void progress(){
          keyPressedTisTurn = false;
          if(inputs.size() > 0){
               steerSnake(inputs.get(0));
          }
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
