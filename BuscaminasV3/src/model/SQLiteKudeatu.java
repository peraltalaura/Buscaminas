/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author peralta.laura
 */
public class SQLiteKudeatu {

    private static Connection connect() {
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

    public static void puntuazioaGehitu(Jokalaria j) {
        String sql = "INSERT INTO Rankinga(izena,denbora,laukiak) VALUES(?,?,?)";

        try (Connection conn = SQLiteKudeatu.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, j.getIzena());
            pstmt.setInt(2, j.getDenbora());
            pstmt.setInt(3, j.getLaukiak());
            pstmt.executeUpdate();
            System.out.println("Jokalaria gehitu da.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
