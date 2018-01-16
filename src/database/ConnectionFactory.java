package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionFactory {
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/database?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "";
	
	public static Connection getConnection(){
		try {
			Class.forName(DRIVER);			
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Erro ao estabelecer a conex�o com o Banco de Dados.");
			throw new RuntimeException("Erro na conex�o.", e);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao estabelecer a conex�o com o Banco de Dados.");
			throw new RuntimeException("Erro na conex�o.", e);
		}
	}
	
	public static void closeConnection(Connection con){
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Erro" + e);
			}
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt){
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Erro" + e);
			}
		}
		
		closeConnection(con);
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				System.err.println("Erro" + e);
			}
		}
		
		closeConnection(con, stmt);
	}
	
	
}