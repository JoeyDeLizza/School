package AdvancedGraphics;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;

public class AppWindow {

	private JFrame frame;
	private NodePlot plot;

	/**
	 * Create the application.
	 */
	public AppWindow() {
		initialize();
	}


	private void initialize() {
		
		frame = new JFrame("Advanced Graphics");
		plot = new NodePlot();
		
		// Creates JList
		JList<Node> list = new JList<Node>();
		
		frame.setResizable(false);
		frame.setBounds(100, 100, 870, 481);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(plot);
		scrollPane.setViewportBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(119, 8, 400, 400);
		frame.getContentPane().add(scrollPane);
		
		JButton btnGrid = new JButton("Add Grid");
		btnGrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot.addItem(new NodeGrid());
				// Sets the JList to include the NodeGrid
				list.setListData(new Vector<Node>(plot.getList()));
			}
		});
		btnGrid.setBounds(12, 28, 97, 23);
		frame.getContentPane().add(btnGrid);
		
		JButton btnOrigin = new JButton("Add Origin");
		btnOrigin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot.addItem(new NodeOrigin());
				// sets the JList to include the NodeOrigin
				list.setListData(new Vector<Node>(plot.getList()));
			}
		});
		btnOrigin.setBounds(12, 60, 97, 23);
		frame.getContentPane().add(btnOrigin);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				plot.clear();
				// Sets the JList to the cleared shapes arrayList
				list.setListData(new Vector<Node>(plot.getList()));
			}
		});
		
		
		btnClear.setBounds(12, 361, 97, 23);
		frame.getContentPane().add(btnClear);
		
		JButton btnAddRandom = new JButton("Add 10");
		btnAddRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawRandomShapes(10);
				// Add the shapes to the arrayList
				list.setListData(new Vector<Node>(plot.getList()));
			}
		});
		btnAddRandom.setBounds(12, 94, 97, 23);
		frame.getContentPane().add(btnAddRandom);
		
		JButton btnAdd = new JButton("Add 1000");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawRandomShapes(1000);
				// Adds the shapes to the arrayList
				list.setListData(new Vector<Node>(plot.getList()));
			}
		});
		btnAdd.setBounds(12, 128, 97, 23);
		frame.getContentPane().add(btnAdd);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(532, 8, 302, 400);
		frame.getContentPane().add(scrollPane_1);
		
		scrollPane_1.setViewportView(list);
		
		// Removes a selected shape from JList
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plot.removeItem(list.getSelectedIndex());
				list.setListData(new Vector<Node>(plot.getList()));
			}
		});
		btnRemove.setBounds(12, 163, 97, 23);
		frame.getContentPane().add(btnRemove);
		
		// Randomly recolors every shape
		JButton btnRecolor = new JButton("Recolor");
		btnRecolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Node item : plot.getList()) {
					item.recolor();
				}
				list.setListData(new Vector<Node>(plot.getList()));
				plot.repaint();
			}
		});
		btnRecolor.setBounds(12, 194, 97, 23);
		frame.getContentPane().add(btnRecolor);

		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		
		// Open a file of shape data
		JMenuItem mntmNewMenuItem = new JMenuItem("Open");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser jf = new JFileChooser();
				
				int result = jf.showOpenDialog(null);
				
				if (result!= jf.APPROVE_OPTION) {
					System.err.println("File not Selected");
					return;
				}
				
				// Open the file
				File file = jf.getSelectedFile();
				
				// Create the Scanner object and scope to the entire main method
				Scanner in = null;
				
				try {
					// Try to open the file
					in = new Scanner(file);
					
					try {
						// Read in the data one line at a time
						while(in.hasNextLine()) {
							// Do something with this String
							String line = in.nextLine();
							
							String[] components = line.split(",");
							
							switch (components[0]) {
							case "Rectangle":

								// Create random X and Y locations
								int x = Integer.parseInt(components[1]);
								int y = Integer.parseInt(components[2]);

								// Set a random radius or height/width
								int w = Integer.parseInt(components[3]);
								int h = Integer.parseInt(components[4]);

								// Create random color components (0-255)
								int red = Integer.parseInt(components[5]);
								int green = Integer.parseInt(components[6]);
								int blue = Integer.parseInt(components[7]);
								
								Color c = new Color(red,green,blue);
								
								plot.addItem(new NodeRectangle(x,y,w,h,c));
								list.setListData(new Vector<Node>(plot.getList()));
								
								break;
							case "Circle":
								// Create random X and Y locations
								 x = Integer.parseInt(components[1]);
								 y = Integer.parseInt(components[2]);

								// Set a random radius or height/width
								 int r = Integer.parseInt(components[3]);

								// Create random color components (0-255)
								 red = Integer.parseInt(components[4]);
								 green = Integer.parseInt(components[5]);
								 blue = Integer.parseInt(components[6]);
								
								 c = new Color(red,green,blue);
								
								plot.addItem(new NodeCircle(x,y,r,c));
								list.setListData(new Vector<Node>(plot.getList()));
								break;
							case "Triangle":
								// Create random X and Y locations
								 x = Integer.parseInt(components[1]);
								 y = Integer.parseInt(components[2]);

								// Set a random radius or height/width
								 h = Integer.parseInt(components[3]);

								// Create random color components (0-255)
								 red = Integer.parseInt(components[4]);
								 green = Integer.parseInt(components[5]);
								 blue = Integer.parseInt(components[6]);
								
								 c = new Color(red,green,blue);
								
								plot.addItem(new NodeTriangle(x,y,h,c));
								list.setListData(new Vector<Node>(plot.getList()));
								break;
							}
						}
						
						
					// Fix anything that broke and clean up
					} catch (NoSuchElementException ee) {
						System.err.println("Record Error: " + ee.getMessage());
					} catch (IndexOutOfBoundsException ee) {
						System.err.println("Parse Error: " + ee.getMessage());
					} catch (NumberFormatException ee) {
						System.err.println("Data Error: " + ee.getMessage());
					} finally {
						in.close();
					}
					
				} catch (FileNotFoundException ee) {
					System.err.println("File Unavailable: " + ee.getMessage());
				} 
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		mnNewMenu.add(mntmExit);
	}
	
	// Use this method to help you create a shape loader	
	private void drawRandomShapes(int count) {
		Random generator = new Random();
		int num;
		
		for (int i=0; i<count; i++) {
			
			// Create random X and Y locations
			int x = generator.nextInt(401)-200;
			int y = generator.nextInt(401)-200;

			// Set a random radius or height/width
			int r = generator.nextInt(201);
			int w = generator.nextInt(401);
			int h = generator.nextInt(401);

			// Create random color components (0-255)
			int red = generator.nextInt(128)+128;
			int green = generator.nextInt(128)+128;
			int blue = generator.nextInt(128)+128;
			
			Color c = new Color(red,green,blue);
			
			num = generator.nextInt(3);
			
			if (num == 0) {
				plot.addItem(new NodeTriangle(x,y,h,c));
			} else if(num == 1) {
				plot.addItem(new NodeRectangle(x,y,w,h,c));
			} else
				plot.addItem(new NodeCircle(x,y,r,c));
			
		}

	}
	
	

	public void setVisible(boolean b) {
		if (b) {
			frame.setVisible(true);
		} else {
			frame.setVisible(false);
		}
	}
}
