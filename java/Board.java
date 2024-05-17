import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Font;

public class Board extends JPanel {

    private int boardWidth;
    private int boardHeight;
    private int squareSize;
    private Snake snake;
    private Target target;
    private Font scoreFont;
    private BufferedImage targetImage;
    private BufferedImage snakeImage;
    private BufferedImage grassImage;
    private BufferedImage borderImage;

    public Board() {
        initialiseBoard();
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //paint background and border
        g.drawImage(grassImage, 0, 0, null);
        g.drawImage(borderImage, 0, 25, null);
        //paint target
        if (target != null) {
            int targetX = (int) target.getPosition().getX() * squareSize;
            int targetY = (int) target.getPosition().getY() * squareSize;
            g.drawImage(targetImage, targetX, targetY, null);
        }
        //paint snake
        for (Point segment : snake.getSegments()) {
            int snakeX = (int) segment.getX() * squareSize;
            int snakeY = (int) segment.getY() * squareSize;
            g.drawImage(snakeImage, snakeX, snakeY, null);
        }
        //paint score at the top
        g.setFont(scoreFont);
        g.setColor(Color.BLACK);
        g.drawString("Size " + snake.getSize(), 310, 25);
    }

    private void initialiseBoard() {
        setImages();
        boardWidth = 25;
        boardHeight = 25;
        squareSize = 25;
        scoreFont = new Font("Tahoma", Font.BOLD, 20);
    }

    //read images from the resource file
    private void setImages() {
        targetImage = readBufferedImage("./resources/originalTarget.png");
        borderImage = readBufferedImage("./resources/originalBorder.png");
        snakeImage = readBufferedImage("./resources/originalSnake.png");
        grassImage = readBufferedImage("./resources/originalBG.png");
    }

    //convenient method for reading images
    private BufferedImage readBufferedImage(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
