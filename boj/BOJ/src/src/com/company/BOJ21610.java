package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21610 {

    private static class Node{
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1,1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j= 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            move(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
            check();
            evaporate();
        }

        System.out.println(allSum());
    }

    private static int allSum(){
        int temp = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                temp += map[i][j];
            }
        }

        return temp;
    }

    private static void evaporate(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(visit[i][j]){
                    visit[i][j] = false;
                }else{
                    if(map[i][j]>=2){
                        map[i][j] -= 2;
                        q.add(new Node(i, j));
                    }
                }
            }
        }
    }

    private static void check(){
        while(!q.isEmpty()){
            Node n = q.poll();
            visit[n.x][n.y] = true;
            for(int i = 1; i < 8; i+=2){
                int nx = n.x + dx[i];
                int ny = n.y + dy[i];
                if(nx>=0 && ny>=0 && nx < N && ny < N && map[nx][ny]>0){
                    map[n.x][n.y]++;
                }
            }
        }
    }

    private static void move(int dir, int length){
        int size = q.size();
        for(int i = 0; i < size; i++){
            Node n = q.poll();
            int nx = mod(n.x + dx[dir] * length);
            int ny = mod(n.y + dy[dir] * length);
            map[nx][ny]++;
            q.add(new Node(nx, ny));
        }
    }

    private static int mod(int num){
        while(num < 0) num += N;
        return num%N;
    }

    private static void init(){
        map = new int[N][N];
        visit = new boolean[N][N];
        q.add(new Node(N - 1, 0));
        q.add(new Node(N - 2, 0));
        q.add(new Node(N - 1, 1));
        q.add(new Node(N - 2, 1));
    }
}
