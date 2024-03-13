package liampowney;

import java.util.ArrayList;

public class HeatSeeker extends SnakeAI{

    @Override
    public Direction makeChoice(Model m) {

        ArrayList<Direction> options = new ArrayList<Direction>();
        if (!(m.getCurrentDirection()==Direction.DOWN || m.detectCollision(Direction.UP))) {options.add(Direction.UP);}
        if (!(m.getCurrentDirection()==Direction.UP || m.detectCollision(Direction.DOWN))) {options.add(Direction.DOWN);}
        if (!(m.getCurrentDirection()==Direction.LEFT || m.detectCollision(Direction.RIGHT))) {options.add(Direction.RIGHT);}
        if (!(m.getCurrentDirection()==Direction.RIGHT || m.detectCollision(Direction.LEFT))) {options.add(Direction.LEFT);}

        if (options.size()==0) {return null;}

        int[] nextDistance = new int[options.size()];

        int indexOfLowest=0;

        for (int i=0; i<nextDistance.length; i++) {
            nextDistance[i]=distanceFromNode(m.nextPixel(options.get(i)), m.getApple(), m);
            if (nextDistance[i]<nextDistance[indexOfLowest]) {indexOfLowest=i;}
        }

        return options.get(indexOfLowest);
    }
}
