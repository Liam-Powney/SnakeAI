package liampowney.snakeais;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import liampowney.Direction;
import liampowney.Model;

public class BreadthFirst extends SnakeAI{

    private LinkedList<Direction> path;
    private Iterator<Direction> pathIterator;

    @Override
    public Direction makeChoice(Model m) {
        if (path==null || pathIterator.hasNext()==false) {
            path=findPath(m);
            if (path==null) {return m.getCurrentDirection();}
            pathIterator=path.iterator();
        }
        return pathIterator.next();
    }

    public LinkedList<Direction> findPath(Model m) {

        LinkedList<Node> nodeQueue = new LinkedList<Node>();
        ArrayList<Integer> nodesChecked = new ArrayList<Integer>();

        // add first nodes to be checked
        for (Direction d : options(m)) {
            Node n = new Node(new Model(m, d, true), d);
            nodeQueue.addLast(n);
            nodesChecked.add(n.getModel().getSnakeHead());
        }


        // check nodes
        while (nodeQueue.size()>0) {

            Node n = nodeQueue.getFirst();

            if (m.getApple()==n.getModel().getSnakeHead()) {
                return n.getPath();
            }

            for (Direction d : options(n.getModel())) {

                int next = n.getModel().nextPixel(d);

                if (!nodesChecked.contains(next)) {
                    Node newNode = new Node(n, d);
                    nodeQueue.addLast(newNode);
                    nodesChecked.add(newNode.getModel().getSnakeHead());
                }
            }

            nodeQueue.removeFirst();
        }

        return null;

    }

    private ArrayList<Direction> options(Model m) {

        ArrayList<Direction> options = new ArrayList<Direction>();

        for (Direction d: Direction.values()) {

            if (m.getCurrentDirection()==Direction.UP && d==Direction.DOWN) {continue;}
            if (m.getCurrentDirection()==Direction.DOWN && d==Direction.UP) {continue;}
            if (m.getCurrentDirection()==Direction.LEFT && d==Direction.RIGHT) {continue;}
            if (m.getCurrentDirection()==Direction.RIGHT && d==Direction.LEFT) {continue;}

            if (!m.detectCollision(d)) {
                options.add(d);
            }
        }
        return options;        
    }



    
}

