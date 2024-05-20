package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceManager {

    private final BufferedImage targetImage;
    private final BufferedImage snakeImage;
    private final BufferedImage grassImage;
    private final BufferedImage borderImage;

    public ResourceManager() throws IOException {
        targetImage = readBufferedImage("target.png");
        borderImage = readBufferedImage("border.png");
        snakeImage = readBufferedImage("snake.png");
        grassImage = readBufferedImage("background.png");
    }

    public BufferedImage getTargetImage() {
        return this.targetImage;
    }

    public BufferedImage getSnakeImage() {
        return this.snakeImage;
    }

    public BufferedImage getGrassImage() {
        return this.grassImage;
    }

    public BufferedImage getBorderImage() {
        return this.borderImage;
    }

    private BufferedImage readBufferedImage(String path) throws IOException {
        return ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(path)));
    }
}
