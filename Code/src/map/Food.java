package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import snake0.Data;

/**
 * Nouriture pour le serpent
 * @author Alexandre,Damien,Elie
 *
 */
public class Food extends JPanel
{
	
	
    
   

    protected int posX;
    protected int posY;
	
	private Color color = new Color(211, 161, 0);
	
	/**
	 * Crée une nouvelle food pour le serpent
	 * @param posX position horizontal de l'objectif
	 * @param posY position vertical de l'objectif
	 */
	public Food(int posX, int posY)
	{       
		this.setSize(new Dimension(Data.CASESIZE, Data.CASESIZE));
                
                
		this.setLocation(posX*Data.CASESIZE, posY*Data.CASESIZE);
		
		this.posX = posX;
		this.posY = posY;
	}
	
	/**
	 * Changer l'emplacement de la food
	 * @param posX position horizontal de l'objectif
	 * @param posY position vertical de l'objectif
	 */
	public void moveIt(int posX, int posY)
	{
		color = new Color(211, 161, 0);
		
		this.posX = posX;
		this.posY = posY;
		
		this.setLocation(posX*Data.CASESIZE, posY*Data.CASESIZE);
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawOval(Data.CASESIZE/6, Data.CASESIZE/6, Data.CASESIZE-(Data.CASESIZE/3)-1, Data.CASESIZE-(Data.CASESIZE/3)-1);
		
		g.setColor(color);
		g.fillOval(Data.CASESIZE/6+1, Data.CASESIZE/6+1, Data.CASESIZE-(Data.CASESIZE/3)-3, Data.CASESIZE-(Data.CASESIZE/3)-3);
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
