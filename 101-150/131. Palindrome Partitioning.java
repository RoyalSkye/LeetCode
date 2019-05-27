// 1.backtracking
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new LinkedList<>();
        List<String> ll = new LinkedList<>();
        dfs(s, 0, ll, ans);
        return ans;
    }
    
    private void dfs(String s, int pos, List<String> ll, List<List<String>> ans){
        if(pos >= s.length()){
            ans.add(new LinkedList<String>(ll));
            return;
        } 
        for(int i=pos;i<s.length();i++){
            if(isPalindrome(s, pos, i)){
                ll.add(s.substring(pos, i+1));
                dfs(s, i+1, ll, ans);
                ll.remove(ll.size()-1);
            }
        }
    }
    
    private boolean isPalindrome(String s, int start, int end){
        while(start < end){
            if(s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }
}

// 2.dp+backtracking
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new LinkedList<>();
        List<String> ll = new LinkedList<>();
        boolean[][] dp = new boolean[s.length()][s.length()];
        for(int i=0;i<s.length();i++){
            for(int j=0;j<=i;j++){
                if(s.charAt(i)==s.charAt(j) && (i-j<=2 || dp[j+1][i-1])){
                    dp[j][i] = true;
                }
            }
        }
        dfs(dp, s, 0, ll, ans);
        return ans;
    }
    
    private void dfs(boolean[][] dp, String s, int pos, List<String> ll, List<List<String>> ans){
        if(pos >= s.length()){
            ans.add(new LinkedList<String>(ll));
            return;
        } 
        for(int i=pos;i<s.length();i++){
            if(dp[pos][i]){
                ll.add(s.substring(pos, i+1));
                dfs(dp, s, i+1, ll, ans);
                ll.remove(ll.size()-1);
            }
        }
    }
}