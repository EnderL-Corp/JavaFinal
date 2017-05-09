package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class BoardPanel extends JPanel
{
	  private int numTiles = 9;
	  private int commanderPosX = numTiles / 2;
      private int commanderPosY = 0;

      
	  public void paintComponent(Graphics g)
	  {
			Rectangle r = this.getBounds();
			int height = r.height;
			int width = r.width;
		    int tileHeight = height / numTiles;
		    int tileWidth = width / numTiles;
		    
		    boolean fill = false;
		    	    
		    for(int i = 0; i < numTiles; i++)
		    {
		    	for(int j = 0; j < numTiles; j++)
		    	{
		    		//Graphics2D g = new Graphics2D()
					//Graphics2D g2 = (Graphics2D) g;
		    		g.setColor(Color.black);   
		    		
		    		if( ( (i == 0 || i == numTiles - 1) && (j == 0 || j == 1|| j == numTiles - 2 || j == numTiles - 1) ) || ( (i == 1 || i == numTiles - 2) && (j == 0 || j == numTiles - 1) ) )
		    		{
		    			g.fillRect(i*tileWidth, j*tileHeight, tileWidth, tileHeight);
		    		}
		    		
		    		
		    		/*
		    		if (fill)
		    		{
		    			g.fillRect(i*tileWidth,j*tileHeight,tileWidth,tileHeight);
		    		} */
		    		else
		    		{
		    			g.drawRect(i*tileWidth,j*tileHeight,tileWidth,tileHeight);
		    		}
		    		
					if(j == commanderPosX && i == commanderPosY)
					{
						g.setColor(Color.red); 
						g.fillRect(i*tileWidth, j*tileHeight, tileWidth, tileHeight);
					}
		    		/*
		    		fill = !fill;
		    	}
		    	
		    	if (numTiles%2 == 0)
		    	{
		    		fill = !fill; */
		    	} 
		    }
	  }
	  
	  public void changeCommanderPos(int x, int y)
	  {
		  commanderPosX = x;
		  commanderPosY = y;
	  }
	  
	  public void changeMapSize(String size)
	  {
		  switch(size)
		  {
		  	case "small":
		  		numTiles = 9;
		  		break;
		  	case "medium":
		  		numTiles = 15;
		  		break;
		  	case "large":
		  		numTiles = 21;
		  		break;
		  	default:
		  		numTiles = 9;
		  		break;
		  }
		  commanderPosX = numTiles / 2;
	  }
}
