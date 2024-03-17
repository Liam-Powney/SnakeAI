package liampowney.snakeais;

import liampowney.Direction;
import liampowney.Model;

public abstract class SnakeAI {

    public abstract Direction makeChoice(Model m);

    public int xDistance(Model m) {
        return m.getApple()%m.getWidth()-m.getSnakeHead()%m.getWidth();
    }

    public int yDistance(Model m) {
        return m.getApple()/m.getWidth()-m.getSnakeHead()/m.getWidth();
    }

    public int distanceFromNode(int position, int node, Model m) {
        int x = Math.abs(node%m.getWidth()-position%m.getWidth());
        int y = Math.abs(node/m.getWidth()-position/m.getWidth());
        return x+y;
    }
}
