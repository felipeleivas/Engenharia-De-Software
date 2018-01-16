package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.RegistredUser;

public class RegistredUserDAO {

	private Connection con = null;
	
	public RegistredUserDAO() {
		con = ConnectionFactory.getConnection();
	}
	
	public boolean create(RegistredUser user) throws SQLException{
		
		String sql = "INSERT INTO registredusers (email,password,name,adress) VALUES (?,?,?,?)";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user.getEmail());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getName());
			stmt.setString(4, user.getAddress());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
			if(e.getErrorCode() == 1062){
				throw e;
			}
			return false;
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public Boolean update(Object x, Object y){
		return true;
	}	

	public ArrayList<RegistredUser> searchByName(String name) {
		String sql = "SELECT email from registredusers where name like ? ";
		BookDAO bookDAO = new BookDAO();
		BookDAO bookDAO2 = new BookDAO();
		name = "%" + name + "%";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<RegistredUser> users = new ArrayList<RegistredUser>();
			RegistredUser user = null;
			while(resultSet.next()) {
				user = this.readByEmail(resultSet.getString(1));
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			System.err.println("Erro SearchUserByName " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public RegistredUser readByEmail(String email) throws SQLException {
		String sql = "SELECT email,password,name,adress from registredusers where email = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			resultSet.next();
			valores.add(resultSet.getString(1));
			valores.add(resultSet.getString(2));
			valores.add(resultSet.getString(3));
			valores.add(resultSet.getString(4));		
			RegistredUser user = new RegistredUser(valores.get(0), valores.get(1), valores.get(2), valores.get(3));
			BookDAO bookDAO = new BookDAO();
			BookDAO bookDAO2 = new BookDAO();
			user.setOwnBooks(bookDAO.readOwnBooks(valores.get(0)));
			user.setWantBooks(bookDAO2.readWantBooks(valores.get(0)));
			ExchangeProposalDAO exchangeProposalDAO = new ExchangeProposalDAO();
			user.setProposals(exchangeProposalDAO.readUserProposals(valores.get(0)));
			return user;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
			if(e.getErrorCode() == 0){
				throw e;
			}
		} 
		return null;
	}
}
