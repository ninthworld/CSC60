package org.ninthworld.binarytrees;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;

public class Main {
	
	private JFrame frame;
	private DisplayBSTree bsPanel;
	
	public Main(){
		AVLTree tree = new AVLTree();
		//BSTree tree = new BSTree();
		
		frame = new JFrame("AVL Tree");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(640, 480);
		frame.setBackground(Color.decode("#3F3F49"));
		
		bsPanel = new DisplayBSTree(tree);
		frame.add(bsPanel);
		
		new Thread(new Runnable(){
			public void run(){
				for(int i=0; i<16; i++){
					tree.insert(i);
					frame.repaint();
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tree.remove(9);
				tree.remove(8);
				frame.repaint();
			}
		}).start();
	}
	
	public static void main(String[] args){
		new Main();
		
		/*Random rand = new Random(12345L);
		int maxInts = (int)Math.pow(2, 12);
		int[] incInts = new int[maxInts],
			  decInts = new int[maxInts],
			  rndInts = new int[maxInts];
		
		for(int i=0; i<maxInts; i++){
			incInts[i] = i;
			decInts[i] = maxInts - (i+1);
			rndInts[i] = rand.nextInt(maxInts);
		}

		System.out.printf("(AVL Tree)\n\n");
		printAVLTreeAverage(incInts, 4, "Increasing");		
		//printAVLTreeAverage(decInts, 4, "Decreasing");
		//printAVLTreeAverage(rndInts, 4, "Random    ");*/
	}
	
	public static void printAVLTreeAverage(int[] values, int trials, String msg){
		double[] tests = new double[trials];
		System.out.printf("%s - ", msg);
		for(int i=0; i<tests.length; i++){
			tests[i] = testAVLTreeMilli(values);
			System.out.printf("%6.1fms, ", tests[i]);
		}
		
		double avg = 0;
		for(double d : tests){
			avg += d;
		}
		avg /= tests.length;
		System.out.printf("Avg: %6.1fms\n\n", avg);
	}
	
	public static double testAVLTreeMilli(int[] values){
		long start, end;
		AVLTree avlTree = new AVLTree();
		
		start = System.nanoTime();
		for(int i : values){
			avlTree.insert(i);
		}
		end = System.nanoTime();
		
		System.out.printf(" %d ", avlTree.getRoot().getHeight());
		
		return (end - start) / 1000000.0;
	}
}
