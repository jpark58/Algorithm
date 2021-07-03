package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7579 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int ans = Integer.MAX_VALUE;

        int[] memoryArr = new int[n];
        int[] costArr = new int[n];
        int[][] dp = new int[n][10001];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            memoryArr[i] = Integer.parseInt(st1.nextToken());
            costArr[i] = Integer.parseInt(st2.nextToken());
        }

        for(int i = 0; i < n; i++){
            int cost = costArr[i];
            int memory = memoryArr[i];

            for(int j = 0; j <= 10000; j++){
                if(i == 0){
                    if(j >= cost) dp[i][j] = memory;
                }
                else{
                    if(j >= cost){
                        dp[i][j] = Math.max(dp[i-1][j-cost]+memory, dp[i-1][j]);
                    }
                    else{
                        dp[i][j] = dp[i - 1][j];
                    }
                }

                if(dp[i][j]>=m) ans = Math.min(ans, j);
            }
        }
        System.out.println(ans);
    }
}
