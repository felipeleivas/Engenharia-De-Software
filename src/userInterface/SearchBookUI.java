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

public class SearchBookUI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private Book selectedBook;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Book oldBook;
	private ArrayList<Book> bookList;
	private JList wantIist;
	private ArrayList<Book> wantBookList;
	private RegistredUser user;
	private HintTextField titleField;
	private HintTextField autorField;
	private HintTextField genreField;
	private HintTextField isbnField;
	
	public SearchBookUI(Controller controller) {
		this.user = user;
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(366, 285);
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
		label_1.setBounds(309, 2, 30, 32);
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
		label_2.setBounds(336, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel lblKindler = new JLabel("Kindler");
		lblKindler.setForeground(Color.LIGHT_GRAY);
		lblKindler.setFont(new Font("Kalinga", Font.BOLD, 14));
		lblKindler.setBounds(179, 2, 74, 23);
		mainPanel.add(lblKindler);
		
		titleField = new HintTextField("Título");
		titleField.setForeground(Color.BLACK);
		titleField.setFont(new Font("Tahoma", Font.BOLD, 13));
		titleField.setBorder(null);
		titleField.setBackground(Color.LIGHT_GRAY);
		titleField.setBounds(26, 70, 313, 20);
		mainPanel.add(titleField);
		
		autorField = new HintTextField("Autor");
		autorField.setForeground(Color.BLACK);
		autorField.setFont(new Font("Tahoma", Font.BOLD, 13));
		autorField.setBorder(null);
		autorField.setBackground(Color.LIGHT_GRAY);
		autorField.setBounds(26, 110, 313, 20);
		mainPanel.add(autorField);
		
		genreField = new HintTextField("Gênero");
		genreField.setForeground(Color.BLACK);
		genreField.setFont(new Font("Tahoma", Font.BOLD, 13));
		genreField.setBorder(null);
		genreField.setBackground(Color.LIGHT_GRAY);
		genreField.setBounds(26, 150, 313, 20);
		mainPanel.add(genreField);
		
		isbnField = new HintTextField("ISBN");
		isbnField.setForeground(Color.BLACK);
		isbnField.setFont(new Font("Tahoma", Font.BOLD, 13));
		isbnField.setBorder(null);
		isbnField.setBackground(Color.LIGHT_GRAY);
		isbnField.setBounds(26, 190, 313, 20);
		mainPanel.add(isbnField);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchBook(controller);
			}
		});
		btnPesquisar.setBounds(224, 235, 115, 23);
		mainPanel.add(btnPesquisar);
	}
	
	public void openUI() {
		this.mainFrame.setVisible(true);
	}
	
	private void searchBook(Controller controller) {
		Boolean title = titleField.getText().trim().equals("");
		Boolean autor = autorField.getText().trim().equals("");
		Boolean genre = genreField.getText().trim().equals("");
		Boolean isbn = isbnField.getText().trim().equals("");
		
		ArrayList<Book> searchedBooks = controller.searchBooks(titleField.getText(), autorField.getText(), genreField.getText(), isbnField.getText());
		mainFrame.dispose();
		controller.openListBooks(searchedBooks);
	}
}
