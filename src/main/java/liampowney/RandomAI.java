package liampowney;

import java.util.Random;

public class RandomAI extends SnakeAI{

    private Random r;

    public RandomAI() {this.r = new Random();}

    @Override
    public Direction makeChoice(Model m) {
        int i = r.nextInt(4);
        switch (i) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.DOWN;
            case 2:
                return Direction.LEFT;
            case 3:
                return Direction.RIGHT;
        }
        return null;
    }

}
