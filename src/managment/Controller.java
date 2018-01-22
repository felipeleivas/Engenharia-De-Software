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
import userInterface.UserWantBooksUI;
import utils.AddTypes;
import userInterface.AddUserBookUI;
import userInterface.AdminUI;
import userInterface.ListBooksUI;
import userInterface.ListUsersUI;

public class Controller {
	private DataBase database;
	private SessionData sessionData;
	private ArrayList<Book> wantBookToTradeList;
	private String wantBookUserEmail;
	private ArrayList<Book> setBookToOfferList;
	
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
		if(this.sessionData.getAdmin() != null) {
			AdminUI adminUI = new AdminUI(this);
			adminUI.openUI();
		} else {
			MainUI mainUI = new MainUI(this);
			mainUI.openUI();
		}
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
	
	public void openUserExchangeProposals() throws SQLException {
		UserExchangeProposalsUI userExchangeProposalsUI;
		userExchangeProposalsUI = new UserExchangeProposalsUI(this, this.database.readRegistredUser(this.sessionData.getLoggedUser().getEmail()));
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
	
	public void openUserOwnBooks() throws SQLException {
		UserOwnBooksUI userOwnBooksUI = new UserOwnBooksUI(this, this.database.readRegistredUser(this.sessionData.getLoggedUser().getEmail()));
		userOwnBooksUI.openUI();
	}
	
	public void openUserWantBooks(RegistredUser user) throws SQLException {
		UserWantBooksUI userWantBooksUI = new UserWantBooksUI(this, user);
		userWantBooksUI.openUI();
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
	
	public void setBookToOffer(String bookTitle) {
		this.setBookToOfferList = new ArrayList<Book>();
		this.setBookToOfferList.add(this.sessionData.getLoggedUser().getOwnBook(bookTitle));
	}
	
	public void setOwnBookToTrade(Book book) {
		ArrayList<Book> ownBookList = new ArrayList<Book>();
		ownBookList.add(book);
		this.sessionData.getLoggedUser().addExchangeProposal(ownBookList, this.wantBookToTradeList, this.wantBookUserEmail, this.database);
		cleanProposal();
	}
	
	public void setOtherUserBookIWant(Book book, RegistredUser user) {
		ArrayList<Book> otherUserBookList = new ArrayList<Book>();
		otherUserBookList.add(book);
		this.sessionData.getLoggedUser().addExchangeProposal(this.setBookToOfferList, otherUserBookList, user.getEmail(), this.database);
	}
		
	public RegistredUser aceptExchangeProposal(ExchangeProposal proposal) {
		this.sessionData.getLoggedUser().aceptExchangeProposal(proposal, this.database);
		return this.sessionData.getLoggedUser();
	}
	
	public RegistredUser cancelExchangeProposal(ExchangeProposal proposal) {
		this.sessionData.getLoggedUser().cancelExchangeProposal(proposal, this.database);
		return this.sessionData.getLoggedUser();
	}
	
	public void cleanProposal() {
		this.wantBookToTradeList = new ArrayList<Book>();
		this.wantBookUserEmail = "";
	}
	
	public boolean userOwnThisBook(Book book) {
		return this.sessionData.getLoggedUser().ownThisBook(book);
	}
	
	public ArrayList<ExchangeProposal> getAceptedProposals() {
		return this.database.readAceptedProposals();
	}
	
	public boolean removeProposal(ExchangeProposal proposal) {
		return this.database.removeExchangeProposal(proposal);
	}
	
	public boolean validateProposal(ExchangeProposal proposal) {
		RegistredUser user;
		this.database.removeExchangeProposal(proposal);
		try {
			user = this.database.readRegistredUser(proposal.getUser1Email());
			user.finalizeProposal(proposal.getUser1Books().get(0), database);
			user = this.database.readRegistredUser(proposal.getUser2Email());
			user.finalizeProposal(proposal.getUser2Books().get(0), database);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
