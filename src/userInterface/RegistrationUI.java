package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JTextField;

import managment.Controller;
import utils.HintTextField;
import utils.PasswordHintTextField;

public class RegistrationUI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private HintTextField addressField;
	private PasswordHintTextField passwordField;
	private HintTextField emailField;
	private HintTextField nameField;
	private PasswordHintTextField confirmPasswordField;
	
	public RegistrationUI(Controller controller) {
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(434, 319);
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
		label_1.setBounds(375, 2, 30, 32);
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
		label_2.setBounds(404, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel lblKindler = new JLabel("Kindler");
		lblKindler.setForeground(Color.LIGHT_GRAY);
		lblKindler.setFont(new Font("Kalinga", Font.BOLD, 14));
		lblKindler.setBounds(183, 2, 74, 23);
		mainPanel.add(lblKindler);
		
		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_3.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/add-a-contact-on-phone-interface-symbol-of-a-user-with-a-plus-sign(2).png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_3.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/add-a-contact-on-phone-interface-symbol-of-a-user-with-a-plus-sign(3).png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				registerUser(controller);
			}
		});
		label_3.setIcon(new ImageIcon(RegistrationUI.class.getResource("/utils/add-a-contact-on-phone-interface-symbol-of-a-user-with-a-plus-sign(3).png")));
		label_3.setBounds(338, 222, 64, 75);
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
		label_4.setBounds(27, 230, 74, 64);
		mainPanel.add(label_4);
		
		passwordField = new PasswordHintTextField("Senha");
		passwordField.setForeground(Color.BLACK);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 13));
		passwordField.setBorder(null);
		passwordField.setBackground(Color.LIGHT_GRAY);
		passwordField.setBounds(27, 91, 175, 20);
		mainPanel.add(passwordField);
		
		nameField = new HintTextField("Nome");
		nameField.setForeground(Color.BLACK);
		nameField.setFont(new Font("Tahoma", Font.BOLD, 13));
		nameField.setBorder(null);
		nameField.setBackground(Color.LIGHT_GRAY);
		nameField.setBounds(27, 171, 378, 20);
		mainPanel.add(nameField);
		
		addressField = new HintTextField("Endereço");
		addressField.setForeground(Color.BLACK);
		addressField.setFont(new Font("Tahoma", Font.BOLD, 13));
		addressField.setBorder(null);
		addressField.setBackground(Color.LIGHT_GRAY);
		addressField.setBounds(27, 131, 378, 20);
		mainPanel.add(addressField);
		
		emailField = new HintTextField("Email");
		emailField.setForeground(Color.BLACK);
		emailField.setFont(new Font("Tahoma", Font.BOLD, 13));
		emailField.setBorder(null);
		emailField.setBackground(Color.LIGHT_GRAY);
		emailField.setBounds(27, 51, 375, 20);
		mainPanel.add(emailField);
		
		confirmPasswordField = new PasswordHintTextField("Confirmar senha");
		confirmPasswordField.setForeground(Color.BLACK);
		confirmPasswordField.setFont(new Font("Tahoma", Font.BOLD, 13));
		confirmPasswordField.setBorder(null);
		confirmPasswordField.setBackground(Color.LIGHT_GRAY);
		confirmPasswordField.setBounds(227, 91, 175, 20);
		mainPanel.add(confirmPasswordField);
	}
	
	public void openUI() {
		this.mainFrame.setVisible(true);
	}
	
	private void cleanFields() {
		this.passwordField.setText("");
		this.confirmPasswordField.setText("");
		this.nameField.setText("");
		this.addressField.setText("");
		this.emailField.setText("");
	}
	
	private boolean checkFields() {
		Boolean password = this.passwordField.getPassword().length == 0;
		Boolean confirmPassword = this.confirmPasswordField.getPassword().length == 0;
		Boolean name = this.nameField.getText().trim().equals("");
		Boolean address = this.addressField.getText().trim().equals("");
		Boolean email = this.emailField.getText().trim().equals("");
		
		if(password) {
			JOptionPane.showMessageDialog(null, "Informe uma senha válida.");
			return false;
		} 
		
		if(confirmPassword) {
			JOptionPane.showMessageDialog(null, "Informe uma senha de confirmação válida.");
			return false;
		}else{			
			if(!(this.passwordField.getText().equals(this.confirmPasswordField.getText()))) {
				JOptionPane.showMessageDialog(null, "As senhas informadas são diferentes.");
				return false;
			}
		}
		
		if(name) {
			JOptionPane.showMessageDialog(null, "Informe um nome válido.");
			return false;
		}
		if(address) {
			JOptionPane.showMessageDialog(null, "Informe um endereço válido.");
			return false;
		}
		if(email) {
			JOptionPane.showMessageDialog(null, "Informe um email válido.");
			return false;
		}
		
		return true;
	}
	
	private void registerUser(Controller controller) {
		if(checkFields()) {
			try {
				if(controller.registerUser(emailField.getText(), passwordField.getText(), nameField.getText(), addressField.getText())) {
					JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
					mainFrame.dispose();
					controller.openLogin();
				}else{
					JOptionPane.showMessageDialog(null, "Erro no cadastro.");
				}	
			} catch(SQLException e) {
				if(e.getErrorCode() == 1062) {
					JOptionPane.showMessageDialog(null, "Email já cadastrado.");
				}
			}
		}
	}
}
