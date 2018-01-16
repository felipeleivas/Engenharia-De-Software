package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import domain.Book;
import domain.RegistredUser;
import managment.Controller;
import utils.HintTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListUsersUI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private List<RegistredUser> searchedUsers;
	private JTable usersTable;
	private JScrollPane scrollPane;
	private JButton selectButton;
	
	public ListUsersUI(Controller controller, List<RegistredUser> searchedUsers) {
		this.searchedUsers = searchedUsers;
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(435, 350);
		this.mainFrame.setUndecorated(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.mainFrame.setLocation(dim.width/2-this.mainFrame.getSize().width/2, dim.height/2-this.mainFrame.getSize().height/2);
		this.mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		this.mainFrame.getContentPane().add(this.mainPanel);
		mainPanel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ListUsersUI.class.getResource("/utils/spotify-logo-button(1).png")));
		label.setBounds(6, 2, 30, 32);
		mainPanel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				label_1.setIcon(new ImageIcon(ListUsersUI.class.getResource("/utils/minimize.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_1.setIcon(new ImageIcon(ListUsersUI.class.getResource("/utils/icons8-minimize-window-26.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mainFrame.setState(Frame.ICONIFIED);
			}
		});
		label_1.setIcon(new ImageIcon(ListUsersUI.class.getResource("/utils/icons8-minimize-window-26.png")));
		label_1.setBounds(375, 2, 30, 32);
		mainPanel.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_2.setIcon(new ImageIcon(ListUsersUI.class.getResource("/utils/\u00EDndice.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_2.setIcon(new ImageIcon(ListUsersUI.class.getResource("/utils/\u00EDndiceclose.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				mainFrame.dispose();
			}
		});
		label_2.setIcon(new ImageIcon(ListUsersUI.class.getResource("/utils/\u00EDndiceclose.png")));
		label_2.setBounds(404, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel title = new JLabel("Usuários encontrados:");
		title.setForeground(Color.LIGHT_GRAY);
		title.setFont(new Font("Kalinga", Font.BOLD, 14));
		title.setBounds(26, 35, 175, 23);
		mainPanel.add(title);
		
		JLabel label_4 = new JLabel("");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label_4.setIcon(new ImageIcon(ListUsersUI.class.getResource("/utils/back-purple.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label_4.setIcon(new ImageIcon(ListUsersUI.class.getResource("/utils/back-black.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				backToSearch(controller);
			}
		});
		label_4.setIcon(new ImageIcon(ListUsersUI.class.getResource("/utils/back-black.png")));
		label_4.setBounds(26, 267, 74, 64);
		mainPanel.add(label_4);
		
		JLabel label_5 = new JLabel("Kindler");
		label_5.setForeground(Color.LIGHT_GRAY);
		label_5.setFont(new Font("Dialog", Font.BOLD, 14));
		label_5.setBounds(188, 2, 74, 23);
		mainPanel.add(label_5);
		
		usersTable = new JTable();
		usersTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome"
			}
		));
		usersTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		usersTable.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				selectButton.setEnabled(true);
			}
		});
		scrollPane = new JScrollPane(usersTable);
		mainPanel.add(scrollPane);
		usersTable.setFillsViewportHeight(true);
		scrollPane.setBounds(26, 69, 378, 197);		
		setUsers(searchedUsers);
		
		selectButton = new JButton("Selecionar");
		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectProfile(controller, usersTable.getSelectedRow());
			}
		});
		selectButton.setEnabled(false);
		selectButton.setBounds(303, 277, 102, 23);
		mainPanel.add(selectButton);
	}
	
	public void openUI(List<RegistredUser> searchedUsers) {
		this.searchedUsers = searchedUsers;
		
		this.mainFrame.setVisible(true);
	}	
	
	private void backToSearch(Controller controller) {
		mainFrame.dispose();
		controller.openSearchUser();
	}

	private void setUsers(List<RegistredUser> users) {
		DefaultTableModel model;
		model = (DefaultTableModel) usersTable.getModel();
		int rows = model.getRowCount();
		
		for (int i = rows - 1; i >= 0; i--) {
		    model.removeRow(i);
		}
		
		for(RegistredUser user : users){
			String[] linha = {user.getName()};
			model.addRow(linha);
		}
	}
	
	private void selectProfile(Controller controller, int selectedIndex) {
		if (this.searchedUsers != null && this.searchedUsers.size() > selectedIndex) {
			RegistredUser selectedUser = this.searchedUsers.get(selectedIndex);
			System.out.println(selectedUser.getOwnBooks());
			mainFrame.dispose();
			controller.openUserProfile(selectedUser);
		} else {
			JOptionPane.showMessageDialog(null, "Usuário inválido");
		}
		
	}
}
