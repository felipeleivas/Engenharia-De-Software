package domain;

import java.util.ArrayList;

import database.DataBase;

public class RegistredUser extends User {
	
	private String name;
	private String address;
	private ArrayList<Book> ownBooks;
	private ArrayList<Book> wantBooks;
	private ArrayList<ExchangeProposal> proposals;
	
	public RegistredUser(String email, String password, String name, String address) {
		super(email, password);
		this.name = name;
		this.address = address;
		this.ownBooks = new ArrayList<Book>();
		this.wantBooks = new ArrayList<Book>();
		this.proposals = new ArrayList<ExchangeProposal>();
	}
	
	public RegistredUser(String email, String password, String name, String address, ArrayList<Book> ownBooks, ArrayList<Book> wantBooks) {
		super(email, password);
		this.address = address;
		this.ownBooks = ownBooks;
		this.wantBooks = wantBooks;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
		
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String number) {
		this.address = number;
	}

	public ArrayList<Book> getOwnBooks() {
		return ownBooks;
	}

	public void setOwnBooks(ArrayList<Book> ownBooks) {
		this.ownBooks = ownBooks;
	}

	public ArrayList<Book> getWantBooks() {
		return wantBooks;
	}

	public void setWantBooks(ArrayList<Book> wantBooks) {
		this.wantBooks = wantBooks;
	}
	
	public ArrayList<ExchangeProposal> getProposals() {
		return proposals;
	}

	public void setProposals(ArrayList<ExchangeProposal> proposals) {
		this.proposals = proposals;
	}
	
	@Override
	public String toString() {
		return "\nEndereço :"+ this.address + " Email:  " + this.getEmail() + " Nome: " + this.name;
	}
	
	public boolean ownThisBook(Book book) {
		System.out.println("Livro externo:" + book.getTitle());
		for(Book auxBook : this.ownBooks) {
			System.out.println("Livro interno:" + auxBook.getTitle());
			if(auxBook.getTitle().equals(book.getTitle())) {
				return true;
			}
		}
		return false;
	}
	
	public Book getOwnBook(String title) {
		for(Book book : this.ownBooks) {
			if(book.getTitle().equals(title)) {
				return book;
			}
		}
		return null;
	}
	
	public void addExchangeProposal(ArrayList<Book> ownBooks, ArrayList<Book> wantBooks, String wantBooksUserEmail, DataBase dataBase) {
		ExchangeProposal exchangeProposal = new ExchangeProposal("AGUARDANDO ACEITE", "AGUARDANDO ACEITE" , this.getEmail(), wantBooksUserEmail, ownBooks, wantBooks);
		this.proposals.add(exchangeProposal);
		dataBase.addExchangeProposal(exchangeProposal);
	}
	
	public void aceptExchangeProposal(ExchangeProposal proposal, DataBase dataBase) {
		this.changeProposalStatus(proposal, "ACEITA", dataBase);
	}
	
	public void cancelExchangeProposal(ExchangeProposal proposal, DataBase dataBase) {
		this.proposals.remove(proposal);
		dataBase.removeExchangeProposal(proposal);
	}
	
	public void changeProposalStatus(ExchangeProposal proposal, String newStatus, DataBase dataBase) {
		
		if(proposal.getUser1Email().compareTo(this.getEmail()) == 0) {
			proposal.setStatusUser1(newStatus);
		}else {
			proposal.setStatusUser2(newStatus);
		}
		
		if(proposal.getStatusUser1().equals("ACEITA") && proposal.getStatusUser2().equals("ACEITA")) {
			proposal.setStatusUser1("AGUARDANDO VALIDAÇÂO");
			proposal.setStatusUser2("AGUARDANDO VALIDAÇÂO");
		} 
		
		dataBase.updateExchangeProposal(proposal);
		this.proposals = dataBase.readUserExchangeProposals(this.getEmail());
	}
	
	public boolean finalizeProposal(Book book, DataBase dataBase) {
		return dataBase.deleteOwnBook(book, this.getEmail());
	}
}
