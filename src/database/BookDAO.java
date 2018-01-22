package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Book;

public class BookDAO {
private Connection con = null;

	public BookDAO() {
		con = ConnectionFactory.getConnection();
	}
	
	
	public boolean update(Book oldBook, Book newBook, String email) {
		return false;
	}

	public boolean delete(Book book, String email) {
		
		String sql = "delete from ownbooks where ownbooks.bookId=" + book.getBookId() + ";";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.err.println("Erro " + e);
			return false;
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}	
		return true;
	}
		
	public boolean addOwnBook(Book book, String email) {
		
		String sql = "INSERT INTO ownbooks (title,author,genre,edition,language,isbn,photo,comment,email,bookId) VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getGenre());
			stmt.setString(4, book.getEdition());
			stmt.setString(5, book.getLanguage());
			stmt.setString(6, book.getISBN());
			stmt.setString(7, book.getPhoto());
			stmt.setString(8, book.getComment());
			stmt.setString(9, email);
			stmt.setInt(10, this.getNextAvailableId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("Erro asd " + e);
			return false;
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public ArrayList<Book> searchBooks(String title, String autor, String genre, String isbn) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ";// ownbooks.title = ? and ownboooks.autor = ? and ownboooks.genre = ?";
		if(!title.trim().equals("")) {
			sql += "ownbooks.title = ";
			sql += "'" + title + "'";
			sql += " and ";
		}
		if(!autor.trim().equals("")) {
			sql += "ownbooks.author = ";
			sql += "'" +autor + "'";
			sql += " and ";
		}
		if(!genre.trim().equals("")) {
			sql += "ownbooks.genre = ";
			sql += "'" +genre + "'";
			sql += " and ";
		}
		if(!isbn.trim().equals("")) {
			sql += "ownbooks.isbn = ";
			sql += "'" + isbn + "'";
			sql += " and ";
		}
		sql = sql.substring(0, sql.length() - 4);
		sql +=";";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}
	
	public ArrayList<Book> readOwnBooks(String email) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownbooks.email = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));

				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro readOwnBooks " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}
	
	public boolean updateOwnBook(Book oldBook, Book newBook, String email) {
		return true;
	}
	
	public boolean deleteOwnBook(Book book, String email) {
		return false;
	}
	
	public boolean addWantBook(Book book, String email) {
		
		String sql = "INSERT INTO wantbooks (title,author,genre,edition,language,isbn,photo,comment,email,bookId) VALUES (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, book.getTitle());
			stmt.setString(2, book.getAuthor());
			stmt.setString(3, book.getGenre());
			stmt.setString(4, book.getEdition());
			stmt.setString(5, book.getLanguage());
			stmt.setString(6, book.getISBN());
			stmt.setString(7, book.getPhoto());
			stmt.setString(8, book.getComment());
			stmt.setString(9, email);
			stmt.setInt(10, this.getNextAvailableId());
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
			return false;
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
	}

	public ArrayList<Book> readWantBooks(String email) {
		String sql = "SELECT wantbooks.title, wantbooks.author, wantbooks.genre, wantbooks.edition, wantbooks.language, wantbooks.isbn, wantbooks.photo, wantbooks.comment, wantbooks.bookId from wantbooks where wantbooks.email = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}
	
	public boolean updateWantBook(Book oldBook, Book newBook, String email) {
		return true;
	}
	
	public boolean deleteWantBook(Book book, String email) {
		return false;
	}

	public ArrayList<Book> readBooks(String title, String autor, String genre, String isbn) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownbooks.title = ? and ownboooks.author = ? and ownboooks.genre = ? and ownboooks.isbn = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, autor);
			stmt.setString(3, genre);
			stmt.setString(4, isbn);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro readOwmBooks " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooks(String title, String autor, String genre) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownbooks.title = ? and ownboooks.autor = ? and ownboooks.genre = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, autor);
			stmt.setString(3, genre);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooks(String title, String autor) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownbooks.title = ? and ownboooks.autor = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, autor);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooks(String title) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownbooks.title = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooks() {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooksByTitleAutorIsbn(String title, String autor, String isbn) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownbooks.title = ? and ownboooks.genre = ? and ownboooks.isbn = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, autor);
			stmt.setString(3, isbn);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooksByTitleGenreIsbn(String title, String genre, String isbn) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownbooks.title = ? and ownboooks.autor = ? and ownboooks.isbn = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, genre);
			stmt.setString(3, isbn);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooksByTitleGenre(String title, String genre) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownbooks.title = ? and ownboooks.genre = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, genre);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooksByTitleIsbn(String title, String isbn) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownbooks.title = ? and ownboooks.isbn = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, isbn);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooksByAutorGenreIsbn(String autor, String genre, String isbn) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownboooks.autor = ? and ownboooks.genre = ? and ownboooks.isbn = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, autor);
			stmt.setString(2, genre);
			stmt.setString(3, isbn);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooksByAutorGenre(String autor, String genre) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where and ownboooks.autor = ? and ownboooks.genre = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, autor);
			stmt.setString(2, genre);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooksByAutorIsbn(String autor, String isbn) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownboooks.autor = ? and ownboooks.isbn = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, autor);
			stmt.setString(2, isbn);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooksByAutor(String autor) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownboooks.autor = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, autor);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooksByGenreIsbn(String genre, String isbn) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownboooks.genre = ? and ownboooks.isbn = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, genre);
			stmt.setString(2, isbn);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}

	public ArrayList<Book> readBooksByGenre(String genre) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownboooks.genre = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, genre);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}
	

	public ArrayList<Book> readBooksByIsbn(String isbn) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownboooks.isbn = ?";
		PreparedStatement stmt = null;
		ArrayList<Book> bookList = new ArrayList<Book>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, isbn);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			while(resultSet.next()) {
				valores.add(resultSet.getString(1));
				valores.add(resultSet.getString(2));
				valores.add(resultSet.getString(3));
				valores.add(resultSet.getString(4));
				valores.add(resultSet.getString(5));	
				valores.add(resultSet.getString(6));
				valores.add(resultSet.getString(7));
				valores.add(resultSet.getString(8));
				Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
				book.setBookId(resultSet.getInt(9));
				bookList.add(book);
				valores = new ArrayList<String>();
			}
			return bookList;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}
	
	private Integer getNextAvailableId() {
		String sql = "select max(bookId) from ownbooks";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			ResultSet resultSet = stmt.executeQuery();
			while(resultSet.next()) {
				return 1 + resultSet.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("Erro 2 " + e);
		}
		return 0;
	}
	
	
	public Book readBooksById(Integer bookId) {
		String sql = "SELECT ownbooks.title, ownbooks.author, ownbooks.genre, ownbooks.edition, ownbooks.language, ownbooks.isbn, ownbooks.photo, ownbooks.comment, ownbooks.bookId from ownbooks where ownbooks.bookId = ?";
		PreparedStatement stmt = null;		
		try {
			stmt = con.prepareStatement(sql);
			System.out.println("BOOK ID: " + bookId);
			stmt.setInt(1, bookId);
			ResultSet resultSet = stmt.executeQuery();
			ArrayList<String> valores = new ArrayList<String>();
			resultSet.next();
			valores.add(resultSet.getString(1));
			valores.add(resultSet.getString(2));
			valores.add(resultSet.getString(3));
			valores.add(resultSet.getString(4));
			valores.add(resultSet.getString(5));	
			valores.add(resultSet.getString(6));
			valores.add(resultSet.getString(7));
			valores.add(resultSet.getString(8));
			Book book = new Book(valores.get(0), valores.get(1), valores.get(2), valores.get(3), valores.get(4), valores.get(5), valores.get(6), valores.get(7));
			book.setBookId(resultSet.getInt(9));
			System.out.println("livro:" + book.getTitle());
			return book;
		} catch (SQLException e) {
			System.err.println("Erro 1 " + e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
		return null;
	}
	
	public String readBookEmail(Book book) {
		String sql = "SELECT ownbooks.email from ownbooks where ownbooks.bookId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, book.getBookId());
			ResultSet resultSet = stmt.executeQuery();
			resultSet.next();
			return resultSet.getString(1);
		} catch(SQLException e) {
			System.err.println("Erro " + e);
		}
		return "Fail";
	}
}
