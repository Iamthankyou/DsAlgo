package Graph.MST;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import Utils.IntegerTriple;
import Utils.UnionFind;

public class Kruskal {
	private ArrayList<IntegerTriple> EL;
	private int V,E;
	
	public Kruskal() {
		buildGraph();
	}
	
	public void buildGraph() {
		EL = new ArrayList<>();
		
		try (Scanner sc = new Scanner(new File("/home/duy/DsAlgo/src/Graph/MST/input.txt"))){
			V = sc.nextInt();
			E = sc.nextInt();
			
			for (int i=0; i<E; i++) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				int w = sc.nextInt();
				
				EL.add(new IntegerTriple(w,u,v));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void kruskal() {
		UnionFind uf = new UnionFind(V);
		int mst = 0;
		int count = 0;

		Collections.sort(EL, new Comparator<IntegerTriple>() {

			@Override
			public int compare(IntegerTriple arg0, IntegerTriple arg1) {
				return arg0.first()-arg1.first();
			}
			
		});
		
		for(IntegerTriple i:EL) {
			System.out.println(i.third()+" "+i.second()+" "+i.first());
		}
		
		for (IntegerTriple i:EL) {
			if (!uf.isConnected(i.second(), i.third())) {
				mst+=i.first();
				System.out.println(i.second()+" "+i.third());
				uf.union(i.second(), i.third());
				count++;
			}
			
			if (count == V-1) {
				break;
			}
		}
		
		System.out.println("MST: "+mst);
	}
	
	public static void main(String args[]) {
		Kruskal kruskal = new Kruskal();
		kruskal.kruskal();
	}
}
