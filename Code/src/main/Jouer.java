/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;

/**
 *
 * @author dj715494
 */
public class Jouer implements ActionListener{
    
    private Main parent;

    public Jouer(Main parent) {
        this.parent = parent;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        parent.replay();
    }
    
}
