package Graph.Traversal;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import Utils.IntegerPair;

public class BFS {
	private ArrayList<ArrayList<IntegerPair>> AL;
	private int V;
	private int E;
	private ArrayList<Integer> dist;
	
	public void buildGraph() {
		try(Scanner sc = new Scanner(new File("/home/duy/DsAlgo/src/Graph/Traversal/input2.txt"))){
			V = sc.nextInt();
			E = sc.nextInt();
			
			AL = new ArrayList<>();
			for (int u=0; u<V; u++) {
				AL.add(new ArrayList<>());
			}
			
			for (int i=0; i<E; i++){
				int a= sc.nextInt();
				int b= sc.nextInt();
				AL.get(a).add(new IntegerPair(b,0));
				AL.get(b).add(new IntegerPair(a,0));
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void bfs(int s) {
		dist = new ArrayList<>(Collections.nCopies(V,1<<31));
		dist.set(s, 0); // distance s-s is 0
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(s);
		
		int layer = -1;
		
		while (!queue.isEmpty()) {
			int u = queue.poll();

			if (dist.get(u) != layer) {
				System.out.println();
				System.out.print("Layer: "+dist.get(u)+" ");
			}
			layer = dist.get(u);
			System.out.print("Visit "+u+", ");
			
			for (IntegerPair v_w:AL.get(u)) {
				int v = v_w.first();

				if (dist.get(v) == 1<<31) {
					dist.set(v,dist.get(u)+1);
					queue.offer(v);
				}
			}
		}
	}
	
	public static void main(String args[]) {
		BFS bfs = new BFS();
		
		bfs.buildGraph();
		bfs.bfs(0);
	}
}
