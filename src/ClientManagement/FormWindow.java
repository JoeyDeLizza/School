import java.awt.EventQueue;

import Accounts.Account;
import Accounts.CheckingAccount;
import Accounts.Client;
import Accounts.Portfolio;
import Accounts.SavingsAccount;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.JPanel;
import javax.swing.JTree;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.SpringLayout;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.beans.PropertyChangeEvent;
import java.awt.Color;

public class FormWindow {

	private JFrame frame;
	public ArrayList<Client> clients = new ArrayList<>();
	public JTree tree = new JTree(new DefaultMutableTreeNode("Client"));



	/**
	 * Create the application.
	 */
	public FormWindow() {
		initialize();
	}
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 695, 510);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(20, 20, 429, 158);
		frame.getContentPane().add(scrollPane);
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		JList list = new JList(listModel);
		scrollPane.setViewportView(list);
		
		JFormattedTextField LastNameField = new JFormattedTextField();
		LastNameField.setBounds(552, 58, 137, 37);
		frame.getContentPane().add(LastNameField);
		
		JFormattedTextField firstNameField = new JFormattedTextField();
		firstNameField.setBounds(552, 20, 137, 37);
		frame.getContentPane().add(firstNameField);
		
		JFormattedTextField phoneField = new JFormattedTextField();
		phoneField.setBounds(552, 99, 137, 37);
		frame.getContentPane().add(phoneField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(319, 190, 370, 264);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		panel_1.add(textArea, BorderLayout.CENTER);
		

		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(461, 30, 79, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setBounds(461, 68, 79, 16);
		frame.getContentPane().add(lblLastName);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setBounds(461, 109, 79, 16);
		frame.getContentPane().add(lblPhone);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 190, 287, 264);
		frame.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		
		
		panel.add(tree, BorderLayout.CENTER);
		
		// Set up JTree
		DefaultMutableTreeNode parentNode; 
		DefaultMutableTreeNode node;
		
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		model.insertNodeInto(new DefaultMutableTreeNode("Portfolios"), root, 0);
		//parentNode = (DefaultMutableTreeNode) model.getChild(root, 0);
		//node = new DefaultMutableTreeNode( "Accounts" );
		//addNodeToDefaultTreeModel( model, parentNode, node );

		
		//tree.setModel(null);
		
		
		// Update JTree
		tree.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				model.reload(root);
				if (clients.size() > 0) {
				for (int i = 0; i < tree.getRowCount(); i++) {
				    tree.expandRow(i);
					}
				}
					
			}
		});
		
		
		
		
		// Update Interface
	
		list.setVisible(true);
		list.repaint();
		JButton btnNewButton = new JButton("Update Interface");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				textArea.append("Updating Interface...\n");
				for(int i = 0; i < clients.size(); i++) {
					

					if (clients.get(i).getID().equals(firstNameField.getText() + LastNameField.getText())) {
						updateInterface(listModel, textArea);
					}
				}

			}
		});
		btnNewButton.setBounds(527, 149, 162, 29);
		frame.getContentPane().add(btnNewButton);
		
		

		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Client");
		menuBar.add(mnNewMenu);
		
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Add New Client");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String fn = JOptionPane.showInputDialog(null, "Enter First Name:", 
		                null, JOptionPane.INFORMATION_MESSAGE);
				
				String ln = JOptionPane.showInputDialog(null, "Enter Last Name:", 
		                 null, JOptionPane.INFORMATION_MESSAGE);
				
				String pn = JOptionPane.showInputDialog(null, "Enter Phone Number:", 
		                null, JOptionPane.INFORMATION_MESSAGE);
				clients.add(new Client(fn,ln,pn));
				
				System.out.println(clients.get(0).getFirstName());
				
				textArea.append("Adding New Client...\n");
				
				//model.reload(root);
				
			}
		
			
		});
		
		
		
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Remove Client");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 DefaultMutableTreeNode parentNode; 
					DefaultMutableTreeNode node;
					
					DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
					
					DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
					
					model = (DefaultTreeModel)tree.getModel();
					node = (DefaultMutableTreeNode) model.getChild(root, 0);
					model.removeNodeFromParent(node);
					node = new DefaultMutableTreeNode( "Portfolios");
					addNodeToDefaultTreeModel( model, root, node );
					model.reload(root);
					
					listModel.clear();
					textArea.append("Removing Client\n");
					firstNameField.setText("");
					LastNameField.setText("");
					phoneField.setText("");

					

			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Save Client");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				saveClient();
				//loadClient();
				textArea.append("Saving Client...\n");

			}
		});
		mnNewMenu.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Load Client");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				loadClient();
				loadTree();
				updateInterface(listModel, textArea);
				
				firstNameField.setText(clients.get(clients.size()-1).getFirstName());
				LastNameField.setText(clients.get(clients.size()-1).getLastName());
				phoneField.setText(clients.get(clients.size()-1).getPhoneNumber());

				textArea.append("Loading Client...\n");


				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_1 = new JMenu("Portfolio");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add New Portfolio");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PortName = JOptionPane.showInputDialog(null, "Enter Portfolio Name:", 
		                null, JOptionPane.INFORMATION_MESSAGE);
				Portfolio newPort = new Portfolio(PortName);
				clients.get(0).addPortfolio(newPort);
				
				// Add to JTree
				DefaultMutableTreeNode parentNode; 
				DefaultMutableTreeNode node;
				
				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
				
				DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
				parentNode = (DefaultMutableTreeNode) model.getChild(root, 0);
				node = new DefaultMutableTreeNode( PortName );
				addNodeToDefaultTreeModel( model, parentNode, node );
				
				textArea.append("Adding New Portfolio...\n");

				
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Remove Portfolio");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("Account");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Add New Account");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Object[] choices1 = clients.get(0).getClientPortfolioNames().toArray();
			    String portN = (String) JOptionPane.showInputDialog(null, "Choose Portfolio.",
			        null, JOptionPane.QUESTION_MESSAGE, null, 
			        choices1,
			        choices1[0]);
			    if(portN != null) {
				
					String[] choices = { "Checkings Account", "Savings Account" };
				    String accT = (String) JOptionPane.showInputDialog(null, "Choose the account Type.",
				        null, JOptionPane.QUESTION_MESSAGE, null, 
				        choices,
				        choices[0]);
				   if(accT != null) {
				    
				    Account acc = null;
				    String depStr = JOptionPane.showInputDialog(null, "Enter the amount to deposit:", 
			                null, JOptionPane.INFORMATION_MESSAGE);
				    double dep = Double.parseDouble(depStr);
				    
				    String accNum = JOptionPane.showInputDialog(null, "Enter Account Number:", 
			                null, JOptionPane.INFORMATION_MESSAGE);
				    
				    String desc = null;
				    if (accT == choices[0]) {
						acc = new CheckingAccount(dep, accNum);
						desc = ((CheckingAccount) acc).getDescription();
					    //clients.get(0).getPortfolios().get(0).addAccount(acc);
					    System.out.println(accT);
	
				    } else if(accT == choices[1]) {
				    	
				    	acc = new SavingsAccount(dep, accNum);
				    	desc = ((SavingsAccount) acc).getDescription();
					   // clients.get(0).getPortfolios().get(1).addAccount(acc);
					    System.out.println(accT);
				    	
				    }
				    
				    int nodeNum = 0;
				    for (int i = 0; i < choices1.length; i++) {
				    	
				    	if (portN == choices1[i]) {
							   clients.get(0).getPortfolios().get(i).addAccount(acc);
							   nodeNum = i;
	

				    	
				    	}
			    	
				    }
				    
					DefaultMutableTreeNode parentNode; 
					DefaultMutableTreeNode node;
					
					DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
					
					DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
					parentNode = (DefaultMutableTreeNode) model.getChild(root, 0);
					parentNode = (DefaultMutableTreeNode) model.getChild(parentNode, nodeNum);
	
					node = new DefaultMutableTreeNode(desc);
					addNodeToDefaultTreeModel( model, parentNode, node );
				    
					textArea.append("Adding New Account... "  + acc.getAccountNumber() + " ($" +acc.getBalance() +")\n");
				   } else {
						textArea.append("Adding account cancelled.\n");
				   }

			    } else {
					textArea.append("Adding account cancelled.\n");
			    }
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Remove Account");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Object[] choices1 = clients.get(0).getClientPortfolioNames().toArray();
			    String portN = (String) JOptionPane.showInputDialog(null, "Choose Portfolio.",
			        null, JOptionPane.QUESTION_MESSAGE, null, 
			        choices1,
			        choices1[0]);
			    
			    Portfolio port = clients.get(0).getClientPortfolioByName(portN);
			    
			    DefaultMutableTreeNode parentNode; 
				DefaultMutableTreeNode node;
				
				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
				
				DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
				
				// Sets parent node to root
				parentNode = (DefaultMutableTreeNode) model.getChild(root, 0);
				// Sets parent node to Accounts parent node
				parentNode = (DefaultMutableTreeNode) model.getChild(parentNode, 0);
				// Sets parent node to node to be removed
				parentNode = (DefaultMutableTreeNode) model.getChild(parentNode, 0);
		
			    model.removeNodeFromParent(parentNode);
			    
			    clients.get(0).getPortfolios().remove(port);
			    
			    
				textArea.append("Removing Account...\n");

			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_5);
	}
	
	private static void addNodeToDefaultTreeModel( DefaultTreeModel treeModel, DefaultMutableTreeNode parentNode, DefaultMutableTreeNode node ) {
		
		treeModel.insertNodeInto(  node, parentNode, parentNode.getChildCount()  );
		
		if (  parentNode == treeModel.getRoot()  ) {
			treeModel.nodeStructureChanged(  (TreeNode) treeModel.getRoot()  );
		}
	}
	
	private void updateInterface(DefaultListModel<String> listModel, JTextArea textArea) {
		if (clients.size() == 0) {
			return;
		} else {
		listModel.clear();
		listModel.addElement("First Name: " + clients.get(0).getFirstName());
		listModel.addElement("Last Name: " + clients.get(0).getLastName());
		listModel.addElement("Phone Number: " + clients.get(0).getPhoneNumber());
		listModel.addElement("Number of Portfolios: " + clients.get(0).getPortfoliocount());
		listModel.addElement("Portfolios Total Value: ($" + clients.get(0).getClientPortfolioValue() +")");
		listModel.addElement("Portfolios: " + clients.get(0).getPortfoliocount());
		
		// Portfolios
		for (int i = 0; i < clients.get(0).getPortfoliocount(); i++) {
			ArrayList<String> names = clients.get(0).getClientPortfolioNames();
			listModel.addElement("Portfolio " + names.get(i) + ": $" + clients.get(0).getPortfolios().get(i).getValue());

			ArrayList<String> nameList = new ArrayList<String>();
			//Accounts
			for (int j = 0; j < clients.get(0).getPortfolios().get(i).getPortfolioAccounts().size(); j++) {
				nameList = clients.get(0).getPortfolios().get(j).getPortfolioMemberNamesWithTypesAndBal();
				
				
			}
			for (String s : nameList) {
				
				listModel.addElement(s);
				System.out.println(s);
			}
		}
			//+ " " + clients.get(i).getPortfolios().get(i).getPortfolioMemberValues().get(i));
		}


		System.out.println(clients.get(0).getPortfolios().get(0).getPortfolioAccounts().size());
		System.out.println(clients.get(0).getPortfolios().get(1).getPortfolioAccounts().size());

		
		textArea.append("Updating Interface...\n");
		
	}
	
	public void saveClient() {
		
		Client client = clients.get(0);
		
		try {
			FileOutputStream fos = new FileOutputStream(clients.get(0).getID());
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(client);
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loadClient() {
		try {
			
			JFileChooser jf = new JFileChooser();
			
			int result = jf.showOpenDialog(null);
			
			if (result!= jf.APPROVE_OPTION) {
				System.err.println("File not Selected");
				return;
			}
			
			// Open the file
			File file = jf.getSelectedFile();
			
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream oos = new ObjectInputStream(fis);
			Client client2 = new Client((Client) oos.readObject());
			if (clients.size() == 0) {
				clients.add(client2);
			} else {
			clients.set(0, client2);
			}
			System.out.println(clients.get(0).getID());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File not Found");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Class not Found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loadTree() {
	    DefaultMutableTreeNode parentNode; 
		DefaultMutableTreeNode node;
		
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		
		// Sets parentnode to portfolio node
		parentNode = (DefaultMutableTreeNode) model.getChild(root, 0);
		try {
			model = (DefaultTreeModel)tree.getModel();
			node = (DefaultMutableTreeNode) model.getChild(root, 0);
			model.removeNodeFromParent(node);
			node = new DefaultMutableTreeNode( "Portfolios");
			addNodeToDefaultTreeModel( model, root, node );
			model.reload(root);
		    
			for(int i = 0; i < clients.get(0).getPortfoliocount(); i++) {
				
				parentNode = (DefaultMutableTreeNode) model.getChild(root, 0);
				node = new DefaultMutableTreeNode( clients.get(0).getPortfolios().get(i).getName() );
				addNodeToDefaultTreeModel( model, parentNode, node );
				
				parentNode = (DefaultMutableTreeNode) model.getChild(parentNode, i);

				for(int j = 0; j < clients.get(0).getPortfolios().get(i).getPortfolioAccounts().size(); j++) {
					
					node = new DefaultMutableTreeNode( clients.get(0).getPortfolios().get(i).getPortfolioAccounts().get(j));
					addNodeToDefaultTreeModel( model, parentNode, node );

				}
			}
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Error");


	}
	
	}
}
