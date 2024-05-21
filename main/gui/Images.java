package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 * Images class which represents the manager and database for images.
 * Additionally provides a utility to draw text to the screen and load fonts.
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
public class Images
{

    
    private static BufferedImage tileSet = loadImg("main/images/Dungeon_Tileset.png");
    
    private static ImageIcon floor1 = resize(crop(tileSet, 96, 0, 16, 16), 32, 32);
    private static ImageIcon floor2 = resize(crop(tileSet, 112, 0, 16, 16 ), 32, 32);
    private static ImageIcon floor3 = resize(crop(tileSet, 128, 0, 16, 16 ), 32, 32);
    private static ImageIcon floor4 = resize(crop(tileSet, 144, 0, 16, 16 ), 32, 32);
    private static ImageIcon floor5 = resize(crop(tileSet, 96, 16, 16, 16 ), 32, 32);
    private static ImageIcon floor6 = resize(crop(tileSet, 112, 16, 16, 16 ), 32, 32);
    private static ImageIcon floor7 = resize(crop(tileSet, 128, 16, 16, 16 ), 32, 32);
    private static ImageIcon floor8 = resize(crop(tileSet, 144, 16, 16, 16 ), 32, 32);
    private static ImageIcon rightcorner = resize(crop(tileSet, 80, 64, 16, 16 ), 32, 32);
    private static ImageIcon leftcorner = resize(crop(tileSet, 0, 64, 16, 16 ), 32, 32);
    private static ImageIcon bottom1 = resize(crop(tileSet, 16, 64, 16, 16 ), 32, 32);
    private static ImageIcon bottom2 = resize(crop(tileSet, 32, 64, 16, 16 ), 32, 32);
    private static ImageIcon bottom3 = resize(crop(tileSet, 48, 64, 16, 16 ), 32, 32);
    private static ImageIcon bottom4 = resize(crop(tileSet, 64, 64, 16, 16 ), 32, 32);
    private static ImageIcon top1 = resize(crop(tileSet, 16, 0, 16, 16 ), 32, 32);
    private static ImageIcon top2 = resize(crop(tileSet, 32, 0, 16, 16 ), 32, 32);
    private static ImageIcon top3 = resize(crop(tileSet, 48, 0, 16, 16 ), 32, 32);
    private static ImageIcon top4 = resize(crop(tileSet, 64, 0, 16, 16 ), 32, 32);
    private static ImageIcon left1 = resize(crop(tileSet, 0, 0, 16, 16 ), 32, 32);
    private static ImageIcon left2 = resize(crop(tileSet, 0, 16, 16, 16 ), 32, 32);
    private static ImageIcon left3 = resize(crop(tileSet, 0, 32, 16, 16 ), 32, 32);
    private static ImageIcon left4 = resize(crop(tileSet, 0, 48, 16, 16 ), 32, 32);
    private static ImageIcon right1 = resize(crop(tileSet, 80, 0, 16, 16 ), 32, 32);
    private static ImageIcon right2 = resize(crop(tileSet, 80, 16, 16, 16 ), 32, 32);
    private static ImageIcon right3 = resize(crop(tileSet, 80, 32, 16, 16 ), 32, 32);
    private static ImageIcon right4 = resize(crop(tileSet, 80, 48, 16, 16 ), 32, 32);
    private static BufferedImage dark = crop(tileSet, 128, 112, 16, 16 );
    private static ImageIcon torch1 = resize(loadImg("main/images/torch_1.png"), 32, 32);
    private static ImageIcon torch2 = resize(loadImg("main/images/torch_2.png"), 32, 32);
    private static ImageIcon torch3 = resize(loadImg("main/images/torch_3.png"), 32, 32);
    private static ImageIcon torch4 = resize(loadImg("main/images/torch_4.png"), 32, 32);
    private static ImageIcon warrior1 = resize(loadImg("main/images/warriors/warrior_1.png"), 32, 32);
    private static ImageIcon warrior2 = resize(loadImg("main/images/warriors/warrior_2.png"), 32, 32);
    private static ImageIcon warrior3 = resize(loadImg("main/images/warriors/warrior_3.png"), 32, 32);
    private static ImageIcon warrior4 = resize(loadImg("main/images/warriors/warrior_4.png"), 32, 32);
    private static ImageIcon awarrior1 = resize(loadImg("main/images/warriors/awarrior_1.png"), 32, 32);
    private static ImageIcon awarrior2 = resize(loadImg("main/images/warriors/awarrior_2.png"), 32, 32);
    private static ImageIcon awarrior3 = resize(loadImg("main/images/warriors/awarrior_3.png"), 32, 32);
    private static ImageIcon awarrior4 = resize(loadImg("main/images/warriors/awarrior_4.png"), 32, 32);
    /*private static ImageIcon skeleton1 = resize(loadImg("main/images/skeletons/skeleton_1.png"), 32, 32);
    private static ImageIcon skeleton2 = resize(loadImg("main/images/skeletons/skeleton_2.png"), 32, 32);
    private static ImageIcon skeleton3 = resize(loadImg("main/images/skeletons/skeleton_3.png"), 32, 32);
    private static ImageIcon skeleton4 = resize(loadImg("main/images/skeletons/skeleton_4.png"), 32, 32);*/
    private static ImageIcon askeleton1 = resize(loadImg("main/images/skeletons/askeleton_1.png"), 32, 32);
    private static ImageIcon askeleton2 = resize(loadImg("main/images/skeletons/askeleton_2.png"), 32, 32);
    private static ImageIcon askeleton3 = resize(loadImg("main/images/skeletons/askeleton_3.png"), 32, 32);
    private static ImageIcon askeleton4 = resize(loadImg("main/images/skeletons/askeleton_4.png"), 32, 32);
    private static BufferedImage healthbars = loadImg("main/images/Healthbars.png");
    private static BufferedImage full = crop(healthbars, 33, 37, 80, 9);
    private static BufferedImage empty = crop(healthbars, 33, 51, 80, 9);
    private static ImageIcon button = resize(crop(loadImg("main/images/buttons.png"), 0, 16, 34, 16), 134, 50);
    private static BufferedImage scoreFrame = loadImg("main/images/ScoreFrame.png");
    
