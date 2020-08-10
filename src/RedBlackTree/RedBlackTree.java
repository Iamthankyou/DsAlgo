package RedBlackTree;

import java.util.Random;

public class RedBlackTree<Key extends Comparable<Key>,Value> {
	private Node root;
	
	private boolean isRed(Node x) {
		if (x==null) {
			return false;
		}
		
		return x.getColor() == true;
	}
	
	private Node rotateLeft(Node e) {
		// swap 
		Node s = e.getRight();
		e.setRight(s.getLeft());
		s.setLeft(e);
		
		s.setColor(e.getColor());
		e.setColor(true);
		
		s.setN(e.getN());
		e.setN(1+size(e.getLeft())+size(e.getRight()));
		
		return s;
	}
	
	private Node rotateRight(Node s) {
		Node e = s.getLeft();
		s.setLeft(e.getRight());
		e.setRight(s);
		
		e.setColor(s.getColor());
		s.setColor(true);
		
		e.setN(s.getN());
		s.setN(1+size(e.getRight())+size(e.getLeft()));
		
		return e;
	}
	
	private void flipColor(Node x) {
		x.setColor(true);
		x.getLeft().setColor(false);
		x.getRight().setColor(false);
	}
	
	public void put(Key key, Value val) {
		root = put(root,key,val);
		// False is Black
		root.setColor(false);
	}
	
	public Node put(Node root, Key key, Value val) {
		if (root==null) {
			return new Node(key,val,1,true);
		}
		
		int cmp = key.compareTo((Key) root.getKey());
		
		if (cmp<0) {
			root.setLeft(put(root.getLeft(),key,val));
		}
		else if (cmp>0) {
			root.setRight(put(root.getRight(),key,val));
		}
		else {
			root.setVal(val);
		}
		
		if (isRed(root.getRight()) && !isRed(root.getLeft())) {
			root = rotateLeft(root);
		}
		
		if (isRed(root.getLeft()) && isRed(root.getLeft().getLeft())) {
			root = rotateRight(root);
		}
		
		if (isRed(root.getLeft()) && isRed(root.getRight())) {
			flipColor(root);
		}
		
		root.setN(1+size(root.getLeft())+size(root.getRight()));
		
		return root;
	}
	
	public void deleteMin() {
		if (!isRed(root.getLeft()) && !isRed(root.getRight())) {
			root.setColor(true);
		}
		
		root = deleteMin(root);
		
		if (root!=null) {
			root.setColor(false);
		}
	}
	
	public Node deleteMin(Node x) {
		if (x.getLeft()==null) {
			return null;
		}
		
		if (!isRed(x.getLeft()) && !isRed(x.getLeft().getLeft())) {
			x= moveRedLeft(x);
		}
		
		x.setLeft(deleteMin(x.getLeft()));
		
		return balance(x);
	}
	
	public Node balance(Node x) {
		if (!isRed(x.getLeft()) && isRed(x.getRight())) {
			x = rotateLeft(x);
		}
		
		if (isRed(x.getLeft()) && isRed(x.getLeft().getLeft())) {
			x = rotateRight(x);
		}
		
		if (isRed(x.getLeft()) && isRed(x.getRight())) {
			flipColor(x);
		}
		
		x.setN(1+size(x.getLeft())+size(x.getRight()));
		
		return x;
	}
	
	public Node moveRedLeft(Node x) {
		// x.left && x.left.left is black
		flipColor(x);
		
		if (isRed(x.getRight().getLeft())) {
			System.out.println("moveRedLeft");
			x.setRight(rotateRight(x.getRight()));
			x = rotateLeft(x);
			flipColor(x);
		}
		
		return x;
	}
	
	public Node moveRedRight(Node x) {
		// x.right && x.right.left is black
		flipColor(x);
		
		if (!isRed(x.getLeft().getLeft())) {
			x = rotateRight(x);
		}
		
		return x;
	}
	
	public void delete(Key key) {
		delete(root,key);
	}
	
	public Node delete(Node x, Key key) {
		if (key.compareTo((Key)x.getKey())<0) {
			if (!isRed(x.getLeft()) && !isRed(x.getLeft().getLeft())) {
				x = moveRedLeft(x);
			}
			
			x.setLeft(delete(x.getLeft(),key));
		}
		else {
			if (isRed(x.getLeft())) {
				x = rotateRight(x);
			}
			
			if (key.compareTo((Key)x.getKey()) == 0 && x.getRight() == null) {
				return null;
			}
			
			if (!isRed(x.getRight()) && !isRed(x.getRight().getLeft())) {
				x = moveRedRight(x);
			}
			
			if (key.compareTo((Key)x.getKey()) == 0){
				// I don't understand
				x.setVal(get(x.getRight(),(Key) min(x.getRight()).getKey()));
				x.setKey(min(x.getRight()).getKey());
				x.setRight(deleteMin(x.getRight()));
			}
			else {
				x.setRight(delete(x.getRight(),key));
			}
		}
		
		return balance(x);
	}
	
	public Key min() {
		return (Key) min(root).getKey();
	}
	
	public Node min(Node x) {
		if (x.getLeft() == null) {
			return x;
		}
		
		return min(x.getLeft());
	}
	
	public Value get(Node x, Key key) {
		while (x!=null) {
			int cmp = key.compareTo((Key)x.getKey());
			if (cmp<0) {
				get(x.getLeft(),key);
			}
			else if (cmp>0) {
				get(x.getRight(),key);
			}
			else {
				return (Value) x.getVal();
			}
		}
		
		return null;
	}
	
	public int size(Node x) {
		if (x==null) {
			return 0;
		}
		
		return x.getN();
	}
	
	public String dfs() {
		return dfs(root, new StringBuilder(),"","");
	}
	
	public String dfs(Node root, StringBuilder res, String padding, String pointer) {
		if (root == null) {
			return res.toString();
		}
		
		res.append(padding);
		res.append(pointer);
		res.append(root.getVal());
		res.append("\n");
		
		StringBuilder paddingBuilder = new StringBuilder(padding);
		paddingBuilder.append("|  ");
		
		String paddingForBoth = paddingBuilder.toString();
		String pointerToRight = "└──";
		String pointerToLeft = root.getRight()!=null?"├──" : "└──";
		
		dfs(root.getLeft(),res,paddingForBoth,pointerToLeft);
		dfs(root.getRight(),res,paddingForBoth,pointerToRight);
		
//		System.out.println();
		return res.toString();
	}
	
	public static void main(String args[]) {
		RedBlackTree<Integer,Integer> tree = new RedBlackTree<>();
		Random random = new Random();
		
//		tree.put("A","A");
		tree.put(6,6);
		tree.put(17,17);
		tree.put(14,14);
		
		System.out.println(tree.dfs());

		tree.delete(14);
		System.out.println();
		
		System.out.println(tree.dfs());
	}
	
	
}
