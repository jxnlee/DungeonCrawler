package main;

/**
 * Launcher class, once the class is run, it will launch the game
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
public class Launcher
{
    private static Game game;


    /**
     * Main method: starts the game by instantiating a Game object
     * 
     * @param args
     *            is not used
     */
    public static void main( String[] args )
    {
        game = new Game();
    }


    /**
     * Accessor method for the game object: gets the Game object and returns it
     * 
     * @return Game game - the instantiated game object that the user plays
     */
    public static Game getGame()
    {
        return game;
    }
}
