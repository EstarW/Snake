package snake0;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import graph.Dialog;
import main.Main;
import map.Food;

/**
 * La tete du serpent
 *
 * @author Alexandre,Damien,Elie
 *
 */
public class Snake extends JPanel implements Runnable, KeyListener {

    public final static short WEST = -1;
    public final static short EST = 1;
    public final static short NORTH = -2;
    public final static short SOUTH = 2;
    public final static short PLAY = 0;
    public final static short DEAD = 1;

    private Main parent;
    private Block tete;
    private ArrayList<Block> queue = new ArrayList<Block>();

    private short dir = WEST;
    private short dirAv = WEST;

    private Color color;
    private short statue = PLAY;

    private Food objectif;
    private int score = 0;
    private JPanel pc = new JPanel();

    /**
     * Crée un nouveau serpent
     *
     * @param tete le block représentant sa tête
     * @param nbrQueu le nombre de block en plus de sa tête
     */
    public Snake(Block tete, int nbrQueu, Main _parent) {
        this.tete = tete;
        color = new Color(0, 180, 0);
        parent = _parent;
        objectif = new Food((int) (Math.random() * Data.NBRCASEX), (int) (Math.random() * Data.NBRCASEY), parent);
        for (int i = 0; i < nbrQueu; i++)//Ajoute le corp
        {
            if (i == 0) {
                queue.add(new Block(tete));
            } else {
                queue.add(new Block(queue.get(i - 1)));
            }

            queue.get(i).setColor(color);
        }

        //Ajouter a la map
        Data.MAP.add(tete);
        Data.MAP.add(queue.toArray(new Block[queue.size()]));
        Data.MAP.add(objectif);

        //Ajouter le panel du score a l'ecran
        pc.add(new JLabel("Score: "));
        pc.add(new JLabel(String.valueOf(score)));
    }

    /**
     * Fait réapparaître le serpent
     *
     * @param tete le block représentant la tête
     * @param nbrQueu le nombre de block suivant la tête
     */
    public void reborn(Block tete, int nbrQueu) {
        this.tete = tete;
        color = new Color(0, 180, 0);
        score = 0;

        pc.removeAll();
        pc.add(new JLabel("Score: "));
        pc.add(new JLabel(String.valueOf(score)));

        queue.removeAll(queue);

        for (int i = 0; i < nbrQueu; i++) //Crée le corps du serpent
        {
            if (i == 0) {
                queue.add(new Block(tete));
            } else {
                queue.add(new Block(queue.get(i - 1)));
            }

            queue.get(i).setColor(color);
        }

        objectif = new Food((int) (Math.random() * Data.NBRCASEX), (int) (Math.random() * Data.NBRCASEY), parent);

        Data.MAP.add(tete);
        Data.MAP.add(queue.toArray(new Block[queue.size()]));
        Data.MAP.add(objectif);

        dir = WEST;
        dirAv = WEST;
        statue = PLAY;
    }

    /**
     * Ajouter un block en plus au serpent
     */
    public void addOne() {
        Block b = new Block(queue.get(queue.size() - 1));
        b.setColor(color);

        queue.add(b);
        Data.MAP.add(b);
    }

    /**
     * Fait avancer le serpent d'une case
     */
    public void avance() {
        for (int k = queue.size() - 1; k >= 0; k--) //Remplacer le Block n-1 par celui n
        {
            queue.get(k).avance();
        }

        if (dir + dirAv != 0) //Ne peut pas se diriger dans le sens contraire
        {
            tete.avance(dir); //on garde la même direction
            dirAv = dir;
        } else {
            tete.avance((short) (dirAv)); //On fait avancer la tete dans la direction dir
        }
        for (int k = 0; k < queue.size(); k++) {
            if (queue.get(k).getLocation().equals(tete.getLocation())) //Si la tete touche un autre block du serpent, on perd
            {
                statue = DEAD; //Le serpent meurt
                Dialog.danger(null, "Perdu !");
                parent.replay();
            }
        }

        if (tete.posX == objectif.getPosX() && tete.posY == objectif.getPosY()) //Si le serpent ramasse une food
        {

            objectif.moveIt();
            this.addOne();
            score++;
            ((JLabel) pc.getComponent(1)).setText(String.valueOf(score));
            pc.repaint();
        }
    }

    /**
     * Thread run
     */
    public void run() {
        while (true) {
            if (statue == PLAY) //Bouge le serpent s'il est vivant
            {
                this.avance();

                try {
                    Thread.sleep(1040 - 10 * Data.SNAKESPEED);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Change la direction du serpent quand une touche flechée est pressée
     *
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            dir = NORTH;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            dir = SOUTH;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            dir = WEST;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            dir = EST;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    /**
     *
     * @return les block formant la queue du serpent
     */
    public ArrayList<Block> getQueu() {
        return queue;
    }

    /**
     * Snake.DEAD: mort Snake.PLAY: vivant
     *
     * @return statu du serpent
     */
    public short getStatue() {
        return statue;
    }

    /**
     * Snake.DEAD: mort Snake.PLAY: vivant
     *
     * @param statue statue à donner au serpent
     */
    public void setStatue(short statue) {
        this.statue = statue;
    }

    /**
     *
     * @return Panel du score
     */
    public JPanel getPanel() {
        return this.pc;
    }

    /**
     *
     * @return Le score
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

}
