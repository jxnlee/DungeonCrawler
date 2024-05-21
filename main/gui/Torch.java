package gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *  represents a wall torch
 *
 *  @author  Raeed Azom & Jeffery Lee
 *  @version May 26, 2020
 *  @author  Period: 1
 *  @author  Assignment: APCS Final Project
 *
 *  @author  Sources: none
 */
public class Torch
{
    private ImageIcon[] images;
    private int num;
    private JLabel label;
    
    /**
     * creates a torch object
     */
    public Torch()
    {
        ImageIcon wall = Images.getTop()[(int)(Math.random()*4)];
        ImageIcon[] temp = Images.getFrontTorch();
        images = new ImageIcon[temp.length];
        for (int i = 0; i<4; i++)
        {
            ImageIcon pict = Images.combine( wall, temp[i] );
            images[i] = pict;
        }
        num = (int)(Math.random()*4)-1;
        label = new JLabel();
    }
    
    /**
     * updates this torch
     */
    public void update()
    {
        num++;
        if (num == 4)
        {
            num = 0;
        }
        label.setIcon( images[num] );
    }
    
    /**
     * returns label of this torch
     * @return label of this torch
     */
    public JLabel getLabel()
    {
        return label;
    }
}
