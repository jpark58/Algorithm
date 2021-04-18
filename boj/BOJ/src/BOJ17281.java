import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17281 {
	static int max;
	static int N;
	static int[][] hitters;
	static int[] lineUp;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		max = 0;
		N = Integer.parseInt(br.readLine());
		hitters = new int[N+1][10];
		StringTokenizer st;
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= 9; j++) {
				hitters[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		lineUp = new int[10];
		visited = new boolean[10];
		
		lineUp[4] = 1;
		visited[4] = true;
		
		perm(2);
		
		System.out.println(max);
	}
	
	public static void perm(int height) {
		if(height == 10) {
			play();
			return;
		}
		
		for(int i = 1; i <= 9; i++) {
			if(visited[i]) {
				continue;
			}
			
			visited[i] = true;
			lineUp[i] = height;
			perm(height+1);
			visited[i] = false;
		}
	}
	
	public static void play() {
		int score = 0;
		int startPlayer = 1;
		boolean[] base;
		
		for(int i = 1; i <= N; i++) {
			int outCount = 0;
			base = new boolean[4];
			
			outer: while(true) {
				for(int j = startPlayer; j<= 9; j++) {
					int player = hitters[i][lineUp[j]];
					
					switch(player) {
						case 0:
							outCount++;
							break;
						case 1:
							for(int k = 3; k >= 1; k--) {
								if(base[k]) {
									if(k==3) {
										score++;
										base[k] = false;
										continue;
									}
									
									base[k] = false;
									base[k+1] = true;
								}
							}
							
							base[1] = true;
							break;
						case 2:
							for(int k = 3; k >= 1; k--) {
								if(base[k]) {
									if(k==3 || k==2) {
										score++;
										base[k]=false;
										continue;
									}
									
									base[k] = false;
									base[k+2] = true;
								}
							}
							
							base[2] = true;
							break;
							
						case 3:
							for(int k = 3; k >= 1; k--) {
								if(base[k]) {
									score++;
									base[k] = false;
								}
							}
							base[3] = true;
							break;
							
						case 4:
							for(int k = 3; k >= 1; k--) {
								if(base[k]) {
									base[k] = false;
									score++;
								}
							}
							score++;
							break;
						}
						
						if(outCount == 3) {
							startPlayer = j+1;
							if(startPlayer == 10) {
								startPlayer = 1;
							}
							
							break outer;
						}
				}
				
				startPlayer = 1;
			}
		}
		
		max = Math.max(max, score);
	}
}
