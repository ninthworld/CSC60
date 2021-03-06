package org.ninthworld.binarytrees;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;

import org.ninthworld.binarytrees.BSTest.BSTestArrayType;

public class Main {
	
	public static void main(String[] args){
		
		int maxNum = 1000000;
		int[] ascending = BSTest.generateArray(BSTestArrayType.INCREASING, maxNum);
		int[] descending = BSTest.generateArray(BSTestArrayType.DECREASING, maxNum);
		int[] randomized = BSTest.generateArray(BSTestArrayType.RANDOMIZED, maxNum);
				
		double avlIncTime = BSTest.insertTest(new AVLTree(), ascending);
		double avlDecTime = BSTest.insertTest(new AVLTree(), descending);
		double avlRndTime = BSTest.insertTest(new AVLTree(), randomized);
		
		System.out.printf("AVL Tree Insert Times (for %d values) - \nAscending: %6.2fms, Descending: %6.2fms, Random: %6.2fms\n\n", maxNum, avlIncTime, avlDecTime, avlRndTime);
		
		double rbIncTime = BSTest.insertTest(new RBTree(), ascending);
		double rbDecTime = BSTest.insertTest(new RBTree(), descending);
		double rbRndTime = BSTest.insertTest(new RBTree(), randomized);

		System.out.printf("Red-Black Tree Insert Times (for %d values) - \nAscending: %6.2fms, Descending: %6.2fms, Random: %6.2fms\n\n", maxNum, rbIncTime, rbDecTime, rbRndTime);
		
		// Optional: Display graph of data points
		displayGraph("AVL Tree", new AVLTree());
		displayGraph("Red-Black Tree", new RBTree());
	}
	
	public static void displayGraph(String title, BSTree type){
		int maxValue = 1000000;
		int[] incArray = BSTest.generateArray(BSTestArrayType.INCREASING, maxValue);
		int[] decArray = BSTest.generateArray(BSTestArrayType.DECREASING, maxValue);
		int[] rndArray = BSTest.generateArray(BSTestArrayType.RANDOMIZED, maxValue);

		int width = 720, height = 640;
		JFrame frame = new JFrame(title);
		Rectangle rect = new Rectangle(10, 10, 80, 80);
		DisplayGraph graph = new DisplayGraph(rect, maxValue, 1000);
		
		frame.setBackground(Color.decode("#3F3F49"));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.add(graph);
		
		new Thread(new Runnable(){
			public void run(){
				int intervals = 40, trials = 3;
				for(int i=0; i<intervals; i++){
					for(int j=0; j<trials; j++){
						double x = i*(maxValue/intervals);
						double yInc, yDec, yRnd;
						
						if(type instanceof AVLTree){
							yInc = BSTest.insertTest(new AVLTree(), incArray, (int) x);
							yDec = BSTest.insertTest(new AVLTree(), decArray, (int) x);
							yRnd = BSTest.insertTest(new AVLTree(), rndArray, (int) x);
						}else if(type instanceof RBTree){
							yInc = BSTest.insertTest(new RBTree(), incArray, (int) x);
							yDec = BSTest.insertTest(new RBTree(), decArray, (int) x);
							yRnd = BSTest.insertTest(new RBTree(), rndArray, (int) x);							
						}else{
							yInc = BSTest.insertTest(new BSTree(), incArray, (int) x);
							yDec = BSTest.insertTest(new BSTree(), decArray, (int) x);
							yRnd = BSTest.insertTest(new BSTree(), rndArray, (int) x);							
						}
						
						graph.addData(new DisplayGraphData(x, yInc, Color.BLUE));
						graph.addData(new DisplayGraphData(x, yDec, Color.GREEN));
						graph.addData(new DisplayGraphData(x, yRnd, Color.RED));
						graph.repaint();
						
						try {
							Thread.sleep(1000/60);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}
}
