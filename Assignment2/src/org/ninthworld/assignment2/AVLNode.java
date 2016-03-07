package org.ninthworld.assignment2;

public class AVLNode {

	private int value, height;
	private AVLNode left, right;
	
	public AVLNode(int value){
		this.value = value;
		this.height = 1;
		this.left = this.right = null;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setHeight(int height){
		this.height = height;
	}
	
	public AVLNode getLeft(){
		return left;
	}
	
	public void setLeft(AVLNode left){
		this.left = left;
	}
	
	public AVLNode getRight(){
		return right;
	}
	
	public void setRight(AVLNode right){
		this.right = right;
	}
	
	public boolean hasLeft(){
		return (getLeft() != null);
	}
	
	public boolean hasRight(){
		return (getRight() != null);
	}
	
	public void updateHeight(){
		int leftHeight, rightHeight;
		leftHeight = (hasLeft() ? getLeft().getHeight() : 0);
		rightHeight = (hasRight() ? getRight().getHeight() : 0);
		
		setHeight(Math.max(leftHeight, rightHeight) + 1);
	}
	
	public int getBalance(){
		int leftHeight, rightHeight;
		leftHeight = (hasLeft() ? getLeft().getHeight() : 0);
		rightHeight = (hasRight() ? getRight().getHeight() : 0);
		
		return leftHeight - rightHeight;
	}
	
	public void RRotate(){
		AVLNode y = clone();
		AVLNode x = y.getLeft();
		AVLNode z = x.getRight();
		
		x.setRight(y);
		y.setLeft(z);
		
		y.updateHeight();
		x.updateHeight();
		
		copy(x);
	}
	
	public void LRotate(){
		AVLNode x = clone();
		AVLNode y = x.getRight();
		AVLNode z = y.getLeft();
		
		y.setLeft(x);
		x.setRight(z);
		
		x.updateHeight();
		y.updateHeight();
		
		copy(y);
	}
	
	public void insert(int value){
		if(value == getValue()){
			return;
		}else if(value < getValue()){
			if(hasLeft()){
				getLeft().insert(value);
			}else{
				setLeft(new AVLNode(value));
			}
		}else{
			if(hasRight()){
				getRight().insert(value);
			}else{
				setRight(new AVLNode(value));
			}
		}
		updateHeight();
		
		int balance = getBalance();
		if(balance > 1 && value < getLeft().getValue()){
			RRotate();
		}
		
		if(balance < -1 && value > getRight().getValue()){
			LRotate();
		}
		
		if(balance > 1 && value > getLeft().getValue()){
			getRight().RRotate();
			LRotate();
		}
		
		if(balance < -1 && value < getRight().getValue()){
			getRight().RRotate();
			LRotate();
		}
	}
	
	public AVLNode clone(){
		AVLNode clone = new AVLNode(this.getValue());
		clone.setHeight(this.getHeight());
		clone.setLeft(this.getLeft());
		clone.setRight(this.getRight());
		return clone;
	}
	
	public void copy(AVLNode copy){
		this.setValue(copy.getValue());
		this.setHeight(copy.getHeight());
		this.setLeft(copy.getLeft());
		this.setRight(copy.getRight());
	}
}