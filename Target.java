import java.util.Random;

public class Target{
     private int x;
     private int y;
     private int maxWidth;
     private int maxHeight;

     public Target(int maxWidth, int maxHeight){
          this.maxWidth = maxWidth;
          this.maxHeight = maxHeight;
          spawn();
     }

     public int spawn(){
          x = nextInt(maxWidth);
          y = nextInt(maxHeight);
     }

     public int getX(){
          return x;
     }

     public int getY(){
          return y;
     }

}
