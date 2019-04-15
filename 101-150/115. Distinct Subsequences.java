// 1.my solution - Map<Character, List<Integer>> - TLE - 54 / 63 test cases passed.
class Solution {
    public int numDistinct(String s, String t) {
        if(t.length() == 0) return 1;
        if(s.length() < t.length()) return 0;
        Map<Character, List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for(int i=0;i<s.length();i++){
            List<Integer> ll = map.getOrDefault(s.charAt(i), new LinkedList<Integer>());
            ll.add(i);
            map.put(s.charAt(i), ll);
        }
        return recursive(map, 0, -1, t);
    }
    
    private int recursive(Map<Character, List<Integer>> map, int curpos, int pre, String t){
        if(curpos == t.length()) return 1;
        int res = 0;
        if(map.get(t.charAt(curpos)) == null) return 0;
        for(int tmp : map.get(t.charAt(curpos))){
            if(tmp > pre){
                res += recursive(map, curpos+1, tmp, t);
            }
        }
        return res;
    }
}

// 2.dp - space&time: O(m*n) 
// https://leetcode.com/problems/distinct-subsequences/discuss/37327/Easy-to-understand-DP-in-Java
class Solution {
    public int numDistinct(String s, String t) {
        int row = t.length(), column = s.length();
        int[][] dp = new int[row+1][column+1];
        for(int i=0;i<=column;i++) dp[0][i] = 1;
        for(int i=1;i<=row;i++) dp[row][0] = 0;
        for(int i=1;i<=row;i++){
            for(int j=1;j<=column;j++){
                if(t.charAt(i-1) == s.charAt(j-1)) dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
                else dp[i][j] = dp[i][j-1];
            }
        }
        return dp[row][column];
    }
}
// we can also optimize space complexity to O(N)