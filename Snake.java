import java.util.ArrayList;
import java.awt.Point;
public class Snake{
     private ArrayList<Point> segments;
     public final int UP = 2;
     public final int DOWN = -2;
     public final int RIGHT = 1;
     public final int LEFT = -1;
     private int directionHeading;

     public Snake(){
          putSnakeOnStartPosition();
     }

     public void changeDirection(int direction){
          switch(direction){
               case UP: directionHeading = UP;
               case DOWN: directionHeading = DOWN;
               case RIGHT: directionHeading = RIGHT;
               case LEFT: directionHeading = LEFT;
          }
     }
//TODO move the snake
     public void move(){

     }

     public ArrayList<Point> getSegments(){
          return segments;
     }

     public int getDirectionHeading(){
          return directionHeading;
     }

     private void putSnakeOnStartPosition(){
          segments = new ArrayList<Point>();
          segments.add(new Point(0,2));
          segments.add(new Point(0,1));
          segments.add(new Point(0,0));
          directionHeading = UP;
     }

}
