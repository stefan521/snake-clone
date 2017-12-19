import java.util.ArrayList;
import java.awt.Point;

public class Snake{
     private ArrayList<Point> segments;
     public final int UP = 2;
     public final int DOWN = -2;
     public final int RIGHT = 1;
     public final int LEFT = -1;
     private int directionHeading;
     private boolean capturedTarget;

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

     public void move(){
          moveHead();
          if(capturedTarget)
               capturedTarget = false;
          else segments.remove(0);
     }

     public void grow(){
          capturedTarget = true;
     }

     public Point getHead(){
          return segments.get(segments.size()-1);
     }

     public Point getTail(){
          return segments.get(0);
     }

     public int getSize(){
          return segments.size();
     }

     public ArrayList<Point> getSegments(){
          return segments;
     }

     public int getDirectionHeading(){
          return directionHeading;
     }

     private void moveHead(){
          Point head = segments.get(segments.size()-1);
          int x = (int)head.getX();
          int y = (int)head.getY();
          if(directionHeading == UP) y += 1;
          else if(directionHeading == DOWN) y -= 1;
          else if(directionHeading == RIGHT) x += 1 ;
          else if(directionHeading == LEFT) x -= 1;
          segments.add(new Point(x, y));
     }

     private void putSnakeOnStartPosition(){
          segments = new ArrayList<Point>();
          segments.add(new Point(0,0));
          segments.add(new Point(0,1));
          segments.add(new Point(0,2));
          directionHeading = UP;
          capturedTarget = false;
     }

     public String toString(){
          return segments.toString();
     }
}
