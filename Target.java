import java.util.Random;

public class Target{
     private int x;
     private int y;
     private int maxWidth;
     private int maxHeight;
     private Random randomObj;

     public Target(int maxWidth, int maxHeight){
          this.maxWidth = maxWidth;
          this.maxHeight = maxHeight;
          spawn();
     }

     public void spawn(){
          x = randomObj.nextInt(maxWidth);
          y = randomObj.nextInt(maxHeight);
     }

     public int getX(){
          return x;
     }

     public int getY(){
          return y;
     }

}
