import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class BOJ17406 {
	
	static int N;
	static int M;
	static int K;
	static int[][] arr;
	static int[][] ops;
	static int min;
	static int[][] copyMap;
	static boolean[] visited;
	static ArrayList<Integer> perms;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		min = Integer.MAX_VALUE;
		
		arr = new int[N][M];
		copyMap = new int[N][M];
		ops = new int[K][3];
		visited = new boolean[K];
		perms = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				copyMap[i][j] = arr[i][j];
			}
		}
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				ops[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		permutation(K);
		
		System.out.println(min);
	}
	
	public static void permutation(int depth) {
		if(depth == 0) {
			work();
			return;
		}
		
		for(int i = 0; i < K; i++) {
			if(!visited[i]) {
				visited[i] = true;
				perms.add(i);
				permutation(depth-1);
				perms.remove(perms.size()-1);
				visited[i] = false;
			}
		}
	}
	
	public static void work() {
		arr = copyMap;
		for(int e: perms) {
			int x = ops[e][0];
			int y = ops[e][1];
			int z = ops[e][2];
			
			rotate(x-1,y-1,z);
		}
		getA();
	}
	
	public static void rotate(int x, int y, int size) {
		int s = size;
		while(s > 0) {
			int tempUp = arr[x-s][y-s];
			int tempRight = arr[x-s][y+s];
			int tempDown = arr[x+s][y+s];
			int tempLeft = arr[x+s][y-s];
			
			//right
			for(int i = y+s; i > y-s+1; i--) {
				arr[x-s][i] = arr[x-s][i-1];
			}
			
			//down
			for(int i = x+s; i > x-s+1; i--) {
				arr[i][y+s] = arr[i-1][y+s];
			}
			
			
			//left
			for(int i = y-s; i < y+s-1; i++) {
				arr[x+s][i] = arr[x+s][i+1];
			}
			
			//up
			for(int i = x-s; i<x+s-1; i++) {
				arr[i][y-s] = arr[i+1][y-s];
			}
			
			arr[x-s][y-s+1] = tempUp;
			arr[x-s+1][y+s] = tempRight;
			arr[x+s][y+s-1] = tempDown;
			arr[x+s-1][y-s] = tempLeft;
			
			s--;
		}
	}
	
	public static void getA() {
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < M; j++) {
				sum += arr[i][j];
			}
			min = Math.min(min, sum);
		}
	}
}
