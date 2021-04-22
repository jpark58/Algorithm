import java.util.*;
import java.io.*;

class Point{
	int x, y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class BOJ16234 {
	static int N, L, R;
	static int[][] map;
	static int[][] union;
	static boolean[][] visited;
	static int countTotal;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		union = new int[N][N];
		visited = new boolean[N][N];
		countTotal = 0;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean flag = false;
		while(!flag) {
			Queue<Point> q = new LinkedList<>();
			
			ArrayList<Point> list = new ArrayList<>();

			int sum = 0, count = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					Point point = new Point(i, j);
					q.add(point);
					while(!q.isEmpty()) {
						Point curr = q.poll();
						
						if(!visited[curr.x][curr.y]) {
							visited[curr.x][curr.y] = true;
							sum += map[curr.x][curr.y];
							list.add(new Point(curr.x, curr.y));
							count++;
							
							for(int k = 0; k < 4; k++) {
								int nx = curr.x + dx[k];
								int ny = curr.y + dy[k];
								
								if(nx>=0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
									int diff = Math.abs(map[nx][ny] - map[curr.x][curr.y]);
									if(diff >= L && diff <= R) {
										Point temp = new Point(nx,ny);
										q.add(temp);
									}
								}
							}
						}
					}
					
					if(list.size() > 1) {
						int val = sum/count;
						for(Point p: list) {
							map[p.x][p.y] = val;
						}
						flag = true;
					}
					
					list.clear();
					sum = 0;
					count = 0;
				}
			}
			if(flag) {
				countTotal++;
				visited = new boolean[N][N];
				flag = false;
			}else {
				break;
			}
		}
		
		System.out.println(countTotal);
	}
	
}
