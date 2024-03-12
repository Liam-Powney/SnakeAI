package liampowney;

public class App 
{
    public static void main( String[] args )
    {   
        SnakeAI ai = new RandomAI();
        GameWindow newGame = new GameWindow(ai);
        newGame.startGame();
    }
}
