package liampowney;

public class HumanGamePanel extends GamePanel{

    public HumanGamePanel() {
        super();
    }

    public HumanGamePanel(int h, int w) {
        super(h, w);
    }

    @Override
    public void run() {

        while (this.gameThread!=null) {
            this.currentTime=System.currentTimeMillis();
            delta += (currentTime-lastTime)/drawInterval;
            lastTime=currentTime;

            Direction d = keyH.manageHumanInputs();
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

}
