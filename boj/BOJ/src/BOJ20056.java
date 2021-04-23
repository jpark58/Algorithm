import java.io.*;
import java.util.*;



public class BOJ20056 {
	static int N,M,K;
	
	
	static class Ball{
		int r, c, m, d, s;
		public Ball(int r, int c, int m, int d, int s) {
			this.r = r;
			this.c = c;
			this.m = m; 
			this.d = d;
			this.s = s;
		}
	}
	
	static class Cell{
		ArrayList<Ball> balls;
		
		public Cell() {
			balls = new ArrayList<>();
		}
	}
	
	static Cell[][] map;
	static Cell[][] copyMap;
	static ArrayList<Ball> ballList;
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		
		map = new Cell[N][N];
		ballList = new ArrayList<>();
	
		
		for(int i = 0; i <N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new Cell();
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Ball ball = new Ball(r,c,m,d,s);
			map[r][c].balls.add(ball);
			ballList.add(ball);
		}
		
		work();
		
		int sum = 0;
		for(Ball ball: ballList) {
			sum += ball.m;
		}
		
		System.out.println(sum);
	}
	
	public static void work() {
		
		while(K > 0) {
			copyMap = new Cell[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					copyMap[i][j] = new Cell();
				}
			}
			
			
			Queue<Ball> q = new LinkedList<>();
			for(Ball b: ballList) {
				q.offer(b);
			}
			
			ballList.clear();
			
			while(!q.isEmpty()) {
				Ball currBall = q.poll();
				moveBall(currBall);
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j< N; j++) {
					if(copyMap[i][j].balls.size() > 1) {
						int sumMass = 0;
						int sumSpeed = 0;
						boolean isSame = true;
						int temp = copyMap[i][j].balls.get(0).d;
						
						for(Ball ball: copyMap[i][j].balls) {
							sumMass += ball.m;
							sumSpeed += ball.s;
							if(temp%2 != ball.d % 2) {
								isSame = false;
							}
						}
						
						int mass = sumMass/5;
						int speed = sumSpeed/copyMap[i][j].balls.size();
						
						copyMap[i][j].balls.clear();
						
						int idx;
						if(isSame) {
							idx = 0;
						}else {
							idx = 1;
						}
						
						for(; idx < 8; idx+=2) {
							copyMap[i][j].balls.add(new Ball(i,j,mass, idx, speed));
						}
						
						if(mass > 0) {
							for(Ball b: copyMap[i][j].balls) {
								ballList.add(b);
							}
						}
					}
				}
			}
			K--;
		}
	}
	
	public static void moveBall(Ball currBall) {
		int r = currBall.r;
		int c = currBall.c;
		int d = currBall.d;
		int m = currBall.m;
		int s = currBall.s;
		
		int ny = (c + N + dy[d] * (s % N)) % N;
		int nx = (r + N + dx[d] * (s % N)) % N;
		
		copyMap[r][c].balls.add(new Ball(nx,ny,m,d,s));
		
		
	}
}
