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
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.JTextField;

import domain.RegistredUser;
import managment.Controller;
import utils.HintTextField;

public class SearchUserUI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private HintTextField nameField;
	
	public SearchUserUI(Controller controller) {
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(296, 263);
		this.mainFrame.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.mainFrame.setLocation(dim.width/2-this.mainFrame.getSize().width/2, dim.height/2-this.mainFrame.getSize().height/2);
		this.mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		this.mainFrame.getContentPane().add(this.mainPanel);
		mainPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/spotify-logo-button(1).png")));
		label.setBounds(6, 2, 30, 32);
		mainPanel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label_1.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/minimize.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_1.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/icons8-minimize-window-26.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mainFrame.setState(Frame.ICONIFIED);
			}
		});
		label_1.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/icons8-minimize-window-26.png")));
		label_1.setBounds(238, 2, 30, 32);
		mainPanel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_2.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/\u00EDndice.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_2.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/\u00EDndiceclose.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mainFrame.dispose();
			}
		});
		label_2.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/\u00EDndiceclose.png")));
		label_2.setBounds(266, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel title = new JLabel("Pesquisar usuários");
		title.setForeground(Color.LIGHT_GRAY);
		title.setFont(new Font("Kalinga", Font.BOLD, 14));
		title.setBounds(16, 52, 135, 23);
		mainPanel.add(title);
		
		JLabel label_3 = new JLabel("");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_3.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/ok-purple.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_3.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/ok-black.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				researchUsers(controller);
			}
		});
		label_3.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/ok-black.png")));
		label_3.setBounds(222, 176, 64, 75);
		mainPanel.add(label_3);
		
		JLabel label_4 = new JLabel("");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_4.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/back-purple.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_4.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/back-black.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				backToMain(controller);
			}
		});
		label_4.setIcon(new ImageIcon(SearchUserUI.class.getResource("/utils/back-black.png")));
		label_4.setBounds(6, 187, 74, 64);
		mainPanel.add(label_4);
		
		nameField = new HintTextField("  Nome");
		nameField.setForeground(Color.BLACK);
		nameField.setFont(new Font("Tahoma", Font.BOLD, 13));
		nameField.setBorder(null);
		nameField.setBackground(Color.LIGHT_GRAY);
		nameField.setBounds(16, 86, 264, 20);
		mainPanel.add(nameField);
		
		JLabel label_5 = new JLabel("Kindler");
		label_5.setForeground(Color.LIGHT_GRAY);
		label_5.setFont(new Font("Dialog", Font.BOLD, 14));
		label_5.setBounds(145, 2, 74, 23);
		mainPanel.add(label_5);
	}
	
	public void openUI() {
		this.mainFrame.setVisible(true);
	}
	
	private void backToMain(Controller controller) {
		mainFrame.dispose();
		controller.openMain();
	}
	
	private void researchUsers(Controller controller) {
		if(checkFields()) {
			ArrayList<RegistredUser> searchedUsers= controller.researchUsers(nameField.getText());
			if(searchedUsers != null) {
				mainFrame.dispose();
				controller.openListUsers(searchedUsers);
			} else{
				JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
			}
		}
	}
	private boolean checkFields() {
		Boolean name = this.nameField.getText().trim().equals("");		
		if(name) {
			JOptionPane.showMessageDialog(null, "Informe um título válido.");
			return false;
		}
		
		return true;
	}
}
