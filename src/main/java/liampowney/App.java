package liampowney;

public class App 
{
    public static void main( String[] args )
    {   
        SnakeAI ai = new HeatSeeker();
        GameWindow newGame = new GameWindow(ai);
        newGame.startGame();
    }
}
