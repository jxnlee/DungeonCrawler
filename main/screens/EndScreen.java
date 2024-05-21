package screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import gui.Images;
import main.Launcher;


/**
 * EndScreen class which represents the End screen that is displayed when the
 * user dies. The end screen will display a message that tells the user a game
 * over message, their highest score (including the most recent score), their
 * most recent score, and whether their most recent score is a new record. The
 * end screen will also contain a background image and a retry button that, when
 * pressed, will take the user back to the menu screen
 *
 * @author Jeffrey Lee, Raeed Azom
 * @version May 26, 2020
 * @author Period: 1
 * @author Assignment: APCS Final Project
 *
 * @author Sources: none
 */
@SuppressWarnings("serial")
public class EndScreen extends Screen
{
    private List<Long> scoreBoard;

    private int yDifference;

    private long recentScore;

    private boolean isNewestRecord;


    /**
     * Constructor: sets up the end screen with a label containing the
     * background image, game over message, hightest scores, most recent score,
     * whether the most recent score is a new record, and retry button. Once the
     * retry button is clicked, screen switches to the menu screen
     */
    public EndScreen()
    {
        super();

        JButton button = makeButton( "Retry",
            Images.getButton(),
            400,
            425,
            120,
            50,
            20 );
        this.add( button );
        this.setBackgroundImg( "DungeonBackground2.gif", 800, 600 );
        this.getContentPane().add( ( makeLabel( background ) ) );
        button.addActionListener( ( new ActionListener()
        {

            @Override
            public void actionPerformed( ActionEvent e )
            {
                setVisible( false );
                Launcher.getGame().getMenu().setVisible( true );
            }

        } ) );
        this.pack();
    }


    /**
     * Sets the most recent score to the given score
     * 
     * @param recentScore
     *            - the most recent score that the user earned
     */
    public void setRecentScore( long recentScore )
    {
        this.recentScore = recentScore;
    }


    /**
     * sets isNewRecord to true if the most recent record is a new record,false
     * if otherwise
     * 
     * @param isNewRecord
     *            - true if the most recent record is a new record, false if
     *            otherwise
     */
    public void recentIsNewRecord( boolean isNewRecord )
    {
        isNewestRecord = isNewRecord;
    }


    /**
     * Creates and returns a label given the background image. Additionally
     * displays a game over message, highest scores, most recent scores, and
     * whether or not the most recent score is a new record
     * 
     * @param backgroundImg
     *            - the background image in which the the label will contain
     * @return JLabel object that consists of the given background image
     */
    @Override
    protected JLabel makeLabel( ImageIcon backgroundImg )
    {
        scoreBoard = Launcher.getGame().getScores();
        JLabel label = new JLabel( background )
        {
            public void paintComponent( Graphics g )
            {
                super.paintComponent( g );
                Images.drawText( g,
                    "Game Over",
                    400,
                    75,
                    true,
                    Color.WHITE,
                    Images.loadFont( 60 ) );
                Images.drawText( g,
                    "HighScores:",
                    400,
                    135,
                    true,
                    Color.YELLOW,
                    Images.loadFont( 47 ) );
                yDifference = 60;
                Long score;
                for ( int i = 0; i < scoreBoard.size() && i < 5; i++ )
                {
                    score = scoreBoard.get( i );
                    if ( i == 0 )
                    {
                        Images.drawText( g,
                            score.toString(),
                            400,
                            135 + yDifference,
                            true,
                            Color.YELLOW,
                            Images.loadFont( 42 ) );
                        yDifference += 25;
                    }
                    else
                    {
                        Images.drawText( g,
                            score.toString(),
                            400,
                            135 + yDifference,
                            true,
                            Color.YELLOW,
                            Images.loadFont( 36 ) );
                    }
                    yDifference += 35;
                }
                Images.drawText( g,
                    "Your Score: " + recentScore,
                    400,
                    525,
                    true,
                    Color.YELLOW,
                    Images.loadFont( 40 ) );
                if ( isNewestRecord )
                {
                    Images.drawText( g,
                        "Your Newest Record!",
                        400,
                        570,
                        true,
                        Color.YELLOW,
                        Images.loadFont( 20 ) );
                }
            }
        };
        return label;
    }

}
