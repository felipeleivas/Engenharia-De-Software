package domain;

import java.util.ArrayList;

public class ExchangeProposal {
	

	private Integer id;
	private String statusUser1;
	private String statusUser2;
	private String user1Email;
	private String user2Email;
	private ArrayList<Book> user1Books;
	private ArrayList<Book> user2Books;
	
	public ExchangeProposal(String statusUser1, String statusUser2, String user1Email, String user2Email,
			ArrayList<Book> user1Books, ArrayList<Book> user2Books) {
		super();
		this.statusUser1 = statusUser1;
		this.statusUser2 = statusUser2;
		this.user1Email = user1Email;
		this.user2Email = user2Email;
		this.user1Books = user1Books;
		this.user2Books = user2Books;
	}
	public ExchangeProposal() {
		super();
	}
	public String getStatusUser1() {
		return statusUser1;
	}
	public void setStatusUser1(String statusUser1) {
		this.statusUser1 = statusUser1;
	}
	public String getStatusUser2() {
		return statusUser2;
	}
	public void setStatusUser2(String statusUser2) {
		this.statusUser2 = statusUser2;
	}
	public String getUser1Email() {
		return user1Email;
	}
	public String getUser2Email() {
		return user2Email;
	}
	public ArrayList<Book> getUser1Books() {
		return user1Books;
	}
	public ArrayList<Book> getUser2Books() {
		return user2Books;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUser1Email(String user1Email) {
		this.user1Email = user1Email;
	}
	public void setUser2Email(String user2Email) {
		this.user2Email = user2Email;
	}
	public void setUser1Books(ArrayList<Book> user1Books) {
		this.user1Books = user1Books;
	}
	public void setUser2Books(ArrayList<Book> user2Books) {
		this.user2Books = user2Books;
	}
	
	public Boolean bothAcceptExchange() {
		if(this.statusUser1 == this.statusUser2 && this.statusUser1 == "ACEITO") {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "ExchangeProposal [id=" + id + ", statusUser1=" + statusUser1 + ", statusUser2=" + statusUser2
				+ ", user1Email=" + user1Email + ", user2Email=" + user2Email + ", user1Books=" + user1Books
				+ ", user2Books=" + user2Books + "]";
	}
}
