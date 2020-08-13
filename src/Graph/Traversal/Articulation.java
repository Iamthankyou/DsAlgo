package Graph.Traversal;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import Utils.IntegerPair;

public class Articulation {
	private static final int UNVISITED = 0;
	private ArrayList<ArrayList<IntegerPair>> AL;
	private ArrayList<Integer> dfsNum,dfsLow,dfsParent,articulationVertex;
	private int dfsNumberCounter,dfsRoot,rootChildren;
	private int V;
	
	public Articulation() {
		buildGraph();
		
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
	
	public void articulationPointAndBridge(int u) {
		dfsLow.set(u, dfsNumberCounter);
		dfsNum.set(u, dfsNumberCounter++);
		
		for (IntegerPair v_w:AL.get(u)) {
			if (dfsNum.get(v_w.first()) == UNVISITED) {
				dfsParent.set(v_w.first(),u);
				
				if (u == dfsRoot) {
					rootChildren++;
				}
				
				articulationPointAndBridge(v_w.first());
				
				if (dfsLow.get(v_w.first())>=dfsNum.get(u)) {
					articulationVertex.set(u, 1);
				}
				
				if (dfsLow.get(v_w.first())>dfsNum.get(u)) {
					System.out.println("Edge: "+u+","+v_w.first());
				}
				
				dfsLow.set(u, Math.min(dfsLow.get(u),dfsLow.get(v_w.first())));
			}
			else if (v_w.first() != dfsParent.get(u)) {
				dfsLow.set(u, Math.min(dfsLow.get(u),dfsNum.get(v_w.first())));
			}
		}
	}
	
	public void articulation() {
		dfsNum = new ArrayList<>(Collections.nCopies(V, UNVISITED));
		dfsLow = new ArrayList<>(Collections.nCopies(V, -1));
		dfsParent = new ArrayList<>(Collections.nCopies(V, 0));
		articulationVertex = new ArrayList<>(Collections.nCopies(V, 0));
		
		dfsNumberCounter = 0;
		
		System.out.println("Bridge: ");
		for (int u=0; u<V; u++) {
			if (dfsNum.get(u) == UNVISITED){
				dfsRoot = u;
				rootChildren = 0;
				articulationPointAndBridge(u);
				articulationVertex.set(dfsRoot, (rootChildren>1)?1:0);
			}
		}
		
		System.out.println("Arcutilation Points: ");
		for (int u=0; u<V; u++) {
			if (articulationVertex.get(u)==1) {
				System.out.println("Vertex "+ u);
			}
		}
	}
	
	public static void main(String args[]) {
		Articulation art = new Articulation();
		
		art.articulation();
		
	}
}
