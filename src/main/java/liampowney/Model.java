package liampowney;

import java.util.LinkedList;
import java.util.Random;

public class Model {

    private final int height;
    private final int width;
    private boolean[] pixels;
    private LinkedList<Integer> snake;
    private Direction currentDirection;
    private int apple;
    private boolean appleEaten;
    private boolean alive;

    private int score;

    public Model() {
        this(15, 20);
    }

    public Model(int h, int w) {
        this.height=h;
        this.width=w;
        this.pixels=new boolean[h*w];
        this.snake = createSnake(5);
        this.currentDirection=Direction.RIGHT;
        newApple();
        this.alive=true;
        this.score=0;
    }

    private LinkedList<Integer> createSnake(int length) {
        LinkedList<Integer> snake = new LinkedList<>();
        for (int i=0; i<length; i++) {
            int pos = (((height*width)/2)-i);
            snake.addLast(pos);
            pixels[pos]=true;
        }
        return snake;
    }

    private void newApple() {
        int availablePixels = pixels.length-snake.size();
        Random rand = new Random();
        int r = rand.nextInt(availablePixels);

        int count=0;
        for (int i=0; i<pixels.length; i++) {
            if (!pixels[i]) {
                if (count==r) {apple = i; return;}
                else {count++;}
            }
        }
    }

    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public boolean[] getPixels() {return pixels;}
    public LinkedList<Integer> getSnake() {return snake;}
    public int getSnakeHead() {return snake.getFirst();}
    public Direction getCurrentDirection() {return currentDirection;}
    public int getApple() {return apple;}
    public boolean getAlive() {return alive;}
    public int getScore() {return score;}

    public void receiveInstruction(Direction d) {
        if ( (d==Direction.UP && currentDirection==Direction.DOWN) || 
            (d==Direction.DOWN && currentDirection==Direction.UP) ||
            (d==Direction.LEFT && currentDirection==Direction.RIGHT) ||
            (d==Direction.RIGHT && currentDirection==Direction.LEFT) ||
            (d==null) ) {d=currentDirection;}
        currentDirection=d;
        // detect collision for new pixel
        if (detectCollision(d)) {
            alive=false;
            return;
        }
        // if no collision, update snake pos
        switch (d) {
            case Direction.UP:
                snake.addFirst(snake.getFirst()-width);
                break;
            case Direction.DOWN:
                snake.addFirst(snake.getFirst()+width);
                break;
            case Direction.LEFT:
                snake.addFirst(snake.getFirst()-1);
                break;
            case Direction.RIGHT:
                snake.addFirst(snake.getFirst()+1);
                break;
        }
        pixels[snake.getFirst()]=true;

        if (appleEaten) {
            appleEaten=false;
        }
        else {
            pixels[snake.getLast()]=false;
        snake.removeLast();
        }

        if (snake.getFirst()==apple) {
            appleEaten=true;
            newApple();
        }
    }

    public boolean detectCollision(Direction d) {
        int nextPixel=nextPixel(d);
        if (nextPixel<0 || nextPixel>pixels.length-1 || (nextPixel%width==0 && d==Direction.RIGHT) || (nextPixel%width==width-1 && d==Direction.LEFT) ) {return true;}
        if (pixels[nextPixel]) {return true;}
        return false;
    }

    public int nextPixel(Direction d) {
        switch (d) {
            case Direction.UP:
                return snake.getFirst()-width;
            case Direction.DOWN:
                return snake.getFirst()+width;
            case Direction.LEFT:
                return snake.getFirst()-1;
            case Direction.RIGHT:
                return snake.getFirst()+1;
            default:
                throw new Error();
        }
    }
}
