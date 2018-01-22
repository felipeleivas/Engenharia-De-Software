package database;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.Book;
import domain.ExchangeProposal;
import domain.RegistredUser;

public class DataBase {

	public ArrayList<Book> searchBooks(String title, String autor, String genre, String isbn){
		BookDAO bookDAO = new BookDAO();
		return bookDAO.searchBooks(title, autor, genre, isbn);
	}
	
	public boolean createRegistredUser(RegistredUser user) throws SQLException{
		RegistredUserDAO userDAO = new RegistredUserDAO();
		
		try {
			if(userDAO.create(user)){
				return true;
			}else{
				return false;
			}	
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public RegistredUser readRegistredUser(String username) throws SQLException{
		RegistredUserDAO userDAO = new RegistredUserDAO();
		try {
			return userDAO.readByEmail(username);
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public boolean updateRegistredUser(RegistredUser oldUser, RegistredUser newUser){
		RegistredUserDAO userDAO = new RegistredUserDAO();
		
		if(userDAO.update(oldUser, newUser)){
			return true;
		}else{
			return false;
		}
	}
	
	/*public boolean deleteRegistredUser(RegistredUser user){
		RegistredUserDAO userDAO = new RegistredUserDAO();
		
		if(userDAO.delete(user)){
			return true;
		}else{
			return false;
		}
	}*/
		
	public ArrayList<RegistredUser> searchUsers(String name) {
		RegistredUserDAO userDAO = new RegistredUserDAO();
		return userDAO.searchByName(name);
	}
	
	public boolean addOwnBook(Book book, String username) {
		BookDAO bookDAO = new BookDAO();
		if(bookDAO.addOwnBook(book, username)) {
			return true;
		}else{
			return false;
		}
	}
		
	public ArrayList<Book> readOwnBookList(String username) {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.readOwnBooks(username);
	}
	
	public String readBookEmail(Book book) {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.readBookEmail(book);
	}
	
	public boolean updateOwnBook(Book oldBook, Book newBook, String username) {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.updateOwnBook(oldBook, newBook, username);
	}
	
	public boolean addWantBook(Book book, String username) {
		BookDAO bookDAO = new BookDAO();
		if(bookDAO.addWantBook(book, username)) {
			return true;
		}else{
			return false;
		}
	}
	
	public ArrayList<Book> readWantBookList(String email) {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.readWantBooks(email);
	}
	
	public boolean deleteOwnBook(Book book, String email) {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.delete(book, email);
	}
	
	public boolean addExchangeProposal(ExchangeProposal exchangeProposal) {
		ExchangeProposalDAO exchangeProposalDAO = new ExchangeProposalDAO();
		return exchangeProposalDAO.createExchangeProposal(exchangeProposal);
	}
	
	public ArrayList<ExchangeProposal> readUserExchangeProposals(String email) {
		ExchangeProposalDAO exchangeProposalDAO = new ExchangeProposalDAO();
		return exchangeProposalDAO.readUserProposals(email);
	}
	
	public boolean updateExchangeProposal(ExchangeProposal exchangeProposal) {
		ExchangeProposalDAO exchangeProposalDAO = new ExchangeProposalDAO();
		return exchangeProposalDAO.updateProposal(exchangeProposal);
	}
	
	public boolean removeExchangeProposal(ExchangeProposal proposal){
		ExchangeProposalDAO exchangeProposalDAO = new ExchangeProposalDAO();
		return exchangeProposalDAO.deleteProposal(proposal);
	}
	
	public ArrayList<ExchangeProposal> readAceptedProposals() {
		ExchangeProposalDAO exchangeProposalDAO = new ExchangeProposalDAO();
		return exchangeProposalDAO.readAceptedProposals();
	}
}
