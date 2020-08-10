package Graph.Traversal;

import java.io.BufferedReader;
import java.io.FileReader;

public class FloodFill {
	private char[][] mat;
	private int m,n;
	private int dr[] = {0,0,1,1,1,-1,-1,-1};
	private int dc[] = {1,-1,1,-1,0,1,-1,0};
	private int res = 0; // sum change color
	
	public FloodFill(){
		build();
	}
	
	public void build() {
		try(BufferedReader br = new BufferedReader(new FileReader("/home/duy/DsAlgo/src/Graph/Traversal/input3.txt"))){
			String[] m_n = br.readLine().split(" ");
			m = Integer.parseInt(m_n[0]);
			n = Integer.parseInt(m_n[1]);
			mat = new char[m][n];

			for (int i=0; i<m; i++) {
				String line = br.readLine();
//				System.out.println(line);
				for (int j=0; j<n; j++) {
					mat[i][j] = line.charAt(j);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public void print() {
		for (char[] arr:mat) {
			for (char i:arr) {
				System.out.print(i);
			}
			System.out.println();
		}	
	}
	
	public int floodFill(int r, int c, char c1, char c2) {
		if (r<0 || c<0 || r>= mat.length || c>=mat[0].length) {
			return 0;
		}
		
		if (mat[r][c] !=c1) {
			return 0;
		}
		
		int ans = 1;
		mat[r][c] = c2;
		
		for (int i=0; i<8; i++) {
			ans+=floodFill(r+dr[i],c+dc[i],c1,c2);
		}
		
		return ans;
	}
	
	public void floodFill(char c1, char c2) {
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				floodFill(i,j,c1,c2);
			}
		}
	}
	
	public static void main(String args[]) {
		FloodFill floodFill = new FloodFill();

		floodFill.floodFill('x','y');
		floodFill.print();
	}
}
