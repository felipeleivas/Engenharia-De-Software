package managment;

import java.sql.SQLException;
import java.util.ArrayList;

import database.DataBase;
import domain.Book;
import domain.ExchangeProposal;
import domain.RegistredUser;
import userInterface.LoginUI;
import userInterface.MainUI;
import userInterface.RegistrationUI;
import userInterface.SearchBookUI;
import userInterface.SearchUserUI;
import userInterface.UserExchangeProposalsUI;
import userInterface.UserOwnBooksUI;
import userInterface.UserProfileUI;
import utils.AddTypes;
import userInterface.AddUserBookUI;
import userInterface.ListBooksUI;
import userInterface.ListUsersUI;

public class Controller {
	private DataBase database;
	private SessionData sessionData;
	private ArrayList<Book> wantBookToTradeList;
	private String wantBookUserEmail;
	
	public Controller(DataBase database) {
		openLogin();
		this.database = database;
		this.sessionData = new SessionData(database);
		this.wantBookToTradeList = new ArrayList<Book>();
	}
	
	public void openRegistration() {
		RegistrationUI registrationUI = new RegistrationUI(this);
		registrationUI.openUI();
	}
	
	public void openLogin() {
		LoginUI loginUI = new LoginUI(this);
		loginUI.openUI();
	}
	
	public void openMain() {
		MainUI mainUI = new MainUI(this);
		mainUI.openUI();
	}
	
	public void openAddOwnBookToUser() {
		AddUserBookUI userBooksUI = new AddUserBookUI(this, AddTypes.AddOwnedBook);
		userBooksUI.openUI();
	}
	
	public void openAddWantBookToUser() {
		AddUserBookUI userBooksUI = new AddUserBookUI(this, AddTypes.AddWantBook);
		userBooksUI.openUI();
	}
	
	public void openSearchUser() {
		SearchUserUI searchUserUI = new SearchUserUI(this);
		searchUserUI.openUI();
	}
	
	public void openSearchBook() {
		SearchBookUI searchBookUI = new SearchBookUI(this);
		searchBookUI.openUI();
	}
	
	public void openUserExchangeProposals() {
		UserExchangeProposalsUI userExchangeProposalsUI = new UserExchangeProposalsUI(this, this.sessionData.getLoggedUser());
		userExchangeProposalsUI.openUI();
	}
	
	public ArrayList<Book> searchBooks(String title, String autor, String genre, String isbn) {
		return this.database.searchBooks(title, autor, genre, isbn);
	}
	
	public void openListUsers(ArrayList<RegistredUser> searchedUsers) {
		ListUsersUI listUsersUI = new ListUsersUI(this,searchedUsers);
		listUsersUI.openUI(searchedUsers);
	}
	
	public void openListBooks(ArrayList<Book> searchedBooks) {
		ListBooksUI listBooksUI = new ListBooksUI(this, searchedBooks);
		listBooksUI.openUI();
	}
	
	public void openUserProfile(RegistredUser user) {
		UserProfileUI userProfileUI = new UserProfileUI(this, user);
		userProfileUI.openUI();		
	}
	
	public void openUserOwnBooks() {
		UserOwnBooksUI userOwnBooksUI = new UserOwnBooksUI(this, this.sessionData.getLoggedUser());
		userOwnBooksUI.openUI();
	}
	
	public boolean registerUser(String email, String password, String name, String address) throws SQLException {
		try {
			RegistredUser newUser = new RegistredUser(email, password, name, address);
			return this.database.createRegistredUser(newUser);
		} catch(SQLException e) {
			throw e;
		}
	}
	
	public boolean addOwnBook(String title, String author, String genre, String edition, String language, String ISBN, String photo, String comment) {
		Book book = new Book(title, author, genre, edition, language, ISBN, photo, comment);
		return this.database.addOwnBook(book, sessionData.getLoggedUser().getEmail());
	}
	
	public ArrayList<Book> getOwnBookList() {
		return this.database.readOwnBookList(this.sessionData.getLoggedUser().getEmail());
	}
	
	public boolean updateOwnBook(Book oldBook, Book newBook) {
		return this.database.updateOwnBook(oldBook, newBook, sessionData.getLoggedUser().getEmail());
	}
	
	public boolean addWantBook(String title, String author, String genre, String edition, String language, String ISBN, String photo, String comment) {
		Book book = new Book(title, author, genre, edition, language, ISBN, photo, comment);
		return this.database.addWantBook(book,sessionData.getLoggedUser().getEmail());
	}
	
	public ArrayList<Book> getWantBookList() {
		return this.database.readWantBookList(sessionData.getLoggedUser().getEmail());
	}
	
	public boolean login(String username, String password) throws SQLException {
		return this.sessionData.login(username, password);
	}
	
	public void logout() {
		this.sessionData.logout();
		openLogin();
	}
	
	public ArrayList<RegistredUser> researchUsers(String name) {
		return this.database.searchUsers(name);
	}
	
	public void setWantBookToTrade(Book book) {
		this.wantBookToTradeList.add(book);
		this.wantBookUserEmail = this.database.readBookEmail(book);
	}
	
	public void setWantBookToTrade(Book book, RegistredUser user) {
		this.wantBookToTradeList.add(book);
		this.wantBookUserEmail = user.getEmail();
	}
	
	public void setOwnBookToTrade(Book book) {
		ArrayList<Book> ownBookList = new ArrayList<Book>();
		ownBookList.add(book);
		this.sessionData.getLoggedUser().addExchangeProposal(ownBookList, this.wantBookToTradeList, this.wantBookUserEmail, this.database);
		cleanProposal();
	}
		
	public void aceptExchangeProposal(ExchangeProposal proposal) {
		this.sessionData.getLoggedUser().aceptExchangeProposal(proposal, this.database);
	}
	
	public void cancelExchangeProposal(ExchangeProposal proposal) {
		this.sessionData.getLoggedUser().cancelExchangeProposal(proposal, this.database);
	}
	
	public void cleanProposal() {
		this.wantBookToTradeList = new ArrayList<Book>();
		this.wantBookUserEmail = "";
	}
}
