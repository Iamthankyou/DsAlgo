package Graph.SSSP;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import Utils.IntegerPair;

public class Dijkstra {
	private ArrayList<ArrayList<IntegerPair>> AL;
	private int V,E,s;
	private ArrayList<Integer> dist;
	private ArrayList<Integer> p;
	
	public Dijkstra() {
		buildGraph();
	}
	
	public void buildGraph() {
		try (Scanner sc = new Scanner(new File("/home/duy/DsAlgo/src/Graph/SSSP/input.txt"))){
			V = sc.nextInt();
			E = sc.nextInt();
			s = sc.nextInt();
			AL = new ArrayList<>();

			for (int i=0; i<V; i++) {
				AL.add(new ArrayList<>());
			}
			
			for (int i=0; i<=E; i++) {
			    int u = sc.nextInt();
				int v = sc.nextInt();
				int w = sc.nextInt();
				
				AL.get(u).add(new IntegerPair(v,w));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dijkstra() {
		PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>(new Comparator<IntegerPair>() {
			@Override
			public int compare(IntegerPair a, IntegerPair b) {
				return a.first()-b.first();
			}
		});
		
		p = new ArrayList<>(Collections.nCopies(V, -1));
		dist = new ArrayList<>(Collections.nCopies(V, 1000000));
		
		pq.offer(new IntegerPair(0,s));
		dist.set(s, 0);
		
		while (!pq.isEmpty()) {
			IntegerPair front =  pq.poll();
			
			if (front.first()>dist.get(front.second())) {
				continue;
			}

			for (IntegerPair i:AL.get(front.second())) {
				int v = i.first();
				int w = i.second();
				
				if (dist.get(front.second())+w>=dist.get(v)) {
					continue;
				}
				
				dist.set(v,dist.get(front.second())+w);
				pq.offer(new IntegerPair(dist.get(v),v));
				p.set(v, front.second());
			}
		}
		
		for (int i:dist) {
//			System.out.println(i);
		}
	}
	
	public void printPath(int u) {
		if (u==s) {
			System.out.println(" "+u);
			return;
		}
		
		printPath(p.get(u));
		System.out.println(" "+u);
	}
	
	public static void main(String args[]) {
		Dijkstra dijkstra = new Dijkstra();
		
		dijkstra.dijkstra();
		
		dijkstra.printPath(4);
	}	
}
