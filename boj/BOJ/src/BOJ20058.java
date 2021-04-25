import java.io.*;
import java.util.*;

public class BOJ20058 {
	static int N, Q, size, sum;
	static ArrayList<Integer> L;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int maxIce;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		L = new ArrayList<>();
		size = (int) Math.pow(2, N);
		
		map = new int[size][size];
		
		for(int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j= 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < Q; i++) {
			L.add(Integer.parseInt(st.nextToken()));
		}
		
		for(int l: L) {
			rotate((int)Math.pow(2, l));
			melt();
		}
		sum = 0;
		int max = 0;
		
		boolean visited[][] = new boolean[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(!visited[i][j] && map[i][j] > 0) {
                    visited[i][j] = true;
                    max = Math.max(max, answer(i, j, visited));
                }
            }
        }
        System.out.println(sum + "\n" + max);
	}
	
	public static int answer(int x, int y, boolean visit[][]) {
        int count = 1;
        sum += map[x][y];
        
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=0 && nx<size && ny>=0 && ny<size && map[nx][ny] > 0 && !visit[nx][ny]) {
                visit[nx][ny] = true;
                count += answer(nx, ny, visit);
            }
        }
        
        return count;
    }
	
	
	
	public static void melt() {
		Queue<Integer> qx = new LinkedList<>();
		Queue<Integer> qy = new LinkedList<>();
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				int iceCount = 0;
				for(int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if(nx>=0 && nx < size && ny>=0 && ny<size) {
						if(map[nx][ny] > 0) iceCount++;
					}
				}
				
				if(iceCount < 3) {
					qx.add(i);
					qy.add(j);
				}
			}
		}
		
		while(!qx.isEmpty() && !qy.isEmpty()) {
			int x = qx.poll();
			int y = qy.poll();
			map[x][y]--;
		}
	}
	
	public static void rotate(int len) {
		for(int i = 0; i < size; i+=len) {
			for(int j = 0; j < size; j+= len) {
				rotateArr(i, j, len);
			}
		}
	}
	
	public static void rotateArr(int x, int y, int len) {
		for(int i = x; i < x+len; i++) {
			for(int j = y; j < y+len; j++) {
				map[j][x+len-i-1] = map[i][j];
			}
		}
	}
}
