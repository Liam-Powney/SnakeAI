package liampowney;

import javax.swing.JFrame;

public class HumanGame {

    private JFrame window;
    private GamePanel gamePanel;

    public HumanGame() {
        this.window = new JFrame();
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setResizable(false);
        this.window.setTitle("Snake!");
        this.gamePanel = new GamePanel();
        this.window.add(gamePanel);
        this.window.pack();
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);
    }
    public void startGame() {
        gamePanel.startGameThead();}
}
