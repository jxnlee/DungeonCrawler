package entities;

import java.awt.Point;

import gui.Images;
import gui.Room;
import gui.Top;
import gui.Level;
import main.Launcher;


/**
 * The User class is the representation of the player in which the user controls
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: 1
 */
public class User extends GameCharacter
{
    private int dx = 0;

    private int dy = 0;

    private int level;

    private int maxHealth;

    private int killCount;

    private boolean attacking;

    private int counter = 0;

    private Top myTop;

    private boolean moving;


    /**
     * Constructor: Instantiates the room object and location. Sets the stats
     * and adds usuer to the room
     * 
     * @param room
     *            - the room in which the user is in
     * @param p
     *            - the location of the user in the room
     */
    public User( Room room, Point p )
    {
        super( room, p );
        type = "user";
        images = Images.getAttackWarrior();
        attackImages = Images.getAttackWarrior();
        image = images[0];

        attacking = false;
        moving = false;

        score = 0;
        level = 1;
        attackDamage = 75;
        health = 750;
        maxHealth = 750;
        room.addEntity( this );
    }


    /**
     * Updates the state of the user accordingly to its stats and userinput
     */
    public void update()
    {
        if ( health > maxHealth )
        {
            health = maxHealth;
        }
        counter--;
        myTop.update( this );
        front = new Point( getLocation().x + dx, getLocation().y + dy );
        if ( attacking )
        {
            attack();
            counter = 5;
            attacking = false;
        }
        if ( moving )
        {
            if ( dy < 0 && ( getLocation().equals( new Point( 8, 0 ) )
                || getLocation().equals( new Point( 9, 0 ) ) ) )
            {
                addToScore( 10 * level * room.getRoomNum() );
                Room nextRoom = Launcher.getGame()
                    .getLevel()
                    .generateRoom( new Room( 15 ),
                        false,
                        room.getRoomNum() + 1 );
                room.removeEntity( this );
                this.setRoom( nextRoom );
                setLocation( new Point( getLocation().x,
                    nextRoom.getMap().getLayout()[0].length - 1 ) );
                nextRoom.addEntity( this );
            }
            else
            {
                move( front );
            }
        }
        else
        {
            idle();
        }
    }


    /**
     * removes and kills user and additional ends the game
     */
    public void die()
    {
        super.die();
        Level lvl = Launcher.getGame().getLevel();
        lvl.endGame( score );

    }


    /**
     * gets and returns the user's current health
     * 
     * @return - the user's current health
     */
    public int getHealth()
    {
        return health;
    }


    /**
     * Add's to the user's score a given number of points
     * 
     * @param points
     *            - the points to add to the score;
     */
    public void addToScore( long points )
    {
        score += points;
    }


    /**
     * The x difference when moving
     * 
     * @param dx
     *            - the amount to set the x difference to
     */
    public void setDx( int dx )
    {
        this.dx = dx;
    }


    /**
     * The difference when moving
     * 
     * @param dy
     *            - the amount to set the y difference to
     */
    public void setDy( int dy )
    {
        this.dy = dy;
    }


    /**
     * Sets the user's attack status to a given boolean value
     * 
     * @param a
     *            - the given attack status to set the user's attack status to
     */
    public void setAttacking( boolean a )
    {
        if ( counter > 0 )
        {
            return;
        }
        attacking = a;
    }


    /**
     * Returns true if the user is currently attacking, false otherwise
     * 
     * @return true if the user is currently attacking, false otherwise
     */
    public boolean isAttacking()
    {
        return attacking;
    }


    /**
     * Sets the user's moving status to a given boolean value
     * 
     * @param m
     *            - the given value to set the user's moving status to
     */
    public void setMoving( boolean m )
    {
        moving = m;
    }


    /**
     * returns true if the user is moving, false if otherwise
     * 
     * @return true if the user is moving, false if otherwise
     */
    public boolean isMoving()
    {
        return moving;
    }


    /**
     * Increases the user's statistics for each call of the method
     */
    public void levelUp()
    {
        level++;
        attackDamage += ( level * 25 );
        maxHealth += level * 10;
        health = maxHealth;
    }


    /**
     * Gets and returns the user's max health
     * 
     * @return maxHealth - the user's maximum health
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }


    /**
     * adds to the user's score for each successful kill of a character, the
     * increase in the kill count also allows the user to level up
     * 
     * @param other
     *            - the character in which the user successfully killed
     */
    @Override
    protected void successKill( GameCharacter other )
    {
        killCount++;
        if ( killCount > level * 3 )
        {
            levelUp();
            killCount = 0;
        }
        if ( other instanceof Enemy )
        {
            addToScore( ( (Enemy)other ).takeScore() );
        }

    }


    /**
     * returns whether the user is against an enemy type entity
     * 
     * @return true if facing an enemy, false if otherwise
     */
    @Override
    protected boolean isAgainst( Entity other )
    {
        return other instanceof Enemy;
    }


    /**
     * Sets the top that is associated with the User
     * 
     * @param newTop
     *            - the new Top in which the user's top is set to
     */
    public void setTop( Top newTop )
    {
        myTop = newTop;
    }
}
