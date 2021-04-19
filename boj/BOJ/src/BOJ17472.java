import java.io.*;
import java.util.*;

public class BOJ17472 {
	static int N;
	static int M;
	static int[][] map;
	static int label;
	static Queue<Node> q;
	static ArrayList<Node> bridge;
	static int[] parent;
	static int[][] dir = {{-1, 0}, {1,0}, {0, -1}, {0, 1}};
	static boolean[][] visited;
	
	static class Node implements Comparable<Node>{
		int r,c,d;
		
		Node(int r, int c, int d){
			this.r = r;
			this.c = c;
			this.d = d;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.d - o.d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		label = 0;
		visited = new boolean[N][M];
		q = new LinkedList<>();
		bridge = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) -1;
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j]==0 && !visited[i][j]) {
					label++;
					visited[i][j] = true;
					map[i][j] = label;
					q.offer(new Node(i,j,0));
					labeling();
				}
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] > 0) {
					q.offer(new Node(r,c,0));
					calDistance(map[r][c]);
				}
			}
		}
		
		parent = new int[label+1];
		for(int i = 1; i <= label; i++) parent[i] = i;
		
		kruskal();
	}
	
	private static void kruskal() {
		Collections.sort(bridge);
		
		int cnt = 0;
		int dis = 0;
		
		for(Node node : bridge) {
			if(find(node.r) != find(node.c)) {
				union(node.r, node.c);
				dis += node.d;
				cnt++;
			}
		}
		
		if(cnt != label-1) System.out.println(-1);
		else System.out.println(dis);
	}
	
	private static int find(int a) {
		if(parent[a] == a) {
			return a;
		}
		
		return parent[a] = find(parent[a]);
	}
	
	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		parent[rootB] = rootA;
	}
	
	public static void calDistance(int start) {
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = curr.r + dir[i][0];
				int nc = curr.c + dir[i][1];
				
				int distance = 0;
				boolean flag = false;
				
				while(true) {
					if(nr >= N || nr < 0 || nc >= M || nc < 0 || map[nr][nc] == start) break;
					if(map[nr][nc]!=-1) {
						flag = true;
						break;
					}
					distance++;
					nr+=dir[i][0];
					nc+=dir[i][1];
				}
				
				if(flag) {
					if(distance < 2) continue;
					bridge.add(new Node(start, map[nr][nc], distance));
				}
			}
		}
		
		q.clear();
	}
	
	public static void labeling() {
		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = curr.r + dir[i][0];
				int nc = curr.c + dir[i][1];
				
				if(nr >= N || nr < 0 || nc >= M || nc < 0 || visited[nr][nc]) continue;
				if(map[nr][nc]==0) {
					visited[nr][nc] = true;
					map[nr][nc] = label;
					q.offer(new Node(nr,nc,0));
				}
			}
		}
		
		q.clear();
	}
}
