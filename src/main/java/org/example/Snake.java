package org.example;

import java.util.ArrayList;
import java.awt.Point;

public class Snake {

    private ArrayList<Point> segments;
    private Direction direction;
    private boolean capturedTarget;

    public Snake() {
        resetSnake();
    }

    public void resetSnake() {
        segments = new ArrayList<>();
        segments.add(new Point(3, 3));
        segments.add(new Point(3, 4));
        segments.add(new Point(3, 5));
        direction = Direction.DOWN;
        capturedTarget = false;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void grow() {
        capturedTarget = true;
    }

    public Point getHead() {
        return segments.get(segments.size() - 1);
    }

    public int getSize() {
        return capturedTarget ? segments.size() + 1 : segments.size();
    }

    public ArrayList<Point> getSegments() {
        return segments;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isCoiled() {
        Point head = getHead();
        for (Point eachSegment : getSegments()) {
            if (head.equals(eachSegment) && head != eachSegment) {
                return true;
            }
        }
        return false;
    }

    public void move() {
        moveHead();

        if (capturedTarget) {
            capturedTarget = false;
        } else {
            segments.remove(0);
        }
    }

    private void moveHead() {
        Point head = segments.get(segments.size() - 1);
        int x = (int) head.getX();
        int y = (int) head.getY();

        switch (direction) {
            case UP:
                segments.add(new Point(x, y - 1));
                break;
            case DOWN:
                segments.add(new Point(x, y + 1));
                break;
            case LEFT:
                segments.add(new Point(x - 1, y));
                break;
            case RIGHT:
                segments.add(new Point(x + 1, y));
                break;
        }
    }

}
