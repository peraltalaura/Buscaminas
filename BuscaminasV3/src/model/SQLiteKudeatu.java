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

    public void puntuazioaGehitu(String izena, String denbora) {
        String sql = "INSERT INTO Rankinga(izena,denbora) VALUES(?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, izena);
            pstmt.setString(2, denbora);
            pstmt.executeUpdate();
            System.out.println("Jokalaria gehitu da.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
