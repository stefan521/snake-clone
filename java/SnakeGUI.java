import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;

public class SnakeGUI {

    private JPanel gamePanel;
    private JFrame containerJF;
    private final Game game;
    private final Board board;
    private Snake snake;

    public SnakeGUI(Game game) {
        board = new Board();
        this.game = game;
        setFrame();
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
        board.setSnake(snake);
    }

    public void setTarget(Target target) {
        board.setTarget(target);
    }

    public Board getBoard() {
        return board;
    }

    //initialise the frame
    private void setFrame() {
        containerJF = new JFrame("snake");
        setPanels();
        containerJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        containerJF.setMinimumSize(new Dimension(656, 715));
        containerJF.setResizable(false);
        containerJF.setLayout(new BorderLayout());
        containerJF.add(gamePanel, BorderLayout.CENTER);
        setFrameListener();
        containerJF.setVisible(true);
    }

    private void setPanels() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(board, BorderLayout.CENTER);
    }

    //add key listener to the frame
    private void setFrameListener() {
        containerJF.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                sendInputToGame(e.getKeyCode());
            }

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }

    private void sendInputToGame(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                game.addDirectionInput(Direction.UP);
                break;
            case KeyEvent.VK_S:
                game.addDirectionInput(Direction.DOWN);
                break;
            case KeyEvent.VK_A:
                game.addDirectionInput(Direction.LEFT);
                break;
            case KeyEvent.VK_D:
                game.addDirectionInput(Direction.RIGHT);
                break;
        }
    }

}
