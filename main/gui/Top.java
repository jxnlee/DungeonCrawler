package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entities.User;

/**
 *  Represents top menu bar of game
 *
 *  @author  Raeed Azom & Jeffery Lee
 *  @version May 26, 2020
 *  @author  Period: 1
 *  @author  Assignment: APCS Final Project
 *
 *  @author  Sources: none
 */
@SuppressWarnings("serial")
public class Top extends JPanel
{
    //private int roomNum;
    private HealthBar myHealth;
    private JLabel[] backs;
    private ScoreBoard myScore;
    
    /**
     * @param size size of top region
     */
    public Top(int size)
    {
        setup(size);
    }
    
    /**
     * sets up top region of the map
     * @param size
     */
    public void setup(int size)
    {
        ImageIcon back1;
        ImageIcon back2;
        ImageIcon back3;
        int length = size/3;
        int height = size/4;
        if (size%3 == 0)
        {
            back1 = Images.getWhite( length+1, height );
            back2 = Images.getWhite( length, height );
            back3 = Images.getWhite( length+1, height );
        }
        else if (size%3 == 1)
        {
            back1 = Images.getWhite( length, height );
            back2 = Images.getWhite( length+1, height );
            back3 = Images.getWhite( length, height );
        }
        else
        {
            back1 = Images.getWhite( length+1, height );
            back2 = Images.getWhite( length, height );
            back3 = Images.getWhite( length+1, height );
        }
        
        JLabel backone = new JLabel(back1);
        JLabel backtwo = new JLabel(back2);
        JLabel backthree = new JLabel(back3);
        backs = new JLabel[3];
        backs[0] = backone;
        backs[1] = backtwo;
        backs[2] = backthree;
        
        //Work on leftmost side: Contains just the healthbar
        int healthsize = back1.getIconWidth()/128;
        ImageIcon[] bars = Images.getHealthBars( healthsize );
        ImageIcon full = bars[0];
        ImageIcon empty = bars[1];
        int offset = (empty.getIconWidth()-full.getIconWidth())/2;
        myHealth = new HealthBar(full, empty, null, offset);
        
        myScore = new ScoreBoard(back2.getIconHeight()/32);
        back2 = Images.combine( back2,myScore.getImg() );
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        add(backone,c);
        c.gridx = 1;
        c.gridy = 0;
        add(backtwo,c);
        c.gridx = 2;
        c.gridy = 0;
        add(backthree,c);
    }
    
    /**
     * updates top area using a user
     * @param myUser user for this top area
     */
    public void update(User myUser)
    {
        if (myUser.getMaxHealth() == myHealth.getMaxHealth())
        {
            updateHealth(myUser.getHealth());
        }
        else
        {
            myUser.setHealth( myUser.getHealth()-1 );
            myHealth = new HealthBar(myHealth.getFull(), myHealth.getEmpty(), myUser, myHealth.getOffset());
            myUser.setHealth( myUser.getHealth()+1 );
            updateHealth(myUser.getHealth());
        }
        updateScore(myUser.getScore());
    }
    
    private void drawBar(ImageIcon[] bar, int offset)
    {
        ImageIcon back;
        back = Images.combine( (ImageIcon)backs[0].getIcon(), bar[1], 16, 50 );
        back = Images.combine( back, bar[0], 16+offset, 50 );
        BufferedImage img = Images.toBufferedImage(back.getImage());
        Graphics g = img.getGraphics();
        g.setColor( Color.white );
        g.fillRect( 20, 15, 100 , 30  );
        Images.drawText( g, myHealth.getHealth(), 25, 45, false, Color.black, Images.loadFont( 15 ) );
        g.dispose();
        back = new ImageIcon(img);
        backs[0].setIcon( back );
    }
    
    private void updateHealth(int newHealth)
    {
        if(myHealth.update( newHealth ))
        {
            drawBar(myHealth.getBar(), myHealth.getOffset());
        }
    }
    
    private void updateScore(long newScore)
    {
        backs[1].setIcon( myScore.update(newScore) );
    }
    
    /*public void setRoomNum( int roomNum )
    {
        this.roomNum = roomNum;
    }*/
}