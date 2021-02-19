/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jolasa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Scanner;
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
public class Jokua extends JFrame implements MouseListener {

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
    private JButton jolastenDago;
    private JLabel jolastutakoDenbora;

    public Jokua(int tamaina) {
        panelOsoa = new JPanel();
        panelOsoa.setLayout(new BoxLayout(panelOsoa, BoxLayout.Y_AXIS));
        add(panelOsoa);
        setTitle("BUSCAMINAS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.tamaina = tamaina;
        banderaFaltaKop = tamaina;
        erakutsitaKop = 0;
        irudiakSortu();
        zelaiaBete();
        initErakutsitakoak();
        hasi();
        setVisible(true);
        setSize(tamaina * KARRATU_TAMAINA, tamaina * KARRATU_TAMAINA + 50);

    }

    public void hasi() {
        goikoPanela = new JPanel();
        goikoPanela.setLayout(new BoxLayout(goikoPanela, BoxLayout.X_AXIS));
        goikoPanela.setVisible(true);

        JLabel banderaTitulua = new JLabel("Banderak ");
        banderaTitulua.setAlignmentX(Component.LEFT_ALIGNMENT);
        banderaTitulua.setHorizontalAlignment(JLabel.LEFT);
        goikoPanela.add(banderaTitulua);
        banderaFalta = new JLabel("" + banderaFaltaKop);
        goikoPanela.add(banderaFalta);

        jolastenDago = new JButton(jolasten);
        jolastenDago.setPreferredSize(new Dimension(this.KARRATU_TAMAINA + 10, this.KARRATU_TAMAINA + 10));
        jolastenDago.setMaximumSize(new Dimension(this.KARRATU_TAMAINA + 10, this.KARRATU_TAMAINA + 10));
        jolastenDago.setBorderPainted(true);
        jolastenDago.setName("jolasten");
        goikoPanela.add(Box.createRigidArea(new Dimension((tamaina - 1) * 15 - 95, 50)));
        goikoPanela.add(jolastenDago, BorderLayout.PAGE_START); //indica que el juego está funcionando
        goikoPanela.add(Box.createRigidArea(new Dimension((tamaina - 1) * 15 - 85, 50)));
        //panelSuperior.add(numeroBanderas); indica el número de banderas puestas

        JLabel tiempo = new JLabel("Tiempo ");
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

    public void irudiakSortu() {
        mina = new ImageIcon("src/irudiak/mine.png");
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

        bandera = new ImageIcon("src/irudiak/flag.png");
        Image banderaIrudi = bandera.getImage();
        Image banderaNeurri = banderaIrudi.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        bandera = new ImageIcon(banderaNeurri);
    }

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
                jolastenDago.setIcon(hilda);
                JOptionPane.showMessageDialog(rootPane, "GALDU DUZU :'(");
                System.exit(0);
                break;

                case 0:
                    botoiak[x][y].setBackground(Color.lightGray);
                    erakutsita[x][y] = true;
                    erakutsitaKop++;

                    

                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            try {
                                botoiaKlikatuta(x + 1, y + j);
                            } catch (Exception e2) {

                            }
                        }
                        if (irabaziDuzu()) {
                        JOptionPane.showMessageDialog(rootPane, "IRABAZI DUZU!!");
                        System.exit(0);
                    }

                    }
                    break;
                default:
                    botoiak[x][y].setText(Integer.toString(zelaia[x][y]));
                    botoiak[x][y].setBackground(Color.LIGHT_GRAY);
                    erakutsita[x][y] = true;
                    erakutsitaKop++;

                    if (irabaziDuzu()) {
                        JOptionPane.showMessageDialog(rootPane, "IRABAZI DUZU!!");
                        System.exit(0);
                    }
                    break;
            }

        }
    }

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

    public boolean irabaziDuzu() {
        return erakutsitaKop == Math.pow(tamaina, 2) - tamaina;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Sartu ilara kopurua:");
        int tamaina = in.nextInt();
        Jokua j = new Jokua(tamaina);
    }

    @Override
    public void mouseClicked(MouseEvent em) {
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
}
