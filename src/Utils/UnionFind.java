package Utils;

import java.util.ArrayList;
import java.util.Collections;

// Weight Quick Union
public class UnionFind {
	private ArrayList<Integer> id; // parent of link
	private ArrayList<Integer> size; // size of commpenent for root
	private int n;
	
	public UnionFind(int n) {
		this.n = n;
		id = new ArrayList<>(n);
		size = new ArrayList<>(Collections.nCopies(n, 1));
		for (int i=0; i<n ;i++) {
			id.add(i); // default
		}
	}
	
	public int count() {
		return n;
	}
	
	public int find(int n) {
		while (n!=id.get(n)) {
			n = id.get(n);
		}
		
		return n;
	}
	
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}
	
	public void union(int p, int q) {
		int i = find(p);
		int j = find(q);
		
		if (i==j) {
			return;
		}
		
		if (size.get(i)<size.get(j)) {
			id.set(i,j);
			size.set(j, size.get(j)+size.get(i));
		}
		else {
			id.set(j,i);
			size.set(i,size.get(i)+size.get(j));
		}
		
		n--;
	}
	
	public static void main(String args[]) {
		UnionFind uf = new UnionFind(10);
		
		uf.union(4, 3);
		uf.union(3, 8);
		uf.union(6, 5);
		uf.union(9, 4);
		uf.union(2, 1);
		uf.union(8, 9);
		uf.union(5,0);
		uf.union(7, 2);
		uf.union(6, 1);
		uf.union(1, 0);
		uf.union(6, 7);
		System.out.println(uf.find(3));
		
		System.out.println(uf.isConnected(4, 8));
	}
}
