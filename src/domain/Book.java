package domain;

public class Book {
	private Integer bookId;
	private String title;
	private String author;
	private String genre;
	private String edition;
	private String language;
	private String ISBN;
	private String photo;
	private String comment;
	
	public Book(String title, String author, String genre, String edition, String language, String ISBN, String photo, String comment) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.edition = edition;
		this.language = language;
		this.ISBN = ISBN;
		this.photo = photo;
		this.comment = comment;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}	
}
