package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import entities.Enemy;
import entities.Entity;
import entities.GameCharacter;
import entities.User;


/**
 * Room class which represents the room in which the enemies and user are
 * currently present in. The enemies and the user communicate with the room
 * class (and vice versa) in order to enable movement and other actions.
 *
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
@SuppressWarnings("serial")
public class Room extends JPanel
{
    private Tile[][] layout;

    private List<Entity> entities;

    private Map map;

    private int roomNum;


    /**
     * Sets up a room with a generated map accordingly to the given size
     * 
     * @param size
     */
    public Room( int size )
    {
        entities = new ArrayList<Entity>();
        map = new Map( size, 3 );
        layout = map.getLayout();
        setLayout( new GridBagLayout() );
        GridBagConstraints con = new GridBagConstraints();
        con.fill = GridBagConstraints.BOTH;
        for ( int c = 0; c < layout.length; c++ )
        {
            con.gridx = c;
            for ( int r = 0; r < layout[c].length; r++ )
            {
                con.gridy = r;
                add( layout[c][r].getLabel(), con );
            }
        }
    }


    /**
     * Adds an entity to the room and allow it to be displayed to the screen
     * while also adding it to the ArrayList of entities
     * 
     * @param entity
     *            - given Entity to add to the room
     */
    public void addEntity( Entity entity )
    {
        Point p = entity.getLocation();
        if ( !layout[p.x][p.y].isPassable() )
        {
            p = findEmpty( p );
        }
        layout[p.x][p.y].setPassable( false );
        layout[p.x][p.y].setType( entity.getType() + "entity" );
        ImageIcon current = layout[p.x][p.y].getImage();
        entity.setCurrent( current );
        layout[p.x][p.y].getLabel()
            .setIcon( Images.combine( current, entity.getImg() ) );
        entities.add( entity );
    }


    /**
     * Moves a given entity from its original location to a specified location
     * 
     * @param entity
     *            - the entity that is moving
     * @param p
     *            - the specified location that the entity needs to move to
     */
    public void move( Entity entity, Point p )
    {
        if ( entity.getLocation().distance( p ) > 1.5 || p.y < 0
            || p.x >= layout.length || p.y >= layout[0].length
            || !layout[p.x][p.y].isPassable() )
        {
            return;
        }

        Tile newTile = layout[p.x][p.y];
        Tile current = layout[entity.getLocation().x][entity.getLocation().y];
        newTile.setPassable( false );
        newTile.setType( entity.getType() + "entity" );
        current.setPassable( true );
        current.setType( "floor" );
        current.getLabel().setIcon( entity.getCurrent() );
        entity.setCurrent( newTile.getImage() );
        newTile.getLabel()
            .setIcon( Images.combine( newTile.getImage(), entity.getImg() ) );
        entity.setLocation( p );
    }


    /**
     * Retrieves and returns an entity, if it exists, that is located above a
     * given location, otherwise, return null
     * 
     * @param p
     *            - the location that is given to identify any existing entity
     *            in the location above
     * @return Entity - the entity that is located above the location, null if
     *         it doesn't exist
     */
    public Entity getUpEntity( Point p )
    {
        if ( layout[p.x][p.y - 1].getType().contains( "entity" ) )
        {
            return entityAt( new Point( p.x, p.y - 1 ) );
        }
        return null;
    }


    /**
     * Retrieves and returns an entity, if it exists, that is located below a
     * given location, otherwise, return null
     * 
     * @param p
     *            - the location that is given to identify any existing entity
     *            in the location below
     * @return Entity - the entity that is located below the location, null if
     *         it doesn't exist
     */
    public Entity getDownEntity( Point p )
    {
        if ( layout[p.x][p.y + 1].getType().contains( "entity" ) )
        {
            return entityAt( new Point( p.x, p.y + 1 ) );
        }
        return null;
    }


    /**
     * Retrieves and returns an entity, if it exists, that is located to the
     * left of a given location, otherwise, return null
     * 
     * @param p
     *            - the location that is given to identify any existing entity
     *            in the location to the left
     * @return Entity - the entity that is located to the left of the location,
     *         null if it doesn't exist
     */
    public Entity getLeftEntity( Point p )
    {
        if ( layout[p.x - 1][p.y].getType().contains( "entity" ) )
        {
            return entityAt( new Point( p.x - 1, p.y ) );
        }
        return null;
    }


    /**
     * Retrieves and returns an entity, if it exists, that is located to the
     * right of a given location, otherwise, return null
     * 
     * @param p
     *            - the location that is given to identify any existing entity
     *            in the location to the right
     * @return Entity - the entity that is located to the right of the location,
     *         null if it doesn't exist
     */
    public Entity getRightEntity( Point p )
    {
        if ( layout[p.x + 1][p.y].getType().contains( "entity" ) )
        {
            return entityAt( new Point( p.x + 1, p.y ) );
        }
        return null;
    }


    /**
     * "Kills" a given entity by removing it from the room and setting its alive
     * status to false (If it is a character)
     * 
     * @param entity
     *            - given entity to kill
     */
    public void kill( Entity entity )
    {
        if ( entity instanceof GameCharacter )
        {
            ( (GameCharacter)entity ).setAliveStatus( false );
        }
        removeEntity( entity );

    }


    /**
     * Removes a given entity from the room and the ArrayList of entities
     * 
     * @param entity
     *            - given entity to remove
     */
    public void removeEntity( Entity entity )
    {
        entities.remove( entity );
        layout[entity.getLocation().x][entity.getLocation().y]
            .setPassable( true );
        layout[entity.getLocation().x][entity.getLocation().y]
            .setType( "floor" );
        layout[entity.getLocation().x][entity.getLocation().y].getLabel()
            .setIcon( entity.getCurrent() );
    }


    /**
     * Redraws a given entity to an alternate image
     * 
     * @param entity
     *            - the given entity to redraw
     * @param img
     *            - the alternate image that will replace the entity's current
     *            image
     */
    public void redraw( Entity entity, ImageIcon img )
    {
        int locX = entity.getLocation().x;
        int locY = entity.getLocation().y;
        layout[locX][locY].getLabel()
            .setIcon( Images.combine( layout[locX][locY].getImage(), img ) );
    }


    /**
     * Redraws a given entity to an alternate image accordingly to the x and y
     * offset
     * 
     * @param entity
     *            - the given entity to redraw
     * @param img
     *            - the alternate image that will replace the entity's current
     *            image
     * @param offx
     *            - x position offset
     * @param offy
     *            - y position offset
     */
    public void redraw( Entity entity, ImageIcon img, int offx, int offy )
    {
        int locX = entity.getLocation().x;
        int locY = entity.getLocation().y;
        layout[locX][locY].getLabel().setIcon( Images.combine( layout[locX][locY].getImage(), img, offx, offy ));
    }


    /**
     * Locates, retrieves, and returns the user in the room if it exists
     * 
     * @return User - the user object is the identified user in the room,
     *         returns null if there is no existing user
     */
    public User getUser()
    {
        Entity entity;
        for ( int i = 0; i < entities.size(); i++ )
        {
            entity = entities.get( i );
            if ( entity.getType().equals( "user" ) )
            {
                return (User)entity;
            }
        }
        return null;
    }


    /**
     * "kills" all of the existing enemies in the room
     */
    public void clearEnemies()
    {
        int size = entities.size();
        Entity entity;
        for ( int i = size - 1; i > 0; i-- )
        {
            entity = entities.get( i );
            if ( entity instanceof Enemy )
            {
                kill( entity );
            }
        }
    }


    /**
     * Identifies and returns an Entity at a specified location, if it exists
     * 
     * @param location
     *            - the specified location to search for an Entity
     * @return Entity - the entity object that is located at the specified
     *         location, null if it does not exist
     */
    public Entity entityAt( Point location )
    {
        Entity entity;
        for ( int i = 0; i < entities.size(); i++ )
        {
            entity = entities.get( i );
            if ( entity.getLocation().equals( location ) )
            {
                return entity;
            }
        }
        return null;
    }


    /**
     * Accessor method for the rooom's room number: gets the room number before
     * returning it.
     * 
     * @return int roomNum - the count of the room in which the user is
     *         currently present in
     */
    public int getRoomNum()
    {
        return roomNum;
    }


    /**
     * Sets the number of the room number to a given integer
     * 
     * @param roomNum
     *            - the new room number to which roomNum will be assigned to.
     */
    public void setRoomNum( int roomNum )
    {
        this.roomNum = roomNum;
    }


    /**
     * Accessor method for the length of the layout of the room: gets the layout
     * length before returning it.
     * 
     * @return
     */
    public int getLength()
    {
        return layout.length;
    }


    /**
     * Accessor method for the map in which the room uses to generate the
     * layout: gets the map before returning it.
     * 
     * @return Map map - the map object that represents and generates the layout
     *         of the room.
     */
    public Map getMap()
    {
        return map;
    }


    /**
     * Searches for an empty / passable tile starting from a given location
     * before searching around that location
     * 
     * @param p
     *            - the starting point to search for an empty location
     * @return Point - a point object that represents a empty point in the room
     */
    public Point findEmpty( Point p )
    {
        int distance = 1;
        while ( true )
        {
            for ( int i = -distance; i <= distance; i++ )
            {
                try
                {
                    if ( layout[p.x + i][p.y + distance].isPassable() )
                    {

                        return new Point( p.x + i, p.y + distance );
                    }
                }
                catch ( Exception e )
                {

                }
                try
                {
                    if ( layout[p.x + i][p.y - distance].isPassable() )
                    {

                        return new Point( p.x + i, p.y - distance );
                    }
                }
                catch ( Exception e )
                {

                }
            }
            for ( int i = -( distance - 1 ); i <= distance - 1; i++ )
            {
                try
                {
                    if ( layout[p.x + distance][p.y + i].isPassable() )
                    {
                        return new Point( p.x + distance, p.y + i );
                    }
                }
                catch ( Exception e )
                {

                }
                try
                {
                    if ( layout[p.x - distance][p.y + i].isPassable() )
                    {
                        return new Point( p.x + distance, p.y + i );
                    }
                }
                catch ( Exception e )
                {

                }
            }
            distance++;
        }
    }
}
