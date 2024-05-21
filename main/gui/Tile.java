package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *  represents a tile within the map
 *
 *  @author  Raeed Azom & Jeffery Lee
 *  @version May 26, 2020
 *  @author  Period: 1
 *  @author  Assignment: APCS Final Project
 *
 *  @author  Sources: none
 */
public class Tile
{
    private boolean passable;
    private final JLabel myLabel;
    private final ImageIcon image;
    private String myType;
    
    /**
     * @param pass is passable
     * @param label label used
     * @param type type of tile
     */
    public Tile(boolean pass, JLabel label, String type)
    {
        passable = pass;
        myLabel = label;
        image = (ImageIcon)label.getIcon();
        myType = type;
    }
    
    /**
     * checks if it is passable 
     * @return returns ispassable
     */
    public boolean isPassable()
    {
        return passable;
    }
    /**
     * sets passablility
     * @param p new passablity
     */
    public void setPassable(boolean p)
    {
        passable = p;
    }
    /**
     * returns label for this class
     * @return label for this class
     */
    public JLabel getLabel()
    {
        return myLabel;
    }
    /**
     * returns type of tile
     * @return tile type
     */
    public String getType()
    {
        return myType;
    }
    /**
     * sets type of tile
     * @param s new type
     */
    public void setType(String s)
    {
        myType = s;
    }
    /**
     * returns image for this tile 
     * @return tile image
     */
    public ImageIcon getImage()
    {
        return image;
    }
    
    /**
     * returns string representation
     * @return string representation
     */
    public String toString()
    {
        return getType();
    }
}
