

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JFormattedTextField;

public class GraphingWindow {

	private JFrame frame;
	private double m;
	private double b;
	private boolean DrawLogXToggle = false;
	private boolean DrawSqrtXToggle = false;
	private boolean DrawXToggle = false;
	private boolean DrawXsqrToggle = false;
	private boolean DrawXCubedToggle = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphingWindow window = new GraphingWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphingWindow() {
		initialize();
	}
	
	/**
	 * This method takes a Graphics object as a parameter and is used by the 
	 * painComponent function to draw a grid.
	 * @param g a graphics object
	 */
	private void drawGrid(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawString("x", 20, 190);
		g.drawString("y", 180, 20);
		g.drawString("1.0", 210, 105); 
		g.drawLine(200, 0, 200, 400);
		g.drawLine(0, 200, 400, 200);
		g.drawLine(195, 300, 205, 300);
		g.drawLine(195, 100, 205, 100);
		g.drawLine(100, 195, 100, 205);
		g.drawLine(300, 195, 300, 205);
	}
	
	// Draw Functions
	
	/**
	 * This method takes a Graphics object as a parameter and is used by the 
	 * painComponent function to draw the f(logx).
	 * @param g a graphics object
	 */
	private void drawLogX(Graphics g) {
		double dx = .0001;
		for (double x=0; x<=2; x+=dx) {
			double y = m*Math.log(x) +b;
			int cX = (int) (100*x + 200);
			int cY = (int) (100*(-y) +200);
			
			g.drawLine(cX, cY, cX, cY);
		}
	}
	
	/**
	 * This method takes a Graphics object as a parameter and is used by the 
	 * painComponent function to draw the f(x).
	 * @param g a graphics object
	 */
	private void drawX(Graphics g) {
		double dx = .0001;
		for (double x=-2; x<=2; x+=dx) {
			double y = m*x +b;
			int cX = (int) (100*x + 200);
			int cY = (int) (100*(-y) +200);
			
			g.drawLine(cX, cY, cX, cY);
		}
	}
	
	/**
	 * This method takes a Graphics object as a parameter and is used by the 
	 * painComponent function to draw the f(x^3).
	 * @param g a graphics object
	 */
	private void drawXCubed(Graphics g) {
		double dx = .0001;
		for (double x=-2; x<=2; x+=dx) {
			double y = m*x*x*x +b;
			int cX = (int) (100*x + 200);
			int cY = (int) (100*(-y) +200);
			
			g.drawLine(cX, cY, cX, cY);
		}
	}
	
	/**
	 * This method takes a Graphics object as a parameter and is used by the 
	 * painComponent function to draw the f(x^2).
	 * @param g a graphics object
	 */
	private void drawXsqr(Graphics g) {
		double dx = .0001;
		for (double x=-2; x<=2; x+=dx) {
			double y = m*x*x +b;
			int cX = (int) (100*x + 200);
			int cY = (int) (100*(-y) +200);
			
			g.drawLine(cX, cY, cX, cY);
		}
	}
	
	/**
	 * This method takes a Graphics object as a parameter and is used by the 
	 * painComponent function to draw the f(sqrtx).
	 * @param g a graphics object
	 */
	private void drawSqrtX(Graphics g) {
		double dx = .0001;
		for (double x=0; x<=2; x+=dx) {
			double y = m*Math.sqrt(x) +b;
			int cX = (int) (100*x + 200);
			int cY = (int) (100*(-y) +200);
			
			g.drawLine(cX, cY, cX, cY);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 432, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel canvas = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawGrid(g);
				
				g.setColor(Color.RED);
				if(getDrawXToggle()) {
					drawX(g);
				}
				
				if(getDrawXsqrToggle()) {
					drawXsqr(g);
				}
				
				if(getDrawXCubedToggle()) {
					drawXCubed(g);
				}
				
				if(getDrawSqrtXToggle()) {
					drawSqrtX(g);
				}
				
				if(getDrawLogXToggle()) {
					drawLogX(g);
				}
			}
		};
		canvas.setBackground(Color.WHITE);
		Border blackline = BorderFactory.createLineBorder(Color.black);
		canvas.setBorder(blackline);
		canvas.setBounds(16, 136, 400, 400);
		frame.getContentPane().add(canvas);
		
		// Labels
		JLabel fxLabel = new JLabel("y = m*f(x)+b");
		fxLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		fxLabel.setBounds(248, 66, 136, 35);
		frame.getContentPane().add(fxLabel);
		
		JLabel scalarLabel = new JLabel("Scalar (m)");
		scalarLabel.setBounds(10, 11, 80, 19);
		frame.getContentPane().add(scalarLabel);
		
		JLabel offsetLabel = new JLabel("Offset (b)");
		offsetLabel.setBounds(205, 13, 74, 14);
		frame.getContentPane().add(offsetLabel);
		