    public Images()
    { 
    }
    
    /**
     * Accessor method that gets and returns the floor images
     * 
     * @return new ImageIcon[] - a list of the different floor images
     */
    public static ImageIcon[] getFloor()
    {
        return new ImageIcon[] { floor1, floor2, floor3, floor4, floor5,
            floor6, floor7, floor8 };
    }


    /**
     * Accessor method that gets and returns the corner images
     * 
     * @return new ImageIcon[] - a list of the different corner images
     */
    public static ImageIcon[] getCorners()
    {
        return new ImageIcon[] { leftcorner, rightcorner };
    }


    /**
     * Accessor method that gets and returns the bottom wall images
     * 
     * @return new ImageIcon[] - a list of the different bottom wall images
     */
    public static ImageIcon[] getBottom()
    {
        return new ImageIcon[] { bottom1, bottom2, bottom3, bottom4 };
    }


    /**
     * Accessor method that gets and returns the top wall images
     * 
     * @return new ImageIcon[] - a list of the different top wall images
     */
    public static ImageIcon[] getTop()
    {
        return new ImageIcon[] { top1, top2, top3, top4 };
    }


    /**
     * Accessor method that gets and returns the right wall images
     * 
     * @return new ImageIcon[] - a list of the different right wall images
     */
    public static ImageIcon[] getRight()
    {
        return new ImageIcon[] { right1, right2, right3, right4 };
    }


    /**
     * Accessor method that gets and returns the left wall images
     * 
     * @return new ImageIcon[] - a list of the different left wall images
     */
    public static ImageIcon[] getLeft()
    {
        return new ImageIcon[] { left1, left2, left3, left4 };
    }


    /**
     * Accessor method that gets and returns the empty dark space image, resized
     * accordingly to the given width and height
     * 
     * @param length
     *            - length to resize the image to (accordingly to scale)
     * @param height
     *            - height to resize the image to (accordingly to scale)
     * @return ImageIcon dark - an image that represents an empty dark space
     */
    public static ImageIcon getEmpty( int length, int height )
    {
        return resize( dark, length * 32, height * 32 );
    }


    /**
     * Accessor method that gets and returns the front torch images
     * 
     * @return new ImageIcon[] - a list of the different front torch images
     */
    public static ImageIcon[] getFrontTorch()
    {
        return new ImageIcon[] { torch1, torch2, torch3, torch4 };
    }


    /**
     * Accessor method that gets and returns the warrior images
     * 
     * @return new ImageIcon[] - a list of the different warrior images
     */
    public static ImageIcon[] getWarrior()
    {
        return new ImageIcon[] { warrior1, warrior2, warrior3, warrior4 };
    }


    /**
     * Accessor method that gets and returns the attack warrior images
     * 
     * @return new ImageIcon[] - a list of the different attack warrior images
     */
    public static ImageIcon[] getAttackWarrior()
    {
        return new ImageIcon[] { awarrior1, awarrior2, awarrior3, awarrior4 };
    }


    /**
     * Accessor method that gets and returns the skeleton images
     * 
     * @return new ImageIcon[] - a list of the different skeleton images
     */
    public static ImageIcon[] getSkeleton()
    {
        return new ImageIcon[] { askeleton1, askeleton2, askeleton3,
            askeleton4 };
    }


    /**
     * Accessor method that gets and returns the different healthbar images
     * 
     * @param mult
     *            - multiplier given for resizing and cropping the healthbar
     * @return new ImageIcon[] - a list of the different healthbar images
     */
    public static ImageIcon[] getHealthBars( int mult )
    {
        return new ImageIcon[] { resize( crop( full, 8, 0, 64, 9 ),
            (int)( ( 128 * mult * 0.8 ) + 0.5 ),
            16 * mult ), resize( empty, 128 * mult, 16 * mult ) };
    }


    /**
     * Accessor method that gets and returns the button image
     * 
     * @return ImageIcon button - an image of a button
     */
    public static ImageIcon getButton()
    {
        return button;
    }



