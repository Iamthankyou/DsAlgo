package Graph.MST;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import Utils.IntegerPair;

public class Prim {
	private ArrayList<ArrayList<IntegerPair>> AL;
	private ArrayList<Boolean> taken;
	private PriorityQueue<IntegerPair> pq;
	private int V,E;
	
	public Prim() {
		build();
	}
	
	public void build() {
		try(Scanner sc = new Scanner(new File("/home/duy/DsAlgo/src/Graph/MST/input.txt"))){
			V = sc.nextInt();
			E = sc.nextInt();
			
			AL = new ArrayList<>();
			
			for (int i=0; i<V; i++) {
				AL.add(new ArrayList<>());
			}
			
			for (int i=0; i<E; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				int c = sc.nextInt();
				
				AL.get(a).add(new IntegerPair(b,c));
				AL.get(b).add(new IntegerPair(a,c));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// sub-problem for Prim
	public void process(int u) {
		taken.set(u, true);
		
		for (IntegerPair i:AL.get(u)) {
			if (!taken.get(i.first())) {
				pq.offer(new IntegerPair(i.second(),i.first()));
			}
		}
	}
	
	public void prim() {
		taken = new ArrayList<>(Collections.nCopies(V, false));
		pq = new PriorityQueue<>(new Comparator<IntegerPair>() {

			@Override
			public int compare(IntegerPair a, IntegerPair b) {
				return a.first()-b.first();
			}
		});
		
		int mst = 0;
		int numTaken = 0;
		
		process(0);
		
		while (!pq.isEmpty()) {
			IntegerPair front = pq.poll();
			
			if (taken.get(front.second())) {
				continue;
			}
			
			mst+=front.first();
			process(front.second());

			if (++numTaken == V-1) {
				break;
			}
		}
		
		System.out.println("MST: "+mst);
		
	}
	
	public static void main(String args[]) {
		Prim prim = new Prim();
		prim.prim();
	}
}
