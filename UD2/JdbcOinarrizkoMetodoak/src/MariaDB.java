
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MariaDB {

    public static void main(String[] args) {
        String database = "db1";
        String taula = "irakasleak";
        connect("");

        // createNewDatabase("db1");
        // createNewTable("db1", "irakasleak");
        // insert(database, taula);
        //delete(3, database, taula);
        //adinaGehitu(database, taula);
        //updateAdina(database, taula);
        //selectAll(database);

    }

    public static Connection connect(String db) {

        String server = "localhost";
        String url = "jdbc:mysql://" + server + "/" + db;
        String user = "root";
        String pass = "root";
        Connection conn = null;
        System.out.println("Konektatu zara!");
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " - " + e.getMessage());
        }
        return conn;
    }

    public static void createNewDatabase(String db) {
        try ( Connection conn = connect("");  Statement stmt = conn.createStatement()) {

            DatabaseMetaData meta = conn.getMetaData();
            System.out.println("The driver name is " + meta.getDriverName());
            String sql = "CREATE DATABASE " + db;
            stmt.executeUpdate(sql);
            System.out.println(db + " datu-basea sortu da.");
        } catch (SQLException ex) {
            System.out.println("Errorea dba sortzerakoan (" + ex.getErrorCode() + "-" + ex.getLocalizedMessage());
        }
    }

    public static void createNewTable(String database, String taula) {

        String sql = "CREATE TABLE IF NOT EXISTS " + taula + "(\n"
                + "    id integer PRIMARY KEY AUTO_INCREMENT,\n"
                + "    izena text);";

        try (
                 Connection conn = connect(database);  Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println(taula + " taula sortua");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(String database, String taula) {
        String sql = "INSERT INTO " + taula + "(izena) Values(?)";

        try ( Connection conn = connect(database);  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "Madariaga");
            pstmt.executeUpdate();

            pstmt.setString(1, "Igor");
            pstmt.executeUpdate();

            pstmt.setString(1, "Gorka");
            pstmt.executeUpdate();

            System.out.println("Erregistro batzuk Txertatu dira.");

        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " ===> " + e.getMessage());
            System.out.println("Txertatu nahi duzun erregistroaren id-a dagoeneko existitzen da.");
        }
    }

    public static void selectAll(String databse) {
        String sql = "SELECT * FROM irakasleak";

        try ( Connection conn = connect(databse);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("izena")+"\t"
                        +rs.getInt("adina"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete(int id, String database, String taula) {
        String sql = "DELETE FROM " + taula + " WHERE id = ?";

        try ( Connection conn = connect(database);  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void adinaGehitu(String database, String taula) {

        String sql = "ALTER TABLE " + taula + " ADD adina int";

        try (
                 Connection conn = connect(database);  Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("adina gehitu da taulan");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateAdina(String database, String taula) {

        String sql = " UPDATE " + taula + " SET adina=LENGTH(izena)*5";

        try (
                 Connection conn = connect(database);  Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("adinak gehitu dira taulan");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
