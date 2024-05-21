package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gui.Level;
import screens.MenuScreen;
import screens.EndScreen;


/**
 * Manages the game itself, the beginning of the game, and the end of the game.
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
public class Game
{
    private Level lvl;

    private MenuScreen menu;

    private EndScreen end;

    private List<Long> scores;


    /**
     * Constructor: instantiates the menu and scores object whilst making the
     * menu visible
     */
    public Game()
    {
        menu = new MenuScreen();
        menu.setVisible( true );
        scores = new ArrayList<Long>( 5 );
    }


    /**
     * Runs to processes at the end of the game such as adding and sorting your
     * scores and statisitics, and transferring the information to be desplayed
     * on the EndScreen
     */
    public void endGame()
    {
        long recentScore = lvl.getUserLevelScore();
        boolean isNewestRecord;
        lvl.setVisible( false );
        if ( scores.size() > 0 && recentScore > scores.get( 0 ) )
        {
            isNewestRecord = true;
        }
        else
        {
            isNewestRecord = false;
        }
        scores.add( recentScore );
        sortScores();
        end = new EndScreen();
        end.recentIsNewRecord( isNewestRecord );
        end.setRecentScore( recentScore );
        end.setVisible( true );
    }


    /**
     * Runs the processes to start a new level (once the button to start the
     * game from the menu screen has been clicked): instantiates and starts a
     * level object whilst making it visible
     */
    public void startGame()
    {
        lvl = new Level( "Dungeon Crawler" );
        lvl.setVisible( true );
        lvl.startGame();
    }


    /**
     * Accessor method for the menu object: gets the menu object and returns it.
     * 
     * @return MenuScreen menu - the menuscreen that is displayed upon launch
     */
    public MenuScreen getMenu()
    {
        return menu;
    }


    /**
     * Accessor method for the lvl object: gets the lvl object and returns it.
     * 
     * @return Level lvl - the game level that the user is playing
     */
    public Level getLevel()
    {
        return lvl;
    }


    /**
     * Sorts the list of scores in descending order
     */
    public void sortScores()
    {
        Collections.sort( scores );
        Collections.reverse( scores );
    }


    /**
     * Accessor method for the scores object: gets the scores object and returns
     * it.
     * 
     * @return List<Long> scores - the list of scores the user earned
     */
    public List<Long> getScores()
    {
        return scores;
    }
}
