package gui;

import java.awt.Point;

/**
 *  thread to manage torches
 *
 *  @author  Raeed Azom & Jeffery Lee
 *  @version May 26, 2020
 *  @author  Period: 1
 *  @author  Assignment: APCS Final Project
 *
 *  @author  Sources: none
 */
public class TorchThread extends Thread
{
    private Torch[] torches;
    
    /**
     * @param layout layout to manage
     * @param places torch locations
     */
    public TorchThread(Tile[][] layout, Point[] places)
    {
        torches = new Torch[places.length];
        for (int i = 0; i<places.length; i++)
        {
            Torch torch = new Torch();
            torches[i] = torch;
            layout[places[i].x][places[i].y] = new Tile(false, torch.getLabel(), "walltorch");
        }
    }

    /**
     * main run method to manage torches
     */
    @Override
    public void run()
    {
        while(true)
        {
            try
            {
                sleep( 200 );
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
            }
            update();
        }
        
    }
    
    /**
     * updates all torches
     */
    private void update()
    {        
        for (int i = 0; i < torches.length; i++)
        {
            torches[i].update();
        }
    }
}
