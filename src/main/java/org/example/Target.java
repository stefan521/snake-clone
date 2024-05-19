package org.example;

import java.util.Random;
import java.awt.Point;

public class Target {

    private final int maxWidth;
    private final int timeToLive;
    private int timeLived;
    private final int minWidth;
    private final int minHeight;
    private static Random randomObj;
    private Point position;

    public Target(int minWidth, int maxWidth, int minHeight) {
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minHeight = minHeight;
        randomObj = new Random();
        this.timeToLive = 30;
        spawn();
    }

    public void increaseTimeLived() {
        timeLived++;
        if (isExpired()) {
            spawn();
        }
    }

    public boolean isExpired() {
        return timeToLive <= timeLived;
    }

    public Point getPosition() {
        return position;
    }

    public void spawn() {
        int x = generateIntGreaterThan(minWidth);
        int y = generateIntGreaterThan(minHeight);
        position = new Point(x, y);
        timeLived = 0;
    }

    public int generateIntGreaterThan(int minimumValue) {
        int valueToGenerate = randomObj.nextInt(maxWidth);
        while (valueToGenerate < minimumValue) {
            valueToGenerate = randomObj.nextInt(maxWidth);
        }
        return valueToGenerate;
    }

}
