// Bidirectional Breadth First Search
// (1)transfer List<String> wordList to hashset, reduce time from 938ms to 18ms
// list.contains will iterate each ele in the list and call equals(), while hashset: hashcode() and equals()
// (2)BFS: we need the shortest path from one word to another and DFS is not suitable for finding the shortest path
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordList.contains(endWord)) return 0;
        int ans = 1;
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        Set<String> visited = new HashSet<>();
        while(!beginSet.isEmpty() && !endSet.isEmpty()){
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            Set<String> newbeginSet = new HashSet<String>();
            for(String word : beginSet){
                char[] chs = word.toCharArray();
                for(int i=0;i<chs.length;i++){
                    for(char c='a';c<='z';c++){
                        char old = chs[i];
                        chs[i] = c;
                        String target = String.valueOf(chs);
                        if(endSet.contains(target)) return ans+1;
                        if(!visited.contains(target) && wordSet.contains(target)){
                            visited.add(target);
                            newbeginSet.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }
            beginSet = newbeginSet;
            ans++;
        }
        return 0;
    }
}