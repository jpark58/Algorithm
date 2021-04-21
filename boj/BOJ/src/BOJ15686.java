import java.util.*;
import java.io.*;

public class BOJ15686 {
	static int N;
	static int M;
	static ArrayList<Chicken> chicken;
	static ArrayList<House> house;
	static int[][] map;
	static int[][] copyMap;
	static int min;
	
	static class House{
		public int x, y;
		
		public House(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Chicken{
		public int x, y;
		
		public Chicken(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		min = Integer.MAX_VALUE;
		map = new int[N+1][N+1];
		copyMap = new int[N+1][N+1];
		chicken = new ArrayList<Chicken>();
		house = new ArrayList<House>();
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				copyMap[i][j] = map[i][j];
				if(map[i][j]== 2) {
					chicken.add(new Chicken(i, j));
				}
				else if(map[i][j]==1) {
					house.add(new House(i, j));
				}
			}
		}
		
		dfs(0, 0, new ArrayList<Integer>());
		System.out.println(min);
	}
	
	public static void dfs(int idx, int depth, ArrayList<Integer> list) {
		if(depth == M) {
			
			int curr = 0;
			
			for(int i = 0; i < house.size(); i++) {
				House currHouse = house.get(i);
				int distance = Integer.MAX_VALUE;
				
				for(int e: list) {
					Chicken currChicken = chicken.get(e); 
					distance = Math.min(distance, Math.abs(currHouse.x - currChicken.x) + Math.abs(currHouse.y - currChicken.y));
				}
				
				curr += distance;
			}
			
			min = Math.min(curr, min);
			
			return;
		}
		
		for(int i = idx; i < chicken.size(); i++) {
			if(!list.contains(i)) {
				list.add(i);
				dfs(idx+1, depth+1, list);
				list.remove(list.size()-1);
			}
		}
	}
}
