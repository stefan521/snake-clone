public class Game{
     private Snake snake;
     private Board board;
     private Target target;

     public Game(){
          snake = new Snake();
          board = new Board();
          target = new Target(10,10);
     }

     public static void main(String[] args){
          Game game = new Game();
     }
}
