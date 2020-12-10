package model;

import global.Global;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Ikaslea;

public class MariaDB {

    public static Connection konektatu() {

        String url = "jdbc:mysql://" + Global.ZERBITZARIA + "/" + Global.DATUBASEA;
        Connection conn = null;
        System.out.println("Konektatu zara!");
        try {
            conn = DriverManager.getConnection(url, Global.getErabiltzailea(), Global.getPasahitza());
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
        }
        return conn;
    }

    public static ObservableList<Ikaslea> datuakMemorianKargatu() {

        ObservableList<Ikaslea> data = FXCollections.observableArrayList();
        Connection konekzioa = null;
        konekzioa = konektatu();
        ResultSet rs = null;

        try {
            Statement stmt = konekzioa.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ikaslea");

            while (rs.next()) {
                int zenbakia = rs.getInt("zenbakia");
                String izena = rs.getString("izena");
                String abizena = rs.getString("abizena1");

                Ikaslea ikasle = new Ikaslea(zenbakia, izena, abizena);
                data.add(ikasle);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;

    }

    public static boolean gehitu(Ikaslea ika) {

        String sql = "INSERT INTO Ikaslea(zenbakia,izena,abizena1) VALUES (?,?,?)";

        try {
            Connection conn = konektatu();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, ika.getZenbakia());
            pstmt.setString(2, ika.getIzena());
            pstmt.setString(3, ika.getAbizena1());
            pstmt.executeUpdate();
            logGehitu(pstmt.toString());
            return true;

        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return false;
        }

    }

    public static boolean ezabatu(Ikaslea ika) {

        String sql = "DELETE FROM Ikaslea WHERE zenbakia = ?";

        try {
            Connection conn = konektatu();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the corresponding param
            pstmt.setInt(1, ika.getZenbakia());
            // execute the delete statement
            pstmt.executeUpdate();
            logGehitu(pstmt.toString());
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
            return false;
        }

        return true;
    }

    public static int aldatu(int zein, String zutabea, String balioBerria) {

        int aldatuak = 0;
        String sql = "UPDATE Ikaslea SET " + zutabea + " = ? WHERE zenbakia = ?";

        try {
            Connection conn = konektatu();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, balioBerria);
            pstmt.setInt(2, zein);
            aldatuak = pstmt.executeUpdate();
            logGehitu(pstmt.toString());
            System.out.println(zein + " ikaslearen " + zutabea + " aldatu egin da datu basean");

            return aldatuak;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    public static void logGehitu(String deskripzioa) {

        String sql = "INSERT INTO log(User,Description) VALUES (?,?)";

        try {
            Connection conn = konektatu();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Global.getErabiltzailea());
            pstmt.setString(2, deskripzioa);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
