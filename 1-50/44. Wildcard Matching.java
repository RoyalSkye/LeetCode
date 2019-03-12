// this problem is similar to 10.RegEx, but it's easier.
// 1.backtracking - Time Limit Exceeded - 1636 / 1808 test cases passed.
class Solution {
    public boolean isMatch(String s, String p) {
        //System.out.println("s:"+s+" p:"+p);
        if(s.length() == 0 && p.length() == 0) return true;
        else if(s.length() == 0){
            if(p.charAt(0) == '*') return isMatch(s, p.substring(1));
            else return false;
        }else if(p.length() == 0) return false;
        char ss = s.charAt(0);
        char pp = p.charAt(0);
        if(pp == '*'){
            return isMatch(s, p.substring(1)) || isMatch(s.substring(1), p);
        }else if(pp != '?'){
            if(ss!=pp) return false;
        }
        return isMatch(s.substring(1), p.substring(1));
    }
}

// 2.dp - O(m*n)
// https://www.youtube.com/watch?v=3ZDZ-N0EPV0
class Solution {
    public boolean isMatch(String text, String pattern) {
        int textlen = text.length();
        int patternlen = pattern.length();
        boolean[][] ans = new boolean[textlen+1][patternlen+1];
        ans[0][0] = true;
        for(int i=0;i<=textlen;i++){
            for(int j=0;j<=patternlen;j++){
                if(j==0 && i==0) continue;
                else if(j==0 && i!=0){  // Column 0
                    ans[i][j] = false;
                }else if(i == 0 && j!=0){  // Row 0
                    if(pattern.charAt(j-1) == '*') ans[i][j] = ans[i][j-1];
                    else ans[i][j] = false;
                }else{
                    if(pattern.charAt(j-1) == '?' || pattern.charAt(j-1) == text.charAt(i-1)) ans[i][j] = ans[i-1][j-1];
                    else if(pattern.charAt(j-1) == '*') ans[i][j] = ans[i][j-1] || ans[i-1][j];
                    else ans[i][j] = false;
                }
            }
        }        
        return ans[textlen][patternlen];
    }
}