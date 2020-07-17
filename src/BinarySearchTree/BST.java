package BinarySearchTree;

public class BST<Key extends Comparable<Key>,Value> {
	private Node root;
	
	
	public int size() {
		return size(root);
	}
	
	private int size(Node root) {
		if (root == null) {
			return 0;
		}
		
		return root.getN();
	}
	
//	public void put(Key key, Value val) {
//		
//	}
	
	public Value get(Key key) {
		return get(root,key);
	}
	
	public Value get(Node root, Key key) {
		if (root==null) {
			return null;
		}
		
		int cmp = key.compareTo((Key)root.getKey());
		
		if (cmp<0) {
			return get(root.getLeft(),key);
		}
		else if (cmp>0) {
			return get(root.getRight(),key);
		}
		else {
			return (Value)root.getValue();
		}
	}
	
	
	public void put(Key key, Value val) {
		root = put(root,key,val);
	}
	
	public Node put(Node x, Key key, Value val) {
		if (x == null) {
			return new Node(key,val);
		}
		
		int cmp = key.compareTo((Key)x.getKey());
		
		if (cmp<0) {
			x.setLeft(put(x.getLeft(),key,val));
		}
		else if (cmp>0) {
			x.setRight(put(x.getRight(),key,val));
		}
		else {
			x.setValue(val);
		}
		
		x.setN(1+size(x.getLeft())+size(x.getRight()));
		
		return x;
	}
	
	public String dfs() {
		return dfs(root,new StringBuilder(),"","");
	}
	
	public Node min(Node x) {
		if (x.getLeft() == null) {
			return x;
		}
		
		return min(x.getLeft());
	}
	
	public String dfs(Node root, StringBuilder res, String padding, String pointer) {
		if (root == null) {
			return res.toString();
		}

		res.append(padding);
		res.append(pointer);
		res.append(root.getValue());
		res.append("\n");
		
		StringBuilder paddingBuilder = new StringBuilder(padding);
		paddingBuilder.append("|  ");
		String paddingForBoth = paddingBuilder.toString();
		String pointerToRight = "└──";
		String pointerToLeft = root.getRight()!=null?"├──" : "└──";

		dfs(root.getLeft(),res,paddingForBoth,pointerToLeft);
		dfs(root.getRight(),res,paddingForBoth,pointerToRight);
		
		return res.toString();
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	public Node deleteMin(Node root) {
		if (root.getLeft() == null) {
			return root.getRight();
		}
		
		root.setLeft(deleteMin(root.getLeft()));
		root.setN(1+size(root.getLeft())+size(root.getRight()));
		
		return root;
	}
	
	public void delete(Key key) {
		root = delete(root,key);
	}
	
	public Node delete(Node root, Key key) {
		if (root == null) {
			return null;
		}
		
		int cmp = key.compareTo((Key)root.getKey());
		if (cmp<0) {
			root.setLeft(delete(root.getLeft(),key));
		}
		else if (cmp>0) {
			root.setRight(delete(root.getRight(),key));
		}
		else {
			if (root.getLeft() == null) {
				return root.getRight();
			}
			
			if (root.getRight() == null) {
				return root.getLeft();
			}
			
			Node t = root;
			root = min(t.getRight());
			root.setRight(deleteMin(t.getRight()));
			root.setLeft(t.getLeft());
		}
		
		root.setN(1+size(root.getLeft())+size(root.getRight()));
		return root;
	}

	public static void main(String args[]) {
		BST tree = new BST();
		
		tree.put("C","C");
		tree.put("E","E");
		tree.put("H","H");
		tree.put("L", "L");
		tree.put("AB", "AB");
		tree.put("AB", "AB");
		tree.put("A","??");
		
		System.out.println(tree.dfs());
		
		tree.delete("A");
		tree.delete("C");
		
		System.out.println(tree.dfs());

	}
}
