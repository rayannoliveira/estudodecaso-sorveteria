/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilitarios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionFactory {
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/bdsorveteria";
    private final static String USER = "root";
    private final static String SENHA = "";
//Metodo responsavel por estabelecer a conexão com o BD
    public static Connection getConnection() {
    
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, SENHA);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Erro na conex�o", e);
        }
    }

    public static void closeConection(Connection con) {

        try {
            if (con != null) {
                con.close();

            }
        } catch (SQLException e) {

            throw new RuntimeException("Erro na conex�o", e);
        }

    }

    public static void closeConection(Connection con, PreparedStatement stmt) {

        closeConection(con);

        try {

            if (stmt != null) {
                stmt.close();

            }
        } catch (SQLException e) {

            throw new RuntimeException("Erro na conex�o", e);
        }

    }

    public static void closeConection(Connection con, PreparedStatement stmt, ResultSet rs) {

        closeConection(con, stmt);

        try {

            if (rs != null) {
                rs.close();

            }
        } catch (SQLException e) {

            throw new RuntimeException("Erro na conex�o", e);
        }

    }

}
