package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import managment.Controller;
import utils.HintTextField;
import utils.PasswordHintTextField;

public class LoginUI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private HintTextField usernameField;
	private JPasswordField pwdSenha;
	private JLabel lblRegistrar;
	
	public LoginUI(Controller controller) {
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(281, 228);
		this.mainFrame.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.mainFrame.setLocation(dim.width/2-this.mainFrame.getSize().width/2, dim.height/2-this.mainFrame.getSize().height/2);
		this.mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		this.mainFrame.getContentPane().add(this.mainPanel);
		mainPanel.setLayout(null);
		
		this.usernameField = new HintTextField("Email");
		usernameField.setForeground(Color.BLACK);
		usernameField.setFont(new Font("Tahoma", Font.BOLD, 13));
		usernameField.setBackground(Color.LIGHT_GRAY);
		usernameField.setBorder(null);
		this.usernameField.setBounds(27, 60, 225, 20);
		this.mainPanel.add(usernameField);
		
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
		label_1.setBounds(222, 2, 30, 32);
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
		label_2.setBounds(251, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel lblKindler = new JLabel("Kindler");
		lblKindler.setForeground(Color.LIGHT_GRAY);
		lblKindler.setFont(new Font("Kalinga", Font.BOLD, 14));
		lblKindler.setBounds(98, 2, 74, 23);
		mainPanel.add(lblKindler);
		
		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_3.setIcon(new ImageIcon(LoginUI.class.getResource("/utils/login(1).png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_3.setIcon(new ImageIcon(LoginUI.class.getResource("/utils/login.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				login(controller);
			}
		});
		label_3.setIcon(new ImageIcon(LoginUI.class.getResource("/utils/login.png")));
		label_3.setBounds(221, 154, 40, 37);
		mainPanel.add(label_3);
		
		pwdSenha = new PasswordHintTextField("Senha");
		pwdSenha.setForeground(Color.BLACK);
		pwdSenha.setFont(new Font("Tahoma", Font.BOLD, 13));
		pwdSenha.setBorder(null);
		pwdSenha.setBackground(Color.LIGHT_GRAY);
		pwdSenha.setBounds(27, 103, 225, 20);
		mainPanel.add(pwdSenha);
		
		lblRegistrar = new JLabel("Registrar Usu\u00E1rio");
		lblRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegistrar.setForeground(new Color(153, 51, 204));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblRegistrar.setForeground(new Color(0, 0, 0));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				openRegistration(controller);
			}
		});
		lblRegistrar.setForeground(new Color(0, 0, 0));
		lblRegistrar.setFont(new Font("Dialog", Font.BOLD, 14));
		lblRegistrar.setBounds(28, 165, 144, 24);
		mainPanel.add(lblRegistrar);
	}
	
	public void openUI() {
		this.mainFrame.setVisible(true);
	}
	
	private boolean checkFields() {
		Boolean username = this.usernameField.getText().trim().equals("");
		@SuppressWarnings("deprecation")
		Boolean password = this.pwdSenha.getText().trim().equals("");

		if(username) {
			JOptionPane.showMessageDialog(null, "Informe um nome de usuário válido.");
			return false;
		}
		if(password) {
			JOptionPane.showMessageDialog(null, "Informe uma senha válida.");
			return false;
		}
		return true;
	}
	
	private void openRegistration(Controller controller) {
		this.mainFrame.dispose();
		controller.openRegistration();
	}
	
	@SuppressWarnings("deprecation")
	private void login(Controller controller) {
		if(checkFields()) {
			try {
				if(controller.login(usernameField.getText(), pwdSenha.getText())) {
					mainFrame.dispose();
					controller.openMain();
				} else {
					JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos.");
				}
			} catch (SQLException e) {
				if(e.getErrorCode() == 0) {
					JOptionPane.showMessageDialog(null, "Email não encontrado.");
				}
			}
		}
	}
}
