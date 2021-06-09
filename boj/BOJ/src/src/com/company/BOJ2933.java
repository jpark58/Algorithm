package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2933 {
    static int r,c,n;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Node> q = new LinkedList<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        for(int i = 0; i < r; i++){
            map[i] = br.readLine().toCharArray();
        }

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            int row = r - Integer.parseInt(st.nextToken());

            fight(row, i);
            solve();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r; i++) {
            sb.append(map[i]);
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void solve(){
        visited = new boolean[r][c];
        ArrayList<Node> clusters = new ArrayList<>();

        // 바닥 체크
        for(int i = 0; i < c; i++){
            if(map[r-1][i] == '.' || visited[r-1][i]) continue;
            visited[r-1][i] = true;
            q.add(new Node(r-1, i));

            while(!q.isEmpty()){
                Node curr = q.poll();

                for(int d = 0; d < 4; d++){
                    int nx = curr.x + dx[d];
                    int ny = curr.y + dy[d];
                    if(nx >= 0 && nx < r && ny>=0 && ny < c && !visited[nx][ny] && map[nx][ny] != '.'){
                        visited[nx][ny] = true;
                        q.add(new Node(nx, ny));
                    }
                }
            }
        }

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(!visited[i][j] && map[i][j] == 'x'){
                    map[i][j] = '.';
                    clusters.add(new Node(i, j));
                }
            }
        }

        if(clusters.isEmpty()) return;

        boolean flag = true;
        while(flag){
            for(Node node: clusters){
                int nx = node.x+1;
                int ny = node.y;

                if(nx < 0 || nx >= r || ny < 0 || ny >= c || map[nx][ny] == 'x'){
                    flag = false;
                    break;
                }
            }

            if(flag){
                for(Node node: clusters){
                    node.x = node.x+1;
                }
            }
        }

        for(Node node: clusters){
            map[node.x][node.y] = 'x';
        }
    }

    public static void fight(int row, int idx){
        if(idx%2 == 0){
            for(int i = 0; i < c; i++){
                if(map[row][i] == 'x'){
                    map[row][i] = '.';
                    break;
                }
            }
        }else{
            for(int i = c-1; i >= 0; i--){
                if(map[row][i] == 'x'){
                    map[row][i] = '.';
                    break;
                }
            }
        }
    }
}
