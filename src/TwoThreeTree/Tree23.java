package TwoThreeTree;

import java.util.ArrayList;
import java.util.List;

public class Tree23 {
	private Node root;

	public Tree23() {
		root = new Node();
	}

	public void insert(int val) {
		if (root == null || root.getValLeft() == Integer.MIN_VALUE) {
			root = new Node();
			root.setValLeft(val);

			System.out.println("===> Insert by ROOT");
			return;
		}

		System.out.println("===> Starting Insert");

		Node curr = root;

		insert(curr, val);
	}

	public List<Node> search(Node curr, Node parent, int val) {
		if (curr == null) {
			System.out.println("===> Curr is null");
			return null;
		}

		if (curr.getValLeft() == val || curr.getValRight() == val) {
			List<Node> res = new ArrayList<>();
			res.add(curr);
			res.add(parent);
			return res;
		}

		if (curr.isLeaf()) {
			System.out.println(val);
			List<Node> res = new ArrayList<>();
			res.add(curr);
			res.add(parent);
			return res;
		}

		if (curr.is2Node()) {
//			System.out.println(val+" ??");
			if (val < curr.getValLeft()) {
				if (curr.getLeft() != null) {
					return search(curr.getLeft(), curr, val);
				} else {
					List<Node> res = new ArrayList<>();
					res.add(curr);
					res.add(parent);
					return res;
				}
			} else {
				if (curr.getRight() != null) {
					return search(curr.getRight(), curr, val);
				} else {
					List<Node> res = new ArrayList<>();
					res.add(curr);
					res.add(parent);
					return res;
				}
			}
		}
		else if (curr.is3Node()) {
			if (val < curr.getValLeft()) {
				if (curr.getLeft() != null) {
					return search(curr.getLeft(), curr, val);
				} else {
					List<Node> res = new ArrayList<>();
					res.add(curr);
					res.add(parent);
					return res;
				}
			} else if (val > curr.getValRight()) {
				if (curr.getRight() != null && curr.getRight().getValLeft()!=Integer.MIN_VALUE) {
					return search(curr.getRight(), curr, val);
				} else {
					List<Node> res = new ArrayList<>();
					res.add(curr);
					res.add(parent);
					return res;
				}
			} else {
				if (curr.getMiddle() != null) {
					return search(curr.getMiddle(), curr, val);
				} else {
					List<Node> res = new ArrayList<>();
					res.add(curr);
					res.add(parent);
					return res;
				}
			}
		}

		return null;
	}

	public void insert(Node curr, int val) {
		List<Node> res = search(curr, curr, val);
		Node node = res.get(0);
		Node parent = res.get(1);
		
		if (res.get(0) != res.get(1)) {
			System.out.println(res.get(0).getValLeft());
			System.out.println(res.get(1).getValLeft());
		}

		if (res.get(0).is2Node()) {
			insertInto2Node(node,val);
		}
		else if (node.is3Node()) {
			if (node == parent) {
				insertIntoSingle3Node(node,val);
			}
		}
	}
	
	public void insertIntoSingle3Node(Node curr, int val) {
		if (val<curr.getValLeft()) {
			curr.getLeft().setValLeft(val);
			curr.getLeft().setValRight(curr.getValRight());
			
			curr.getLeft().setRight(curr.getRight());
//			curr.getLeft().setLeft();
		}
	}

	public void insertInto2Node(Node curr, int val) {
		if (val < curr.getValLeft()) {
			curr.setValRight(curr.getValLeft());
			curr.setValLeft(val);

			curr.setLeft(new Node());
			curr.setRight(curr.getRight());
			curr.setMiddle(curr.getLeft());
		} else {
			curr.setValRight(val);

			curr.setMiddle(curr.getRight());
			curr.setRight(new Node());
		}
	}

//	public void insert(Node curr, int val) {
//		Node node = search(curr,curr,val);
//
//		if (node == null) {
//			System.out.println("===> Why node null ??");
//		}
//		
//		if (node.getValLeft() == val || node.getValRight() == val) {
//			System.out.println("===> Value exists!");
//			return;
//		}
//		
//		if (node.is2Node()) {
//			if (val<node.getValLeft()) {
//				node.setValRight(node.getValLeft());
//				node.setValLeft(val);
//				
//				node.setLeft(new Node());
//				node.setRight(node.getRight());
//				node.setMiddle(node.getLeft());
//			}
//			else {
//				node.setValRight(val);
//				
//				node.setMiddle(node.getRight());
//				node.setRight(new Node());
//			}
//		}
//		
//		return;
//	}

	public void dfs() {
		dfs(root);
	}

	public void dfs(Node curr) {
		if (curr == null || (curr.getLeft() == null && curr.getRight() == null)) {
			return;
		}

		if (curr.isLeaf()) {
			if (curr.is2Node()) {
				System.out.println(curr.getValLeft());
			} else {
				System.out.println(curr.getValLeft() + " " + curr.getValRight());
			}

			return;
		}

		if (curr.is2Node()) {
			System.out.println(curr.getValLeft());
			dfs(curr.getLeft());
			dfs(curr.getRight());
		} else if (curr.is3Node()) {
			System.out.println(curr.getValLeft() + " " + curr.getValRight());
			dfs(curr.getLeft());
			dfs(curr.getMiddle());
			dfs(curr.getRight());
		}

		return;
	}

	public static void main(String args[]) {
		Tree23 tree = new Tree23();

		tree.insert(1);
		tree.insert(3);
		tree.insert(4);
		
		tree.dfs();
	}
}
