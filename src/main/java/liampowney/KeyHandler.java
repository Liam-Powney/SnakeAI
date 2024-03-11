package liampowney;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public Direction lastInstruction;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                lastInstruction=Direction.UP;
                break;
            case KeyEvent.VK_A:
                lastInstruction=Direction.LEFT;
                break;
            case KeyEvent.VK_S:
                lastInstruction=Direction.DOWN;
                break;
            case KeyEvent.VK_D:
                lastInstruction=Direction.RIGHT;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
