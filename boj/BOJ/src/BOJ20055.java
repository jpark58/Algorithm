import java.util.*;
import java.io.*;

public class BOJ20055 {

	static int N, K;
	static int[] arr;
	static boolean[] robotMap;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[2*N+1];
		robotMap = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <= 2*N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int phase = 1;
		int zeros = 0;
		
		while(true) {
			// 1
			int last = arr[2*N];
			for(int i = 2*N; i > 1; i--) {
				arr[i] = arr[i-1];
			}
			arr[1] = last;
			
			for(int i = N; i > 1; i--) {
				robotMap[i] = robotMap[i-1];
			}
			robotMap[1] = false;
			
			
			robotMap[N] = false;
			
			// 2
			for(int i = N; i > 1; i--) {
				if(robotMap[i-1] && !robotMap[i] && arr[i]>=1) {
					robotMap[i] = true;
					robotMap[i-1] = false;
					arr[i]--;
				}
			}
			
			//3
			if(arr[1] > 0) {
				arr[1]--;
				robotMap[1] = true;
			}
			int count = 0;
			for(int i = 1; i <= 2*N; i++) {
				if(arr[i]==0) count++;
			}
			
			if(count >= K) {
				break;
			}
			
			phase++;
			
		}
		
		
		System.out.println(phase);
	}
}
