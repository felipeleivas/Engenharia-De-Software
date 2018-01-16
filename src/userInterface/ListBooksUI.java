package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domain.Book;
import managment.Controller;
import utils.HintTextField;
import utils.PasswordHintTextField;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListBooksUI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JTextField titleField;
	private JTextField authorField;
	private JTextField genreField;
	private JTextField isbnField;
	private JTextField editionField;
	private JTextField languageField;
	private JTextField commentField;
	private ArrayList<Book> bookList;
	private JList list;
	private Book selectedBook;
	private JButton tradeButton;
	
	public ListBooksUI(Controller controller, ArrayList<Book> books) {
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(472, 495);
		this.mainFrame.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.mainFrame.setLocation(dim.width/2-this.mainFrame.getSize().width/2, dim.height/2-this.mainFrame.getSize().height/2);
		this.mainPanel = new JPanel();
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
		label_1.setBounds(415, 2, 30, 32);
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
		label_2.setBounds(442, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel lblKindler = new JLabel("Kindler");
		lblKindler.setForeground(Color.LIGHT_GRAY);
		lblKindler.setFont(new Font("Kalinga", Font.BOLD, 14));
		lblKindler.setBounds(183, 2, 74, 23);
		mainPanel.add(lblKindler);
		
		JLabel lblResultadoDaPesquisa = new JLabel("Resultado da pesquisa");
		lblResultadoDaPesquisa.setForeground(Color.LIGHT_GRAY);
		lblResultadoDaPesquisa.setFont(new Font("Dialog", Font.BOLD, 13));
		lblResultadoDaPesquisa.setBounds(26, 45, 194, 23);
		mainPanel.add(lblResultadoDaPesquisa);
		
		ArrayList<Icon> iconList = new ArrayList<Icon>();
		bookList = books;
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
				tradeButton.setEnabled(true);
			}
		});
		list.setBounds(148, 152, 1, 1);
		list.setVisibleRowCount(-1);
		list.setLayoutOrientation(JList.VERTICAL_WRAP);		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(250, 80));
		listScroller.setBounds(26, 67, 419, 253);
		mainPanel.add(listScroller);		
		
		titleField = new JTextField();
		titleField.setText("T\u00EDtulo");
		titleField.setEditable(false);
		titleField.setColumns(10);
		titleField.setBounds(26, 331, 223, 20);
		mainPanel.add(titleField);
		
		authorField = new JTextField();
		authorField.setText("Autor");
		authorField.setEditable(false);
		authorField.setColumns(10);
		authorField.setBounds(26, 364, 223, 20);
		mainPanel.add(authorField);
		
		genreField = new JTextField();
		genreField.setText("G\u00EAnero");
		genreField.setEditable(false);
		genreField.setColumns(10);
		genreField.setBounds(26, 397, 223, 20);
		mainPanel.add(genreField);
		
		isbnField = new JTextField();
		isbnField.setText("ISBN");
		isbnField.setEditable(false);
		isbnField.setColumns(10);
		isbnField.setBounds(26, 430, 222, 20);
		mainPanel.add(isbnField);
		
		editionField = new JTextField();
		editionField.setText("Edi\u00E7\u00E3o");
		editionField.setEditable(false);
		editionField.setColumns(10);
		editionField.setBounds(263, 331, 182, 20);
		mainPanel.add(editionField);
		
		languageField = new JTextField();
		languageField.setText("Idioma");
		languageField.setEditable(false);
		languageField.setColumns(10);
		languageField.setBounds(263, 364, 182, 20);
		mainPanel.add(languageField);
		
		commentField = new JTextField();
		commentField.setText("Coment\u00E1rio");
		commentField.setEditable(false);
		commentField.setColumns(10);
		commentField.setBounds(263, 397, 182, 20);
		mainPanel.add(commentField);
		
		tradeButton = new JButton("Propor troca");
		tradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setWantBookToTrade(bookList.get(list.getSelectedIndex()));
				mainFrame.dispose();
				try {
					controller.openUserOwnBooks();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		tradeButton.setEnabled(false);
		tradeButton.setBounds(263, 429, 182, 23);
		mainPanel.add(tradeButton);
	}
	
	public void openUI() {
		this.mainFrame.setVisible(true);
	}
}
