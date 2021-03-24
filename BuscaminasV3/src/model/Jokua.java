/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author peralta.laura
 */
public class Jokua extends JFrame implements MouseListener, ActionListener {

    private final JPanel panelOsoa;
    private JPanel goikoPanela;
    private JPanel behekoPanela;
    private JLabel banderaFalta;
    private int banderaFaltaKop;
    private int erakutsitaKop;
    private JButton[][] botoiak;
    private int[][] zelaia;
    private boolean[][] erakutsita;
    private boolean[][] banderaJarrita;
    private int tamaina;
    private final int KARRATU_TAMAINA = 30;
    private ImageIcon mina;
    private ImageIcon jolasten;
    private ImageIcon hilda;
    private ImageIcon bandera;
    private JButton jolastenBotoia;
    private boolean jolastenDago;
    private JLabel jolastutakoDenbora;
    private final Timer denbora = new Timer();
    private String izena;

    /**
     * Jokua klasearen konstruktorea da eta Jokua sortzen du tamaina bat sartzen
     *
     * @param tamaina jolasa sortuko den tamaina
     */
    public Jokua(int tamaina) {

        panelOsoa = new JPanel();
        panelOsoa.setLayout(new BoxLayout(panelOsoa, BoxLayout.Y_AXIS));
        add(panelOsoa);
        setTitle("COVID-19 BILATU");
        setSize(tamaina * KARRATU_TAMAINA, tamaina * KARRATU_TAMAINA + 50);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);
        this.tamaina = tamaina;
        banderaFaltaKop = tamaina;
        erakutsitaKop = 0;
        irudiakSortu();
        zelaiaBete();
        initErakutsitakoak();
        hasi();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    /**
     * Jokuaren panelosoan egongo diren elementuak sortzen ditu, goiko panel bat
     * datuekin eta beheko panel bat jolasteko
     */
    public void hasi() {
        goikoPanela = new JPanel();
        goikoPanela.setLayout(new BoxLayout(goikoPanela, BoxLayout.X_AXIS));
        goikoPanela.setVisible(true);

        JLabel banderaTitulua = new JLabel("Spray-ak: ");
        banderaTitulua.setAlignmentX(Component.LEFT_ALIGNMENT);
        banderaTitulua.setHorizontalAlignment(JLabel.LEFT);
        goikoPanela.add(banderaTitulua);
        banderaFalta = new JLabel("" + banderaFaltaKop);
        goikoPanela.add(banderaFalta);

        jolastenBotoia = new JButton(jolasten);
        jolastenBotoia.setPreferredSize(new Dimension(this.KARRATU_TAMAINA + 10, this.KARRATU_TAMAINA + 10));
        jolastenBotoia.setMaximumSize(new Dimension(this.KARRATU_TAMAINA + 10, this.KARRATU_TAMAINA + 10));
        jolastenBotoia.setBorderPainted(true);
        jolastenBotoia.setName("jolasten");
        goikoPanela.add(Box.createRigidArea(new Dimension((tamaina - 1) * 15 - 95, 50)));
        goikoPanela.add(jolastenBotoia, BorderLayout.PAGE_START); //indica que el juego está funcionando
        goikoPanela.add(Box.createRigidArea(new Dimension((tamaina - 1) * 15 - 85, 50)));
        jolastenBotoia.addActionListener(this);
        //panelSuperior.add(numeroBanderas); indica el número de banderas puestas

        JLabel tiempo = new JLabel("Denbora: ");
        goikoPanela.add(tiempo);
        jolastutakoDenbora = new JLabel("0");
        tiempo.setAlignmentX(Component.RIGHT_ALIGNMENT);
        tiempo.setHorizontalAlignment(JLabel.RIGHT);
        goikoPanela.add(jolastutakoDenbora); //indica el tiempo que se ha estado jugando

        behekoPanela = new JPanel();
        behekoPanela.setVisible(true);
        GridLayout g2 = new GridLayout(tamaina, tamaina);
        behekoPanela.setLayout(g2);

        botoiak = new JButton[tamaina][tamaina];

        for (int i = 0; i < tamaina; i++) {
            for (int j = 0; j < tamaina; j++) {
                botoiak[i][j] = new JButton();
                botoiak[i][j].setPreferredSize(new Dimension(KARRATU_TAMAINA, KARRATU_TAMAINA));
                botoiak[i][j].setBorder(new LineBorder(Color.BLACK));
                botoiak[i][j].setBorderPainted(true);
                botoiak[i][j].setName(i + " " + j);
                botoiak[i][j].addMouseListener(this);
                behekoPanela.add(botoiak[i][j]);
            }
        }
        panelOsoa.add(goikoPanela);
        panelOsoa.add(behekoPanela);

    }

