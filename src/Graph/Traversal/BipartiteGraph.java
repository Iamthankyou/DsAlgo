package Graph.Traversal;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import Utils.IntegerPair;

public class BipartiteGraph {
	private ArrayList<ArrayList<IntegerPair>> AL;
	private boolean flag;
	private int V,E;
	
	public BipartiteGraph() {
		flag = true;
		
		build();
	}
	
	public void build() {
		try(Scanner sc = new Scanner(new File("/home/duy/DsAlgo/src/Graph/Traversal/input2.txt"))){
			V = sc.nextInt();
			E = sc.nextInt();
			AL = new ArrayList<>();
			
			for (int i=0; i<V; i++) {
				AL.add(new ArrayList<>()); 
			}
			
			for (int i=0; i<E; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				
				AL.get(a).add(new IntegerPair(b,0));
				AL.get(b).add(new IntegerPair(a,0));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isBipartite(int s) {
		Queue<Integer> queue = new LinkedList<>();
		ArrayList<Integer> color = new ArrayList<>(Collections.nCopies(V,1<<31));
		queue.add(s);
		
		while (!queue.isEmpty()) {
			int u = queue.poll();
			
			for (IntegerPair v_w:AL.get(u)) {
				int v = v_w.first();
				
				if (color.get(v) == 1<<31) {
					color.set(v, 1-color.get(u));
					queue.offer(v);
				}
				else if (color.get(v) == color.get(u)) {
					flag = false;
					break;
				}
			}
		}
		
		return flag;
	}
	
	public static void main(String args[]) {
		BipartiteGraph bipartiteGraph = new BipartiteGraph();
		
		System.out.println(bipartiteGraph.isBipartite(0));
	}
}
