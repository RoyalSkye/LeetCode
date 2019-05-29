// 1.dfs - TLE - 29 / 36 test cases passed.
// Time - O(2^n) Space: O(N)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        // char[] chs = s.toCharArray();
        return backtracking(s, words, 0);
    }
    
    private boolean backtracking(String s, Set<String> wordDict, int start){
        if(start >= s.length()) return true;
        for(int i=start+1;i<=s.length();i++){
            String tmp = s.substring(start, i);
            if(wordDict.contains(tmp) && backtracking(s, wordDict, i)){
                return true;
            }
        }
        return false;
    }
}

// 2.recursion with memorization
// record the res of subproblems
// With memoization many redundant subproblems are avoided and recursion tree is pruned and thus it reduces the time complexity by a large factor.
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        Boolean[] memory = new Boolean[s.length()]; // note Boolean!
        return backtracking(memory, s, words, 0);
    }
    
    private boolean backtracking(Boolean[] memory, String s, Set<String> wordDict, int start){
        if(start >= s.length()) return true;
        if(memory[start] != null) return memory[start];
        for(int i=start+1;i<=s.length();i++){
            String tmp = s.substring(start, i);
            if(wordDict.contains(tmp) && backtracking(memory, s, wordDict, i)){
                memory[start] = true;
                return true;
            }
        }
        memory[start] = false;
        return false;
    }
}

// 3.BFS
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()];
        queue.add(0);
        while(!queue.isEmpty()){
            int start = queue.remove();
            if(visited[start] == 0){
                for(int j=start+1;j<=s.length();j++){
                    if(words.contains(s.substring(start, j))){
                        queue.add(j);
                        if(j >= s.length()) return true;
                    }
                }
            }
            visited[start] = 1;
        }
        return false;
    }
}

// 4.dp
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i=1;i<=s.length();i++){
            for(int j=0;j<i;j++){
                if(dp[j] && words.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}






