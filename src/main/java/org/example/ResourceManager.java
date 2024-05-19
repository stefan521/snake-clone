package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceManager {

    private BufferedImage targetImage;
    private BufferedImage snakeImage;
    private BufferedImage grassImage;
    private BufferedImage borderImage;

    public ResourceManager() throws IOException {
        targetImage = readBufferedImage("originalTarget.png");
        borderImage = readBufferedImage("originalBorder.png");
        snakeImage = readBufferedImage("originalSnake.png");
        grassImage = readBufferedImage("originalBG.png");
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
