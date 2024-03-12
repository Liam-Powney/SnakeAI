package liampowney;

import javax.swing.JFrame;

public class GameWindow {

    private JFrame window;
    private GamePanel gamePanel;

    public GameWindow() {
        this.window = new JFrame();
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setTitle("Snake!");
        this.gamePanel = new HumanGamePanel();
        this.window.add(gamePanel);
        this.window.pack();
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);
    }

    public GameWindow(SnakeAI s) {
        this.window = new JFrame();
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setTitle("Snake!");
        this.gamePanel = new AIGamePanel(s);
        this.window.add(gamePanel);
        this.window.pack();
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);
    }
    public void startGame() {
        gamePanel.startGameThead();}
}
