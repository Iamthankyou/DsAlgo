package RedBlackTree;

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
	
	public int size(Node x) {
		if (x==null) {
			return 0;
		}
		
		return x.getN();
	}
	
	public void dfs() {
		dfs(root);
	}
	
	public void dfs(Node root) {
		if (root == null) {
			return;
		}
		
		System.out.print(root.getVal());
		
		dfs(root.getLeft());
		dfs(root.getRight());
		
		System.out.println();
		return;
	}
	
	public static void main(String args[]) {
		RedBlackTree<String,String> tree = new RedBlackTree<>();
		
		tree.put("S","S");
		tree.put("E","E");
		tree.put("A","A");
		tree.put("R","R");
		tree.put("C", "C");
		
		tree.dfs();
	}
}
