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
		try (Scanner sc = new Scanner(new File("/home/duy/DsAlgo/src/Graph/Traveral/input.txt"))){
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
		dfsNumberCounter = 0;
		numSCC = 0;
		
		
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
			System.out.print("SCC: "+ (++numSCC)+": ");

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
		ArrayList<IntegerPair> time = new ArrayList<>();
		
		time.add(new IntegerPair(7,23));
		time.add(new IntegerPair(8,5));
		
		time.add(new IntegerPair(8,48));
		time.add(new IntegerPair(9,5));
		
		time.add(new IntegerPair(9,26));
		time.add(new IntegerPair(10,48));
		
		time.add(new IntegerPair(13,2));
		time.add(new IntegerPair(17,31));
		
		time.add(new IntegerPair(20,3));
		time.add(new IntegerPair(23,48));
		
		int res = 0;
		
		for (int i=0; i<time.size(); i+=2) {
			IntegerPair a = time.get(i);
			IntegerPair b = time.get(i+1);
			
			res += (b.first() - a.first()-1)*60;
			
			if (b.second()-a.second()<0) {
				res+=(b.second()+60-a.second());
			}
			else {
				res+=b.second()-a.second()+1;
			}
			
			System.out.println("From "+a.first()+":"+a.second()+" to "+b.first()+":"+b.second()+": "+res/60+":"+res%60);
			
		}
		
		String s = new String();
		
		System.out.println("Time for us: "+ res/60+":"+res%60);
	}
}
