package Graph.Traversal;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Utils.IntegerPair;

public class DFS {
	private static final int UNVISITED = 0;
	private static final int VISITED = 1;
	
	private ArrayList<ArrayList<IntegerPair>> AL;
	private ArrayList<Integer> dfsNum;
	
	private int V;
	
	public void dfs(int u) {
		System.out.print(u+" ");
		dfsNum.set(u, VISITED);
		
		for (IntegerPair v:AL.get(u)) {
			if (dfsNum.get(v.first()) == UNVISITED) {
				dfs(v.first());
			}
		}
//		System.out.println();
	}
	
	public void buildGraph() {
		try (Scanner sc = new Scanner(new File("/home/duy/DsAlgo/src/Graph/Traversal/input.txt"))){
			V = sc.nextInt();
			AL = new ArrayList<>();
			
			for (int u=0; u<V; u++) {
				AL.add(new ArrayList<>());
				int k = sc.nextInt();
				
				while(k-->0) {
					int v=sc.nextInt(),w=sc.nextInt();
					AL.get(u).add(new IntegerPair(v,w));
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dfs() {
		dfsNum = new ArrayList<>(Collections.nCopies(V, UNVISITED));
		int numCC = 0;
		
		for (int u=0; u<V; u++) {
			if (dfsNum.get(u) == UNVISITED) {
				System.out.print("CC "+ ++numCC+": ");
				dfs(u);
				System.out.println();
			}
		}
		
		System.out.println("Connected Component: "+ numCC);
	}
	
	public static void main(String args[]) {
		DFS dfs = new DFS();
		
		dfs.buildGraph();
		dfs.dfs();
	}
	
}
