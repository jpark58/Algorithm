package com.company;

public class LeetInterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(); int len2 = s2.length(); int len3 = s3.length();
        // If simply adding length do not match len3, then there is no way we can forms
        // s3 by interleaving chars in s1,s2
        if(len1 + len2 != len3) {
            return false;
        }

        /*
        dp[i][j] -> true if s1[0..i-1] and s2[0..j-1] interleaves s3[0..i+j-1]
        */
        boolean[][] dp = new boolean[len1+1][len2+1];

        /*
        EMPTY s1,s2 can surely interleave to form EMPTY s3
        */
        dp[0][0] = true;

        /*
        If S2 is empty, then just check whether char in s1 matches with that of s3
        Note: j == 0 here.
        */
        for(int i = 1;i<=len1;i++) {
            dp[i][0] = dp[i-1][0] && s3.charAt(i-1) == s1.charAt(i-1);
        }

        /*
        If S1 is empty, then just check whether char in s2 matches with that of s3
        Note: i == 0 here.
        */
        for(int j = 1;j<=len2;j++) {
            dp[0][j] = dp[0][j-1] && s3.charAt(j-1) == s2.charAt(j-1);
        }

        /*
        Now check for both s1,s2 being non empty
        */
        for(int i = 1; i<=len1; i++) {
            for(int j = 1; j<=len2; j++) {
                // Characters to be compared
                int c1 = s1.charAt(i - 1);
                int c2 = s2.charAt(j - 1);
                int c3 = s3.charAt(i + j - 1);

                // If c3 matches c1, then is every char before c1 in s1 valid?
                // If c3 matches c2, then is every char before c2 in s2 valid?
                dp[i][j] = (c1 == c3 && dp[i-1][j]) || (c2 == c3 && dp[i][j-1]);
            }
        }

        return dp[len1][len2];
    }
}
