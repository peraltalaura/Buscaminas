/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import exekutagarriak.Puntuazioak;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Jokua irekitzean agertzen den menua eta bere akzioak sortzen ditu
 *
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
    private JLabel baloreaGaizki;
    private JLabel zelaiTamaina;
    private JFrame jokalaria;
    private JButton izenaSartu;
    private JButton jokalarienDenbora;
    private JTextField izenaHartu;
    private Puntuazioak ireki;

    ;

    /**
     * Hasiera konstruktorea, initPanel() eta initMenua() metodoei deitzen die
     */
    public Hasiera() {
        this.ireki = new Puntuazioak();
        initPanel();
        initMenua();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Hasiera(int tamaina) {
        this.ireki = new Puntuazioak();
        this.tamaina = tamaina;
        initPanel();
        initMenua();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        Color darkGreen = new Color(0, 102, 0);
        titulua = new JLabel();
        titulua.setText("COVID-19 BILATU");
        titulua.setFont(new Font("Arial", Font.BOLD, 22));
        titulua.setForeground(darkGreen);
        titulua.setBounds(150, 25, 300, 50);
        menua.add(titulua);
        hasiJolasten = new JButton("JOLASTU");
        hasiJolasten.setFont(new Font("Arial", Font.BOLD, 14));
        hasiJolasten.setSize(100, 50);
        hasiJolasten.setLocation(200, 100);
        hasiJolasten.addActionListener(this);
        menua.add(hasiJolasten);
        tamainaAldatu = new JButton("TAMAINA ALDATU");
        tamainaAldatu.setFont(new Font("Arial", Font.BOLD, 14));
        tamainaAldatu.setSize(200, 50);
        tamainaAldatu.setLocation(150, 175);
        tamainaAldatu.addActionListener(this);
        menua.add(tamainaAldatu);
        jokalarienDenbora = new JButton("JOKALARIEN DENBORA");
        jokalarienDenbora.setFont(new Font("Arial", Font.BOLD, 14));
        jokalarienDenbora.setSize(200, 50);
        jokalarienDenbora.setLocation(150, 250);
        jokalarienDenbora.addActionListener(this);
        menua.add(jokalarienDenbora);
        irten = new JButton("IRTEN");
        irten.setFont(new Font("Arial", Font.BOLD, 14));
        irten.setSize(100, 50);
        irten.setLocation(200, 325);
        irten.addActionListener(this);
        menua.add(irten);
        JLabel aukeratutakoTamaina = new JLabel("JOLASAREN TAMAINA: ");
        aukeratutakoTamaina.setFont(new Font("Arial", Font.BOLD, 14));
        aukeratutakoTamaina.setBounds(150, 375, 175, 50);
        menua.add(aukeratutakoTamaina);
        zelaiTamaina = new JLabel("" + tamaina);
        zelaiTamaina.setFont(new Font("Arial", Font.BOLD, 14));
        zelaiTamaina.setBounds(310, 375, 100, 50);
        menua.add(zelaiTamaina);
    }

    /**
     * Aldatu botoian klikatzean irekitzen den menua sortzen du
     */
    public void tamainaAldatu() {
        frame = new JFrame();
        frame.setSize(500, 250);
        frame.setLayout(null);
        baloreaGaizki = new JLabel("TAMAINAREN BALIOA 8-25 IZAN BEHAR DA!");
        baloreaGaizki.setFont(new Font("Arial", Font.BOLD, 14));
        baloreaGaizki.setForeground(Color.red);
        baloreaGaizki.setBounds(100, 15, 300, 50);
        baloreaGaizki.setVisible(false);
        frame.add(baloreaGaizki);
        JLabel testua = new JLabel("SARTU TAMAINA: ");
        testua.setFont(new Font("Arial", Font.BOLD, 14));
        testua.setBounds(125, 50, 150, 50);
        frame.add(testua);
        tamainaBerria = new JTextField();
        tamainaBerria.setFont(new Font("Arial", Font.PLAIN, 14));
        tamainaBerria.setSize(50, 25);
        tamainaBerria.setLocation(260, 65);
        frame.add(tamainaBerria);
        aldatu = new JButton("ALDATU");
        aldatu.setFont(new Font("Arial", Font.BOLD, 14));
        aldatu.addActionListener(this);
        aldatu.setSize(100, 30);
        aldatu.setLocation(200, 125);
        frame.add(aldatu);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }

    public void izenaSartu() {
        jokalaria = new JFrame();
        jokalaria.setSize(500, 250);
        jokalaria.setLayout(null);
        JLabel testua = new JLabel("SARTU ZURE IZENA: ");
        testua.setFont(new Font("Arial", Font.BOLD, 14));
        testua.setBounds(125, 50, 150, 50);
        jokalaria.add(testua);
        izenaHartu = new JTextField();
        izenaHartu.setFont(new Font("Arial", Font.PLAIN, 14));
        izenaHartu.setSize(100, 25);
        izenaHartu.setLocation(260, 65);
        jokalaria.add(izenaHartu);
        izenaSartu = new JButton("SARTU");
        izenaSartu.setFont(new Font("Arial", Font.BOLD, 14));
        izenaSartu.addActionListener(this);
        izenaSartu.setSize(100, 30);
        izenaSartu.setLocation(200, 125);
        jokalaria.add(izenaSartu);
        jokalaria.setLocationRelativeTo(null);
        jokalaria.setAlwaysOnTop(true);
        jokalaria.setVisible(true);
    }

    /**
     * botoiei klikatzean gertatzen diren akzioak
     *
     * @param e klikatzen den elementua hartzen du
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventSource = e.getSource();
        JButton klikatutakoBotoia = (JButton) eventSource;
        if (klikatutakoBotoia == hasiJolasten && !(ireki.isVisible())) {
            izenaSartu();//Eskatu erabiltzaileari bere izena
            this.setVisible(false);
        } else if (ireki.isVisible() && klikatutakoBotoia == hasiJolasten) {
            JOptionPane.showMessageDialog(menua, "Lehioak itxi behar dituzu!");
            
        } else if (klikatutakoBotoia == izenaSartu) {
            Jokua j = new Jokua(tamaina); //joku berri bat sortzen du emandako tamainarekin
            j.setIzena(izenaHartu.getText());
            jokalaria.setVisible(false);
        } else if (klikatutakoBotoia == tamainaAldatu && (frame == null || frame.isVisible() == false)) {
            this.setVisible(false);
            tamainaAldatu();
        } else if (klikatutakoBotoia == aldatu) {
            try {
                if (!(Integer.parseInt(tamainaBerria.getText()) >= 8 && Integer.parseInt(tamainaBerria.getText()) <= 25)) {
                    baloreaGaizki.setVisible(true);
                } else {
                    tamaina = Integer.parseInt(tamainaBerria.getText());
                    zelaiTamaina.setText("" + tamaina);
                    frame.setVisible(false);
                    this.setVisible(true);
                }
            } catch (NumberFormatException x) {
                baloreaGaizki.setVisible(true);
            }
        } else if (klikatutakoBotoia == jokalarienDenbora && !(ireki.isVisible())) {
            ireki.setVisible(true);

        } else if (klikatutakoBotoia == irten) {
            System.exit(0);
        }
    }

}
