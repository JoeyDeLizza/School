package AdvancedGraphics;

import java.awt.Color;
import java.awt.Graphics;


/**
 * 
 * NodeTriangle draws a right triangle with the origin starting from the right angle
 *
 */
public class NodeTriangle extends Node {
	
	// Every subclass of Node must provide implementation 
	// for the draw() method (see below) as well as inheriting the 
	// protected instance variable c for the shape color
	
	private int x = 0;
	private int y = 0;
	private int shapeBase= 0;
	private int shapeHeight = 0;
	private int plotWidth = 0;
	private int plotHeight = 0;

	public NodeTriangle(int x, int y,int shapeHeight, Color c) {
		this.x = x;
		this.y = y;
		this.shapeBase = shapeHeight;
		this.shapeHeight = shapeHeight;
		this.c = c;
	}
	
	public void draw(Graphics g) {
		
		plotWidth = (int) g.getClipBounds().getWidth();
		plotHeight = (int) g.getClipBounds().getHeight();
		
		int dX = (x + (plotWidth / 2));
		int dY = (-y + (plotHeight / 2));
		
		g.setColor(c);
		
		int[] xPoints = {dX, dX, dX+shapeBase};
		int[] yPoints = {dY, dY-shapeHeight, dY};
		g.drawPolygon(xPoints, yPoints, 3);	
	}
	
	public String toString() {
		return "Triangle " 
				+ "x" + x + " "
				+ "y" + y + " "
				+ "W" + shapeBase + " "
				+ "H" + shapeHeight+ " "
				+ "R" + c.getRed() + " "
				+ "G" + c.getGreen() + " "
				+ "B" + c.getBlue();
	}
	
}


