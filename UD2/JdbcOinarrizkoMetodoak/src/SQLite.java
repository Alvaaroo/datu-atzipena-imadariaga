
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author sqlitetutorial.net
 */
public class SQLite {

    public static void main(String[] args) {
        String dbIzena;
        dbIzena = "datubaseak/db1.db";

        connect(dbIzena);

        //  createNewDatabase(dbIzena);
        String taulaIzena;
        taulaIzena = "Taula1";

        //  createTable(dbIzena, taulaIzena);
        insert(dbIzena, taulaIzena);
       
       

        selectAll(dbIzena);
        
        //delete(3,dbIzena,taulaIzena);

    }

    public static Connection connect(String db) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + db;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewDatabase(String fileName) {

        Connection conn = connect(fileName);

        try {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException ex) {
            System.out.println("Ezin konektatu.Ezin datubasea sortu.");
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Errorea konexio ixterakoan");
                }
            }
        }

    }

    public static void createTable(String database, String table) {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS " + table + "(\n"
                + "    id integer PRIMARY KEY,\n"
                + "    eremu1 text,\n"
                + "    eremu2 text)";

        try (
                 Connection conn = connect(database);  Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println(table + " taula sortua");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insert(String database, String taula) {
        String sql = "INSERT INTO " + taula + "(id,eremu1) Values(?,?)";

        try ( Connection conn = connect(database);  PreparedStatement pstmt = conn.prepareStatement(sql)) {
           
             pstmt.setInt(1, 1);
            pstmt.setString(2, "Jorgito");
            pstmt.executeUpdate();        

            pstmt.setInt(1, 2);
            pstmt.setString(2, "Juanito");
            pstmt.executeUpdate();
            
            pstmt.setInt(1, 3);
            pstmt.setString(2, "Jaimito");
            pstmt.executeUpdate();

            

            System.out.println("Erregistro batzuk Txertatu dira.");

        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " ===> " + e.getMessage());
            System.out.println("Txertatu nahi duzun erregistroaren id-a dagoeneko existitzen da.");
        }
    }

    public static void selectAll(String databse) {
        String sql = "SELECT * FROM Taula1";

        try ( Connection conn = connect(databse);  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t"
                        + rs.getString("eremu1"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete(int id,String database,String taula ) {
        String sql = "DELETE FROM "+taula+" WHERE id = ?";

        try ( Connection conn = connect(database);  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
