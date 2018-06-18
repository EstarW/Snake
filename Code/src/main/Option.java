package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import snake0.Data;
import static snake0.Data.NBRCASEX;
import static snake0.Data.NBRCASEY;
import static snake0.Data.SNAKESPEED;

/**
 * Boite de dialogue Option
 * classe qui permet de gérer les options du jeu 
 * @author Alexandre,Damien,Elie
 *
 */
public class Option extends JDialog implements ActionListener {

    private Main parent;
    private final Option before = this;

    private JSlider speed = new JSlider(0, 100, SNAKESPEED);
    private JSlider nbrCaseX = new JSlider(10, 100, NBRCASEY);
    private JSlider nbrCaseY = new JSlider(10, 100, NBRCASEX);
    private JButton but = new JButton("Valider");

    private JPanel block1 = new JPanel();
    private JPanel block2 = new JPanel();
    private JPanel block3 = new JPanel();
    private JPanel block4 = new JPanel();

    /**
     * Constructeur de la boite de dialogue Option     *
     * @param _parent Fenetre mere
     */
    public Option(Main _parent) {
        super(new JDialog(), "Options", true);

        this.parent = _parent;

        this.setResizable(true);
        this.setLayout(new GridLayout(4, 1));
        this.setPreferredSize(new Dimension(600, 140));
        
        //vitesse 
        
        block1.setLayout(new FlowLayout());

        speed.setMajorTickSpacing(10);
        speed.setPaintTicks(true);
        speed.setPaintLabels(true);

        block1.add(new JLabel("Vitesse: "));
        block1.add(speed);

        // Nombre ligne
        
        block2.setLayout(new FlowLayout());

        nbrCaseX.setMajorTickSpacing(10);
        nbrCaseX.setPaintTicks(true);
        nbrCaseX.setPaintLabels(true);

        block2.add(new JLabel("Nombre ligne: "));
        block2.add(nbrCaseX);

        // Nombre colone
        
        block3.setLayout(new FlowLayout());

        nbrCaseY.setMajorTickSpacing(10);
        nbrCaseY.setPaintTicks(true);
        nbrCaseY.setPaintLabels(true);

        block3.add(new JLabel("Nombre colone: "));
        block3.add(nbrCaseY);

        but.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Data.NBRCASEX = (short) before.nbrCaseX.getValue();
                Data.NBRCASEY = (short) before.nbrCaseY.getValue();
                Data.SNAKESPEED = speed.getValue();
                parent.getThread().resume();

                before.dispose();
                parent.replay();
            }
        });

        block4.setLayout(new FlowLayout());
        block4.add(but);

        this.add(block1);
        this.add(block2);
        this.add(block3);
        this.add(block4);

        this.setSize(new Dimension(350, 250));
    }
    /**
     * permet de relancé le jeu quand l'on ferme les options
     * @param e 
     */
    public void actionPerformed(ActionEvent e) {
        parent.getThread().suspend();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
