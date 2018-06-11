<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author dj715494
 */
public class Quitter implements ActionListener{

    private Main parent;

    public Quitter(Main parent) {
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        parent.getThread().stop();
        parent.dispose();
        
    }
    
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author dj715494
 */
public class Quitter implements ActionListener{

    private Main parent;

    public Quitter(Main parent) {
        this.parent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        parent.dispose();
    }
    
}
>>>>>>> 0be1e5c11c43cb15219c4e9e313bd1032bb05796
