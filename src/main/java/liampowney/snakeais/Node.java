package liampowney.snakeais;

import java.util.LinkedList;
import liampowney.Direction;
import liampowney.Model;

public class Node {

    private LinkedList<Direction> path;
    private Model model;

    public Node(Model m, Direction d){
        this.path = new LinkedList<Direction>();
        this.path.add(d);
        this.model=m;;
    }

    public Node(Node n, Direction d) {
        this.model = new Model(n.getModel(), d, false);
        this.path = new LinkedList<Direction>(n.getPath());
        this.path.add(d);
    }

    public LinkedList<Direction> getPath() {return path;}
    public Model getModel() {return model;}
}
