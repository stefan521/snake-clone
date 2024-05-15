import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.util.ArrayList;

public class Game {
   
   private Snake snake;
   private Board board;
   private SnakeGUI gui;
   private Target target;
   private Timer timer;
   private boolean keyPressedTisTurn;
   private ArrayList<Integer> inputs;
   private int leftWallRestriction;
   private int bottomWallRestriction;
   private int rightWallRestriction;
   private int topWallRestriction;

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

   private void steerSnake(int direction) {
      snake.changeDirection(direction);
      inputs.remove(0);
   }

   private void initialise(){
      inputs = new ArrayList<Integer>();
      gui = new SnakeGUI(this);
      board = gui.getBoard();
      leftWallRestriction = 1;
      bottomWallRestriction = board.getBoardHeight()-1;
      rightWallRestriction = board.getBoardWidth()-1;
      topWallRestriction = 2;
      snake = new Snake();
      target = new Target(leftWallRestriction, rightWallRestriction,
      topWallRestriction, bottomWallRestriction);
      gui.setTarget(target);
      gui.setSnake(snake);
      setTimer(69);
   }

   private void progress(){
      keyPressedTisTurn = false;

      if(inputs.size() > 0){
         steerSnake(inputs.get(0));
      }

      if(isSnakeCollectingTarget()){
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
      if(snake.isCoiled() || isSnakeOutOfBounds()){
         return true;
      }
      return false;
   }

   private boolean isSnakeOutOfBounds(){
      Point head = snake.getHead();
      if(head.getX() > rightWallRestriction ||
         head.getX() < leftWallRestriction ||
         head.getY() < topWallRestriction ||
         head.getY() > bottomWallRestriction + 1){
         return true;
      }
      return false;
   }

   private boolean isSnakeCollectingTarget(){
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
