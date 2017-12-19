import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;

public class Target{
     private Point position;
     private int maxWidth;
     private int maxHeight;
     private Random randomObj;
     private int timeToLive;
     private int timeLived;
     private ArrayList<Point> restrictedPoints;

     public Target(int maxWidth, int maxHeight, int timeToLive){
          this.maxWidth = maxWidth;
          this.maxHeight = maxHeight;
          this.timeToLive = timeToLive;
          randomObj = new Random();
          position = new Point();
          restrictedPoints = new ArrayList<Point>();
          spawn();
     }

     public void setRestrictedPoints(ArrayList<Point> pointList){
          restrictedPoints = pointList;
     }

     public void increaseTimeLived(){
          timeLived++;
          if(isExpired()){
               spawn();
          }
     }

     public boolean isExpired(){
          if(timeToLive == timeLived)
               return true;
          return false;
     }

     public Point getPosition(){
          return position;
     }

     public int getX(){
          return (int)position.getX();
     }

     public int getY(){
          return (int)position.getY();
     }

     public void spawn(){
          int x = randomObj.nextInt(maxWidth);
          int y = randomObj.nextInt(maxHeight);
          Point candidatePoint = new Point(x, y);
          timeLived = 0;
          if(x < 1 || y < 1 ||
             x > maxWidth || y > maxHeight ||
              isRestricted(candidatePoint)){
               spawn();
          } else {
               position.setLocation(candidatePoint);
          }

     }

     private boolean isRestricted(Point point){
          for(Point restrictedP : restrictedPoints)
               if(restrictedP.getX() == point.getX() &&
                  restrictedP.getY() == point.getY())
                  return true;
          return false;
     }

}
