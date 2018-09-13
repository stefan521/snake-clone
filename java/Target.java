import java.util.Random;
import java.awt.Point;
import java.util.ArrayList;

public class Target{

   private static int maxWidth;
   private static int maxHeight;
   private int timeToLive;
   private int timeLived;
   private static int minWidth;
   private static int minHeight;
   private static Random randomObj;
   private Point position;

   public Target(int minWidth, int maxWidth, int minHeight, int maxHeight){
      this.minWidth = minWidth;
      this.maxWidth = maxWidth;
      this.minHeight = minHeight;
      this.maxHeight = maxHeight;
      randomObj = new Random();
      this.timeToLive = 30;
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

   public void spawn(){
      int x = generateIntGreaterThan(minWidth);
      int y = generateIntGreaterThan(minHeight);
      position = new Point(x, y);
      timeLived = 0;
   }

   public int generateIntGreaterThan(int minimumValue){
      int valueToGenerate = randomObj.nextInt(maxWidth);
      while(valueToGenerate < minimumValue){
         valueToGenerate = randomObj.nextInt(maxWidth);
      }
      return valueToGenerate;
   }

}
