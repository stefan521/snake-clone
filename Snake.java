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
               if (direction==UP && directionHeading!=DOWN) {directionHeading = UP;}
               if (direction==DOWN && directionHeading!=UP) {directionHeading = DOWN;}
               if (direction==RIGHT && directionHeading!=LEFT) {directionHeading = RIGHT;}
               if (direction==LEFT && directionHeading!=RIGHT) {directionHeading = LEFT;}
     }

     public void move(){
          moveHead();
          if(capturedTarget){
               capturedTarget = false;
          }
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
          segments.add(new Point(3,3));
          segments.add(new Point(3,4));
          segments.add(new Point(3,5));
          directionHeading = UP;
          capturedTarget = false;
     }

     public String toString(){
          return segments.toString();
     }
}
