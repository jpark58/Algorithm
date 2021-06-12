package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6087 {
    static int W, H;
    static char[][] map;
    static int min = Integer.MAX_VALUE;
    static int[][] visited;
    static ArrayList<Point> points;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        visited = new int[H][W];
        points = new ArrayList<>();

        for(int i = 0; i < H; i++){
            String str = br.readLine();
            for(int j = 0; j < W; j++){
                visited[i][j] = Integer.MAX_VALUE;
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'C'){
                    points.add(new Point(i, j, -1, 0));
                }
            }
        }

        bfs();
    }

    private static void bfs(){
        Queue<Point> q = new LinkedList<>();

        Point c1 = points.get(0);
        Point c2 = points.get(1);
        q.add(c1);
        visited[c1.x][c1.y] = 0;

        while(!q.isEmpty()){
            Point p = q.poll();

            int x = p.x; //현재 좌표
            int y = p.y;
            int dir = p.dir;
            int count = p.count;

            if (x == c2.x && y == c2.y) {
                min = Math.min(count, min);
            }

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx>=0 && nx < H && ny >= 0 && ny < W && map[nx][ny] != '*'){

                    int temp = p.count;
                    if(dir != i && dir != -1){
                        temp++;
                    }

                    if(visited[nx][ny] < temp) continue;

                    visited[nx][ny] = temp;
                    q.add(new Point(nx, ny, i, temp));
                }
            }
        }


        System.out.println(min);
    }

    static class Point{
        int x, y, dir, count;
        public Point(int x, int y, int dir, int count){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }
    }
}

