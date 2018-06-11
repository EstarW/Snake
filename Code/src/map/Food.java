package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import main.Main;
import snake0.Block;

import snake0.Data;

/**
 * Nouriture pour le serpent
 * @author Alexandre,Damien,Elie
 *
 */
public class Food extends JPanel
{
	
	
    
   private Main parent;

    protected int posX;
    protected int posY;
	
	private Color color = new Color(211, 161, 0);
	
	/**
	 * Crée une nouvelle food pour le serpent
	 * @param posX position horizontal de l'objectif
	 * @param posY position vertical de l'objectif
	 */
	public Food(int posX, int posY, Main _parent)
	{
		this.setSize(new Dimension(Data.CASESIZE, Data.CASESIZE));
                
		this.setLocation(posX*Data.CASESIZE, posY*Data.CASESIZE);
		
                parent=_parent;
                
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	 * Changer l'emplacement de la food
	 * @param posX position horizontal de l'objectif
	 * @param posY position vertical de l'objectif
	 */
	public void moveIt()
	{
            //color = new Color(211, 161, 0);
            int newX = (int)(Math.random()*Data.NBRCASEX);
            int newY = (int)(Math.random()*Data.NBRCASEY);
            
            while(!TestCorp(newX,newY)){
                newX = (int)(Math.random()*Data.NBRCASEX);
                newY = (int)(Math.random()*Data.NBRCASEY);
            }
            
            this.posX=newX;
            this.posY=newY;
            this.setLocation(posX*Data.CASESIZE, posY*Data.CASESIZE);
	}
	
   @Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawOval(Data.CASESIZE/6, Data.CASESIZE/6, Data.CASESIZE-(Data.CASESIZE/3)-1, Data.CASESIZE-(Data.CASESIZE/3)-1);
		
		g.setColor(color);
		g.fillOval(Data.CASESIZE/6+1, Data.CASESIZE/6+1, Data.CASESIZE-(Data.CASESIZE/3)-3, Data.CASESIZE-(Data.CASESIZE/3)-3);
	}
	
        public boolean TestCorp(int i, int j){
            boolean res=true;
            for (Block b : parent.getSerpent().getQueu()){
                if ( i == b.getX() && j == b.getY()){
                    res=false;
                }
            }
            return res;
        }
        
	/**
	 * @return position horizontal de la food
	 */
	public int getPosX()
	{
		return posX;
	}
	
	/**
	 * @return position vertical de la food
	 */
	public int getPosY()
	{
		return posY;
	}
}
