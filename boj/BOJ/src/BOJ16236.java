import java.io.*;
import java.util.*;

public class BOJ16236 {
	static int N;
	static int[][] map;
	static int feedCount;
	static int sharkX;
	static int sharkY;
	static int sharkEat;
	static int sharkSize;
	static int minX;
	static int minY;
	static int minDistance;
	static int[][] distance;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int time;
	
	private static class Point {
		private int x;
		private int y;
		
		private Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		time = 0;
		sharkEat = 0;
		sharkSize = 2;
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					map[i][j] = 0;
					sharkX = i;
					sharkY = i;
				}
			}
		}

		while(true) {
			distance = new int[N][N];
			minX = Integer.MAX_VALUE;
			minY = Integer.MAX_VALUE;
			minDistance = Integer.MAX_VALUE;
			
			bfs(sharkX, sharkY);
			
			if(minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
				sharkEat++;
				map[minX][minY] = 0;
				if(sharkEat == sharkSize) {
					sharkSize++;
					sharkEat = 0;
				}
				sharkX = minX;
				sharkY = minY;
				time += distance[minX][minY];
			}else {
				break;
			}
			
		}
		
		System.out.println(time);
		
	}
	
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(x,y));
		
		while(!q.isEmpty()) {
			Point curr = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				
				if(nx>=0 && nx<N && ny>=0 && ny < N) {
					if(map[nx][ny] <= sharkSize && distance[nx][ny] == 0) {
						distance[nx][ny] = distance[curr.x][curr.y]+1;
						
						if(map[nx][ny] != 0 && map[nx][ny] < sharkSize) {
							if(minDistance > distance[nx][ny]) {
								minDistance = distance[nx][ny];
								minX = nx;
								minY = ny;
							}
							else if(minDistance == distance[nx][ny]) {
								if(minX == nx) {
									if(minY > ny) {
										minX = nx;
										minY = ny;
									}
								}else if(minX > nx) {
									minX = nx;
									minY = ny;
								}
							}
						}
						q.offer(new Point(nx, ny));
					}
				}
			}
		}
	}
}
