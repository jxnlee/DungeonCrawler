package screens;

import main.Launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import gui.Images;


/**
 * MenuScreen class which represents the menu screen that will be displayed when
 * the game is launched.. The menu screen will consist of a label containing the
 * background image, a text that displays the title, and a button that, upon
 * being clicked, will start the level
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
@SuppressWarnings("serial")
public class MenuScreen extends Screen
{
    /**
     * Constructor: sets up the menu screen with a label containing the
     * background image, title, and start button. Once the start button is
     * clicked, screen switches to the game level
     */
    public MenuScreen()
    {
        super();

        JButton button = makeButton( "Start Game",
            Images.getButton(),
            400,
            325,
            120,
            50,
            14 );
        this.add( button );
        setBackgroundImg( "DungeonBackground1.gif", 800, 600 );
        this.getContentPane().add( ( makeLabel( background ) ) );
        button.addActionListener( ( new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                setVisible( false );
                Launcher.getGame().startGame();
            }

        } ) );
        this.pack();
    }


    /**
     * Creates and returns the menu label given the background image and also
     * displays the title of the game.
     * 
     * @param backgroundImg
     *            - the background image in which the the label will contain
     * @return JLabel label object that consists of the given background image
     */
    @Override
    protected JLabel makeLabel( ImageIcon backgroundImg )
    {
        JLabel label = new JLabel( backgroundImg )
        {
            public void paintComponent( Graphics g )
            {
                super.paintComponent( g );
                Images.drawText( g,
                    "Dungeon Crawler",
                    400,
                    75,
                    true,
                    Color.WHITE,
                    Images.loadFont( 60 ) );
            }
        };
        return label;
    }

}
