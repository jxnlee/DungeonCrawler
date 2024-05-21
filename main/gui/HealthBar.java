package gui;

import javax.swing.ImageIcon;

import entities.User;

/**
 *  Represents the character healthbar as an imageicon overlayed over another one.
 *
 *  @author  Raeed Azom, Jeffrey Lee
 *  @version May 26, 2020
 *  @author  Period: 1
 *  @author  Assignment: APCS Final Project
 *
 *  @author  Sources: none
 */
public class HealthBar
{
    private ImageIcon mainImage;
    private ImageIcon myImage;
    private final ImageIcon myEmpty;
    private int health;
    private int maxHealth;
    private final int myOffset;
    
    /**
     * @param image image of full healthbar
     * @param empty image of empty healthbar
     * @param user user this bar represents
     * @param offset offset of full bar over empty bar
     */
    public HealthBar(ImageIcon image, ImageIcon empty, User user, int offset)
    {
        mainImage = image;
        myImage = mainImage;
        try
        {
            maxHealth = user.getMaxHealth();
            health = user.getHealth();
        }
        catch(NullPointerException e)
        {
            maxHealth = 1;
            health = 1;
        }
        myOffset = offset;
        myEmpty = empty;
    }
    
    /**
     * updates this healthbar to represent new health
     * @param newHealth new health to represent
     * @return whether or not anything changed
     */
    public boolean update(int newHealth)
    {
        if (health != newHealth)
        {
            health = newHealth;
            int newSize = (int)( ((double)(health)/maxHealth)*mainImage.getIconWidth() );
            if (newSize < 0)
            {
                newSize = 1;
            }
            myImage = new ImageIcon(Images.crop( Images.toBufferedImage(mainImage.getImage()), 0,0, newSize, mainImage.getIconHeight() ));
            return true;
        }
        return false;
    }
    
    /**
     * getter method for health
     * @return health
     */
    public String getHealth()
    {
        return health + " / " + maxHealth;
    }
    
    /**
     * getter method for healthbar 
     * @return healthbar image
     */
    public ImageIcon[] getBar()
    {
        return new ImageIcon[] {myImage, myEmpty};
    }
    
    /**
     * getter method for offset used
     * @return offset
     */
    public int getOffset()
    {
        return myOffset;
    }
    
    /**
     * getter method for maxhealth represented by bar
     * @return max health represented by bar
     */
    public int getMaxHealth()
    {
        return maxHealth;
    }

    /**
     * getter method for full bar image
     * @return full bar image
     */
    public ImageIcon getFull()
    {
        return mainImage;
    }

    /**
     * getter method for empty bar image
     * @return empty bar image
     */
    public ImageIcon getEmpty()
    {
        return myEmpty;
    }
}
