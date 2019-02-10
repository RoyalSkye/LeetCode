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

//3. Expand Around Center
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
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