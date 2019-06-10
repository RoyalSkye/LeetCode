// 1.backtracking - TLE - 31 / 39 test cases passed.
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> ans = new LinkedList<>();
        List<String> word = new LinkedList<>();
        // Boolean[] memory = new Boolean[s.length()];
        Set<String> words = new HashSet<>(wordDict);
        dfs(ans, word, s, words, 0);
        return ans;
    }
    
    private void dfs(List<String> ans, List<String> word, String s, Set<String> words, int start){
        if(start >= s.length()){
            String res = "";
            for(int i=0;i<word.size();i++){
                if(i==0) res += word.get(i);
                else res += " " + word.get(i);
            }
            ans.add(res);
            return;
        }
        // if(memory[i] != null) return memory[i];
        for(int i=start+1;i<=s.length();i++){
            String tmp = s.substring(start, i);
            if(words.contains(tmp)){
                word.add(tmp);
                dfs(ans, word, s, words, i);
                word.remove(word.size()-1);
            }
        }
    }
}

// 2.recursion with memoization - AC
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer, List<String>> map = new HashMap<>();
        Set<String> words = new HashSet<>(wordDict);
        return dfs(map, s, words, 0);
    }
    
    private List<String> dfs(Map<Integer, List<String>> map, String s, Set<String> words, int start){
        if(map.get(start)!=null) return map.get(start);
        List<String> res = new LinkedList<>();
        if(start >= s.length()){
            res.add("");
            return res;
        }
        for(int end=start+1;end<=s.length();end++){
            String tmp = s.substring(start, end);
            if(words.contains(tmp)){
                List<String> ll = dfs(map, s, words, end);
                for(String s1 : ll){
                    res.add(tmp + (s1.equals("") ? "" : " ") + s1);
                }
            }
        }
        map.put(start, res);
        return res;
    }
}

// 3.dp - but TLE - 31 / 39 test cases passed.
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer, List<String>> map = new HashMap<>();
        List<String> l0 = new LinkedList<>();
        l0.add("");
        map.put(0, l0);
        Set<String> words = new HashSet<>(wordDict);
        for(int i=1;i<=s.length();i++){
            List<String> ll = new LinkedList<>();
            for(int j=0;j<i;j++){ // [0, i)
                String tmp = s.substring(j, i);
                if(map.get(j)!=null && words.contains(tmp)){
                    List<String> l2 = map.get(j);
                    for(String s1 : l2) ll.add(s1+(s1.equals("") ? "" : " ")+tmp);
                }
                map.put(i, ll);
            }
        }
        return map.get(s.length());
    }
}