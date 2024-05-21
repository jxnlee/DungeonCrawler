package entities;

import java.awt.Point;

import javax.swing.ImageIcon;

import gui.Room;


/**
 * An abstract class Game Character, which represents a character (an animate
 * entity)
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
public abstract class GameCharacter extends Entity
{
    protected int health;

    protected int attackDamage;

    protected boolean isAlive;

    protected int iconNum;

    protected long score;

    protected Point front;

    protected ImageIcon[] images;

    protected ImageIcon[] attackImages;


    protected enum Directions {
        up, down, left, right
    };

    protected Directions facing;

    protected boolean attacking;


    /**
     * Constructor: Instantiates the room object and location. Additionally sets
     * its alive status
     * 
     * @param room
     *            - the room in which the character is in
     * @param p
     *            - the location of the character in the room
     */
    public GameCharacter( Room room, Point p )
    {
        super( room, p );
        iconNum = (int)( Math.random() * 4 ) - 1;
        isAlive = true;
    }


    /**
     * gets and returns the character's health
     * 
     * @return the character's health
     */
    protected int getHealth()
    {
        return health;
    }


    /**
     * Sets the character's health to a given integer
     * 
     * @param health
     *            - the new health value to set the Character's health to
     */
    public void setHealth( int health )
    {
        this.health = health;
    }


    /**
     * gets and returns the character's score
     * 
     * @return the character's score
     */
    public long getScore()
    {
        return score;
    }


    /**
     * moves the character to a given location before changing it's image status
     * 
     * @param p
     *            - the location in which the character moves to
     */
    protected void move( Point p )
    {
        room.move( this, p );
        idle();
    }


    /**
     * Puts the thread to sleep for a given duration
     * 
     * @param duration
     *            - the duration for the thread to sleep
     */
    public void charSleep( int duration )
    {
        try
        {
            sleep( duration );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
    }


    /**
     * Attacks a character in front of this character
     */
    protected void attack()
    {
        Entity frontEnemy;
        int offx = 0;
        int offy = 0;
        switch ( facing )
        {
            /*
             * case up: frontEnemy = room.getUpEntity( location ); offy = 10;
             * break; case down: frontEnemy = room.getDownEntity( location );
             * offy = -10; break;
             */
            case right:
                frontEnemy = room.getRightEntity( location );
                offx = 10;
                break;
            case left:
                frontEnemy = room.getLeftEntity( location );
                offx = -10;
                break;
            default:
                frontEnemy = null;
        }
        if ( isAgainst( frontEnemy ) )
        {
            GameCharacter enemy = (GameCharacter)frontEnemy;
            iconNum++;
            if ( iconNum == 4 )
            {
                iconNum = 0;
            }
            // System.out.println("wokring..." + facing);
            room.redraw( this, images[iconNum], offx, offy );
            enemy.setHealth( enemy.getHealth() - attackDamage );
            if ( enemy.health <= 0 )
            {
                successKill( enemy );
            }
        }
    }


    /**
     * Checks whether a character is against an appropriate character type
     * 
     * @param other
     *            - the given entity in which the character will identify
     * @return true if the other entity is a valid entity to attack
     */
    protected abstract boolean isAgainst( Entity other );


    /**
     * Called when a character successfully kills another character
     * 
     * @param other
     *            - the character that is killed
     */
    protected abstract void successKill( GameCharacter other );


    /**
     * Sets the current alive status to a given boolean value
     * 
     * @param isAlive
     *            - the given value to set the character's alive status to
     */
    public void setAliveStatus( boolean isAlive )
    {
        this.isAlive = isAlive;
    }


    /**
     * calls when the character is killed. this character will be removed when
     * it dies
     */
    protected void die()
    {
        room.kill( this );
    }


    /**
     * Changes the characters image so that, upon being called sequentially,
     * will display the character to be idling
     */
    protected void idle()
    {
        iconNum++;
        if ( iconNum == 4 )
        {
            iconNum = 0;
        }
        if ( attacking )
        {
            room.redraw( this, attackImages[iconNum] );
            if ( this instanceof User )
            {
                System.out.println( "attacking" );
            }
        }
        else
        {
            room.redraw( this, images[iconNum] );
        }
    }


    /**
     * Sets the direction that the character is facing to up
     */
    public void faceUp()
    {
        facing = Directions.up;
    }


    /**
     * Sets the direction that the character is facing to down
     */
    public void faceDown()
    {
        facing = Directions.down;
    }


    /**
     * Sets the direction that the character is facing to right
     */
    public void faceRight()
    {
        facing = Directions.right;
    }


    /**
     * Sets the direction that the character is facing to left
     */
    public void faceLeft()
    {
        facing = Directions.left;
    }


    /**
     * The Thread that is started to run the game loop for each character
     */
    @Override
    public void run()
    {
        while ( isAlive )
        {
            charSleep( 200 );
            if ( health <= 0 )
            {
                isAlive = false;
                System.out.println( getType() + " is dead " );
            }
            update();
        }
        die();
    }


    /**
     * Called in order to update the status of the character
     */
    protected abstract void update();
}
