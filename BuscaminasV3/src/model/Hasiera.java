/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author peralta.laura
 */
public class Hasiera extends JFrame implements ActionListener {

    private JPanel menua;
    private int tamaina;
    private JButton hasiJolasten;
    private JButton tamainaAldatu;
    private JButton irten;
    private JLabel titulua;

    public Hasiera() {
        initPanel();
    }

    public void initPanel() {
        menua = new JPanel();
        add(menua);
        setTitle("MENUA");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
