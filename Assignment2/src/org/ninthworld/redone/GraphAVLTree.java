package org.ninthworld.redone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JPanel;

public class GraphAVLTree extends JPanel {
	
	private HashMap<String, HashMap<Integer, Double>> tests;
	private int y_min = 0, y_max = 1000,    y_interval = y_max/10;
	private int x_min = 0, x_max = 2000000, x_interval = x_max/10;
	
	public GraphAVLTree(HashMap<String, HashMap<Integer, Double>> tests){
		this.tests = tests;
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Avant Garde", Font.PLAIN, 12));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		paintComponent(g2d);
	}
	
	public void paintComponent(Graphics2D g){		
		// Y Properties
		int y_padding_top = 32, y_padding_bot = 32, y_padding_left = 16, y_padding_right = -8;
		int y_height = this.getHeight() - y_padding_top - y_padding_bot;
		double y_count = (y_max - y_min) / y_interval;
		double y_spacing = y_height / y_count;
		int y_max_width = g.getFontMetrics().stringWidth(Integer.toString(y_max));
		
		// X Properties
		int x_max_width = g.getFontMetrics().stringWidth(Integer.toString(x_max));
		int x_padding_top = y_padding_top + y_height + g.getFontMetrics().getHeight(), x_padding_left = y_padding_left + y_max_width, x_padding_right = x_max_width + 16;
		int x_width = this.getWidth() - x_padding_left - x_padding_right;
		double x_count = (x_max - x_min) / x_interval;
		double x_spacing = x_width / x_count;
		
		// Drawing Y
		g.setColor(Color.WHITE);
		g.drawString("Time", x_padding_left + x_width + 16, y_padding_top + y_height/2);
		g.drawString("(ms)", x_padding_left + x_width + 18, y_padding_top + y_height/2 + 16);
		
		g.setColor(Color.GREEN);
		g.drawString("Random", x_padding_left + x_width + 8, y_padding_top + y_height/4);

		g.setColor(Color.ORANGE);
		g.drawString("Increase", x_padding_left + x_width + 8, y_padding_top + y_height/4 + 16);
		
		g.setColor(Color.CYAN);
		g.drawString("Decrease", x_padding_left + x_width + 8, y_padding_top + y_height/4 + 32);
		
		for(int i=0; i<=y_count; i++){
			String str = Integer.toString(i*y_interval);
			int x = y_padding_left + y_max_width - g.getFontMetrics().stringWidth(str) + y_padding_right;
			int y = (int)(y_padding_top + y_height - i*y_spacing);
			
			g.setColor(Color.WHITE);
			g.drawString(str, x, y + g.getFontMetrics().getHeight()/4);
			
			g.setColor(Color.BLACK);
			g.drawLine(y_padding_left + y_max_width, y, x_padding_left + x_width, y);
		}

		// Drawing X
		g.setColor(Color.WHITE);
		g.drawString("Insertions (#)", x_padding_left + x_width/2, y_padding_top - 12);
		for(int i=0; i<=x_count; i++){
			String str = Integer.toString(i*x_interval);
			int x = (int)(x_padding_left + i*x_spacing);
			int y = x_padding_top;
			
			g.setColor(Color.WHITE);
			g.drawString(str, x - g.getFontMetrics().stringWidth(str)/2, y);
			
			g.setColor(Color.BLACK);
			g.drawLine(x, y - g.getFontMetrics().getHeight(), x, y_padding_top);
		}
		
		// Draw Graph Borders
		g.setColor(Color.WHITE);
		g.drawLine(x_padding_left, y_padding_top, x_padding_left, y_padding_top + y_height);
		g.drawLine(x_padding_left, y_padding_top + y_height, x_padding_left + x_width, y_padding_top + y_height);
		
		// Draw Data Points		
		int radius = 2;
		for(String key : tests.keySet()){
			for(Entry<Integer, Double> set : tests.get(key).entrySet()){
				int x = set.getKey();
				double y = set.getValue();
				
				int left = (int)( x_padding_left + ( x / (double)(x_max - x_min) ) * x_width );
				int top  = (int)( y_padding_top + y_height - ( y / (double)(y_max - y_min) ) * y_height );
				
				if(key.equals("Increasing")){
					g.setColor(Color.ORANGE);
				}else if(key.equals("Decreasing")){
					g.setColor(Color.CYAN);
				}else if(key.equals("Randomized")){
					g.setColor(Color.GREEN);
				}
				g.fillOval(left - radius, top - radius, radius*2, radius*2);
			}
		}
	}
}