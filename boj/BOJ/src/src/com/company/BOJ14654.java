package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14654 {
    static int N;
    static ArrayList<Integer> teamA;
    static ArrayList<Integer> teamB;

    public static void main(String[] args) throws Exception {
	// write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        teamA = new ArrayList<>();
        for(int i = 0; i < N; i++){
            teamA.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        teamB = new ArrayList<>();
        for(int i = 0; i < N; i++){
            teamB.add(Integer.parseInt(st.nextToken()));
        }
        int cntA = 0, cntB = 0;
        int ans = 0;
        for(int i = 0; i < N ; i++){
            if(teamA.get(i) == 1 && teamB.get(i) == 2) {
                cntA = 0;
                cntB++;
            }
            else if(teamA.get(i) == 2 && teamB.get(i) == 3) {
                cntA = 0;
                cntB++;
            }
            else if(teamA.get(i) == 3 && teamB.get(i) == 1) {
                cntA = 0;
                cntB++;
            }
            else if(teamA.get(i) == 1 && teamB.get(i) == 2) {
                cntA++;
                cntB = 0;
            }
            else if(teamA.get(i) == 2 && teamB.get(i) == 3) {
                cntA++;
                cntB = 0;
            }
            else if(teamA.get(i) == 3 && teamB.get(i) == 1) {
                cntA++;
                cntB = 0;
            }
            else if(teamA.get(i) == teamB.get(i)){
                if(cntA == 1) {
                    cntA = 0;
                    cntB++;
                }
                else {
                    cntB = 0;
                    cntA++;
                }
            }
            ans = Math.max(ans, Math.max(cntA, cntB));
        }

        System.out.println(ans);
    }
}
