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
import domain.RegistredUser;
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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import javax.swing.JCheckBoxMenuItem;

public class UserProfileUI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JTextField titleField;
	private JTextField authorField;
	private JTextField genreField;
	private JTextField isbnField;
	private Book selectedBook;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField editionField;
	private JTextField languageField;
	private JTextField commentField;
	private JButton tradeButton;
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
	private RegistredUser user;
	private JButton offerButton;
	
	public UserProfileUI(Controller controller, RegistredUser user) {
		this.user = user;
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(908, 497);
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
				controller.openMain();
			}
		});
		label_2.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/\u00EDndiceclose.png")));
		label_2.setBounds(878, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel lblKindler = new JLabel("Kindler");
		lblKindler.setForeground(Color.LIGHT_GRAY);
		lblKindler.setFont(new Font("Kalinga", Font.BOLD, 14));
		lblKindler.setBounds(418, 2, 74, 23);
		mainPanel.add(lblKindler);
		
		JLabel lblMeusLivros = new JLabel("Livros que o usu\u00E1rio possui");
		lblMeusLivros.setForeground(Color.LIGHT_GRAY);
		lblMeusLivros.setFont(new Font("Dialog", Font.BOLD, 13));
		lblMeusLivros.setBounds(20, 68, 194, 23);
		mainPanel.add(lblMeusLivros);
		
		ArrayList<Icon> iconList = new ArrayList<Icon>();
		bookList = user.getOwnBooks();
		if (bookList != null) {
			for(Book book : bookList) {
				try{
					iconList.add(new ImageIcon(MainUI.class.getResource("/utils/" + book.getPhoto())));
				}catch (Exception e) {
					iconList.add(new ImageIcon(MainUI.class.getResource("/utils/unkown.jpg")));				}
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
				tradeButton.setEnabled(true);
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
			try{
				iconList3.add(new ImageIcon(MainUI.class.getResource("/utils/" + book.getPhoto())));
			}catch (Exception e) {
				iconList3.add(new ImageIcon(MainUI.class.getResource("/utils/unkown.jpg")));
			}
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
		
		tradeButton = new JButton("Propor troca");
		tradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.setWantBookToTrade(bookList.get(list.getSelectedIndex()), user);
				mainFrame.dispose();
				try {
					controller.openUserOwnBooks();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		tradeButton.setEnabled(false);
		tradeButton.setBounds(257, 452, 182, 23);
		mainPanel.add(tradeButton);
		
		JLabel lblLivrosQueDesejo = new JLabel("Livros que o usu\u00E1rio deseja");
		lblLivrosQueDesejo.setForeground(Color.LIGHT_GRAY);
		lblLivrosQueDesejo.setFont(new Font("Dialog", Font.BOLD, 13));
		lblLivrosQueDesejo.setBounds(461, 68, 194, 23);
		mainPanel.add(lblLivrosQueDesejo);
		
		ArrayList<Icon> wantIconList = new ArrayList<Icon>();
		wantBookList = user.getWantBooks();
		if (wantBookList != null) {
			for(Book book : wantBookList) {
				try{
					wantIconList.add(new ImageIcon(MainUI.class.getResource("/utils/" + book.getPhoto())));
				}
				catch (Exception e) {
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
				offerButton.setEnabled(true);
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
		
		offerButton = new JButton("Ofertar livro");
		offerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		offerButton.setEnabled(false);
		offerButton.setBounds(698, 450, 182, 23);
		mainPanel.add(offerButton);
		
		JLabel lblNome = new JLabel("Nome:" + user.getName());
		lblNome.setForeground(Color.LIGHT_GRAY);
		lblNome.setFont(new Font("Dialog", Font.BOLD, 13));
		lblNome.setBounds(20, 45, 351, 23);
		mainPanel.add(lblNome);
		
		JLabel lblEndereo = new JLabel("Endere\u00E7o:" + user.getAddress());
		lblEndereo.setForeground(Color.LIGHT_GRAY);
		lblEndereo.setFont(new Font("Dialog", Font.BOLD, 13));
		lblEndereo.setBounds(461, 45, 330, 23);
		mainPanel.add(lblEndereo);
	}
	
	public void openUI() {
		this.mainFrame.setVisible(true);
	}
	
}
