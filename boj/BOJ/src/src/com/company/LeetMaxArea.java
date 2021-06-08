package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class LeetMaxArea {
    int row, col;
    boolean[][] visited;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    static class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public int maxAreaOfIsland(int[][] grid) {
        int area = 0;
        row = grid.length;
        col = grid[0].length;
        visited = new boolean[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(!visited[i][j] && grid[i][j]==1){
                    visited[i][j] = true;
                    area = Math.max(area, bfs(i,j,grid));
                }
            }
        }

        return area;
    }

    public int bfs(int x, int y, int[][] grid){
        int count = 0;

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x,y));

        while(!q.isEmpty()){
            Point curr = q.poll();
            visited[curr.x][curr.y] = true;
            count++;

            for(int i = 0; i < 4; i++){
                int nx = curr.x+dx[i];
                int ny = curr.y+dy[i];

                if(nx>=0 && nx < row && ny >= 0 && ny < col && !visited[nx][ny] && grid[nx][ny]==1){
                    visited[nx][ny] = true;
                    q.offer(new Point(nx, ny));
                }
            }
        }

        return count;
    }
}