		// Radio Butts
		JRadioButton xButt = new JRadioButton("X");
		xButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( xButt.isSelected() )
					setDrawXToggle(true);
				else 
					setDrawXToggle(false);				
			}
		});
		xButt.setBounds(16, 48, 109, 23);
		frame.getContentPane().add(xButt);
		
		JRadioButton xSqrButt = new JRadioButton("X^2");
		xSqrButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( xSqrButt.isSelected() )
					setDrawXsqrToggle(true);
				else 
					setDrawXsqrToggle(false);
			}
		});
		xSqrButt.setBounds(16, 74, 74, 23);
		frame.getContentPane().add(xSqrButt);
		
		JRadioButton LogXButt = new JRadioButton("Log(x)");
		LogXButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( LogXButt.isSelected() )
					setDrawLogXToggle(true);
				else 
					setDrawLogXToggle(false);
			}
		});
		LogXButt.setBounds(16, 101, 79, 23);
		frame.getContentPane().add(LogXButt);
		
		JRadioButton xCubedButt = new JRadioButton("X^3");
		xCubedButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( xCubedButt.isSelected() )
					setDrawXCubedToggle(true);
				else 
					setDrawXCubedToggle(false);
			}
		});
		xCubedButt.setBounds(122, 74, 64, 23);
		frame.getContentPane().add(xCubedButt);
		
		JRadioButton sqrtXButt = new JRadioButton("Sqrt(X)");
		sqrtXButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( sqrtXButt.isSelected() )
					setDrawSqrtXToggle(true);
				else 
					setDrawSqrtXToggle(false);
			}
		});
		sqrtXButt.setBounds(122, 101, 74, 23);
		frame.getContentPane().add(sqrtXButt);
	
		// Text Fields
		JFormattedTextField slopeTextField = new JFormattedTextField();
		slopeTextField.setText("1.0");
		slopeTextField.setBounds(93, 8, 64, 23);
		frame.getContentPane().add(slopeTextField);
		
		JFormattedTextField offsetTextField = new JFormattedTextField();
		offsetTextField.setText("0.0");
		offsetTextField.setBounds(281, 8, 64, 23);
		frame.getContentPane().add(offsetTextField);
		
		// Graph Butt
		JButton graphButt = new JButton("Graph");
		graphButt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				if (setUserOffset(offsetTextField.getText()) && setValidSlope(slopeTextField.getText())) {
					canvas.repaint();
				}
				else {
					JOptionPane.showMessageDialog(frame, "Input needs to be a valid number");
				}
			}
		});
		graphButt.setBounds(214, 101, 196, 23);
		frame.getContentPane().add(graphButt);
		

	}
	
	/**
	 * This function takes a string as an argument and checks to see if it is a valid number.
	 * @param string
	 * @return returns true if the string is a valid number valid and false if it is not
	 */
	public static boolean isNumeric(String string) {
		if (string == null)
			return false;
		try {
			double d = Double.parseDouble(string);
		} catch (NumberFormatException nfe){
			return false;
		}
		return true;
	}
	
	// Getters and Setters
	public double getB() {
		return this.b;
	}
	
	public void setB(double b) {
		this.m = b;
	}
	
	public boolean getDrawLogXToggle() {
		return this.DrawLogXToggle;
	}
	
	public void setDrawLogXToggle(boolean setting) {
		this.DrawLogXToggle = setting;
	}
	
	public boolean getDrawXToggle() {
		return this.DrawXToggle;
	}
	
	public void setDrawXToggle(boolean setting) {
		this.DrawXToggle = setting;
	}
	
	public boolean getDrawXsqrToggle() {
		return this.DrawXsqrToggle;
	}
	
	public void setDrawXsqrToggle(boolean setting) {
		this.DrawXsqrToggle = setting;
	}
	
	public boolean getDrawXCubedToggle() {
		return this.DrawXCubedToggle;
	}
	
	public void setDrawXCubedToggle(boolean setting) {
		this.DrawXCubedToggle = setting;
	}
	
	public boolean getDrawSqrtXToggle() {
		return this.DrawSqrtXToggle;
	}
	
	public void setDrawSqrtXToggle(boolean setting) {
		this.DrawSqrtXToggle = setting;
	}
	
	public double getM() {
		return this.m;
	}
	
	public void setM(double m) {
		this.m = m;
	}
	
	/**
	 * This function sets m to the passed value if it is a valid number
	 * @param string
	 * @return returns true if the string passed is a valid number
	 */
	public boolean setValidSlope(String string) {
		if (isNumeric(string)) {
			this.m = Double.parseDouble(string);
			return true;
		}
		else {
			System.out.println("Error: not a number");
			return false;
		}
	}
	
	/**
	 * This function sets b to the passed value if it is a valid number
	 * @param string
	 * @return returns true if the string passed is a valid number and false if it is not
	 */
	public boolean setUserOffset(String string) {
		if (isNumeric(string)) {
			this.b = Double.parseDouble(string);
			return true;
		}
		else {
			System.out.println("Error: not a number");
			return false;
		}
	}

}
