import java.util.Random;
import java.awt.Point;

public class Target{
     private Point position;
     private int maxWidth;
     private int maxHeight;
     private Random randomObj;
     private int timeToLive;
     private int timeLived;

     public Target(int maxWidth, int maxHeight, int timeToLive){
          this.maxWidth = maxWidth;
          this.maxHeight = maxHeight;
          this.timeToLive = timeToLive;
          randomObj = new Random();
          position = new Point();
          spawn();
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
          timeLived = 0;
          if(x == 0 || y == 0 || x == maxWidth || y == maxHeight){
               spawn();
          }
          position.setLocation(x, y);
     }

}
