package org.ninthworld.redone;

public class AVLTree {
	
	AVLNode root;
	
	public AVLTree(){
		this.root = null;
	}
	
	public void insert(int value){
		root = AVLTree.insert(value, root);
	}

	public String toString(){
		return root.toString();
	}
	
	private static AVLNode insert(int value, AVLNode node){
		if(node == null){
			node = new AVLNode(value);
		}else{
			if(value == node.value){
				return node;
			}else if(value < node.value){
				node.left = insert(value, node.left);
			}else{
				node.right = insert(value, node.right);
			}
		}
		
		node.height = updateHeight(node);
		
		int balance = balance(node);
		if(balance > 1){ // Left heavy
			if(value < node.left.value){ // Left-left heavy
				node = leftRotate(node);
			}else{ // Left-right heavy
				node.left = rightRotate(node.left);
				node = leftRotate(node);
			}
		}else if(balance < -1){ // Right heavy
			if(value < node.right.value){ // Right-left heavy
				node.right = leftRotate(node.right);
				node = rightRotate(node);				
			}else{ // Right-right heavy
				node = rightRotate(node);
			}
		}
		
		return node;
	}
	
	private static AVLNode leftRotate(AVLNode node){
		AVLNode a = node;
		AVLNode b = a.left;
		AVLNode bR = b.right;
		
		a.left = bR;
		b.right = a;
		
		a.height = updateHeight(a);
		b.height = updateHeight(b);
		
		return b;
	}
	
	private static AVLNode rightRotate(AVLNode node){
		AVLNode a = node;
		AVLNode b = a.right;
		AVLNode bL = b.left;
		
		a.right = bL;
		b.left = a;
		
		a.height = updateHeight(a);
		b.height = updateHeight(b);
		
		return b;
	}
	
	private static int balance(AVLNode node){
		if(node == null){
			return 0;
		}else{
			return height(node.left) - height(node.right);
		}
	}
	
	private static int updateHeight(AVLNode node){
		if(node == null){
			return -1;
		}else{
			return (int) Math.max(height(node.left), height(node.right)) + 1;
		}
	}
	
	private static int height(AVLNode node){
		if(node == null){
			return -1;
		}else{
			return node.height;
		}
	}
	/*private static int height(AVLNode node){
		if(node == null){
			return -1;
		}else{
			return (int) Math.max(height(node.left), height(node.right)) + 1;
		}
	}*/
}

class AVLNode {
	int height, value;
	AVLNode left, right;
	
	public AVLNode(int value){
		this.height = 0;
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public String toString(){
		return "AVLNode["+value+", left:"+(left==null?"":left.toString())+", right:"+(right==null?"":right.toString())+"]";
	}
}