import java.util.Random;
import java.awt.Point;

public class Target{
     private Point position;
     private int maxWidth;
     private int maxHeight;
     private Random randomObj;
     private int timeToLive;

     public Target(int maxWidth, int maxHeight, int timeToLive){
          this.maxWidth = maxWidth;
          this.maxHeight = maxHeight;
          this.timeToLive = timeToLive;
          spawn();
     }

     public void decreaseTimeToLive(){
          timeToLive--;
     }

     public boolean isExpired(){
          if(timeToLive <= 0)
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

     private void spawn(){
          randomObj = new Random();
          position = new Point();
          int x = randomObj.nextInt(maxWidth);
          int y = randomObj.nextInt(maxHeight);
          position.setLocation(x, y);
     }

}
