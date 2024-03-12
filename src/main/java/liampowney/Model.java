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
        if (detectCollision()) {
            alive=false;
            return;
        }
        // if no collision, update snake pos
        switch (currentDirection) {
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

    private boolean detectCollision() {

        int nextPixel=0;

        switch (currentDirection) {
            case Direction.UP:
                nextPixel=snake.getFirst()-width;
                if (nextPixel<0) {return true;}
                break;
            case Direction.DOWN:
                nextPixel=snake.getFirst()+width;
                if (nextPixel>(height*width)) {return true;}
                break;
            case Direction.LEFT:
                nextPixel=snake.getFirst()-1;
                if (nextPixel%width==width-1) {return true;}
                break;
            case Direction.RIGHT:
                nextPixel=snake.getFirst()+1;
                if (nextPixel%width==0) {return true;}
                break;
        }
        if (pixels[nextPixel]) {return true;}

        return false;
    }
}
