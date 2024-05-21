package gui;

import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * The Map class uses a 2D array to represent the level map that is generated in
 * each room.
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
public class Map
{
    private Tile[][] layout;

    private Tile leftExitTile;

    private Tile rightExitTile;

    private Tile leftEntranceTile;

    private Tile rightEntranceTile;


    /**
     * Constructor: generates the layout of the room given the size of the map
     * and the torchDistance
     * 
     * @param size
     *            - the given size of the map
     * @param torchDistance
     *            - the specified distance of the torches
     */
    public Map( int size, int torchDistance )
    {
        makeLayout( size, torchDistance );
    }


    /**
     * Generates the layout of the room by setting each cell in 2D array layout
     * datastructure to a Tile
     * 
     * @param size
     *            - the given size of the map
     * @param torchDistance
     *            - the specified distance of the torches
     */
    private void makeLayout( int size, int torchDistance )
    {
        // Adjust size
        while ( size % torchDistance != 0 )
        {
            size++;
        }
        if ( size <= torchDistance )
        {
            size = torchDistance + 1;
        }
        size += 3;

        // Making layout
        layout = new Tile[size][size];

        // Making floor
        ImageIcon[] floor = Images.getFloor();
        for ( int col = 1; col < size - 1; col++ )
        {
            for ( int r = 1; r < size - 1; r++ )
            {
                layout[col][r] = new Tile( true,
                    new JLabel( floor[(int)( Math.random() * 8 )] ),
                    "floor" );
            }
        }
        leftExitTile = new Tile( true,
            new JLabel( floor[(int)Math.random() * 8] ),
            "floor" );
        rightExitTile = new Tile( true,
            new JLabel( floor[(int)Math.random() * 8] ),
            "floor" );
        leftEntranceTile = new Tile( true,
            new JLabel( floor[(int)Math.random() * 8] ),
            "floor" );
        rightEntranceTile = new Tile( true,
            new JLabel( floor[(int)Math.random() * 8] ),
            "floor" );
        layout[8][0] = leftExitTile;
        layout[9][0] = rightExitTile;
        layout[8][size - 1] = leftEntranceTile;
        layout[9][size - 1] = rightEntranceTile;
        // Side Walls
        ImageIcon[] rwall = Images.getRight();
        ImageIcon[] lwall = Images.getLeft();
        for ( int r = 0; r < size - 1; r++ )
        {
            layout[0][r] = new Tile( false,
                new JLabel( lwall[(int)( Math.random() * 4 )] ),
                "leftwall" );
            layout[size - 1][r] = new Tile( false,
                new JLabel( rwall[(int)( Math.random() * 4 )] ),
                "rightwall" );
        }

        // Corners
        ImageIcon[] corners = Images.getCorners();
        layout[0][size - 1] = new Tile( false,
            new JLabel( corners[0] ),
            "cornerwall" );
        layout[size - 1][size - 1] = new Tile( false,
            new JLabel( corners[1] ),
            "cornerwall" );

        // Top and bottom walls minus torches
        ImageIcon[] top = Images.getTop();
        ImageIcon[] bottom = Images.getBottom();
        for ( int c = 1; c < size - 1; c++ )
        {
            if ( c == 8 || c == 9 )
            {
                continue;
            }
            if ( ( c - 1 ) % torchDistance != 0 )
            {
                layout[c][0] = new Tile( false,
                    new JLabel( top[(int)( Math.random() * 4 )] ),
                    "topwall" );
            }
            layout[c][size - 1] = new Tile( false,
                new JLabel( bottom[(int)( Math.random() * 4 )] ),
                "bottomwall" );
        }

        // Making torches on top walls
        int wallSize = size - 2;
        Point[] torches = new Point[wallSize / torchDistance + 1];
        int num = 0;
        for ( int i = 0; i <= wallSize; i += torchDistance )
        {
            torches[num] = new Point( i + 1, 0 );
            num++;
        }
        TorchThread torch = new TorchThread( layout, torches );
        torch.start();
    }


    /**
     * Accessor method for the 2D array layout datastructure: gets the layout
     * datastructure before returning it.
     * 
     * @return Tile[][] layout - 2D array that represents the layout of the room
     *         in which each cell contains a Tile
     */
    public Tile[][] getLayout()
    {
        return layout;
    }
}
