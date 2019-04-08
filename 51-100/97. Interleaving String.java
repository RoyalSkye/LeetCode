// 1.backtracking - TLE - 99 / 101 test cases passed.
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length() != s1.length() + s2.length()) return false;
        return backtracking(s1, s2, s3);
    }
    
    private boolean backtracking(String s1, String s2, String s3){
        if(s1.length()==0 && s2.length()==0 && s3.length()==0) return true;
        char target = s3.charAt(0);
        char c1 = s1.length()==0 ? ' ' : s1.charAt(0);
        char c2 = s2.length()==0 ? ' ' : s2.charAt(0);
        if(target!=c1 && target!=c2) return false;
        if(c1 == c2){
            return backtracking(s1.substring(1), s2.substring(0), s3.substring(1)) || backtracking(s1.substring(0), s2.substring(1), s3.substring(1));
        }else{
            if(target == c1) return backtracking(s1.substring(1), s2.substring(0), s3.substring(1));
            else return backtracking(s1.substring(0), s2.substring(1), s3.substring(1));
        }
    }
}

// 2.my solution - dp
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length() != s1.length() + s2.length()) return false;
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        dp[0][0] = 1;
        return backtracking(s1, s2, s3, dp);
    }
    
    private boolean backtracking(String s1, String s2, String s3, int[][] dp){
        int l1 = s1.length();
        int l2 = s2.length();
        if(dp[l1][l2]!=0) return dp[l1][l2] == 1;
        if(l1==0 && l2==0 && s3.length()==0) return true;
        char target = s3.charAt(0);
        char c1 = l1==0 ? ' ' : s1.charAt(0);
        char c2 = l2==0 ? ' ' : s2.charAt(0);
        if(target!=c1 && target!=c2){
            dp[l1][l2] = -1;
            return false;
        } 
        if(c1 == c2){
            if(backtracking(s1.substring(1), s2.substring(0), s3.substring(1), dp) || backtracking(s1.substring(0), s2.substring(1), s3.substring(1), dp)) {
                dp[l1][l2] = 1;
                return true;
            }else{
                dp[l1][l2] = -1;
                return false;
            } 
        }else{
            if(target == c1){
                if(backtracking(s1.substring(1), s2.substring(0), s3.substring(1), dp)){
                    dp[l1][l2] = 1;
                    return true;
                }else{
                    dp[l1][l2] = -1;
                    return false;
                }
            } 
            else{
                if (backtracking(s1.substring(0), s2.substring(1), s3.substring(1), dp)){
                    dp[l1][l2] = 1;
                    return true;
                }else{
                    dp[l1][l2] = -1;
                    return false;
                }
            } 
        }
    }
}

// dp with 2D / 1D
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s3.length() != s1.length() + s2.length()) return false;
        boolean[][] dp = new boolean[s1.length()+1][s2.length()+1];
        for(int i=0;i<=s1.length();i++){
            for(int j=0;j<=s2.length();j++){
                if(i==0 && j==0) dp[0][0] = true;
                else if(i == 0){
                    dp[0][j] = dp[0][j-1] && (s2.charAt(j-1) == s3.charAt(i+j-1));
                }else if(j == 0){
                    dp[i][0] = dp[i-1][0] && (s1.charAt(i-1) == s3.charAt(i+j-1));
                }else{
                    dp[i][j] = (dp[i-1][j] && (s1.charAt(i-1) == s3.charAt(i+j-1))) || (dp[i][j-1] && (s2.charAt(j-1) == s3.charAt(i+j-1)));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }
}

public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        boolean dp[] = new boolean[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[j] = true;
                } else if (i == 0) {
                    dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if (j == 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[s2.length()];
    }
}