    /**
     * beheko panelean egongo diren botoien datuak sortzen ditu
     */
    public void zelaiaBete() {
        Random ausazkoa = new Random();

        zelaia = new int[tamaina][tamaina];
        for (int i = 0; i < tamaina; i++) {
            for (int j = 0; j < tamaina; j++) {
                zelaia[i][j] = 0;
            }
        }

        int jarritakoMinak = 0;
        int x;
        int y;
        while (jarritakoMinak < tamaina) {
            x = ausazkoa.nextInt(tamaina);
            y = ausazkoa.nextInt(tamaina);
            if (zelaia[x][y] != -1) {
                zelaia[x][y] = -1;
                jarritakoMinak++;
            }
        }

        for (int i = 0; i < tamaina; i++) {
            for (int j = 0; j < tamaina; j++) {
                if (zelaia[i][j] == -1) {
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {

                            try {
                                if (zelaia[i + k][j + l] != -1) {
                                    zelaia[i + k][j + l] += 1;
                                }
                            } catch (Exception e) {

                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * zein botoi klikatuta dauden eta bandera jarri den jakiteko
     */
    public void initErakutsitakoak() {
        erakutsita = new boolean[tamaina][tamaina];
        banderaJarrita = new boolean[tamaina][tamaina];
        for (int i = 0; i < tamaina; i++) {
            for (int j = 0; j < tamaina; j++) {
                erakutsita[i][j] = false;
                banderaJarrita[i][j] = false;
            }
        }
    }

    /**
     * jokuan ikusiko diren irudiak sortuko ditu
     */
    public void irudiakSortu() {
        mina = new ImageIcon("src/irudiak/covid.png");
        Image minaIrudi = mina.getImage();
        Image minaNeurri = minaIrudi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        mina = new ImageIcon(minaNeurri);

        jolasten = new ImageIcon("src/irudiak/Smiley.png");
        Image jolastenIrudi = jolasten.getImage();
        Image jolastenNeurri = jolastenIrudi.getScaledInstance(this.KARRATU_TAMAINA, this.KARRATU_TAMAINA, java.awt.Image.SCALE_SMOOTH);
        jolasten = new ImageIcon(jolastenNeurri);

        hilda = new ImageIcon("src/irudiak/dead.png");
        Image hildaIrudi = hilda.getImage();
        Image hildaNeurri = hildaIrudi.getScaledInstance(this.KARRATU_TAMAINA, this.KARRATU_TAMAINA, java.awt.Image.SCALE_SMOOTH);
        hilda = new ImageIcon(hildaNeurri);

        bandera = new ImageIcon("src/irudiak/spray.png");
        Image banderaIrudi = bandera.getImage();
        Image banderaNeurri = banderaIrudi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        bandera = new ImageIcon(banderaNeurri);
    }

    /**
     * botoi bat klikatzean zer gertatzen den
     *
     * @param x botoiaren kokapena arrayean
     * @param y botoiaren kokapena arrayean
     */
    public void botoiaKlikatuta(int x, int y) {
        if (!erakutsita[x][y] && !banderaJarrita[x][y]) {
            erakutsita[x][y] = true;
            switch (zelaia[x][y]) {
                case -1:
                    try {
                    botoiak[x][y].setIcon(mina);
                } catch (Exception e1) {

                }
                botoiak[x][y].setBackground(Color.RED);
                jolastenBotoia.setIcon(hilda);
                jolastenDago = false;
                JOptionPane.showMessageDialog(rootPane, "KUTSATU ZARA, 10 EGUN ETXEAN :'(");
                setVisible(false);
                Hasiera h = new Hasiera(tamaina);
                break;

                case 0:
                    botoiak[x][y].setBackground(Color.lightGray);
                    erakutsita[x][y] = true;
                    erakutsitaKop++;
                    if (erakutsitaKop == 1) {
                        jolastenDago = true;
                        kontatu();
                    }

                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            try {
                                botoiaKlikatuta(x + 1, y + j);
                            } catch (Exception e2) {

                            }
                        }
                        if (irabaziDuzu()) {
                            jolastenDago = false;
                            JOptionPane.showMessageDialog(rootPane, "ZORIONAK KALERA IRTETEN JARRAITU AHAL DUZU!!");
                            SQLiteKudeatu.puntuazioaGehitu(new Jokalaria(izena, Integer.parseInt(jolastutakoDenbora.getText()),tamaina));
                            setVisible(false);
                            Hasiera ha = new Hasiera(tamaina);
                            break;
                        }

                    }
                    break;
                default:
                    botoiak[x][y].setText(Integer.toString(zelaia[x][y]));
                    botoiak[x][y].setBackground(Color.LIGHT_GRAY);
                    erakutsita[x][y] = true;
                    erakutsitaKop++;
                    if (erakutsitaKop == 1) {
                        jolastenDago = true;
                        kontatu();
                    }
                    if (irabaziDuzu()) {
                        jolastenDago = false;
                        JOptionPane.showMessageDialog(rootPane, "ZORIONAK KALERA IRTETEN JARRAITU AHAL DUZU!!");
                        SQLiteKudeatu.puntuazioaGehitu(new Jokalaria(izena, Integer.parseInt(jolastutakoDenbora.getText()),tamaina));
                        setVisible(false);
                        Hasiera ha = new Hasiera(tamaina);
                        break;
                    }
                    break;
            }

        }
    }

    /**
     * eskubiko botoia, bandera jartzeko, klikatzean gertatzen dena
     *
     * @param x botoiaren kokapena arrayean
     * @param y botoiaren kokapena arrayean
     */
    public void eskubikoBotoiaKlikatuta(int x, int y) {
        if (!erakutsita[x][y]) {
            if (banderaJarrita[x][y]) {
                botoiak[x][y].setIcon(null);
                banderaJarrita[x][y] = false;
                banderaFaltaKop = Integer.parseInt(banderaFalta.getText());
                banderaFaltaKop++;
                banderaFalta.setText("" + banderaFaltaKop);
            } else {
                if (Integer.parseInt(banderaFalta.getText()) > 0) {
                    botoiak[x][y].setIcon(bandera);
                    banderaJarrita[x][y] = true;
                    banderaFaltaKop = Integer.parseInt(banderaFalta.getText());
                    banderaFaltaKop--;
                    banderaFalta.setText("" + banderaFaltaKop);
                }
            }
        }
    }

    /**
     * irabazteko bete behar den baldintza
     *
     * @return irabazteko baldintza betetzen den ala ez
     */
    public boolean irabaziDuzu() {
        return erakutsitaKop == Math.pow(tamaina, 2) - tamaina;
    }

    /**
     * jolastutako denbora kontatzen du
     */
    public void kontatu() {
        TimerTask task = new TimerTask() {
            int t = 0;

            @Override
            public void run() {
                if (jolastenDago) {
                    jolastutakoDenbora.setText("" + t++);
                }
            }
        };
        denbora.schedule(task, 0, 1000);

    }

    /**
     * botoi bat klikatzen denean zer gertatzen den
     *
     * @param em zer botoi klikatu den jakiteko eta akzioa kontrolatzeko
     */
    @Override
    public void mouseClicked(MouseEvent em) {
        if ((erakutsitaKop > 0 && jolastenDago) || erakutsitaKop == 0) {
            Object eventSource = em.getSource();
            JButton klikatutakoBotoia = (JButton) eventSource;
            String[] xy = klikatutakoBotoia.getName().split(" ", 2);
            int x = Integer.parseInt(xy[0]);
            int y = Integer.parseInt(xy[1]);
            if (em.getButton() == MouseEvent.BUTTON3) {
                eskubikoBotoiaKlikatuta(x, y);
            } else {
                botoiaKlikatuta(x, y);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent em) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * botoi bat klikatzen denean zer gertatzen den
     *
     * @param e zer botoi klikatu den jakiteko eta akzioa kontrolatzeko
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object eventSource = e.getSource();
        JButton klikatutakoBotoia = (JButton) eventSource;
        if (klikatutakoBotoia == jolastenBotoia) {
            jolastenDago = !jolastenDago;
        }
    }
}
