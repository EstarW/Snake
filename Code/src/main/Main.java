package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import graph.Dialog;
import snake0.Block;
import snake0.Data;
import snake0.Snake;

/**
 * Class Main du Snake
 * @author Alexandre,Damien,Elie
 *
 */

public class Main extends JFrame
{
	
	
	private final Main before = this;
	private Block tete;
	private Snake serpent;
	private JPanel mapPanel = new JPanel();
        private Thread thread;
	
   
    public static void main(String[] args)
	{
		new Main();
	}
	
	/**
	 * Constructeur pour la fenêtre du jeu
	 */
	public Main()
	{
		this.setTitle("Snake Revolution");
		this.setLayout(new BorderLayout());
		
		//MENU 
		
			JMenuBar mb = new JMenuBar();
				JMenu jeu = new JMenu("Jeu");
					JMenuItem option = new JMenuItem("Options");
                                        JMenuItem jouer = new JMenuItem("Jouer");
                                        JMenuItem quitter = new JMenuItem("Quitter");

			
			option.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
                        jouer.addActionListener(new Jouer(this));
			option.addActionListener(new Option(this));
                        quitter.addActionListener(new Quitter(this));
			
                        jeu.add(jouer);
			jeu.add(option);
                        jeu.add(quitter);

			mb.add(jeu);
			
			
			this.getContentPane().add(mb, BorderLayout.NORTH);
		
		//SNAKE 
			
			mapPanel.setLayout(new FlowLayout());
			mapPanel.add(Data.MAP, BorderLayout.CENTER);
			this.getContentPane().add(mapPanel);
		
			tete = new Block((Data.NBRCASEX-1)/2, (Data.NBRCASEY-1)/2);
			tete.setColor(new Color(0,20,0));
			serpent = new Snake(tete, Data.SNAKESIZE);
			
                        thread = new Thread(serpent);
			thread.start();
			this.addKeyListener(serpent);
			
		//COMPTEUR
			
			this.add(serpent.getPanel(), BorderLayout.SOUTH);
			
		this.pack();
		this.setLocationRelativeTo(null);
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * Relance une partie
	 */
	public void replay()
	{
		Data.MAP.removeAll();
		Data.MAP.repaint();
		
		mapPanel.removeAll();
		mapPanel.repaint();
		
		this.remove(mapPanel);
		mapPanel.remove(Data.MAP);
		Data.reborn();
		
		mapPanel.add(Data.MAP, BorderLayout.CENTER);
		this.getContentPane().add(mapPanel);
		
		tete.moveIt((Data.NBRCASEX-1)/2, (Data.NBRCASEY-1)/2);
		tete.setColor(new Color(0,20,0));
		serpent.reborn(tete, Data.SNAKESIZE);
		
		this.pack();
		this.setLocationRelativeTo(null);
	}

    public Snake getSerpent() {
        return serpent;
    }

    public Thread getThread() {
        return thread;
    }
    
       
}