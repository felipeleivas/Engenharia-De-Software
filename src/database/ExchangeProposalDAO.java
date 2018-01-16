package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Book;
import domain.ExchangeProposal;

public class ExchangeProposalDAO {
	private Connection con = null;

	public ExchangeProposalDAO() {
		con = ConnectionFactory.getConnection();
	}
	
	
	public Boolean createExchangeProposal(ExchangeProposal exchangeProposal) {

		String sql = "INSERT INTO exchangeProposal (statusUser1,statusUser2,emailUser1,emailUser2,id) VALUES (?,?,?,?,?)";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, exchangeProposal.getStatusUser1());
			stmt.setString(2, exchangeProposal.getStatusUser2());
			stmt.setString(3, exchangeProposal.getUser1Email());
			stmt.setString(4, exchangeProposal.getUser2Email());	
			int proposalId = this.getNextAvailableId();
			stmt.setInt(5, proposalId);
			stmt.executeUpdate();
			this.createUser1BooksOnProposal(exchangeProposal.getUser1Books(), proposalId);
			this.createUser2BooksOnProposal(exchangeProposal.getUser2Books(), proposalId);

			return true;
		} catch (SQLException e) {
			System.err.println("Erro Criando exchangeproposal no banco " + e);
			return false;
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public Boolean createUser1BooksOnProposal(ArrayList<Book> user1books, Integer proposalId) {

		String sql = "INSERT INTO exchangeProposalBookUser1 (bookId,proposalId) VALUES (?,?)";
		PreparedStatement stmt = null;
		
		try {
			for(Book book: user1books) {	
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, book.getBookId());
				stmt.setInt(2, proposalId);
				stmt.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			System.err.println("Erro Criando exchangeproposaluser1 no banco " + e);
			return false;
		}
	}
	
	public Boolean createUser2BooksOnProposal(ArrayList<Book> user2books, Integer proposalId) {

		String sql = "INSERT INTO exchangeProposalBookUser2 (bookId,proposalId) VALUES (?,?)";
		PreparedStatement stmt = null;
		
		try {
			for(Book book: user2books) {	
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, book.getBookId());
				stmt.setInt(2, proposalId);
				stmt.executeUpdate();
			}
			return true;
		} catch (SQLException e) {
			System.err.println("Erro createUser2BooksOnProposal " + e);
			return false;
		}
	}
	
	public ExchangeProposal readProposal(Integer proposalId) {
		String sql = "Select statusUser1,statusUser2,emailUser1,emailUser2,id from exchangeProposal where exchangeProposal.id = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, proposalId);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				ExchangeProposal exchangeProposal = new ExchangeProposal();
				exchangeProposal.setStatusUser1(resultSet.getString(1));
				exchangeProposal.setStatusUser2(resultSet.getString(2));
				exchangeProposal.setUser1Email(resultSet.getString(3));
				exchangeProposal.setUser2Email(resultSet.getString(4));
				int bookId = resultSet.getInt(5);
				System.out.println("bookId atual : " + bookId);
				exchangeProposal.setId(bookId);
				exchangeProposal.setUser1Books(this.readUser1ProposalBooks(bookId));
				exchangeProposal.setUser2Books(this.readUser2ProposalBooks(bookId));
				System.out.println("aqui " + exchangeProposal.getUser2Email());
				System.out.println(exchangeProposal.getUser1Books().get(0).getTitle());
				return exchangeProposal;
				}
		} catch (SQLException e) {
			System.err.println("Erro readProposal aqui " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;

	}
	
	public ArrayList<Book> readUser1ProposalBooks(Integer proposalId){
		String sql = "Select bookId from exchangeProposalBookUser1 where exchangeProposalBookUser1.proposalId = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> user1bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, proposalId);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				BookDAO bookDAO = new BookDAO();
				System.out.println("To aquiiii " + resultSet.getInt(1));
				user1bookList.add(bookDAO.readBooksById(resultSet.getInt(1)));
				
				}
			return user1bookList;
		} catch (SQLException e) {
			System.err.println("Erro readUser1ProposalBooks " + e);
		}
		return user1bookList;

	}
	public ArrayList<Book> readUser2ProposalBooks(Integer proposalId){
		String sql = "Select bookId from exchangeProposalBookUser2 where exchangeProposalBookUser2.proposalId = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> user2bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, proposalId);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				BookDAO bookDAO = new BookDAO();
				user2bookList.add(bookDAO.readBooksById(resultSet.getInt(1)));
				
				}
			return user2bookList;
		} catch (SQLException e) {
			System.err.println("Erro readUser2ProposalBooks " + e);
		}
		return user2bookList;

	}
	
	public ArrayList<ExchangeProposal> readUserProposals(String userEmail){
		String sql = "Select id from exchangeProposal where exchangeProposal.emailUser1 = ? or exchangeProposal.emailUser2 = ?";
		PreparedStatement stmt = null;
		ArrayList<ExchangeProposal> userProposals = new ArrayList<ExchangeProposal>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, userEmail);
			stmt.setString(2, userEmail);
			ResultSet resultSet = stmt.executeQuery();
			ExchangeProposalDAO exchangeProposalDAO = new ExchangeProposalDAO();
			while(resultSet.next()) {
				System.out.println(resultSet.getInt(1));
				userProposals.add(exchangeProposalDAO.readProposal(resultSet.getInt(1)));
				}
			System.out.println(userProposals.get(0));
			return userProposals;
		} catch (SQLException e) {
			System.err.println("Erro getUserProposals " + e);
		}
		System.out.println(userProposals);
		return userProposals;
	}
	

	public boolean updateProposal(ExchangeProposal exchangeProposal) {
		
	String sql = "update exchangeProposal set statusUser1 = ? ,statusUser2 = ? where exchangeProposal.id = ?";
	PreparedStatement stmt = null;
	ArrayList<Book> user2bookList = new ArrayList<Book>();
	
	try {
		stmt = con.prepareStatement(sql);
		stmt.setString(1, exchangeProposal.getStatusUser1());
		stmt.setString(2, exchangeProposal.getStatusUser2());
		stmt.setInt(3, exchangeProposal.getId());

		stmt.executeUpdate();
		
		return true;
	} catch (SQLException e) {
		System.err.println("Erro updateProposal " + e);
	}
	return false;
	}
	
	public boolean deleteProposal(ExchangeProposal exchangeProposal) {
		
		String sql = "delete from exchangeProposal where exchangeProposal.id=?;";
		PreparedStatement stmt = null;
		ArrayList<Book> user2bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, exchangeProposal.getId());

			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				BookDAO bookDAO = new BookDAO();
				user2bookList.add(bookDAO.readBooksById(resultSet.getInt(1)));
				
				}
			return true;
		} catch (SQLException e) {
			System.err.println("Erro deleteProposal " + e);
		}
		return false;
		}


	private Integer getNextAvailableId() {
		String sql = "select max(id) from exchangeProposal";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				return 1 + resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("Erro getNextAvailableId " + e);
		}
		return 0;
	}
}