    public static ImageIcon getWhite(int length, int height)
    {
        BufferedImage img = new BufferedImage(length*32, height*32, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor( Color.white );
        g.fillRect( 0, 0, length*32, height*32 );
        return new ImageIcon(img);
    }
    public static ImageIcon getScoreFrame(int size)
    {
        return resize(scoreFrame, size*32, size*32);
    }
    
    /**
     * Combines and returns two images, the bottom image and the top image
     * 
     * @param bottom
     *            - the image that will be combined to the top but placed at the
     *            bottom
     * @param top
     *            - the image that will be combined to the bottom but placed at
     *            the top
     * @return new ImageIcon - the image of the combined top and bottom images
     */
    public static ImageIcon combine( ImageIcon bottom, ImageIcon top )
    {
        BufferedImage finalImage = new BufferedImage( bottom.getIconWidth(),
            bottom.getIconHeight(),
            BufferedImage.TYPE_INT_ARGB );
        Graphics2D g = finalImage.createGraphics();
        g.drawImage( bottom.getImage(), 0, 0, null );
        g.drawImage( top.getImage(), 0, 0, null );
        g.dispose();
        return new ImageIcon( finalImage );
    }


    /**
     * Combines and returns two images, the bottom and the top image,
     * additionally set according to the offset of the x and y values
     * 
     * @param bottom
     *            - the image that will be combined to the top but placed at the
     *            bottom
     * @param top
     *            - the image that will be combined to the bottom but placed at
     *            the top
     * @param offx
     *            - the offset of the x value of the position
     * @param offy
     *            - the offset of the y value of the position
     * @return new ImageIcon - the image of the combined top and bottom images
     *         set according to the offest of the x and y values
     */
    public static ImageIcon combine(
        ImageIcon bottom,
        ImageIcon top,
        int offx,
        int offy )
    {
        BufferedImage finalImage = new BufferedImage( bottom.getIconWidth(),
            bottom.getIconHeight(),
            BufferedImage.TYPE_INT_ARGB );
        Graphics2D g = finalImage.createGraphics();
        g.drawImage( bottom.getImage(), 0, 0, null );
        g.drawImage( top.getImage(), offx, offy, null );
        g.dispose();
        return new ImageIcon(finalImage);
    }


    /**
     * Resizes and returns a given the new width and height
     * 
     * @param img
     *            - given image to resize
     * @param newW
     *            - new given width
     * @param newH
     *            - new given height
     * @return new ImageIcon - resized image accordingly to the given width and
     *         height
     */
    public static ImageIcon resize( BufferedImage img, int newW, int newH )
    {
        Image image = img.getScaledInstance( newW, newH, Image.SCALE_SMOOTH );
        return new ImageIcon( image );
    }


    /**
     * Crops a given image accordingly to the given width and height starting at
     * the position with the given x and y values
     * 
     * @param img
     *            - given image to crop
     * @param x
     *            - given x position to crop from
     * @param y
     *            - given y position to crop from
     * @param width
     *            - given width to crop
     * @param height
     *            - given height to crop
     * @return new BufferedImage - the cropped image given the position and
     *         width and height
     */
    public static BufferedImage crop(
        BufferedImage img,
        int x,
        int y,
        int width,
        int height )
    {
        return img.getSubimage( x, y, width, height );
    }
    
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        return bimage;
    }


    /**
     * Loads and returns an image given by its path
     * 
     * @param path
     *            - given path to locate the image
     * @return new BufferedImage - the new image that is loaded from its path
     */
    public static BufferedImage loadImg( String path )
    {
        try
        {
            return ImageIO.read( new File( path ) );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Loads and returns a specific font at the given size
     * 
     * @param size
     *            the size of the font
     * @return new Font - the font that is loaded at the given size
     */
    public static Font loadFont( float size )
    {
        String path = "main/res/slkscr.ttf";
        try
        {
            return Font.createFont( Font.TRUETYPE_FONT, new File( path ) )
                .deriveFont( Font.PLAIN, size );
        }
        catch ( FontFormatException e )
        {
            e.printStackTrace();
            System.exit( 1 );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            System.exit( 1 );
        }
        return null;
    }


    /**
     * Draws a string to the screen with the given text, position, color, font,
     * and whether the placement for the position is at the text's center
     * 
     * @param g
     *            - Graphics tool
     * @param text
     *            - text to draw to the screen
     * @param xPos
     *            - x position to draw the text
     * @param yPos
     *            - y position to draw the text
     * @param center
     *            - true if the desired placement at the position is the center
     *            of the string, false if otherwise
     * @param color
     *            - the color of the text
     * @param font
     *            - the font of the text
     */
    public static void drawText(
        Graphics g,
        String text,
        int xPos,
        int yPos,
        boolean center,
        Color color,
        Font font )
    {
        g.setColor( color );
        g.setFont( font );
        int x = xPos;
        int y = yPos;
        if ( center )
        {
            FontMetrics fontMetric = g.getFontMetrics( font );
            x = xPos - fontMetric.stringWidth( text ) / 2;
            y = ( yPos - fontMetric.getHeight() / 2 ) + fontMetric.getAscent();
        }
        g.drawString( text, x, y );
    }
}
