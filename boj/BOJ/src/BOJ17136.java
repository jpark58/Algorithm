import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ17136 {
	
	static int[][] map;
	static int min;
	static int[] papers = { 0, 5, 5, 5, 5, 5 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		min = Integer.MAX_VALUE;
        StringTokenizer st;
		
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
		
		
		bfs(0,0,0);
		
		if(min == Integer.MAX_VALUE) {
			System.out.println(-1);
		}else{
			System.out.println(min);
		}
        br.close();
	}

	public static void bfs(int x, int y, int count) {
		
		if(x>=9 && y>9) {
			min = Math.min(count, min);
			return;
		}
		if(count >= min) {
			return;
		}
		
		if(y > 9) {
			bfs(x+1, 0, count);
			return;
		}
		
		if(map[x][y]==1) {
			for(int i = 5; i >= 1; i--) {
				if(papers[i] > 0 && isPossible(x,y,i)) {
					update(x,y,i, 0);
					papers[i]--;
					bfs(x,y+1, count+1);
					papers[i]++;
					update(x,y,i,1);
				}
			}
		}else {
			bfs(x,y+1,count);
		}
		
	}
	
	public static void update(int x, int y, int len, int status) {
		for(int i = x; i < x+len; i++) {
			for(int j = y; j < y+len; j++) {
				map[i][j] = status;
			}
		}
	}
	
	public static boolean isPossible(int x, int y, int len) {
		for(int i = x; i < x+len; i++) {
			for(int j = y; j<y+len; j++) {
				
				if( i >= 10 || j >= 10) {
					return false;
				}
				
				if(map[i][j]!= 1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	
}
