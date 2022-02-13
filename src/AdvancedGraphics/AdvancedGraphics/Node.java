package AdvancedGraphics;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public abstract class Node {
	
	// Every subclass supports a Color...
	// Access: protected (subclasses can access and modify directly) 
	protected Color c;
	
	// Every subclass of Node is forced to implement this method
	public abstract void draw(Graphics g);
	
	public void recolor() {
		Random generator = new Random();
		
		int red = generator.nextInt(128)+128;
		int green = generator.nextInt(128)+128;
		int blue = generator.nextInt(128)+128;
		
		this.c = new Color(red,green,blue);
	}
}
