package gui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import entities.Enemy;
import entities.User;
import main.Launcher;


/**
 * Level class which represents the level in which the user will be playing on.
 * The level contains all the necessary processes, objects, and functions to
 * start, play, and end the game
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
@SuppressWarnings("serial")
public class Level extends JFrame implements KeyListener
{
    private Room activeRoom;

    private Room previousRoom;

    private User user;

    private Container pane;

    private GridBagConstraints constraints;

    private long userLevelScore;

    private Top top;


    /**
     * Constructor: sets up the level to prepare for the user to play. prepares
     * the key listener and initializes the user score
     * 
     * @param text
     */
    public Level( String text )
    {
        super( text );
        addKeyListener( this );
        userLevelScore = 0;
        setup();
    }


    /**
     * Sets up and prepares the level to be displayed
     */
    public void setup()
    {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        pane = getContentPane();
        pane.setLayout( new GridBagLayout() );
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
    }


    /**
     * Generates and prepares a given room (additionally with enemies), further
     * specified by its number and whether it is the first room. Additionally
     * removes the previously generated room (if such a room exists)
     * 
     * @param room
     *            - the Room object that will be generated
     * @param isFirst
     *            - true if the room is the first room, false if otherwise
     * @param roomNum
     *            - the specified room number
     * @return Room room - the newly generated room
     */
    public Room generateRoom( Room room, boolean isFirst, int roomNum )
    {
        if ( !isFirst )
        {
            previousRoom = activeRoom;
            closeRoom( previousRoom );
        }
        constraints.gridx = 0;
        constraints.gridy = 1;
        room.setRoomNum( roomNum );
        pane.add( room, constraints );
        activeRoom = room;
        for ( int i = 0; i < Math.random() * 10 + 1; i++ )
        {
            ( new Enemy( room,
                new Point( (int)( Math.random() * 13 ) + 1,
                    (int)( Math.random() * 13 ) + 1 ) ) ).start();
        }
        constraints.gridx = 0;
        constraints.gridy = 0;
        //top = new Top(room.getLength());
        //user.setTop(top);
        //top.setRoomNum( roomNum );
        //pane.add( top, constraints );
        return room;
    }


    /**
     * Starts the game by generating a room and adding the user to the room
     */
    public void startGame()
    {
        Room newRoom = new Room(15);
        user = new User(newRoom, new Point(8, newRoom.getMap().getLayout()[0].length - 1));
        user.start();
        generateRoom(newRoom, true, 1);
        top = new Top(activeRoom.getLength());
        user.setTop( top );
        pane.add( top, constraints );
        pack();
        setMinimumSize(getSize());
        setSize(getSize().width+30, getSize().height+30);
        top.update(user);
    }


    /**
     * Ends the game by closing all of the rooms and taking the score to store
     * into the list of scores in the Game class
     * 
     * @param userScore
     *            - the given score that the user earns
     */
    public void endGame( long userScore )
    {
        if ( previousRoom != null )
        {
            closeRoom( previousRoom );
        }
        closeRoom( activeRoom );
        userLevelScore = userScore;
        Launcher.getGame().endGame();
    }


    /**
     * Clears all of the enemies in the given room before removing the room
     * 
     * @param room
     *            - the room to be removed
     */
    public void closeRoom( Room room )
    {
        room.clearEnemies();
        pane.remove( room );
    }


    /**
     * Accesor method for the user's score: gets the userLevelScore by returning
     * it
     * 
     * @return long userLevelScore - the user's saved score
     */
    public long getUserLevelScore()
    {
        return userLevelScore;
    }


    /**
     * Method is not used in our program, only specified and declared
     * 
     * @param e
     *            - inputed key event
     */
    @Override
    public void keyTyped( KeyEvent e )
    {
    }


    /**
     * Receives user input from the keyboard and has the User game character
     * respond accordingly to the key event
     * 
     * @param e
     *            - inputed key event
     */
    @Override
    public void keyPressed( KeyEvent e )
    {
        int key = e.getKeyCode();
        switch ( key )
        {
            case KeyEvent.VK_UP:
                user.setMoving( true );
                user.setDy( -1 );
                user.faceUp();
                break;
            case KeyEvent.VK_DOWN:
                user.setMoving( true );
                user.setDy( 1 );
                user.faceDown();
                break;
            case KeyEvent.VK_RIGHT:
                user.setMoving( true );
                user.setDx( 1 );
                user.faceRight();
                break;
            case KeyEvent.VK_LEFT:
                user.setMoving( true );
                user.setDx( -1 );
                user.faceLeft();
                break;
            case KeyEvent.VK_Q:
                break;
            case KeyEvent.VK_O:
                Enemy ene = new Enemy( activeRoom, new Point( 1, 1 ) );
                ene.start();
                break;
            default:
                break;
        }

    }


    /**
     * Receives user input from the keyboard and has the User game character
     * respond accordingly to the key event
     * 
     * @param e
     *            - inputed key event
     */
    @Override
    public void keyReleased( KeyEvent e )
    {
        int key = e.getKeyCode();
        switch ( key )
        {
            case KeyEvent.VK_UP:
                user.setMoving( false );
                user.setDy( 0 );
                break;
            case KeyEvent.VK_DOWN:
                user.setMoving( false );
                user.setDy( 0 );
                break;
            case KeyEvent.VK_RIGHT:
                user.setMoving( false );
                user.setDx( 0 );
                break;
            case KeyEvent.VK_LEFT:
                user.setMoving( false );
                user.setDx( 0 );
                break;
            case KeyEvent.VK_Q:
                user.setAttacking( true );
                break;
            default:
                break;
        }

    }
}
