package org.ninthworld.redone;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;

public class TreeTesting {
		
	public TreeTesting(){
		Random rand = new Random(12345L);
		
		JFrame frame = new JFrame("AVL Tree");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 540);
		frame.setBackground(Color.decode("#3F3F49"));
		
		HashMap<String, HashMap<Integer, Double>> tests = new HashMap<String, HashMap<Integer, Double>>();
		tests.put("Randomized", new HashMap<Integer, Double>());
		tests.put("Decreasing", new HashMap<Integer, Double>());
		tests.put("Increasing", new HashMap<Integer, Double>());
		
		GraphAVLTree avlGraph = new GraphAVLTree(tests);
		frame.add(avlGraph);

		int samples = 2, max = 2000000, interval = 50000;
		int[] incArray = getIncArray(max);
		int[] decArray = getDecArray(max);
		int[] rndArray = getRndArray(max, rand);
		
		new Thread(new Runnable(){
			public void run(){
				for(String key : tests.keySet()){
					if(key.equals("Increasing")){
						HashMap<Integer, Double> map = tests.get(key);
						for(int i=0; i<samples; i++){
							for(int j=0; j<max; j+=interval){
								map.put(j+i, testAVLTreeMilli(incArray, j));								
								frame.repaint();
							}
						}
					}else if(key.equals("Decreasing")){
						HashMap<Integer, Double> map = tests.get(key);
						for(int i=0; i<samples; i++){
							for(int j=0; j<max; j+=interval){
								map.put(j+i, testAVLTreeMilli(decArray, j));								
								frame.repaint();
							}
						}
					}else if(key.equals("Randomized")){
						HashMap<Integer, Double> map = tests.get(key);
						for(int i=0; i<samples; i++){
							for(int j=0; j<max; j+=interval){
								map.put(j+i, testAVLTreeMilli(rndArray, j));								
								frame.repaint();
							}
						}
					}
				}
			}
		}).start();
	}
	
	public static int[] getIncArray(int count){
		int[] array = new int[count];
		for(int i=0; i<array.length; i++){
			array[i] = i;
		}
		return array;
	}
	
	public static int[] getDecArray(int count){
		int[] array = new int[count];
		for(int i=0; i<array.length; i++){
			array[i] = array.length - (i+1);;
		}
		return array;
	}
	
	public static int[] getRndArray(int count, Random rand){
		int[] array = getIncArray(count);
		for(int i=0; i<array.length; i++){
			int j = rand.nextInt(array.length);
			array[i] = j;
			array[j] = i;
		}
		return array;
	}
	
	public static void main(String[] args){
		new TreeTesting();
		/*
		Random rand = new Random(12345L);
		int maxInts = (int)Math.pow(2, 20);
		int[] incInts = new int[maxInts],
			  decInts = new int[maxInts],
			  rndInts = new int[maxInts];
		
		for(int i=0; i<maxInts; i++){
			incInts[i] = i;
			decInts[i] = maxInts - (i+1);
			rndInts[i] = i;
		}
		for(int i=0; i<rndInts.length; i++){
			int j = rand.nextInt(rndInts.length);
			rndInts[i] = j;
			rndInts[j] = i;
		}

		System.out.printf("(AVL Tree)\n\n");
		
		int trials = 16;
		printAVLTreeAverage(incInts, trials, "Increasing");
		printAVLTreeAverage(decInts, trials, "Decreasing");
		printAVLTreeAverage(rndInts, trials, "  Random  ");
		*/
	}
	
	public static void printAVLTreeAverage(int[] values, int max, int trials, String msg){
		double[] tests = new double[trials];
		System.out.printf("%s - ", msg);
		for(int i=0; i<tests.length; i++){
			tests[i] = testAVLTreeMilli(values, max);
			System.out.printf("%6.1fms, ", tests[i]);
		}
		
		double avg = 0;
		for(double d : tests){
			avg += d;
		}
		avg /= tests.length;
		System.out.printf("Avg: %6.1fms\n\n", avg);
	}
	
	public static double testAVLTreeMilli(int[] values, int max){
		long start, end;
		AVLTree avlTree = new AVLTree();
		
		start = System.nanoTime();
		for(int i=0; i<max; i++){
			avlTree.insert(i);			
		}
		end = System.nanoTime();
		
		return (end - start) / 1000000.0;
	}
}