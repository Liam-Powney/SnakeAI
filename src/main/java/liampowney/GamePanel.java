package liampowney;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    private final int tileSize = 48;

    private final int FPS = 5;
    private final double drawInterval = 1000/FPS;
    private double delta;
    private long lastTime;
    private long currentTime;


    private Model model;
    private Thread gameThread;

    private KeyHandler keyH = new KeyHandler();

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

    public void startGameThead() {

        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {

        while (gameThread!=null) {
            currentTime=System.currentTimeMillis();
            delta += (currentTime-lastTime)/drawInterval;
            lastTime=currentTime;

            Direction d = keyH.manageInputs();
            if (d!=null || d == model.getCurrentDirection()) {
                update(d);
                repaint();
                delta=0;
            }
            

            if(delta>1) {
                update(model.getCurrentDirection());
                repaint();
                delta--;

                if (!model.getAlive()) {
                    model = new Model();
                }
            }
        }
        
    }

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
