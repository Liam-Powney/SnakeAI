package liampowney;

import liampowney.snakeais.SnakeAI;

public class AIGamePanel extends GamePanel{

    private SnakeAI ai;
    private Direction aiChoice;

    public AIGamePanel(SnakeAI s) {
        super();
        this.ai = s;
        this.aiChoice=null;
    }

    public AIGamePanel(SnakeAI s, int h, int w) {
        super(h, w);
        this.ai = s;
        this.aiChoice=null;
    }

    @Override
    public void run() {

        while (this.gameThread!=null) {
            this.currentTime=System.currentTimeMillis();
            delta += (currentTime-lastTime)/drawInterval;
            lastTime=currentTime;

            if (aiChoice==null) {
                aiChoice=ai.makeChoice(model);
            }
            
            Boolean b = keyH.manageAIInputs();
            if (b!=null) {
                if (b) {drawInterval/=2;}
                else {drawInterval*=2;}
            }

            if(delta>1) {
                update(aiChoice);
                aiChoice=null;
                repaint();
                delta--;

                if (!model.getAlive()) {
                    model = new Model();
                }
            }
        }

    }
    
}
