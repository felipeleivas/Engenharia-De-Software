package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

public class UserWantBooksUI {
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
	private JList wantIist;
	private ArrayList<Book> wantBookList;
	private RegistredUser user;
	
	public UserWantBooksUI(Controller controller, RegistredUser user) {
		this.user = user;
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(464, 471);
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
		label_1.setBounds(406, 2, 30, 32);
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
		label_2.setBounds(434, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel lblKindler = new JLabel("Kindler");
		lblKindler.setForeground(Color.LIGHT_GRAY);
		lblKindler.setFont(new Font("Kalinga", Font.BOLD, 14));
		lblKindler.setBounds(188, 2, 74, 23);
		mainPanel.add(lblKindler);
		
		JLabel lblMeusLivros = new JLabel("Livros que o usu\u00E1rio possui");
		lblMeusLivros.setForeground(Color.LIGHT_GRAY);
		lblMeusLivros.setFont(new Font("Dialog", Font.BOLD, 13));
		lblMeusLivros.setBounds(22, 46, 270, 23);
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
		listScroller.setBounds(22, 69, 419, 253);
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
		titleField.setBounds(22, 332, 223, 20);
		mainPanel.add(titleField);
		
		authorField = new JTextField();
		authorField.setText("Autor");
		authorField.setEditable(false);
		authorField.setColumns(10);
		authorField.setBounds(22, 365, 223, 20);
		mainPanel.add(authorField);
		
		genreField = new JTextField();
		genreField.setText("G\u00EAnero");
		genreField.setEditable(false);
		genreField.setColumns(10);
		genreField.setBounds(22, 398, 223, 20);
		mainPanel.add(genreField);
		
		isbnField = new JTextField();
		isbnField.setText("ISBN");
		isbnField.setEditable(false);
		isbnField.setColumns(10);
		isbnField.setBounds(22, 431, 222, 20);
		mainPanel.add(isbnField);
		
		editionField = new JTextField();
		editionField.setText("Edi\u00E7\u00E3o");
		editionField.setEditable(false);
		editionField.setColumns(10);
		editionField.setBounds(259, 332, 182, 20);
		mainPanel.add(editionField);
		
		languageField = new JTextField();
		languageField.setText("Idioma");
		languageField.setEditable(false);
		languageField.setColumns(10);
		languageField.setBounds(259, 365, 182, 20);
		mainPanel.add(languageField);
		
		commentField = new JTextField();
		commentField.setText("Coment\u00E1rio");
		commentField.setEditable(false);
		commentField.setColumns(10);
		commentField.setBounds(259, 398, 182, 20);
		mainPanel.add(commentField);
		
		tradeButton = new JButton("Selecionar");
		tradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectBook(controller);
			}
		});
		tradeButton.setEnabled(false);
		tradeButton.setBounds(259, 430, 182, 23);
		mainPanel.add(tradeButton);
		
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
	}
	
	public void openUI() {
		this.mainFrame.setVisible(true);
	}
	
	private void selectBook(Controller controller) {
		controller.setOtherUserBookIWant(bookList.get(list.getSelectedIndex()), user);
		this.mainFrame.dispose();
		controller.openMain();
		JOptionPane.showMessageDialog(null, "Proposta efetuada com sucesso!");
	}
	
}
