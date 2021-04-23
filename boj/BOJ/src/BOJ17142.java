import java.util.*;
import java.io.*;



public class BOJ17142 {
	static class Point{
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N,M;
	static int[][] map;
	static int[][] copyMap;
	static ArrayList<Point> virus;
	static int time;
	static int emptyCount;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		copyMap = new int[N][N];
		virus = new ArrayList<>();
		time = Integer.MAX_VALUE;
		emptyCount = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] =  temp;
				if(temp == 2) {
					virus.add(new Point(i,j));
				}
				if(temp == 0) {
					emptyCount++;
				}
			}
		}
		
		if(emptyCount != 0) {
			comb(0, 0, new ArrayList<Integer>());
		}else {
			time = 0;
		}
		
		if(time == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(time);
		}
	}
	
	public static void comb(int start, int size, ArrayList<Integer> list) {
		if(size == M) {
			bfs(list, emptyCount);
			System.out.println(list.toString());
			return;
		}
		
		for(int i = start; i < virus.size(); i++) {
			if(!list.contains(i)) {
				list.add(i);
				comb(i+1, size+1, list);
				list.remove(list.size()-1);
			}
		}
	}
	
	public static void bfs(ArrayList<Integer> list, int count) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		for(int i = 0; i < virus.size(); i++) {
			Point p = virus.get(i);
			if(!list.contains(i)){
				copyMap[p.x][p.y] = -2;
			}
		}
		
		Queue<Point> q = new LinkedList<>();
		for(int e: list) {
			Point p = virus.get(e);
			q.offer(p);
		}
		
		int curr = 0;
		
		while(!q.isEmpty()) {
			if(curr >= time) break;
			
			int len = q.size();
			for(int i = 0; i < len; i++) {
				Point currPosition = q.poll();
				for(int j = 0; j < 4; j++) {
					int nx = currPosition.x + dx[j];
					int ny = currPosition.y + dy[j];
					
					if(nx >= 0 && nx < N && ny >= 0 && ny < N) {
						if(copyMap[nx][ny] != 1 && copyMap[nx][ny] != 2) {
							if(copyMap[nx][ny] == 0) {
								count--;
							}
							copyMap[nx][ny] = 2;
							q.offer(new Point(nx,ny));
							
						}
					}
				}
			}

			curr++;
			
			if(count == 0) {
				time = curr;
				return;
			}
			
		}
	}
}
