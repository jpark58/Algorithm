import java.io.*;
import java.util.*;

public class BOJ20057 {
	static int N;
	static int[][] arr;
	static int sum=0;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
 	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j= 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		helper(N/2, N/2, 0, 0, 1);
	}
	
	public static void helper(int x, int y, int dir, int curr, int phase) {
		if(x == 0 && y == 0) {
			System.out.println(sum);
			return;
		}
		
		int nx = x+dx[dir];
		int ny = y+dy[dir];
		
		int rest = spread(nx, ny, arr[nx][ny]);
		
		
		if(curr == phase) {
			dir = (dir+1)%4;
		}
		
	}
	
	public static int spread(int x, int y, int amount) {
		int rest = amount;
		
		if(x+1 < N && y+1 < N) {
			rest -= amount*0.01;
			arr[x+1][y+1] += amount*0.01;
		}else {
			sum += amount*0.01;
		}
		
		if(x-1>=0 && y+1 < N) {
			rest -= amount*0.01;
			arr[x-1][y+1] += amount*0.01;
		}else {
			sum += amount*0.01;
		}
		
		if(x-1 >= 0) {
			rest -= amount*0.07;
			arr[x-1][y] += amount*0.07;
		}else {
			sum += amount*0.07;
		}
		
		if(x+1 < N) {
			rest -= amount*0.07;
			arr[x+1][y] += amount*0.07;
		}else {
			sum += amount*0.07;
		}
		
		if(x-2 >= 0) {
			rest -= amount*0.02;
			arr[x-2][y] += amount*0.02;
		}else {
			sum += amount*0.02;
		}
		
		if(x+2 < N) {
			rest -= amount*0.02;
			arr[x+2][y] += amount*0.02;
		}else {
			sum += amount*0.02;
		}
		
		if(x-1 >= 0 && y-1 >= 0) {
			rest -= amount*0.1;
			arr[x-1][y-1] +=  amount*0.1;
		}else {
			sum += amount*0.1;
		}
		
		if(x+1 >= 0 && y-1 >= 0) {
			rest -= amount*0.1;
			arr[x+1][y-1] +=  amount*0.1;
		}else {
			sum += amount*0.1;
		}
		
		if(y-2>= 0) {
			rest -= amount*0.05;
			arr[x][y-2] += amount*0.05;
		}else {
			sum += amount*0.05;
		}
		
		return rest;
	}
}
