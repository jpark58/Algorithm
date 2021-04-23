import java.io.*;
import java.util.*;

public class BOJ16236{
	static int N;
	static int[][] map;
	static int sharkX, sharkY, sharkSize, sharkEat;
	static int totalFish;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int time;
	static int[][] dist;
	static int minX, minY, minDist;
	
	static class Point{
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		sharkEat = 0;
		totalFish = 0;
		time = 0;
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]== 9) {
					sharkX = i;
					sharkY = j;
					sharkSize = 2;
					map[i][j] = 0;
				}
				if(map[i][j]>=1 && map[i][j]<=6) totalFish++;
			}
		}
		
		while(true) {
			dist = new int[N+1][N+1];
			minX = Integer.MAX_VALUE;
			minY = Integer.MAX_VALUE;
			minDist = Integer.MAX_VALUE;
			
			bfs(sharkX, sharkY);
			
			if(minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
				map[minX][minY] = 0;
				sharkX = minX;
				sharkY = minY;
				sharkEat++;
				time += minDist;
				
				if(sharkSize == sharkEat) {
					sharkSize++;
					sharkEat = 0;
				}
			}else {
				break;
			}
		}
		
		System.out.println(time);
		
	}
	
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(x, y));
		
		while(!q.isEmpty()) {
			Point curr = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				
				if(nx >= 1 && nx <= N && ny>=1 && ny <= N && map[nx][ny] <= sharkSize) {
					if(dist[nx][ny]==0) {
						dist[nx][ny] = dist[curr.x][curr.y] + 1;
						
						if(map[nx][ny] < sharkSize && map[nx][ny] > 0) {
							if(minDist > dist[nx][ny]) {
								minDist = dist[nx][ny];
								minX = nx;
								minY = ny;
							}
							else if(minDist == dist[nx][ny]) {
								if(minX == nx) {
									if(minY > ny) {
										minY = ny;
										minX = nx;
									}
								}
								else if(minX > nx) {
									minX = nx;
									minY = ny;
								}
							}
						}
						
						q.offer(new Point(nx,ny));
					}
				}
			}
		}
	}
}