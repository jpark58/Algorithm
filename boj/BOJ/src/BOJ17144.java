import java.io.*;
import java.util.*;

public class BOJ17144 {
	static int R,C,T;
	static int[][] A;
	static int upperX,lowerX;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
 	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		A = new int[R][C];
		
		upperX = -1;
		lowerX = -1;
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				if(A[i][j] == -1 && upperX == -1){
					A[i][j] = 0;
					upperX = i;
				}
				else if(A[i][j]==-1 && upperX != -1) {
					A[i][j] = 0;
					lowerX = i;
				}
			}
		}
		
 		
 		while(T > 0) {
 			helper();
 			T--;
 		}
		
		int count = 0;
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(A[i][j] > 0) {
					count += A[i][j];
				}
			}
		}
		
		System.out.println(count);

	}
 	
 	public static void helper() {
 		int[][] copyMap = new int[R][C];
 		
 		for(int i = 0; i < R; i++) {
 			for(int j = 0; j < C; j++) {
 				if(i == upperX && j == 0) continue;
 				if(i == lowerX && j == 0) continue;
 				int temp = A[i][j]/5;
 				int count = 0;
 				for(int k = 0; k < 4; k++) {
 					int nx = i + dx[k];
 					int ny = j + dy[k];
 					if(nx >= 0 && nx < R && ny >= 0 && ny < C) {
 						if(nx == upperX && ny == 0) continue;
 						if(nx == lowerX && ny == 0) continue;
 						count++;
 						copyMap[nx][ny] += temp;
 					}
 				}
 				copyMap[i][j] += A[i][j] - temp*count;
 			}
 		}
 		
 		for(int i = 0; i < R; i++) {
 			for(int j = 0; j < C; j++) {
 				A[i][j] = copyMap[i][j];
 			}
 		}


 		purify();
 	}
 	
 	public static void purify() {
 		int top = upperX;
        int down = lowerX;
        
        // 위쪽 공기청정기의 바람은 반시계방향 순환,
        // 아래로 당기기
        for (int i = top - 1; i > 0; i--) 
            A[i][0] = A[i-1][0];
        // 왼쪽으로 당기기
        for (int i = 0; i < C - 1; i++) 
            A[0][i] = A[0][i+1];
        // 위로 당기기
        for (int i = 0; i < top; i++) 
            A[i][C - 1] = A[i + 1][C - 1];
        // 오른쪽으로 당기기
        for (int i = C - 1; i > 1; i--) 
            A[top][i] = A[top][i-1];
        // 공기청정기에서 부는 바람은 미세먼지가 없는 바람
        A[top][1] = 0;
        
        // 아래쪽 공기청정기의 바람은 시계방향으로 순환
        // 위로 당기기
        for (int i = down + 1; i < R - 1; i++) 
            A[i][0] = A[i + 1][0];
        // 왼쪽으로 당기기
        for (int i = 0; i < C - 1; i++) 
            A[R - 1][i] = A[R - 1][i + 1]; 
        // 아래로 당기기
        for (int i = R - 1; i > down; i--) 
            A[i][C - 1] = A[i - 1][C - 1];
        // 오른쪽으로 당기기
        for (int i = C - 1; i > 1; i--) 
            A[down][i] = A[down][i - 1];
        // 공기청정기에서 부는 바람은 미세먼지가 없는 바람
        A[down][1] = 0;
 	}
}
