/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author peralta.laura
 */
public class JokalarienPuntuazioa extends AbstractTableModel {

    private final String[] ZUTABEIZENAK = {"ID", "JOKALARIA", "DENBORA", "TAMAINA"};
    private ArrayList<Integer> idak = new ArrayList<>();
    private ArrayList<Jokalaria> jokalariak = new ArrayList<>();

    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:db/ranking.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public JokalarienPuntuazioa(int tamaina) {
        String sql = "SELECT izena, denbora, laukiak FROM Rankinga";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            int i = 0;
            // loop through the result set
            while (i < 10 && rs.next()) {
                if (tamaina == rs.getInt("laukiak") && tamaina != 0) {
                    i++;
                    idak.add(i);
                    jokalariak.add(new Jokalaria(rs.getString("izena"), rs.getInt("denbora"), rs.getInt("laukiak")));
                } else if (tamaina == 0) {
                    i++;
                    idak.add(i);
                    jokalariak.add(new Jokalaria(rs.getString("izena"), rs.getInt("denbora"), rs.getInt("laukiak")));
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        jokalariOnenak(jokalariak);

    }

    private void jokalariOnenak(ArrayList<Jokalaria> j) {
        for (int n = 0; n < j.size(); n++) {
            for (int i = n + 1; i < j.size(); i++) {
                if (j.get(n).getDenbora() > j.get(i).getDenbora()) {
                    Jokalaria temp = new Jokalaria();
                    temp.setJokalaria(j.get(n));
                    j.get(n).setJokalaria(j.get(i));
                    j.get(i).setJokalaria(temp);
                }
            }
        }
    }

    @Override
    public String getColumnName(int col) {
        return ZUTABEIZENAK[col];

    }

    @Override
    public int getRowCount() {
        return jokalariak.size();
    }

    @Override
    public int getColumnCount() {
        return ZUTABEIZENAK.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return idak.get(rowIndex);
            case 1:
                return jokalariak.get(rowIndex).getIzena();
            case 2:
                return jokalariak.get(rowIndex).getDenbora() + " s";
            case 3:
                return jokalariak.get(rowIndex).getLaukiak();
        }
        return null;
    }
}
