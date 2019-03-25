// 1.dp - non-trivial
// https://en.wikipedia.org/wiki/Edit_distance
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for(int i=0;i<=len1 || i<=len2;i++){
            if(i<=len1) dp[i][0] = i;
            if(i<=len2) dp[0][i] = i;
        }
        for(int i=1;i<=len1;i++){
            for(int j=1;j<=len2;j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}