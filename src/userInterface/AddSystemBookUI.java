package userInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import managment.Controller;
import utils.HintTextField;

public class AddSystemBookUI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private HintTextField titleField;
	private HintTextField editionField;
	private HintTextField authorField;
	private HintTextField languageField;
	private HintTextField genreField;
	private HintTextField photoField;
	private HintTextField isbnField;
	private HintTextField commentField;
	
	public AddSystemBookUI(Controller controller) {
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(484, 363);
		this.mainFrame.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.mainFrame.setLocation(dim.width/2-this.mainFrame.getSize().width/2, dim.height/2-this.mainFrame.getSize().height/2);
		this.mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		this.mainFrame.getContentPane().add(this.mainPanel);
		mainPanel.setLayout(null);
		
		this.titleField = new HintTextField("Título");
		titleField.setForeground(Color.BLACK);
		titleField.setFont(new Font("Tahoma", Font.BOLD, 13));
		titleField.setBackground(Color.LIGHT_GRAY);
		titleField.setBorder(null);
		this.titleField.setBounds(27, 60, 195, 20);
		this.mainPanel.add(titleField);
		
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
		label_1.setBounds(425, 2, 30, 32);
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
		label_2.setBounds(454, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel lblKindler = new JLabel("Kindler");
		lblKindler.setForeground(Color.LIGHT_GRAY);
		lblKindler.setFont(new Font("Kalinga", Font.BOLD, 14));
		lblKindler.setBounds(205, 0, 74, 23);
		mainPanel.add(lblKindler);
		
		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_3.setIcon(new ImageIcon(AddSystemBookUI.class.getResource("/utils/notebook (1).png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_3.setIcon(new ImageIcon(AddSystemBookUI.class.getResource("/utils/notebook.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				registerBook(controller);
			}
		});
		label_3.setIcon(new ImageIcon(AddSystemBookUI.class.getResource("/utils/notebook.png")));
		label_3.setBounds(388, 263, 64, 75);
		mainPanel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_4.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/refresh-arrow(1).png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_4.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/refresh-arrow.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				cleanFields();
			}
		});
		label_4.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/refresh-arrow.png")));
		label_4.setBounds(27, 274, 74, 64);
		mainPanel.add(label_4);
		
		authorField = new HintTextField("Autor");
		authorField.setForeground(Color.BLACK);
		authorField.setFont(new Font("Tahoma", Font.BOLD, 13));
		authorField.setBorder(null);
		authorField.setBackground(Color.LIGHT_GRAY);
		authorField.setBounds(257, 60, 195, 20);
		mainPanel.add(authorField);
		
		genreField = new HintTextField("G�nero");
		genreField.setForeground(Color.BLACK);
		genreField.setFont(new Font("Tahoma", Font.BOLD, 13));
		genreField.setBorder(null);
		genreField.setBackground(Color.LIGHT_GRAY);
		genreField.setBounds(27, 105, 195, 20);
		mainPanel.add(genreField);
		
		editionField = new HintTextField("Edi��o");
		editionField.setForeground(Color.BLACK);
		editionField.setFont(new Font("Tahoma", Font.BOLD, 13));
		editionField.setBorder(null);
		editionField.setBackground(Color.LIGHT_GRAY);
		editionField.setBounds(257, 105, 195, 20);
		mainPanel.add(editionField);
		
		languageField = new HintTextField("Idioma");
		languageField.setForeground(Color.BLACK);
		languageField.setFont(new Font("Tahoma", Font.BOLD, 13));
		languageField.setBorder(null);
		languageField.setBackground(Color.LIGHT_GRAY);
		languageField.setBounds(27, 199, 195, 20);
		mainPanel.add(languageField);
		
		isbnField = new HintTextField("ISBN");
		isbnField.setForeground(Color.BLACK);
		isbnField.setFont(new Font("Tahoma", Font.BOLD, 13));
		isbnField.setBorder(null);
		isbnField.setBackground(Color.LIGHT_GRAY);
		isbnField.setBounds(257, 150, 195, 20);
		mainPanel.add(isbnField);
		
		photoField = new HintTextField("Foto");
		photoField.setForeground(Color.BLACK);
		photoField.setFont(new Font("Tahoma", Font.BOLD, 13));
		photoField.setBorder(null);
		photoField.setBackground(Color.LIGHT_GRAY);
		photoField.setBounds(27, 150, 195, 20);
		mainPanel.add(photoField);
		
		commentField = new HintTextField("Coment�rio");
		commentField.setForeground(Color.BLACK);
		commentField.setFont(new Font("Tahoma", Font.BOLD, 13));
		commentField.setBorder(null);
		commentField.setBackground(Color.LIGHT_GRAY);
		commentField.setBounds(257, 199, 195, 20);
		mainPanel.add(commentField);
	}
	
	public void openUI() {
		this.mainFrame.setVisible(true);
	}
	
	private void cleanFields() {
		this.titleField.setText("");
		this.authorField.setText("");
		this.genreField.setText("");
		this.editionField.setText("");
		this.languageField.setText("");
		this.photoField.setText("");
		this.isbnField.setText("");		
		this.commentField.setText("");
	}
	
	private boolean checkFields() {
		Boolean title = this.titleField.getText().trim().equals("");
		Boolean author = this.authorField.getText().trim().equals("");
		Boolean genre = this.genreField.getText().trim().equals("");
		Boolean edition = this.editionField.getText().trim().equals("");
		Boolean language = this.languageField.getText().trim().equals("");
		Boolean photo = this.photoField.getText().trim().equals("");
		Boolean isbn = this.isbnField.getText().trim().equals("");
		
		if(title) {
			JOptionPane.showMessageDialog(null, "Informe um t�tulo v�lido.");
			return false;
		}
		if(author) {
			JOptionPane.showMessageDialog(null, "Informe um autor v�lido.");
			return false;
		}
		if(genre) {
			JOptionPane.showMessageDialog(null, "Informe um g�nero v�lido.");
			return false;
		}
		if(edition) {
			JOptionPane.showMessageDialog(null, "Informe uma edi��o v�lida.");
			return false;
		}
		if(language) {
			JOptionPane.showMessageDialog(null, "Informe um idioma v�lido.");
			return false;
		}
		if(photo) {
			JOptionPane.showMessageDialog(null, "Informe uma foto v�lida.");
			return false;
		}
		if(isbn) {
			JOptionPane.showMessageDialog(null, "Informe um ISBN v�lido.");
			return false;
		}
		return true;
	}
	
	private void registerBook(Controller controller) {
		if(checkFields()) {
			if(controller.addOwnBook(titleField.getText(), authorField.getText(), genreField.getText(), editionField.getText(), languageField.getText(), isbnField.getText(), photoField.getText(), commentField.getText())) {
				JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
				cleanFields();
			}else{
				JOptionPane.showMessageDialog(null, "Erro no cadastro do livro.");
			}
		}
	}
}
