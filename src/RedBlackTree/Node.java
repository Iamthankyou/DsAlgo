package RedBlackTree;

public class Node<Key extends Comparable<Key>,Value> {
	// true is red, false is black
	Key key;
	Value val;
	Node left,right;
	int n; // nodes in this subtree
	boolean color;
	
	public Node(Key key, Value val, int n, boolean color) {
		this.key = key;
		this.val = val;
		this.n = n;
		this.color = color;
	}
	
	public boolean getColor() {
		return color;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Value getVal() {
		return val;
	}

	public void setVal(Value val) {
		this.val = val;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public void setColor(boolean color) {
		this.color = color;
	}
	
}
