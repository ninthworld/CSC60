package org.ninthworld.binarytrees;

public class BSTree {
	
	private BSNode root;
	
	public BSTree(){
		this.root = null;
	}
	
	public BSNode getRoot(){
		return root;
	}
	
	public void insert(int value){
		root = BSTree.insert(value, root);
	}
	
	public void remove(int value){
		root = BSTree.remove(value, root);
	}
	
	public BSNode find(int value){
		return BSTree.find(value, root);
	}
	
	public BSNode findMin(){
		return BSTree.findMin(root);
	}
	
	public BSNode findMax(){
		return BSTree.findMax(root);
	}
	
	private static BSNode insert(int value, BSNode node){
		if(node == null){
			node = new BSNode(value);
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
	
	private static BSNode remove(int value, BSNode node){
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
					BSNode max = findMax(node.getLeft());
					node.setValue(max.getValue());
					node.setLeft( remove(max.getValue(), node.getLeft()) );
				}
			}else if(value > node.getValue()){
				node.setRight( remove(value, node.getRight()) );
			}else{
				node.setLeft( remove(value, node.getLeft()) );
			}
		}
		
		return node;
	}
	
	private static BSNode find(int value, BSNode node){
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
	
	private static BSNode findMin(BSNode node){
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
	
	private static BSNode findMax(BSNode node){
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
