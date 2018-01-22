package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domain.Book;
import managment.Controller;
import utils.HintTextField;
import javax.swing.JList;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import javax.swing.JCheckBoxMenuItem;

public class MainUI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JTextField titleField;
	private JTextField authorField;
	private JTextField genreField;
	private JTextField isbnField;
	private Book selectedBook;
	private JTextField editionField;
	private JTextField languageField;
	private JTextField commentField;
	private JButton btnEdit;
	private JButton btnDelete;
	private Book oldBook;
	private JList list;
	private ArrayList<Book> bookList;
	private JTextField titleField2;
	private JTextField authorField2;
	private JTextField genreField2;
	private JTextField isbnField2;
	private JTextField editionField2;
	private JTextField languageField2;
	private JTextField commentField2;
	private JList wantIist;
	private JList wantList;
	private ArrayList<Book> wantBookList;
	
	public MainUI(Controller controller) {
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(908, 503);
		this.mainFrame.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.mainFrame.setLocation(dim.width/2-this.mainFrame.getSize().width/2, dim.height/2-this.mainFrame.getSize().height/2);
		this.mainPanel = new JPanel();
		mainPanel.setForeground(Color.LIGHT_GRAY);
		mainPanel.setBackground(Color.DARK_GRAY);
		this.mainFrame.getContentPane().add(this.mainPanel);
		mainPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/spotify-logo-button(1).png")));
		label.setBounds(6, 2, 30, 32);
		mainPanel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label_1.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/minimize.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_1.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/icons8-minimize-window-26.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mainFrame.setState(Frame.ICONIFIED);
			}
		});
		label_1.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/icons8-minimize-window-26.png")));
		label_1.setBounds(850, 2, 30, 32);
		mainPanel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_2.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/\u00EDndice.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_2.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/\u00EDndiceclose.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mainFrame.dispose();
			}
		});
		label_2.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/\u00EDndiceclose.png")));
		label_2.setBounds(878, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel lblKindler = new JLabel("Kindler");
		lblKindler.setForeground(Color.LIGHT_GRAY);
		lblKindler.setFont(new Font("Kalinga", Font.BOLD, 14));
		lblKindler.setBounds(381, 2, 74, 23);
		mainPanel.add(lblKindler);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.WHITE);
		menuBar.setBorder(null);
		menuBar.setBackground(Color.DARK_GRAY);
		menuBar.setBounds(0, 36, 908, 21);
		mainPanel.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Arquivo");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu.setBorder(null);
		mnNewMenu.setForeground(Color.LIGHT_GRAY);
		menuBar.add(mnNewMenu);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmTeste = new JMenuItem("Logout");
		mntmTeste.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				logout(controller);
			}
		});
		mnNewMenu.add(mntmTeste);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				mainFrame.dispose();
			}
		});
		mnNewMenu.add(mntmSair);
		
		JMenu mnUsurios = new JMenu("Usu\u00E1rios");
		mnUsurios.setBorder(null);
		menuBar.add(mnUsurios);
		mnUsurios.setForeground(Color.LIGHT_GRAY);
		mnUsurios.setFont(new Font("Segoe UI", Font.BOLD, 13));
		
		JMenuItem mntmPesquisarUsurios_1 = new JMenuItem("Pesquisar usu\u00E1rios");
		mntmPesquisarUsurios_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				mainFrame.dispose();
				controller.openSearchUser();
			}
		});
		mnUsurios.add(mntmPesquisarUsurios_1);
		
		JMenu mnNewMenu_2 = new JMenu("Livros");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_2.setBorder(null);
		mnNewMenu_2.setForeground(Color.LIGHT_GRAY);
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmAdcionarLivro = new JMenuItem("Livro a oferecer");
		mntmAdcionarLivro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				addOwnBookToUser(controller);
			}
		});
		
		JMenuItem mntmPesquisarLivros = new JMenuItem("Livro desejado");
		mntmPesquisarLivros.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				mainFrame.dispose();
				controller.openSearchBook();
			}
		});
		mnNewMenu_2.add(mntmPesquisarLivros);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu_2.add(separator_1);
		mnNewMenu_2.add(mntmAdcionarLivro);
		
		JMenuItem mntmAdicionarLivrosQue = new JMenuItem("Adicionar livros que desejo");
		mntmAdicionarLivrosQue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				mainFrame.dispose();
				controller.openAddWantBookToUser();
			}
		});
		mnNewMenu_2.add(mntmAdicionarLivrosQue);
		
		JMenu mnTrocas = new JMenu("Propostas");
		menuBar.add(mnTrocas);
		mnTrocas.setForeground(Color.LIGHT_GRAY);
		mnTrocas.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnTrocas.setBorder(null);
		
		JMenuItem mntmConsultarTrocas = new JMenuItem("Consultar propostas");
		mntmConsultarTrocas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				mainFrame.dispose();
				try {
					controller.openUserExchangeProposals();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		mnTrocas.add(mntmConsultarTrocas);
		
		JMenu mnNewMenu_1 = new JMenu("Ajuda");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnNewMenu_1.setBorder(null);
		mnNewMenu_1.setForeground(Color.LIGHT_GRAY);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmTeste_1 = new JMenuItem("Sobre o Kindler");
		mnNewMenu_1.add(mntmTeste_1);
		
		JLabel lblMeusLivros = new JLabel("Livros que possuo");
		lblMeusLivros.setForeground(Color.LIGHT_GRAY);
		lblMeusLivros.setFont(new Font("Dialog", Font.BOLD, 13));
		lblMeusLivros.setBounds(20, 68, 129, 23);
		mainPanel.add(lblMeusLivros);
		
		ArrayList<Icon> iconList = new ArrayList<Icon>();
		bookList = controller.getOwnBookList();
		if (bookList != null) {
			for(Book book : bookList) {
				try{
					iconList.add(new ImageIcon(MainUI.class.getResource("/utils/" + book.getPhoto())));
				}catch (Exception e) {
					iconList.add(new ImageIcon(MainUI.class.getResource("/utils/unkown.jpg")));
				}
			}
		}
		Object[] iconList2 = {};
		iconList2 = iconList.toArray();
		list = new JList(iconList2);
		list.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				selectedBook = bookList.get(list.getSelectedIndex());
				titleField.setText(bookList.get(list.getSelectedIndex()).getTitle());
				authorField.setText(bookList.get(list.getSelectedIndex()).getAuthor());
				genreField.setText(bookList.get(list.getSelectedIndex()).getGenre());
				isbnField.setText(bookList.get(list.getSelectedIndex()).getISBN());
				languageField.setText(bookList.get(list.getSelectedIndex()).getLanguage());
				editionField.setText(bookList.get(list.getSelectedIndex()).getEdition());
				commentField.setText(bookList.get(list.getSelectedIndex()).getComment());
				//btnEdit.setEnabled(true);
			}
		});
		list.setBounds(148, 152, 1, 1);
		list.setVisibleRowCount(-1);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		listScroller.setBounds(20, 91, 419, 253);
		mainPanel.add(listScroller);
		
		ArrayList<Icon> iconList3 = new ArrayList<Icon>();
		ArrayList<Book> bookList2 = new ArrayList<Book>();//controller.getTradeBookList();
		for(Book book : bookList2) {
			iconList3.add(new ImageIcon(MainUI.class.getResource("/utils/" + book.getPhoto())));
		}
		Object[] iconList4 = {};
		iconList4 = iconList3.toArray();
		
		titleField = new JTextField();
		titleField.setText("T\u00EDtulo");
		titleField.setEditable(false);
		titleField.setColumns(10);
		titleField.setBounds(20, 354, 223, 20);
		mainPanel.add(titleField);
		
		authorField = new JTextField();
		authorField.setText("Autor");
		authorField.setEditable(false);
		authorField.setColumns(10);
		authorField.setBounds(20, 387, 223, 20);
		mainPanel.add(authorField);
		
		genreField = new JTextField();
		genreField.setText("G\u00EAnero");
		genreField.setEditable(false);
		genreField.setColumns(10);
		genreField.setBounds(20, 420, 223, 20);
		mainPanel.add(genreField);
		
		isbnField = new JTextField();
		isbnField.setText("ISBN");
		isbnField.setEditable(false);
		isbnField.setColumns(10);
		isbnField.setBounds(20, 453, 222, 20);
		mainPanel.add(isbnField);
		
		editionField = new JTextField();
		editionField.setText("Edi\u00E7\u00E3o");
		editionField.setEditable(false);
		editionField.setColumns(10);
		editionField.setBounds(257, 354, 182, 20);
		mainPanel.add(editionField);
		
		languageField = new JTextField();
		languageField.setText("Idioma");
		languageField.setEditable(false);
		languageField.setColumns(10);
		languageField.setBounds(257, 387, 182, 20);
		mainPanel.add(languageField);
		
		commentField = new JTextField();
		commentField.setText("Coment\u00E1rio");
		commentField.setEditable(false);
		commentField.setColumns(10);
		commentField.setBounds(257, 420, 182, 20);
		mainPanel.add(commentField);
		
		btnEdit = new JButton("Editar");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnEdit.getText().equals("Editar")) {
					btnDelete.setEnabled(true);
					btnEdit.setText("Salvar");
					oldBook = bookList.get(list.getSelectedIndex());
				}else{
					Book newBook = new Book(titleField.getText(), authorField.getText(), genreField.getText(), editionField.getText(), languageField.getText(), isbnField.getText(), oldBook.getPhoto(), commentField.getText());
					if(controller.updateOwnBook(oldBook, newBook)) {
						JOptionPane.showMessageDialog(null, "Livro atualizado!");
						btnEdit.setText("Editar");
						btnEdit.setEnabled(false);
						btnDelete.setEnabled(false);
						refreshOwnBookList(controller);
					}else{
						JOptionPane.showMessageDialog(null, "Falha ao atualizar livro.");
					}
				}
			}
		});
		btnEdit.setEnabled(false);
		btnEdit.setBounds(258, 452, 89, 23);
		mainPanel.add(btnEdit);
		
		btnDelete = new JButton("Excluir");
		btnDelete.setEnabled(false);
		btnDelete.setBounds(350, 452, 89, 23);
		mainPanel.add(btnDelete);
		
		JLabel lblLivrosQueDesejo = new JLabel("Livros que desejo");
		lblLivrosQueDesejo.setForeground(Color.LIGHT_GRAY);
		lblLivrosQueDesejo.setFont(new Font("Dialog", Font.BOLD, 13));
		lblLivrosQueDesejo.setBounds(461, 68, 129, 23);
		mainPanel.add(lblLivrosQueDesejo);
		
		ArrayList<Icon> wantIconList = new ArrayList<Icon>();
		wantBookList = controller.getWantBookList();
		if (wantBookList != null) {
			for(Book book : wantBookList) {
				try{
					wantIconList.add(new ImageIcon(MainUI.class.getResource("/utils/" + book.getPhoto())));
				}catch (Exception e) {
					wantIconList.add(new ImageIcon(MainUI.class.getResource("/utils/unkown.jpg")));
				}
			}
		}
		Object[] wantIconList2 = {};
		wantIconList2 = wantIconList.toArray();
		wantList = new JList(wantIconList2);
		wantList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent arg0) {
				selectedBook = wantBookList.get(wantList.getSelectedIndex());
				titleField2.setText(wantBookList.get(wantList.getSelectedIndex()).getTitle());
				authorField2.setText(wantBookList.get(wantList.getSelectedIndex()).getAuthor());
				genreField2.setText(wantBookList.get(wantList.getSelectedIndex()).getGenre());
				isbnField2.setText(wantBookList.get(wantList.getSelectedIndex()).getISBN());
				languageField2.setText(wantBookList.get(wantList.getSelectedIndex()).getLanguage());
				editionField2.setText(wantBookList.get(wantList.getSelectedIndex()).getEdition());
				commentField2.setText(wantBookList.get(wantList.getSelectedIndex()).getComment());
				//btnEdit.setEnabled(true);
			}
		});
		wantList.setBounds(148, 152, 1, 1);
		wantList.setVisibleRowCount(-1);
		wantList.setLayoutOrientation(JList.VERTICAL_WRAP);
		JScrollPane scrollPane = new JScrollPane(wantList);
		scrollPane.setPreferredSize(new Dimension(250, 80));
		scrollPane.setBounds(461, 91, 419, 253);
		mainPanel.add(scrollPane);
		
		titleField2 = new JTextField();
		titleField2.setText("T\u00EDtulo");
		titleField2.setEditable(false);
		titleField2.setColumns(10);
		titleField2.setBounds(461, 352, 223, 20);
		mainPanel.add(titleField2);
		
		authorField2 = new JTextField();
		authorField2.setText("Autor");
		authorField2.setEditable(false);
		authorField2.setColumns(10);
		authorField2.setBounds(461, 385, 223, 20);
		mainPanel.add(authorField2);
		
		genreField2 = new JTextField();
		genreField2.setText("G\u00EAnero");
		genreField2.setEditable(false);
		genreField2.setColumns(10);
		genreField2.setBounds(461, 418, 223, 20);
		mainPanel.add(genreField2);
		
		isbnField2 = new JTextField();
		isbnField2.setText("ISBN");
		isbnField2.setEditable(false);
		isbnField2.setColumns(10);
		isbnField2.setBounds(462, 451, 222, 20);
		mainPanel.add(isbnField2);
		
		editionField2 = new JTextField();
		editionField2.setText("Edi\u00E7\u00E3o");
		editionField2.setEditable(false);
		editionField2.setColumns(10);
		editionField2.setBounds(698, 352, 182, 20);
		mainPanel.add(editionField2);
		
		languageField2 = new JTextField();
		languageField2.setText("Idioma");
		languageField2.setEditable(false);
		languageField2.setColumns(10);
		languageField2.setBounds(698, 385, 182, 20);
		mainPanel.add(languageField2);
		
		commentField2 = new JTextField();
		commentField2.setText("Coment\u00E1rio");
		commentField2.setEditable(false);
		commentField2.setColumns(10);
		commentField2.setBounds(698, 418, 182, 20);
		mainPanel.add(commentField2);
		
		JButton button = new JButton("Editar");
		button.setEnabled(false);
		button.setBounds(698, 450, 89, 23);
		mainPanel.add(button);
		
		JButton button_1 = new JButton("Excluir");
		button_1.setEnabled(false);
		button_1.setBounds(791, 450, 89, 23);
		mainPanel.add(button_1);
	}
	
	public void openUI() {
		this.mainFrame.setVisible(true);
	}
	
	private void logout(Controller controller) {
		mainFrame.dispose();
		controller.logout();
	}
	
	private void addOwnBookToUser(Controller controller) {
		mainFrame.dispose();
		controller.openAddOwnBookToUser();
	}
	
	private void addWantBookToUser(Controller controller) {
		mainFrame.dispose();
		controller.openAddWantBookToUser();
	}

	private void refreshOwnBookList(Controller controller) {
		ArrayList<Icon> iconList = new ArrayList<Icon>();
		bookList = controller.getOwnBookList();
		if (bookList != null) {
			for(Book book : bookList) {
				iconList.add(new ImageIcon(MainUI.class.getResource("/utils/" + book.getPhoto())));
			}
		}
		Object[] iconList2 = {};
		iconList2 = iconList.toArray();
		list = new JList(iconList2);
		list.validate();
		list.repaint();
		list.clearSelection();
	}
}
