import java.io.*;
import java.util.*;

public class BOJ14501 {
	
	static int N;
	static int[] T;
	static int[] P;
	static int[] dp;
	static int max;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = new int[N+5];
		P = new int[N+5];
		dp = new int[N+5];
		max = 0;
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			dp[i] = Math.max(dp[i], max);
			dp[i+T[i]-1] = Math.max(dp[i+T[i]-1], dp[i]+P[i]);
			max = Math.max(max,  dp[i]);
			
		}
		System.out.println(dp[N]);
	}
}
