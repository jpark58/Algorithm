import java.util.*;
import java.io.*;

class House{
	int x, y;
	
	House(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Chicken{
	int x, y;
	
	Chicken(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ15686 {
	static int N;
	static int M;
	static ArrayList<Chicken> chicken;
	static ArrayList<House> house;
	static int[][] map;
	static int min;
	static boolean[] open;
	


	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		min = Integer.MAX_VALUE;
		map = new int[N][N];
		chicken = new ArrayList<Chicken>();
		house = new ArrayList<House>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]== 2) {
					chicken.add(new Chicken(i, j));
				}
				else if(map[i][j]==1) {
					house.add(new House(i, j));
				}
			}
		}
		open = new boolean[chicken.size()];
		
		dfs(0, 0);
		System.out.println(min);
	}
	
	public static void dfs(int idx, int depth) {
		if(depth == M) {
			
			int curr = 0;
			
			for(int i = 0; i < house.size(); i++) {
				int distance = Integer.MAX_VALUE;
				
				for(int j= 0; j < chicken.size(); j++) {
					if(open[j]) {
						distance = Math.min(distance, Math.abs(house.get(i).x - chicken.get(j).x) + Math.abs(house.get(i).y - chicken.get(j).y));
					}
				}
				
				curr += distance;
			}
			
			min = Math.min(curr, min);
			
			return;
		}
		
		for(int i = idx; i < chicken.size(); i++) {
			open[i] = true;
			dfs(i+1, depth+1);
			open[i] = false;
		}
	}
}
