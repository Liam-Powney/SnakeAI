package liampowney;

import java.util.ArrayList;

public class AStarAI extends SnakeAI{

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
            switch (options.get(i)) {
                case Direction.UP:
                    nextDistance[i]=distanceFromNode(m.getSnakeHead()-m.getWidth(), m.getApple(), m);
                    break;
                case Direction.DOWN:
                    nextDistance[i]=distanceFromNode(m.getSnakeHead()+m.getWidth(), m.getApple(), m);
                    break;
                case Direction.LEFT:
                    nextDistance[i]=distanceFromNode(m.getSnakeHead()-1, m.getApple(), m);
                    break;
                case Direction.RIGHT:
                    nextDistance[i]=distanceFromNode(m.getSnakeHead()+1, m.getApple(), m);
                    break;
            }
            if (nextDistance[i]<nextDistance[indexOfLowest]) {indexOfLowest=i;}
        }

        return options.get(indexOfLowest);
    }
}
