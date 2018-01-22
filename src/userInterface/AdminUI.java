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
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import domain.Book;
import domain.ExchangeProposal;
import domain.RegistredUser;
import managment.Controller;
import utils.HintTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminUI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private List<RegistredUser> searchedUsers;
	private JTable proposalsTable;
	private JScrollPane scrollPane;
	private JButton validateButton;
	private JButton noValidateButton;
	private ArrayList<ExchangeProposal> proposals;
	
	public AdminUI(Controller controller) {
		this.searchedUsers = searchedUsers;
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(787, 468);
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
		label_1.setBounds(729, 2, 30, 32);
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
		label_2.setBounds(757, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel title = new JLabel("Propostas esperando valida\u00E7\u00E3o:");
		title.setForeground(Color.LIGHT_GRAY);
		title.setFont(new Font("Kalinga", Font.BOLD, 14));
		title.setBounds(26, 45, 247, 23);
		mainPanel.add(title);
		
		JLabel label_5 = new JLabel("Kindler");
		label_5.setForeground(Color.LIGHT_GRAY);
		label_5.setFont(new Font("Dialog", Font.BOLD, 14));
		label_5.setBounds(345, 2, 74, 23);
		mainPanel.add(label_5);
		
		proposalsTable = new JTable();
		proposalsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		proposalsTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Livro 1", "Livro 2", "Email 1", "Email 2"
			}
		));
		proposalsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		proposalsTable.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				validateButton.setEnabled(true);
				noValidateButton.setEnabled(true);
			}
		});
		
		scrollPane = new JScrollPane(proposalsTable);
		mainPanel.add(scrollPane);
		proposalsTable.setFillsViewportHeight(true);
		scrollPane.setBounds(26, 69, 731, 336);		
		
		validateButton = new JButton("Validar");
		validateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validateProposal(controller);
			}
		});
		validateButton.setEnabled(false);
		validateButton.setBounds(628, 416, 131, 23);
		mainPanel.add(validateButton);
		
		noValidateButton = new JButton("N\u00E3o validar");
		noValidateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelProposal(controller);
			}
		});
		noValidateButton.setEnabled(false);
		noValidateButton.setBounds(487, 416, 131, 23);
		mainPanel.add(noValidateButton);
		
		setProposals(controller);
	}
	
	public void openUI() {		
		this.mainFrame.setVisible(true);
	}
	
	private void setProposals(Controller controller) {

		DefaultTableModel model;
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Livro 1", "Livro 2", "Email 1", "Email 2"
				}
			);
		
		proposals = controller.getAceptedProposals();
		
		for(ExchangeProposal proposal : proposals){
			System.out.println(proposal.getUser1Books().get(0).getTitle());
				String[] linha = {proposal.getUser1Books().get(0).getTitle(), proposal.getUser2Books().get(0).getTitle(), proposal.getUser1Email(), proposal.getUser2Email()};
				model.addRow(linha);
		}
		this.proposalsTable.setModel(model);
	}
	
	private void cancelProposal(Controller controller) {
		ExchangeProposal proposal = proposals.get(proposalsTable.getSelectedRow());
		if(controller.removeProposal(proposal)) {
			JOptionPane.showMessageDialog(null, "Proposta cancelada!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro ao cancelar proposta.");
		}
	}
	
	private void validateProposal(Controller controller) {
		ExchangeProposal proposal = proposals.get(proposalsTable.getSelectedRow());
		controller.validateProposal(proposal);
		controller.removeProposal(proposal);
	}
}
