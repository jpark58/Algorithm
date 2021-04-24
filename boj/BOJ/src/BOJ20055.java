import java.util.*;
import java.io.*;

public class BOJ20055 {

	static int N, K;
	static int[] arr;
	static boolean[] robotMap;
	static class Robot{
		int position;
		Robot(int position){
			this.position = position;
		}
	}
	
	static ArrayList<Robot> robots;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[2*N+1];
		robotMap = new boolean[2*N+1];
		robots = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 1; i <= 2*N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int phase = 1;
		
		while(true) {
			// 1
			for(Robot r: robots) {
				r.position += 1;
				if(r.position == 2*N+1) {
					r.position = 0;
				}
			}
			System.out.println(robots.size() + "size")
;			
			for(int i = 0; i < robots.size(); i++) {
				if(robots.get(i).position == N) {
					robots.remove(i);
					break;
				}
			}
			
			int last = arr[2*N];
			for(int i = 2*N; i > 2; i--) {
				arr[i] = arr[i-1];
			}
			arr[1] = last;
			
			
			robotMap = new boolean[2*N+1];
			
			for(Robot r: robots) {
				robotMap[r.position] = true;
			}
			
			// 2
			for(Robot r: robots) {
				if(r.position != N) {

					if(r.position != 2*N && robotMap[r.position+1] == false && arr[r.position+1] >= 1) {
						robotMap[r.position] = false;
						robotMap[r.position+1] = true;
						arr[r.position+1]--;
						r.position += 1;
					}
					else if(r.position == 2*N && robotMap[1] == false && arr[1] >= 1) {
						robotMap[2*N] = false;
						robotMap[1] = true;
						arr[1]--;
						r.position = 1;
					}
				}
			}
			
			// 3
			if(arr[1] > 0 && robotMap[1] == false) {
				Robot robot = new Robot(1);
				robots.add(robot);
				arr[1]--;
				robotMap[1] = true;
			}
			
			int count = 0;
			for(int i = 1; i<=2*N; i++) {
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
