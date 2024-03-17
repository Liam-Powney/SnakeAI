package liampowney;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

public abstract class GamePanel extends JPanel implements Runnable{

    protected final int tileSize = 48;

    protected int FPS = 4;
    protected double drawInterval = 1000/FPS;
    protected double delta;
    protected long lastTime;
    protected long currentTime;

    protected Model model;
    protected Thread gameThread;
    protected KeyHandler keyH = new KeyHandler();

    public GamePanel() {
        this.model= new Model();
        this.setPreferredSize(new Dimension(model.getWidth()*tileSize, model.getHeight()*tileSize));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.model= new Model();
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.delta=0;
        this.lastTime=System.currentTimeMillis();
    }

    public GamePanel(int h, int w) {
        this.model= new Model(h, w);
        this.setPreferredSize(new Dimension(model.getWidth()*tileSize, model.getHeight()*tileSize));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.model= new Model();
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.delta=0;
        this.lastTime=System.currentTimeMillis();
    }

    public void startGameThead() {

        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public abstract void run();

    public void update(Direction d) {
        model.receiveInstruction(d);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DRAW SNAKE
        Iterator<Integer> iterator = model.getSnake().iterator();
        Integer it = iterator.next();
        g2.setColor(Color.PINK);
        g2.fillRect((it%model.getWidth())*tileSize, (it/model.getWidth())*tileSize, tileSize, tileSize);
        g2.setColor(Color.GREEN);
        while (iterator.hasNext()) {
            it = iterator.next();
            g2.fillRect((it%model.getWidth())*tileSize, (it/model.getWidth())*tileSize, tileSize, tileSize);
        }
        // DRAW APPLE
        g2.setColor(Color.RED);
        g2.fillRect((model.getApple()%model.getWidth())*tileSize, (model.getApple()/model.getWidth())*tileSize, tileSize, tileSize);
        
        g2.dispose();
    }


}
