package map;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Carte sur laquelle le serpent se déplace
 * @author np036390
 *
 */
public class Map extends JPanel
{
	
	
	private int sizeX;
	private int sizeY;
	
	/**
	 * Crée une carte
	 * @param sizeX taille X en pixel de la carte
	 * @param sizeY taille Y en pixel de la carte
	 */
	public Map(int sizeX, int sizeY)
	{
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		
		this.setSize(sizeX, sizeY);
		this.setPreferredSize(new Dimension(sizeX, sizeY));
		this.setLayout(null);
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.sizeX, this.sizeY);
		
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, this.sizeX-1, this.sizeY-1);
	}
	
	/**
	 * ajoute un composant
	 * @param array
	 */
	public void add(Component[] array)
	{
		for(Component o : array)
			this.add(o);
	}
	
	/**
         * supprime un composant
         * @param array 
         */
	public void remove(Component[] array)
	{
		for(Component o : array)
			this.remove(o);
	}
}
