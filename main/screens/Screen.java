package screens;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gui.Images;


/**
 * Abstract class to represent a screen to display that can contain a background
 * image, buttons, and text.
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
@SuppressWarnings("serial")
public abstract class Screen extends JFrame
{
    protected ImageIcon background;


    /**
     * Constructor: sets the screen to exit upon closing the screen
     */
    public Screen()
    {
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }


    /**
     * Method that creates and returns a button given the text in which it
     * contains, the image of the button, it's center x and y position, it's
     * width and height, and the size of the font of the text
     * 
     * @param text
     *            - the text in which the button will contain
     * @param buttonImg
     *            - the image of the button
     * @param xPos
     *            - the center x position of the button
     * @param yPos
     *            - the center y position of the button
     * @param width
     *            - the width of the button
     * @param height
     *            - the height of the button
     * @param fontSize
     *            - the size of the font of the text that is contained in the
     *            button
     * @return JButton button consisting of the given text, image, x and y
     *         position, width, height, and font size
     */
    protected JButton makeButton(
        String text,
        ImageIcon buttonImg,
        int xPos,
        int yPos,
        int width,
        int height,
        int fontSize )
    {
        JButton button = new JButton( text, buttonImg );
        button.setBounds( xPos - ( width / 2 ),
            yPos - ( height / 2 ),
            width,
            height );
        button.setFont( Images.loadFont( fontSize ) );
        button.setHorizontalTextPosition( JButton.CENTER );
        button.setVerticalTextPosition( JButton.CENTER );
        return button;
    }


    /**
     * Sets the background of the screen to the image given by the path and
     * width and height
     * 
     * @param path
     *            - path in order to locate and get the image
     * @param width
     *            - the width of the image to resize to
     * @param height
     *            - the height of the image to resize to
     */
    protected void setBackgroundImg( String path, int width, int height )
    {
        background = new ImageIcon( this.getClass().getResource( path ) );
        background.setImage( background.getImage()
            .getScaledInstance( width, height, Image.SCALE_DEFAULT ) );

    }


    /**
     * Creates and returns a label given the background image
     * 
     * @param backgroundImg
     *            - the background image in which the the label will contain
     * @return JLabel object that consists of the given background image
     */
    protected abstract JLabel makeLabel( ImageIcon backgroundImg );
}
