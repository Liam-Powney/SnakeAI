package liampowney;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Iterator;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    private final int tileSize = 48;

    private final int FPS = 6;
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
        this.setBackground(Color.white);
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

            if(delta>1) {
                update();
                repaint();
                delta--;

                if (!model.getAlive()) {
                    gameThread=null;
                }
            }
        }
        
    }

    public void update() {
        // if you die, stop the game 
        //if (!model.getAlive()) {gameThread=null;}

        model.executeInstruction(keyH.lastInstruction);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DRAW SNAKE
        Iterator<Integer> iterator = model.getSnake().iterator();
        Integer i = iterator.next();
        g2.setColor(Color.PINK);
        g2.fillRect((i%model.getWidth())*tileSize, (i/model.getWidth())*tileSize, tileSize, tileSize);
        g2.setColor(Color.GREEN);
        while (iterator.hasNext()) {
            i = iterator.next();
            g2.fillRect((i%model.getWidth())*tileSize, (i/model.getWidth())*tileSize, tileSize, tileSize);
        }

        //DRAW APPLE
        
        g2.dispose();
    }


}
