package org.ninthworld.binarytrees;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import org.ninthworld.binarytrees.RBNode.RBColor;

public class DisplayBSTree extends JPanel {
	
	private BSTree tree;
	
	public DisplayBSTree(BSTree tree){
		this.tree = tree;
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Avant Garde", Font.PLAIN, 12));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(tree.getRoot() != null){
			if(tree instanceof AVLTree){
				drawNode(g2d, tree.getRoot(), this.getWidth()/2, 1, ((AVLTree) tree).getRoot().getHeight());
			}else{
				drawNode(g2d, tree.getRoot(), this.getWidth()/2, 1, 8);
			}
		}
	}
	
	public void drawNode(Graphics2D g, BSNode node, int center, int level, int maxDepth){		
		int radius = Math.min(this.getWidth(), this.getHeight()) / 32;
		int levelMargin = this.getHeight()/(maxDepth+1) - 16;
		
		int x = center;
		int y = level*levelMargin;
		
		String strValue = Integer.toString(node.getValue());
		int strValueW = g.getFontMetrics().stringWidth(strValue);
		int strValueH = g.getFontMetrics().getHeight();
		
		g.setColor(Color.WHITE);
		g.fillOval(x - radius, y - radius, radius*2, radius*2);
		
		if(node instanceof RBNode){
			if(((RBNode) node).getColor() == RBColor.BLACK){
				g.setColor(Color.BLACK);
			}else{
				g.setColor(Color.RED);
			}
			g.drawOval(x - radius, y - radius, radius*2, radius*2);
		}
		
		g.setColor(Color.decode("#3F3F49"));
		g.drawString(strValue, x - strValueW/2, y + strValueH/4);
		
		if(node instanceof AVLNode){
			String strHeight = Integer.toString(((AVLNode) node).getHeight());
			int strHeightW = g.getFontMetrics().stringWidth(strHeight);
			int strHeightH = g.getFontMetrics().getHeight();
			
			g.setColor(Color.WHITE);
			g.drawString(strHeight, x - strHeightW/2, y - radius - strHeightH/4);
		}
		
		if(node.getLeft() != null){
			int newLevel = level + 1;
			int newCenter = center - this.getWidth()/(int)Math.pow(2, newLevel);
			double angle = Math.PI/2 - Math.atan2(newLevel*levelMargin - y, newCenter - x);

			g.setColor(Color.WHITE);	
			g.drawLine(
					x + (int)(Math.sin(angle)*radius),
					y + (int)(Math.cos(angle)*radius),
					newCenter + (int)(Math.sin(angle - Math.PI)*radius),
					newLevel*levelMargin + (int)(Math.cos(angle - Math.PI)*radius)
			);
			
			drawNode(g, node.getLeft(), newCenter, newLevel, maxDepth);
		}
		
		if(node.getRight() != null){
			int newLevel = level + 1;
			int newCenter = center + this.getWidth()/(int)Math.pow(2, newLevel);
			double angle = Math.PI/2 - Math.atan2(newLevel*levelMargin - y, newCenter - x);

			g.setColor(Color.WHITE);	
			g.drawLine(
					x + (int)(Math.sin(angle)*radius),
					y + (int)(Math.cos(angle)*radius),
					newCenter + (int)(Math.sin(angle - Math.PI)*radius),
					newLevel*levelMargin + (int)(Math.cos(angle - Math.PI)*radius)
			);
			
			drawNode(g, node.getRight(), newCenter, newLevel, maxDepth);
		}
	}	
}
