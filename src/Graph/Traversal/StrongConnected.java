package Graph.Traversal;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

import Utils.IntegerPair;

public class StrongConnected {
	private static final int UNVISITED = 0;
	private ArrayList<ArrayList<IntegerPair>> AL;
	private ArrayList<Integer> dfsNum,dfsLow,visited;
	private Stack<Integer> stk;
	private int V,E,dfsNumberCounter,numSCC;
	
	public StrongConnected() {
		V=0;
		E=0;
		numSCC=0;
		dfsNumberCounter=0;
		
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
	
	// Find strong connected with tarjan algorithm
	public void tarjanSCC() {
		dfsNum = new ArrayList<>(Collections.nCopies(V,0));
		dfsLow = new ArrayList<>(Collections.nCopies(V,0));
		visited = new ArrayList<>(Collections.nCopies(V,0));
		stk = new Stack<>();
		dfsNumberCounter = 0;
		numSCC = 0;
		
		for (int i=0; i<V; i++) {
			if (dfsNum.get(i) == UNVISITED) {
				tarjanSCC(i);
			}
		}
		
	}
	
	public void tarjanSCC(int u) {
		dfsNum.set(u,dfsNumberCounter);
		dfsLow.set(u, dfsNumberCounter++); // dfsLow <=dfsNum
		visited.set(u,1);
		
		stk.push(u);
		
		for (IntegerPair v_w:AL.get(u)) {
			if (dfsNum.get(v_w.first()) == UNVISITED) {
				tarjanSCC(v_w.first());
			}
			
			if (visited.get(v_w.first())!=0) {
				dfsLow.set(u, Math.min(dfsLow.get(u),dfsLow.get(v_w.first())));
			}
		}
		
		if (dfsLow.get(u) == dfsNum.get(u)){ // this is root start of an SCC
			System.out.print("SCC "+ (++numSCC)+": ");

			while (true) {
				int v = stk.pop();
				System.out.print(v+" ");
				visited.set(v,0);
				
				if (v==u) {
					break;
				}
			}
			
			System.out.println();
		}
	}
	
	
	public static void main(String args[]) {
		StrongConnected scc = new StrongConnected();
		
		scc.tarjanSCC();
	}
}
