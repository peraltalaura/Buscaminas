/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
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

    private final String[] ZUTABEIZENAK = {"ID", "JOKALARIA", "DENBORA"};
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

    public JokalarienPuntuazioa() {
        FileInputStream fin = null;
        String sql = "SELECT id, izena, denbora FROM Rankinga";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            
            // loop through the result set
            while (rs.next()) {
                jokalariak.add(new Jokalaria(rs.getInt("id"),rs.getString("izena"), rs.getString("denbora")));
//                j.setIzena(rs.getString("izena"));
//                j.setDenbora(rs.getString("denbora"));
//                jokalariak.add(j);
//                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
        if (columnIndex == 0) {
            return jokalariak.get(rowIndex).getId();
        } else if (columnIndex == 1) {
            return jokalariak.get(rowIndex).getIzena();
        }else if (columnIndex == 2) {
            return jokalariak.get(rowIndex).getDenbora();
        }else{
                return null;
            }
    }
}
