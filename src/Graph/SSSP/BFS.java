package Graph.SSSP;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import Utils.IntegerPair;

// SSSP Graph unweight with BFS
public class BFS {
	private ArrayList<Integer> p;
	private ArrayList<ArrayList<IntegerPair>> AL;
	private ArrayList<Integer> dist;
	private int V;
	private int E;
	
	public BFS() {
		build();
	}
	
	public void build() {
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
	
	public void printPath(int u) {
		if (p.get(u)==5) {
			System.out.print(" "+u);
			return;
		}

		printPath(p.get(u));
		System.out.print(" "+u);
	}
	
	public void bfs(int s) {
		Queue<Integer> q = new LinkedList<>();
		p = new ArrayList<>(Collections.nCopies(V, -1));
		dist = new ArrayList<>(Collections.nCopies(V, 1<<31));
		
		p.set(s,0);
		q.offer(s);
		
		while (!q.isEmpty()) {
			int u = q.poll();
			
			for (IntegerPair i:AL.get(u)) {
				if (dist.get(i.first()) == 1<<31) {
					dist.set(i.first(), dist.get(u)+1);
					p.set(i.first(), u);
					q.offer(i.first());
				}
			}
		}
	}
	
	public static void main(String args[]) {
		BFS bfs = new BFS();
		bfs.bfs(5);
		
		bfs.printPath(7);
	}
}
