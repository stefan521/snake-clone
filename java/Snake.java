import java.util.ArrayList;
import java.awt.Point;

public class Snake{
   
   private ArrayList<Point> segments;
   public static final int UP = 2;
   public static final int DOWN = -2;
   public static final int RIGHT = 1;
   public static final int LEFT = -1;
   private int directionHeading;
   private boolean capturedTarget;

   public Snake(){
      resetSnake();
   }

   public void resetSnake(){
      segments = new ArrayList<Point>();
      segments.add(new Point(3,3));
      segments.add(new Point(3,4));
      segments.add(new Point(3,5));
      directionHeading = DOWN;
      capturedTarget = false;
   }

   public void changeDirection(int direction){
      if (direction==UP && directionHeading!=DOWN) {directionHeading = UP;}
      if (direction==DOWN && directionHeading!=UP) {directionHeading = DOWN;}
      if (direction==RIGHT && directionHeading!=LEFT) {directionHeading = RIGHT;}
      if (direction==LEFT && directionHeading!=RIGHT) {directionHeading = LEFT;}
   }

   public void grow(){
      capturedTarget = true;
   }

   public Point getHead(){
      return segments.get(segments.size()-1);
   }

   public int getSize(){
      if(capturedTarget){
         return segments.size()+1;
      }
      return segments.size();
   }

   public ArrayList<Point> getSegments(){
      return segments;
   }

   public int getDirectionHeading(){
      return directionHeading;
   }

   public boolean isCoiled(){
      Point head = getHead();
      for(Point eachSegment : getSegments()){
         if(head.equals(eachSegment) && head != eachSegment){
            return true;
         }
      }
      return false;
   }

   public void move(){
      moveHead();
      if(capturedTarget){
         capturedTarget = false;
      }
      else{
         segments.remove(0);
      }
   }

   private void moveHead(){
      Point head = segments.get(segments.size()-1);
      int x = (int)head.getX();
      int y = (int)head.getY();
      if(directionHeading == UP) y -= 1;
      else if(directionHeading == DOWN) y += 1;
      else if(directionHeading == RIGHT) x += 1 ;
      else if(directionHeading == LEFT) x -= 1;
      segments.add(new Point(x, y));
   }

}
