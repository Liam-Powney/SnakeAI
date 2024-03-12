package liampowney;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    private boolean wPressed;
    private boolean wActionTaken;
    private boolean aPressed;
    private boolean aActionTaken;
    private boolean sPressed;
    private boolean sActionTaken;
    private boolean dPressed;
    private boolean dActionTaken;

    private boolean leftPressed;
    private boolean leftActionTaken;
    private boolean rightPressed;
    private boolean rightActionTaken;

    public KeyHandler() {
        this.wPressed=false;
        this.wActionTaken=false;
        this.aPressed=false;
        this.aActionTaken=false;
        this.sPressed=false;
        this.sActionTaken=false;
        this.dPressed=false;
        this.dActionTaken=false;
        this.leftPressed=false;
        this.leftActionTaken=false;
        this.rightPressed=false;
        this.rightActionTaken=false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                wPressed=true;
                break;
            case KeyEvent.VK_A:
                aPressed=true;
                break;
            case KeyEvent.VK_S:
                sPressed=true;
                break;
            case KeyEvent.VK_D:
                dPressed=true;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed=true;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed=true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_W:
                wPressed=false;
                wActionTaken=false;
                break;
            case KeyEvent.VK_A:
                aPressed=false;
                aActionTaken=false;
                break;
            case KeyEvent.VK_S:
                sPressed=false;
                sActionTaken=false;
                break;
            case KeyEvent.VK_D:
                dPressed=false;
                dActionTaken=false;
                break;
            case KeyEvent.VK_LEFT:
                leftPressed=false;
                leftActionTaken=false;
                break;
            case KeyEvent.VK_RIGHT:
                rightPressed=false;
                rightActionTaken=false;
                break;
        }
    }

    public Direction manageHumanInputs() {
        if (wPressed && !wActionTaken) {
            wActionTaken=true;
            return Direction.UP;
        }
        if (aPressed && !aActionTaken) {
            aActionTaken=true;
            return Direction.LEFT;
        }
        if (sPressed && !sActionTaken) {
            sActionTaken=true;
            return Direction.DOWN;
        }
        if (dPressed && !dActionTaken) {
            dActionTaken=true;
            return Direction.RIGHT;
        }
        return null;
    }

    public Boolean manageAIInputs() {
        if (leftPressed && !leftActionTaken) {
            leftActionTaken=true;
            return false;
        }
        if (rightPressed && !rightActionTaken) {
            rightActionTaken=true;
            return true;
        }
        return null;
    }

}
