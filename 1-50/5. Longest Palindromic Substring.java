//1. brute force - 6153ms O(n^3)
class Solution {
    public String longestPalindrome(String s) {
        String ans = "";
        int len = s.length();
        int sublen = 0;
        if(len == 0) return "";
        /*for(int i=0;i<len;i++){
            for(int j=i;j<len;j++){
                if(checkPalindromic(s,i,j) && sublen < j-i+1){
                    ans = s.substring(i,j+1);
                    sublen = ans.length();
                }
            }
        }*/
        for(int i=0;i<len;i++){
            for(int j=i;j<len;j++){
                if(s.charAt(i)==s.charAt(j)){
                    if(checkPalindromic(s,i,j) && sublen < j-i+1){
                        ans = s.substring(i,j+1);
                        sublen = ans.length();
                    }
                }
            }
        }
        return ans;
    }
    public boolean checkPalindromic(String s, int begin, int end){
        if(begin > end) return true;
        if(checkPalindromic(s, begin+1, end-1)){
            return s.charAt(begin) == s.charAt(end);
        }
        return false;
    }
}

//2. Dynamic Programming O(n^2)
class Solution {
    public String longestPalindrome(String s) {
        String ans = "";
        int len = s.length();
        int sublen = 0;
        int[][] table = new int[len][len];
        if(len == 0) return "";
        for(int i=0;i<len-1;i++){
            table[i][i]=1;
            if(s.charAt(i+1) == s.charAt(i)){
                table[i][i+1] = 1;
            }
        }
        table[len-1][len-1] = 1;
        for(int step=3;step<=len;step++){
            for(int i=0;i<len-step+1;i++){
                if(s.charAt(i) == s.charAt(i+step-1)) table[i][i+step-1] = table[i+1][i+step-2];
                else table[i][i+step-1] = 0;
            }
        }
        for(int i=0;i<len;i++){
            for(int j=i;j<len;j++){
                if(table[i][j] == 1 && sublen < j-i+1){
                    ans = s.substring(i,j+1);
                    sublen = ans.length();
                } 
            }
        }
        return ans;
    }
}

//3. Expand Around Center - preference
class Solution {
    public String longestPalindrome(String s) {
        if (s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}

// 4.Manacher’s Algorithm - 0(n)
// Reference: https://articles.leetcode.com/longest-palindromic-substring-part-ii/
// https://www.felix021.com/blog/read.php?2040
class Solution {
    public String longestPalindrome(String s) {
        if(s.length() == 0) return "";
        String T = preProcess(s);
        int n = T.length();
        int[] P = new int[n];
        int C = 0, R = 0;
        for (int i = 1; i < n-1; i++) {  // "#a#b#b#a#"
            P[i] = (R > i) ? Math.min(R-i, P[2*C-i]) : 0;  // i' = C - (i-C)
            //System.out.println("P[i]="+P[i]);
            // Attempt to expand palindrome centered at i
            while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i])) P[i]++;
            // If palindrome centered at i expand past R, adjust center based on expanded palindrome.
            if (P[i] + i > R){
                C = i;
                R = i + P[i];
            }
            //System.out.println("C="+C+" R="+R);
        }
        // Find the maximum element in P.
        int maxLen = 0;
        int centerIndex = 0;
        for (int i = 1; i < n-1; i++) {
            if (P[i] > maxLen) {
            maxLen = P[i];
            centerIndex = i;
            }
        }
        return T.substring(centerIndex-maxLen+1,centerIndex+maxLen).replace("#","");
    }
    
    // Transform S into T.
    // For example, S = "abba", T = "^#a#b#b#a#$".
    // ^ and $ signs are sentinels appended to each end to avoid bounds checking
    private String preProcess(String s) {
      int n = s.length();
      String ret = "^";
      for (int i = 0; i < n; i++)
        ret += "#" + s.substring(i, i+1);
      ret += "#$";
      return ret;
    }
}