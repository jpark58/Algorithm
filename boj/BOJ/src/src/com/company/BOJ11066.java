package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11066 {
    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[size];
            for(int j = 0; j < size; j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }

            System.out.println(solution(arr));
        }
    }

    private static int solution(int[] a){
        int size = a.length;
        int[][] dp = new int[size][size];
        int[] s = new int[size];

        s[0] = a[0];
        for(int i = 1; i < size; i++) s[i] += s[i-1]+a[i];

        for(int i = 0; i < size-1; i++) dp[i][i+1] = a[i] + a[i+1];

        for(int gap = 2; gap < size; gap++){
            for(int i = 0; i+gap < size; i++){
                int j = i+gap;
                dp[i][j] = Integer.MAX_VALUE;

                for(int k = i; k < j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + sum(s, i, j));
                }
            }
        }

        return dp[0][a.length - 1];
    }

    private static int sum(int[] arr, int i, int j){
        if(i == 0) return arr[j];
        else return arr[j] - arr[i - 1];
    }
}
