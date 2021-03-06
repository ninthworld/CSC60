package org.ninthworld.binarytrees;

public class AVLTree extends BSTree {
	
	private AVLNode root;
	
	public AVLTree(){
		super();
		this.root = null;
	}
	
	public AVLNode getRoot(){
		return root;
	}
	
	public void insert(int value){
		root = AVLTree.insert(value, root);
	}
	
	public void remove(int value){
		root = AVLTree.remove(value, root);
	}
	
	public AVLNode find(int value){
		return AVLTree.find(value, root);
	}
	
	public AVLNode findMin(){
		return AVLTree.findMin(root);
	}
	
	public AVLNode findMax(){
		return AVLTree.findMax(root);
	}
	
	private static AVLNode insert(int value, AVLNode node){
		if(node == null){
			node = new AVLNode(value);
		}else{
			if(value == node.getValue()){
				return node;
			}else if(value < node.getValue()){
				node.setLeft( insert(value, node.getLeft()) );
			}else{
				node.setRight( insert(value, node.getRight()) );
			}
		}
		
		node.setHeight( updateHeight(node) );
		
		int balance = balance(node);
		if(balance > 1){ 
			// Left heavy
			if(value < node.getLeft().getValue()){ 
				// Left-left heavy
				node = leftRotate(node);
			}else{ 
				// Left-right heavy
				node.setLeft( rightRotate(node.getLeft()) );
				node = leftRotate(node);
			}
		}else if(balance < -1){ 
			// Right heavy
			if(value < node.getRight().getValue()){ 
				// Right-left heavy
				node.setRight( leftRotate(node.getRight()) );
				node = rightRotate(node);				
			}else{ 
				// Right-right heavy
				node = rightRotate(node);
			}
		}
		
		return node;
	}
	
	private static AVLNode remove(int value, AVLNode node){
		if(node == null){
			return node;
		}else{
			if(value == node.getValue()){
				if(node.getLeft() == null && node.getRight() == null){
					return null;
				}else if(node.getLeft() == null){
					return node.getRight();
				}else if(node.getRight() == null){
					return node.getLeft();
				}else{
					AVLNode min = findMin(node.getRight());
					node.setValue(min.getValue());
					node.setRight( remove(min.getValue(), node.getRight()) );
				}
			}else if(value > node.getValue()){
				node.setRight( remove(value, node.getRight()) );
			}else{
				node.setLeft( remove(value, node.getLeft()) );
			}
		}
		
		node.setHeight( updateHeight(node) );

		int balance = balance(node);
		if(balance > 1){ 
			node = leftRotate(node);
		}else if(balance < -1){ 
			node = rightRotate(node);
		}
		
		return node;
	}
	
	private static AVLNode leftRotate(AVLNode node){
		AVLNode a = node;
		AVLNode b = a.getLeft();
		AVLNode bR = b.getRight();
		
		a.setLeft( bR );
		b.setRight( a );
		
		a.setHeight( updateHeight(a) );
		b.setHeight( updateHeight(b) );
		
		return b;
	}
	
	private static AVLNode rightRotate(AVLNode node){
		AVLNode a = node;
		AVLNode b = a.getRight();
		AVLNode bL = b.getLeft();
		
		a.setRight( bL );
		b.setLeft( a );
		
		a.setHeight( updateHeight(a) );
		b.setHeight( updateHeight(b) );
		
		return b;
	}
	
	private static int balance(AVLNode node){
		if(node == null){
			return 0;
		}else{
			return height(node.getLeft()) - height(node.getRight());
		}
	}
	
	private static int updateHeight(AVLNode node){
		if(node == null){
			return -1;
		}else{
			return (int) Math.max(height(node.getLeft()), height(node.getRight())) + 1;
		}
	}
	
	private static int height(AVLNode node){
		if(node == null){
			return -1;
		}else{
			return node.getHeight();
		}
	}

	private static AVLNode find(int value, AVLNode node){
		if(node == null){
			return node;
		}else{
			if(value == node.getValue()){
				return node;
			}else if(value > node.getValue()){
				return find(value, node.getRight());
			}else{
				return find(value, node.getLeft());
			}
		}
	}
	
	private static AVLNode findMin(AVLNode node){
		if(node == null){
			return node;
		}else{
			if(node.getLeft() == null){
				return node;
			}else{
				return findMin(node.getLeft());
			}
		}
	}
	
	private static AVLNode findMax(AVLNode node){
		if(node == null){
			return node;
		}else{
			if(node.getRight() == null){
				return node;
			}else{
				return findMax(node.getRight());
			}
		}
	}
}
