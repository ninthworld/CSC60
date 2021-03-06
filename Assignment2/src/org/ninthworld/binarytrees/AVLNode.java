package org.ninthworld.binarytrees;

public class AVLNode extends BSNode {

	private int value, height;
	private AVLNode left, right;
	
	public AVLNode(int value) {
		super(value);
		this.value = value;
		this.height = 0;
		this.left = null;
		this.right = null;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public AVLNode getLeft() {
		return left;
	}

	public void setLeft(AVLNode left) {
		this.left = left;
	}

	public AVLNode getRight() {
		return right;
	}

	public void setRight(AVLNode right) {
		this.right = right;
	}

}
