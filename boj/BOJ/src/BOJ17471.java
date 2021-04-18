import java.io.*;
import java.util.*;

public class BOJ17471 {
	static int N;
	static int[] population;
	static HashMap<Integer, ArrayList<Integer>> map;
	static int[] team;
	static int min;
	static boolean flag;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		min = Integer.MAX_VALUE;
		map = new HashMap<>();
		population = new int[N+1];
		team = new int[N+1];
		
		flag = false;
		
		StringTokenizer  st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i<= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			ArrayList<Integer> temp = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int j = 0; j < size; j++) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			
			map.put(i, temp);
		}
		
		comb(1);
		
		if(flag) {
			System.out.println(min);
		}else {
			System.out.println(-1);
		}
	}
	
	public static void comb(int start) {
		if(start == N+1) {
			
			if(isPossible()) {
				flag = true;
				int first = 0, second = 0;
				
				for(int i = 1; i<=N; i++) {
					if(team[i] == 1) {
						first += population[i];
					}
					else{
						second += population[i];
					}
				}
				
				min = Math.min(min, Math.abs(first-second));
			}
		
			return;
		}
		
		team[start] = 1;
		comb(start+1);
		
		team[start] = 2;
		comb(start+1);
	}
	
	public static boolean isPossible() {
		
		boolean[] visited = new boolean[N+1];
		
		int areaA = -1;
		for(int i = 1; i <= N; i++) {
			if(team[i]==1) {
				areaA = i;
				break;
			}
		}
		
		int areaB = -1;
		for(int i = 1; i <= N; i++) {
			if(team[i]==2) {
				areaB = i;
				break;
			}
		}
		
		if(areaA == -1 || areaB == -1) return false;
		
		Queue<Integer> q = new LinkedList<>();
		
		q.add(areaA);
		visited[areaA] = true;
		while(!q.isEmpty()) {
			int now = q.poll();
			ArrayList<Integer> temp = map.get(now);
			
			for(int i = 0; i < temp.size(); i++) {
				if(visited[temp.get(i)]) continue;
				if(team[temp.get(i)]== 2) continue;
				
				visited[temp.get(i)] = true;
				q.add(temp.get(i));
			}
		}
		
		q.add(areaB);
		visited[areaB] = true;
		while(!q.isEmpty()) {
			int now = q.poll();
			ArrayList<Integer> temp = map.get(now);
			
			for(int i = 0; i < temp.size(); i++) {
				if(visited[temp.get(i)]) continue;
				if(team[temp.get(i)]== 1) continue;
				
				visited[temp.get(i)] = true;
				q.add(temp.get(i));
			}
		}
		for(int i = 1; i<=N; i++) {
			if(!visited[i]) return false;
		}
		
		return true;
	}
}
