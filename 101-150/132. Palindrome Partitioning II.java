// 1.dp - TLE
class Solution {
    int ans = Integer.MAX_VALUE;
    
    public int minCut(String s) {
        if(s.length()==0) return 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            for(int j=0;j<=i;j++){
                if(s.charAt(i) == s.charAt(j) && (i-j<=2 || dp[j+1][i-1])){
                    dp[j][i] = true;
                }
            }
        }
        dfs(dp, 0, 0);
        return ans;  
    }
    
    private void dfs(boolean[][] dp, int pos, int step){
        if(pos>=dp.length){
            ans = Math.min(ans, step-1);
            return;
        }
        for(int i=pos;i<dp.length;i++){
            if(dp[pos][i]){
                dfs(dp, i+1, step+1);
            }
        }
    }
}

// 2.7ms
// The definition of 'cut' array is the minimum number of cuts of a sub string. More specifically, cut[n] stores the cut number of string s[0, n-1].
// Here is the basic idea of the solution:
// Initialize the 'cut' array: For a string with n characters s[0, n-1], it needs at most n-1 cut. Therefore, the 'cut' array is initialized as cut[i] = i-1
// Use two variables in two loops to represent a palindrome:
// The external loop variable 'i' represents the center of the palindrome. The internal loop variable 'j' represents the 'radius' of the palindrome. Apparently, j <= i is a must.
// This palindrome can then be represented as s[i-j, i+j]. If this string is indeed a palindrome, then one possible value of cut[i+j] is cut[i-j] + 1, where cut[i-j] corresponds to s[0, i-j-1] and 1 correspond to the palindrome s[i-j, i+j];
class Solution {
    public int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] cut = new int[n+1];
        for(int i=0;i<cut.length;i++) cut[i] = i - 1;
        for(int i=0;i<n;i++){
            //odd 
            for(int j=0;i-j>=0 && i+j<n && s.charAt(i-j) == s.charAt(i+j);j++){ 
                cut[i+j+1] = Math.min(cut[i+j+1], cut[i-j]+1);
            }
            //even
            for(int j=1;i-j+1>=0 && i+j<n && s.charAt(i-j+1) == s.charAt(i+j);j++){
                cut[i+j+1] = Math.min(cut[i+j+1], cut[i-j+1]+1);
            }
        }
        return cut[n];
    }
}

// 3.1ms
class Solution {
    public int minCut(String s) {
        int n = s.length();
        if(n == 0) return 0;
        char[] t = s.toCharArray();
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = -1;
        int i = 0;
        while(i < n) {
            expandAround(t, i, i, dp);
            expandAround(t, i, i+1, dp);
            i++;
        }
        return dp[n];
    }
    
    public void expandAround(char[] t, int i , int j, int[] dp) {
        while(i >= 0 && j < t.length && t[i] == t[j]) {
            dp[j+1] = Math.min(dp[j+1], dp[i]+1);
            i--;j++;
        }
    }
}