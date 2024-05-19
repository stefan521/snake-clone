package org.example;

import javax.swing.Timer;
import java.awt.Point;
import java.util.ArrayList;

public class Game {

    private Snake snake;
    private Board board;
    private Target target;
    private Timer timer;
    private boolean keyPressedTisTurn;
    private ArrayList<Direction> inputs;
    private int leftWallRestriction;
    private int bottomWallRestriction;
    private int rightWallRestriction;
    private int topWallRestriction;

    public Game() {
        initialise();
        startGame();
    }

    public void startGame() {
        snake.resetSnake();
        timer.restart();
    }

    public void pause() {
        timer.stop();
    }

    public void resume() {
        if (!isGameOver()) {
            timer.restart();
        }
    }

    public void addDirectionInput(Direction direction) {
        if (inputs.size() < 2) {
            if (keyPressedTisTurn) {
                inputs.add(direction);
            } else {
                inputs.clear();
                inputs.add(direction);
                keyPressedTisTurn = true;
            }
        }
    }

    private void steerSnake(Direction direction) {
        snake.setDirection(direction);
        inputs.remove(0);
    }

    private void initialise() {
        inputs = new ArrayList<>();
        SnakeGUI gui = new SnakeGUI(this);
        board = gui.getBoard();
        leftWallRestriction = 1;
        bottomWallRestriction = board.getBoardHeight() - 1;
        rightWallRestriction = board.getBoardWidth() - 1;
        topWallRestriction = 2;
        snake = new Snake();
        target = new Target(
                leftWallRestriction,
                rightWallRestriction,
                topWallRestriction
        );
        gui.setTarget(target);
        gui.setSnake(snake);
        setTimer(69);
    }

    private void progress() {
        keyPressedTisTurn = false;

        if (!inputs.isEmpty()) {
            steerSnake(inputs.get(0));
        }

        if (isSnakeCollectingTarget()) {
            snake.grow();
            target.spawn();
        }

        if (isGameOver()) {
            timer.stop();
        } else {
            snake.move();
            target.increaseTimeLived();
        }

        board.repaint();
    }

    private boolean isGameOver() {
        return snake.isCoiled() || isSnakeOutOfBounds();
    }

    private boolean isSnakeOutOfBounds() {
        Point head = snake.getHead();
        return head.getX() > rightWallRestriction ||
                head.getX() < leftWallRestriction ||
                head.getY() < topWallRestriction ||
                head.getY() > bottomWallRestriction + 1;
    }

    private boolean isSnakeCollectingTarget() {
        return snake.getHead().equals(target.getPosition());
    }

    private void setTimer(int delay) {
        timer = new Timer(delay, e -> progress());
    }

    public static void main(String[] args) {
        new Game();
    }
}
