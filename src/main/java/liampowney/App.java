package liampowney;

import liampowney.snakeais.*;

public class App 
{
    public static void main( String[] args )
    {   
        SnakeAI ai = new BreadthFirst();
        GameWindow newGame = new GameWindow(ai);
        newGame.startGame();
    }
}
