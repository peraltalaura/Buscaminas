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
import javax.swing.JTextField;

/**
 * Jokua irekitzean agertzen den menua eta bere akzioak sortzen ditu
 * @author peralta.laura
 */
public class Hasiera extends JFrame implements ActionListener {

    private JPanel menua;
    private int tamaina = 8;
    private JButton hasiJolasten;
    private JButton tamainaAldatu;
    private JButton irten;
    private JLabel titulua;
    private JTextField tamainaBerria;
    private JButton aldatu;
    private JFrame frame;

    /**
     * Hasiera konstruktorea, initPanel() eta initMenua() metodoei deitzen die
     */
    public Hasiera() {
        initPanel();
        initMenua();
    }

    /**
     * jokuaren menuko panela sortzen duen metodoa
     */
    public void initPanel() {
        menua = new JPanel();
        add(menua);
        menua.setLayout(null);
        setTitle("MENUA");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        setVisible(true);
    }

    /**
     * menuko panelaren barruan egongo diren elementuak sortzen dituen metodoa
     */
    public void initMenua() {
        titulua = new JLabel();
        titulua.setText("COVID-19 BILATU");
        titulua.setBounds(200, 25, 100, 50);
        menua.add(titulua);
        hasiJolasten = new JButton("JOLASTU");
        hasiJolasten.setSize(100, 50);
        hasiJolasten.setLocation(200, 100);
        hasiJolasten.addActionListener(this);
        menua.add(hasiJolasten);
        tamainaAldatu = new JButton("TAMAINA ALDATU");
        tamainaAldatu.setSize(200, 50);
        tamainaAldatu.setLocation(150, 200);
        tamainaAldatu.addActionListener(this);
        menua.add(tamainaAldatu);
        irten = new JButton("IRTEN");
        irten.setSize(100, 50);
        irten.setLocation(200, 300);
        irten.addActionListener(this);
        menua.add(irten);
    }

    /**
     * Aldatu botoian klikatzean irekitzen den menua sortzen du
     */
    public void tamainaAldatu() {
        frame = new JFrame();
        frame.setSize(500, 250);
        frame.setLayout(null);
        JLabel testua = new JLabel("SARTU TAMAINA: ");
        testua.setBounds(150, 50, 100, 50);
        frame.add(testua);
        tamainaBerria = new JTextField();
        tamainaBerria.setSize(50, 25);
        tamainaBerria.setLocation(260, 65);
        frame.add(tamainaBerria);
        aldatu = new JButton("ALDATU");
        aldatu.addActionListener(this);
        aldatu.setSize(100, 30);
        aldatu.setLocation(200, 125);
        frame.add(aldatu);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    
    /**
     * botoiei klikatzean gertatzen diren akzioak
     * @param e klikatzen den elementua hartzen du
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventSource = e.getSource();
        JButton klikatutakoBotoia = (JButton) eventSource;
        if (klikatutakoBotoia == hasiJolasten) {
            Jokua j = new Jokua(tamaina); //joku berri bat sortzen du emandako tamainarekin
            this.setVisible(false);
        } else if (klikatutakoBotoia == tamainaAldatu) {
            tamainaAldatu();
        } else if (klikatutakoBotoia == aldatu) {
            tamaina = Integer.parseInt(tamainaBerria.getText());
            frame.setVisible(false);
            menua.setVisible(true);
        } else if (klikatutakoBotoia == irten) {
            System.exit(0);
        }
    }

}
