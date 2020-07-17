package BinarySearchTree;

public class Node<Key extends Comparable<Key>,Value> {
	private Node left;
	private Node right;
	
	private Key key;
	private Value val;
	private int n;
	
	public Node() {
		left = null;
		right = null;
		n = 0;
	}
	
	public Node(Key key, Value val) {
		this.key = key;
		this.val = val;
	}
	
	public Key getKey() {
		return key;
	}
	
	public Value getValue() {
		return val;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public Node getRight() {
		return right;
	}
	
	public int getN() {
		return n;
	}
	
	public void setRight(Node right) {
		this.right =right;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	
	public void setN(int n) {
		this.n = n;
	}
	
	public void setKey(Key key) {
		this.key = key;
	}
	
	public void setValue(Value val) {
		this.val = val;
	}
}
