import java.io.*;
import java.util.*;

public class BOJ14502 {
	static int N, M;
	static int[][] map;
	static ArrayList<Point> zeros;
	static ArrayList<Point> virus;
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int safeMax;
	
	static class Point{
		int x,y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		zeros = new ArrayList<>();
		virus = new ArrayList<>();
		safeMax = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j= 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					zeros.add(new Point(i, j));
				}
				else if(map[i][j]==2) {
					virus.add(new Point(i,j));
				}
			}
		}
		
		comb(0, 3, new ArrayList<Integer>());
		System.out.println(safeMax);
	}
	
	public static void comb(int start, int depth, ArrayList<Integer> list) {
		if(depth == 0) {
			// Copy map
			int[][] copyMap = new int[N][M];
			for(int i = 0; i < N; i++) {
				for(int j= 0; j < M; j++) {
					copyMap[i][j] = map[i][j];
				}
			}
			
			// Build war
			for(int idx: list) {
				Point p = zeros.get(idx);
				copyMap[p.x][p.y] = 1;
			}
			
			// Spread virus
			spread(copyMap);
			int temp = countArea(copyMap);
			
			safeMax = Math.max(safeMax, temp);
			
			return;
		}
		
		for(int i = start; i < zeros.size(); i++) {
			if(!list.contains(i)) {
				list.add(i);
				comb(i+1, depth-1, list);
				list.remove(list.size()-1);
			}
		}
	}
	
	public static int countArea(int[][] copyMap) {
		int count = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(copyMap[i][j] == 0) count++;
			}
		}
		
		return count;
	}
	
	public static void spread(int[][] copyMap) {
		Queue<Point> q = new LinkedList<>();
		
		for(Point p: virus) {
			q.offer(p);
		}
		
		while(!q.isEmpty()) {
			Point curr = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				
				if(nx>=0 && nx < N && ny>=0 && ny<M && copyMap[nx][ny] != 1 && copyMap[nx][ny] != 2) {
					copyMap[nx][ny] = 2;
					q.offer(new Point(nx, ny));
				}
			}
		}
	}
}
