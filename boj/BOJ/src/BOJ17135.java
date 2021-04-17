import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17135 {
	static int max;
    static int n;
    static int m;
    static int d;
    static int[][] map;
    static int[][] copyMap;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[n+1][m+1];
        copyMap = new int[n+1][m+1];
        max = 0;
        
        ArrayList<Integer> shooters = new ArrayList<>();
        
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                copyMap[i][j] = map[i][j];
            }
        }
        
        comb(1, 3, shooters);
        System.out.println(max);
    }
    
    public static void comb(int start, int depth, ArrayList<Integer> shooters){
        if(depth == 0){
            init();
            getCalc(shooters);
            return;
        }
        
        for(int i = start; i <= m; i++){
            shooters.add(i);
            comb(i+1, depth-1, shooters);
            shooters.remove(shooters.size()-1);
        }
    }
    
    public static void init(){
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                map[i][j] = copyMap[i][j];
            }
        }
    }
    
    public static int getDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x2-x1) + Math.abs(y2-y1);
    }
    
    public static void getCalc(ArrayList<Integer> shooters){
        int count = 0;
        for(int i = 1; i <= n; i++){
            boolean[][] enemies = new boolean[n+1][m+1];
            for(int j = 0; j < shooters.size(); j++){
                int curr_shooter = shooters.get(j);
                int minX = Integer.MAX_VALUE;
                int minY = Integer.MAX_VALUE;
                int minD = Integer.MAX_VALUE;
                
                for(int k = 1; k <= n; k++){
                    for(int l = 1; l <= m; l++){
                        if(map[k][l]==1){
                            if(minD >= getDistance(n+1, curr_shooter, k, l)){
                                if(minD > getDistance(n+1, curr_shooter, k, l)){
                                    minD = getDistance(n+1, curr_shooter, k, l);
                                    minX = k;
                                    minY = l;
                                }
                                else{
                                    if(minY > l){
                                        minY = l;
                                        minX = k;
                                    }
                                }
                            }
                        }
                    }
                }
                
                if(minD <= d){
                    enemies[minX][minY] = true;
                }
            }
            
            for(int k = 1; k <= n; k++){
                for(int l = 1; l <= m; l++){
                    if(enemies[k][l]==true){
                        count++;
                        map[k][l] = 0;
                    }
                }
            }
            
            for(int k = 1; k <= m; k++){
                map[n][k]=0;
            }
            
            for(int k = n; k >= 1; k--){
                for(int l = 1; l <= m; l++){
                    map[k][l]=map[k-1][l];
                }
            }
        }
        
        max = Math.max(count, max);
    }
}
