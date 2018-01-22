package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import domain.Book;
import domain.ExchangeProposal;
import domain.RegistredUser;
import managment.Controller;

public class UserExchangeProposalsUI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private Book selectedBook;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Book oldBook;
	private ArrayList<Book> bookList;
	private JList wantIist;
	private ArrayList<Book> wantBookList;
	private RegistredUser user;
	private JTable proposalsToMeTable;
	private JScrollPane scrollPane;
	private JTable proposalsByMeTable;
	private JScrollPane scrollPane2;
	private JButton aceptTradeButton;
	private JButton aceptTradeButton2;
	private JButton cancelTradeButton;
	private JButton cancelTradeButton2;
	private ArrayList<ExchangeProposal> proposalsByMe;
	private ArrayList<ExchangeProposal> proposalsToMe;
	
	public UserExchangeProposalsUI(Controller controller, RegistredUser user) {
		this.user = user;
		this.mainFrame = new JFrame();
		mainFrame.setTitle("Registrar");
		this.mainFrame.setSize(817, 590);
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
		label_1.setBounds(759, 2, 30, 32);
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
		label_2.setBounds(787, 2, 30, 32);
		mainPanel.add(label_2);
		
		JLabel lblKindler = new JLabel("Kindler");
		lblKindler.setForeground(Color.LIGHT_GRAY);
		lblKindler.setFont(new Font("Kalinga", Font.BOLD, 14));
		lblKindler.setBounds(362, 2, 74, 23);
		mainPanel.add(lblKindler);
		
		JLabel lblMeusLivros = new JLabel("Propostas que eu fiz");
		lblMeusLivros.setForeground(Color.LIGHT_GRAY);
		lblMeusLivros.setFont(new Font("Dialog", Font.BOLD, 13));
		lblMeusLivros.setBounds(26, 45, 223, 23);
		mainPanel.add(lblMeusLivros);
		
		proposalsByMeTable = new JTable();
		proposalsByMeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		proposalsByMeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ofereço", "Recebo", "Email do outro usuário", "Meu estado da troca", "Estado da troca do outro usuário"
			}
		));
		proposalsByMeTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		proposalsByMeTable.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(proposalsByMeTable.getValueAt(proposalsByMeTable.getSelectedRow(), 3).equals("ACEITA") ||
						proposalsByMeTable.getValueAt(proposalsByMeTable.getSelectedRow(), 3).equals("AGUARDANDO VALIDAÇÂO")) {
					aceptTradeButton.setEnabled(false);
				} else {
					aceptTradeButton.setEnabled(true);
				}
				cancelTradeButton.setEnabled(true);
			}
		});
		
		scrollPane = new JScrollPane(proposalsByMeTable);
		mainPanel.add(scrollPane);
		proposalsByMeTable.setFillsViewportHeight(true);
		scrollPane.setBounds(26, 69, 765, 197);		
		
		JLabel lblPropostasQueRecebi = new JLabel("Propostas que recebi");
		lblPropostasQueRecebi.setForeground(Color.LIGHT_GRAY);
		lblPropostasQueRecebi.setFont(new Font("Dialog", Font.BOLD, 13));
		lblPropostasQueRecebi.setBounds(26, 308, 223, 23);
		mainPanel.add(lblPropostasQueRecebi);
		
		proposalsToMeTable = new JTable();
		proposalsToMeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		proposalsToMeTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Requer", "Recebo", "Email do outro usuário", "Meu estado da troca", "Estado da troca do outro usuário"
			}
		));
		proposalsToMeTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		proposalsToMeTable.addMouseListener(new java.awt.event.MouseAdapter(){
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(proposalsToMeTable.getValueAt(proposalsToMeTable.getSelectedRow(), 3).equals("ACEITA") ||
						proposalsToMeTable.getValueAt(proposalsToMeTable.getSelectedRow(), 3).equals("AGUARDANDO VALIDAÇÂO")) {
					aceptTradeButton2.setEnabled(false);
				} else {
					aceptTradeButton2.setEnabled(true);
				}
				cancelTradeButton2.setEnabled(true);
			}
		});
		scrollPane2 = new JScrollPane(proposalsToMeTable);
		mainPanel.add(scrollPane2);
		proposalsToMeTable.setFillsViewportHeight(true);
		scrollPane2.setBounds(26, 331, 765, 197);	
		
		aceptTradeButton = new JButton("Aceitar troca");
		aceptTradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setProposalsByMe(controller.aceptExchangeProposal(proposalsByMe.get(proposalsByMeTable.getSelectedRow())));
			}
		});
		aceptTradeButton.setEnabled(false);
		aceptTradeButton.setBounds(26, 274, 156, 23);
		mainPanel.add(aceptTradeButton);
		
		aceptTradeButton2 = new JButton("Aceitar troca");
		aceptTradeButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setProposalsToMe(controller.aceptExchangeProposal(proposalsToMe.get(proposalsToMeTable.getSelectedRow())));
			}
		});
		aceptTradeButton2.setEnabled(false);
		aceptTradeButton2.setBounds(26, 536, 156, 23);
		mainPanel.add(aceptTradeButton2);
		
		cancelTradeButton = new JButton("Cancelar troca");
		cancelTradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setProposalsByMe(controller.cancelExchangeProposal(proposalsByMe.get(proposalsByMeTable.getSelectedRow())));
			}
		});
		cancelTradeButton.setEnabled(false);
		cancelTradeButton.setBounds(192, 274, 156, 23);
		mainPanel.add(cancelTradeButton);
		
		cancelTradeButton2 = new JButton("N\u00E3o aceitar troca");
		cancelTradeButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setProposalsToMe(controller.cancelExchangeProposal(proposalsToMe.get(proposalsToMeTable.getSelectedRow())));
			}
		});
		cancelTradeButton2.setEnabled(false);
		cancelTradeButton2.setBounds(192, 536, 156, 23);
		mainPanel.add(cancelTradeButton2);
		
		
		
		
		setProposalsByMe(user);
		setProposalsToMe(user);
	}
	
	public void openUI() {
		this.mainFrame.setVisible(true);
	}
	
	private void setProposalsByMe(RegistredUser user) {
		this.aceptTradeButton.setEnabled(false);
		this.cancelTradeButton.setEnabled(false);
		this.proposalsByMe = new ArrayList<ExchangeProposal>();
		DefaultTableModel model;
		model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Requer", "Recebo", "Email do outro usuário", "Meu estado da troca", "Estado da troca do outro usuário"
				}
			);
		
		ArrayList<ExchangeProposal> proposals = user.getProposals();
		
		for(ExchangeProposal proposal : proposals){
			System.out.println(proposal.getUser1Books().get(0).getTitle()	);

			if(proposal.getUser1Email().equals(user.getEmail())) {
				this.proposalsByMe.add(proposal);
				String iOffer = "";
				
				for(Book book: proposal.getUser1Books()) {
					iOffer += book.getTitle()+ ", ";
				}
				String iReceive = "";
				for(Book book: proposal.getUser2Books()) {
					iReceive += book.getTitle()+ ", ";
				}
				
				String[] linha = {iOffer, iReceive, proposal.getUser2Email(), proposal.getStatusUser1(), proposal.getStatusUser2()};
				model.addRow(linha);
			}
		}
		this.proposalsByMeTable.setModel(model);
	}
	
	private void setProposalsToMe(RegistredUser user) {
		this.aceptTradeButton2.setEnabled(false);
		this.cancelTradeButton2.setEnabled(false);
		this.proposalsToMe = new ArrayList<ExchangeProposal>();
		DefaultTableModel model;
		model = (DefaultTableModel) proposalsToMeTable.getModel();
		int rows = model.getRowCount();
		
		for (int i = rows - 1; i >= 0; i--) {
		    model.removeRow(i);
		}
		
		ArrayList<ExchangeProposal> proposals = user.getProposals();
		
		for(ExchangeProposal proposal : proposals){
			if(proposal.getUser2Email().equals(user.getEmail())) {
				this.proposalsToMe.add(proposal);
				String iGive = "";
				
				for(Book book: proposal.getUser2Books()) {
					iGive += book.getTitle()+ ", ";
				}
				String iReceive = "";
				for(Book book: proposal.getUser1Books()) {
					iReceive += book.getTitle()+ ", ";
				}
				String[] linha = {iGive, iReceive, proposal.getUser1Email(), proposal.getStatusUser2(), proposal.getStatusUser1()};
				model.addRow(linha);
			}
		}
	}
}
