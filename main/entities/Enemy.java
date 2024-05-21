package entities;

import java.awt.Point;

import gui.Images;
import gui.Room;


/**
 * The Enemy class represents an enemy that tries to attack the user
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
public class Enemy extends GameCharacter
{
    private int randDecision;

    private int difficulty;


    /**
     * Constructor: Instantiates the room object and location. Sets the stats
     * and difficulty and adds the enemy to the room
     * 
     * @param room
     *            - the room in which the enemy is in
     * @param p
     *            - the location in which the enemy is at
     */
    public Enemy( Room room, Point p )
    {
        super( room, p );
        type = "enemy";
        difficulty = 10 * room.getRoomNum();
        attackDamage = 5 * difficulty;
        health = 15 * difficulty;
        attacking = true;
        images = Images.getSkeleton();
        attackImages = images;
        image = images[0];
        score = 10 * difficulty;
        randDecision = (int)( Math.random() * 100 );
        room.addEntity( this );
    }


    /**
     * Updates the state of the enemy accordingly to a random number generator
     * and its set difficulty
     */
    public void update()
    {
        if ( !userExists() )
        {
            isAlive = false;
            return;
        }
        else if ( randDecision < difficulty )
        {
            moveToPlayer();
            if ( randDecision % ( (int)( Math.random() * 10 ) + 1 ) == 0 )
            {
                attack();
            }
        }
        else
        {
            idle();
        }
        randDecision = (int)( Math.random() * 250 );
    }


    /**
     * The score of the enemy is returned and set to 0
     * 
     * @return the enemy's score
     */
    public long takeScore()
    {
        long scoreTaken = score;
        score = 0;
        return scoreTaken;
    }


    /**
     * The enemy's AI that allows them to move to the tile closest to the user
     */
    public void moveToPlayer()
    {
        User user = room.getUser();
        Point userLocation = user.getLocation();
        int myX = getLocation().x;
        int myY = getLocation().y;
        int userX = userLocation.x;
        int userY = userLocation.y;
        if ( Math.abs( myX - userX ) > Math.abs( myY - userY ) )
        {
            if ( myX - userX > 0 )
            {
                move( new Point( myX - 1, myY ) );
                faceLeft();
            }
            else
            {
                move( new Point( myX + 1, myY ) );
                faceRight();
            }
        }
        else
        {
            if ( myY - userY > 0 )
            {
                move( new Point( myX, myY - 1 ) );
                faceDown();
            }
            else
            {
                move( new Point( myX, myY + 1 ) );
                faceUp();
            }
        }
    }


    /**
     * Called when the enemy successfully kills another charaacter, this method
     * is not used
     * 
     * @param other
     *            - the GameCharacter in which the enemy kills
     */
    @Override
    protected void successKill( GameCharacter other )
    {
    }


    /**
     * returns whether the enemy is against the user
     * 
     * @param other
     *            - the other entity to identify whether it is a user
     * @return true if the other entity is the User, false if otherwise
     */
    @Override
    protected boolean isAgainst( Entity other )
    {
        return other instanceof User;
    }


    /**
     * Sets the difficulty of the enemy to a given difficulty
     * 
     * @param difficulty
     *            - the difficulty that the enemy is to be set to
     */
    public void setDifficulty( int difficulty )
    {
        this.difficulty = difficulty;
    }


    /**
     * Returns true if the user exists in the room, false if otherwise
     * 
     * @return true if the user exists in the room, false if otherwise
     */
    private boolean userExists()
    {
        return room.getUser() != null;
    }

}
