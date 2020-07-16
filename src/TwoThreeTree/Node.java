package TwoThreeTree;

public class Node {
	private Node left;
	private Node right;
	private Node middle;
	private int valLeft;
	private int valRight;
	
	public Node() {
		this.left = null;
		this.right = null;
		this.middle = null;
		this.valLeft = Integer.MIN_VALUE;
		this.valRight = Integer.MAX_VALUE;
	}
	
	public Node(Node left, Node right) {
		this.left = left;
		this.right = right;
	}
	
	public Node(Node left, Node middle, Node right) {
		this.left = left;
		this.middle = middle;
		this.right = right;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public Node getRight() {
		return right;
	}

	public Node getMiddle() {
		return middle;
	}
	
	public int getValLeft() {
		return valLeft;
	}
	
	public int getValRight() {
		return valRight;
	}
	
	public void setValLeft(int valLeft) {
		this.valLeft = valLeft;
	}
	
	public void setValRight(int valRight) {
		this.valRight = valRight;
	}

	public void setLeft(Node left) {
		this.left = left;
	}
	
	public void setMiddle(Node middle) {
		this.middle = middle;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	
	public boolean isLeaf() {
		return left==null && middle==null && right==null;
	}
	
	public boolean is2Node() {
		return valRight == Integer.MAX_VALUE;
	}
	
	public boolean is3Node() {
		return valRight != Integer.MAX_VALUE;
	}
	
	
}