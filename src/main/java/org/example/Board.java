package org.example;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Font;

public class Board extends JPanel {

    private final int boardWidth;
    private final int boardHeight;
    private final int squareSize;
    private Snake snake;
    private Target target;
    private final Font scoreFont;
    private final ResourceManager resourceManager;


    public Board(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        boardWidth = 25;
        boardHeight = 25;
        squareSize = 25;
        scoreFont = new Font("Tahoma", Font.BOLD, 20);
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
        g.drawImage(resourceManager.getGrassImage(), 0, 0, null);
        g.drawImage(resourceManager.getBorderImage(), 0, 25, null);
        //paint target
        if (target != null) {
            int targetX = (int) target.getPosition().getX() * squareSize;
            int targetY = (int) target.getPosition().getY() * squareSize;
            g.drawImage(resourceManager.getTargetImage(), targetX, targetY, null);
        }
        //paint snake
        for (Point segment : snake.getSegments()) {
            int snakeX = (int) segment.getX() * squareSize;
            int snakeY = (int) segment.getY() * squareSize;
            g.drawImage(resourceManager.getSnakeImage(), snakeX, snakeY, null);
        }
        //paint score at the top
        g.setFont(scoreFont);
        g.setColor(Color.BLACK);
        g.drawString("Size " + snake.getSize(), 310, 25);
    }
}
