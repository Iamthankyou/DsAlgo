package Graph.Traversal;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

import Utils.IntegerPair;

public class TopologicalSort {
	private static final int VISITED = 1;
	private static final int UNVISITED = 0;
	private ArrayList<ArrayList<IntegerPair>> AL;
	private ArrayList<Integer> dfsNum;
	private ArrayList<Integer> ts;
	private int V;
	
	public TopologicalSort() {
		build();
	}
	
	public void build() {
		try(Scanner sc = new Scanner(new File("/home/duy/DsAlgo/src/Graph/Traversal/input.txt"))){
			V = sc.nextInt();
			AL = new ArrayList<>();

			for (int i=0; i<V; i++) {
				int k = sc.nextInt();
				
				while (k-->0) {
					AL.add(new ArrayList<>());
					int v = sc.nextInt();
					int w = sc.nextInt();
					
					AL.get(i).add(new IntegerPair(v,w));
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dfs(int u) {
		dfsNum.set(u, VISITED);
		
		for (IntegerPair v_w:AL.get(u)) {
			if (dfsNum.get(v_w.first()) == UNVISITED) {
				dfs(v_w.first());
			}
		}

		ts.add(u);
	}
	
	public void topoSort() {
		dfsNum = new ArrayList<>(Collections.nCopies(V, UNVISITED));
		ts = new ArrayList<>();
		
		for (int i=0; i<V; i++) {
			if (dfsNum.get(i) == UNVISITED) {
				dfs(i);
			}
		}
		
		Collections.reverse(ts);
		System.out.println(ts);
	}
	
	public static void main(String args[]) {
		TopologicalSort topo = new TopologicalSort();
		topo.topoSort();
	}
}
