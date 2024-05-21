package entities;

import gui.Room;

import java.awt.Point;
import javax.swing.ImageIcon;


/**
 * The abstract entity class represents any object on a tile that is not a tile
 * or a torch.
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
public abstract class Entity extends Thread
{
    protected Point location;

    protected String type;

    protected ImageIcon image;

    protected ImageIcon current;

    protected Room room;


    /**
     * Constructor: Instantiates the room object and location
     * 
     * @param r
     *            - the room in which the entity is in
     * @param p
     *            - the location of the entity in the room
     */
    public Entity( Room r, Point p )
    {
        room = r;
        location = p;
    }


    /**
     * abstract run method from the Thread class
     */
    @Override
    public abstract void run();


    /**
     * Gets and returns the entity's current location
     * 
     * @return the entity's current location
     */
    public Point getLocation()
    {
        return location;
    }


    /**
     * Sets the entities location to a given location
     * 
     * @param p
     *            - the given location in which the entities location is to be
     *            set to
     */
    public void setLocation( Point p )
    {
        location = p;
    }


    /**
     * Gets and returns a string representation of the type of the entity
     * 
     * @return a string representation of the type of the entity
     */
    public String getType()
    {
        return type;
    }


    /**
     * Gets and returns the image of the entity
     * 
     * @return the image of the entity
     */
    public ImageIcon getImg()
    {
        return image;
    }


    /**
     * Sets the current image tile the Entity is on to a given image
     * 
     * @param icon
     *            - the new Image in which the current image tile the Entity is
     *            on will be set to
     */
    public void setCurrent( ImageIcon icon )
    {
        current = icon;
    }


    /**
     * Accessor method for the image of the current tile in which the Entity is
     * on: gets and returns the image of the tile the entity is on
     * 
     * @return ImageIcon current - the image of the current tile in which the
     *         entity is on
     */
    public ImageIcon getCurrent()
    {
        return current;
    }


    /**
     * Accessor method for the room object: gets the room object before
     * returning it
     * 
     * @return Room room - the current room in which the entity is in
     */
    public Room getRoom()
    {
        return room;
    }


    /**
     * Sets the current room in which the Entity is in to a given room whilst
     * resetting its location
     * 
     * @param room
     *            - the new given room in which entity will move to
     */
    public void setRoom( Room room )
    {
        this.room = room;
        setLocation( new Point( getLocation().x, 0 ) );
    }


    /**
     * Moves the entity to a designated location
     * 
     * @param p
     *            - designated location to move to
     */
    protected void move( Point p )
    {
        room.move( this, p );
    }

}
