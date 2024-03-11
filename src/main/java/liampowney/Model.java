package liampowney;

import java.util.LinkedList;

public class Model {

    private final int height;
    private final int width;
    private boolean[] pixels;
    private LinkedList<Integer> snake;
    private Direction currentDirection;
    private int apple;
    private boolean alive;

    private int score;

    public Model() {
        this(15, 20);
    }

    public Model(int h, int w) {
        this.height=h;
        this.width=w;
        this.pixels=new boolean[h*w];
        this.snake = createSnake(8);
        this.currentDirection=Direction.RIGHT;
        this.apple=0;
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

    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public boolean[] getPixels() {return pixels;}
    public LinkedList<Integer> getSnake() {return snake;}
    public Direction getCurrentDirection() {return currentDirection;}
    public int getApple() {return apple;}
    public boolean getAlive() {return alive;}
    public int getScore() {return score;}

    public void receiveInstruction(Direction d) {
        if (d==null) {d=currentDirection;}
        currentDirection=d;

        if (detectCollision()) {
            alive=false;
            return;
        }

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
        pixels[snake.getLast()]=false;
        snake.removeLast();
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
