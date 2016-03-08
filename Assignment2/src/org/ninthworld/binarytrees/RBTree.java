package org.ninthworld.binarytrees;

import org.ninthworld.binarytrees.RBNode.RBColor;

public class RBTree extends BSTree {
	
	private RBNode root;
	
	public RBTree() {
		super();
		this.root = null;
	}

	public RBNode getRoot() {
		return root;
	}

	public void setRoot(RBNode root) {
		this.root = root;
	}
	
	public void insert(int value){
		RBTree.insert(value, root);
		root.setColor(RBColor.BLACK);
	}

	
	private static RBNode insert(int value, RBNode node){
		if(node == null){
			node = new RBNode(value);
		}else{
			if(value == node.getValue()){
				return node;
			}else if(value > node.getValue()){
				node.setRight( insert(value, node.getRight()) );
			}else{
				node.setLeft( insert(value, node.getLeft()) );
			}
		}

		
		
		return node;
	}
	
	private static RBColor color(RBNode node){
		if(node == null){
			return RBColor.BLACK;
		}else{
			return node.getColor();
		}
	}
}