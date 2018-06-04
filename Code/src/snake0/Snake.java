package snake0;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import graph.Dialog;
import map.Food;

/**
 * Le serpent
 * @author Alexandre,Damien,Elie
 *
 */
public class Snake extends JPanel implements Runnable, KeyListener
{
	
    public final static short WEST = -1;
    public final static short EST = 1;
    public final static short NORTH = -2;
    public final static short SOUTH = 2;
    public final static short PLAY = 0;
    public final static short DEAD = 1;
    
	
	private Block tete;
	private ArrayList<Block> queu = new ArrayList<Block>();
	
	private short dir = WEST;
	private short dirAv = WEST;
	
	private Color color;
	private short statue = PLAY;
	
	private Food objectif = new Food((int)(Math.random()*Data.NBRCASEX), (int)(Math.random()*Data.NBRCASEY));
	private int score = 0;
	private JPanel pc = new JPanel();
        
	
	/**
	 * Crée un nouveau serpent
	 * @param tete le block représentant sa tête
	 * @param nbrQueu le nombre de block en plus de sa tête
	 */
	public Snake(Block tete, int nbrQueu)
	{
		this.tete = tete;
		color = new Color(0,180,0);
                
		
		for(int i=0;i<nbrQueu;i++)//Ajoute le corp
		{
			if(i==0)
				queu.add(new Block(tete));
			else queu.add(new Block(queu.get(i-1)));
			
			queu.get(i).setColor(color);
		}
		
		//Ajouter a la map
		
		Data.MAP.add(tete);
		Data.MAP.add(queu.toArray(new Block[queu.size()]));
		Data.MAP.add(objectif);
		
		//Ajouter le panel du score a l'ecran
		
		pc.add(new JLabel("Score: "));
		pc.add(new JLabel(String.valueOf(score)));
	}
	
	/**
	 * Fait réapparaître le serpent
	 * @param tete le block représentant la tête
	 * @param nbrQueu le nombre de block suivant la tête
	 */
	public void reborn(Block tete, int nbrQueu)
	{
		this.tete = tete;
		color = new Color(0,180,0);
		score = 0;
                
		
		pc.removeAll();
		pc.add(new JLabel("Score: "));
		pc.add(new JLabel(String.valueOf(score)));
		
		queu.removeAll(queu);
		
		for(int i=0;i<nbrQueu;i++) //Crée le corps du serpent
		{
			if(i==0)
				queu.add(new Block(tete));
			else queu.add(new Block(queu.get(i-1)));
			
			queu.get(i).setColor(color);
		}
		
		objectif = new Food((int)(Math.random()*Data.NBRCASEX), (int)(Math.random()*Data.NBRCASEY));
		
		Data.MAP.add(tete);
		Data.MAP.add(queu.toArray(new Block[queu.size()]));
		Data.MAP.add(objectif);
		
		dir = WEST;
		dirAv = WEST;
		statue = PLAY;
	}
	
	/**
	 * Ajouter un block en plus au serpent
	 */
	public void addOne()
	{
		Block b = new Block(queu.get(queu.size()-1));
		b.setColor(color);
		
		queu.add(b);
		Data.MAP.add(b);
	}
	
	/**
	 * Fait avancer le serpent d'une case
	 */
	public void avance()
	{
		for(int k=queu.size()-1;k>=0;k--) //Remplacer le Block n-1 par celui n
			queu.get(k).avance();
		
		if(dir+dirAv != 0) //Ne peut pas se diriger dans le sens contraire
		{
			tete.avance(dir); //on garde la même direction
			dirAv=dir;
		}
		else
			tete.avance((short) (dirAv)); //On fait avancer la tete dans la direction dir
		
		for(int k=0;k<queu.size();k++)
			if(queu.get(k).getLocation().equals(tete.getLocation())) //Si la tete touche un autre block du serpent, on perd
			{
				statue = DEAD; //Le serpent meurt
				Dialog.danger(null, "Perdu !");
			}
		
		if(tete.posX == objectif.getPosX() && tete.posY == objectif.getPosY()) //Si le serpent ramasse une food
		{
			objectif.moveIt((int)(Math.random()*Data.NBRCASEX), (int)(Math.random()*Data.NBRCASEY));
			this.addOne();
			score++;
			((JLabel)pc.getComponent(1)).setText(String.valueOf(score));
			pc.repaint();
		}
	}
	
	/**
	 * Thread run
	 */
	public void run()
	{
		while(true)
		{
			if(statue == PLAY) //Bouge le serpent s'il est vivant
			{
				this.avance();
				
				try
				{
					Thread.sleep(1040-10*Data.SNAKESPEED);
				}
				catch (InterruptedException e){e.printStackTrace();}
			}
		}
	}
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_UP)
			dir=NORTH;
		else if(e.getKeyCode()==KeyEvent.VK_DOWN)
			dir=SOUTH;
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
			dir=WEST;
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			dir=EST;
	}
	
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}
	
	/**
	 * 
	 * @return les block formant la queue du serpent
	 */
	public ArrayList<Block> getQueu()
	{
		return queu;
	}
	
	/**
	 * Snake.DEAD: mort
	 * Snake.PLAY: vivant
	 * @return statu du serpent
	 */
	public short getStatue()
	{
		return statue;
	}
	
	/**
	 * Snake.DEAD: mort
	 * Snake.PLAY: vivant
	 * @param statue statue à donner au serpent
	 */
	public void setStatue(short statue)
	{
		this.statue = statue;
	}
	
	/**
	 * 
	 * @return Panel du score
	 */
	public JPanel getPanel()
	{
		return this.pc;
	}

        
}
