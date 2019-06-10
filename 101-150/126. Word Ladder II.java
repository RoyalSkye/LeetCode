// 1.BFS + DFS
class Solution {
    //BFS to construct adjacent graph + DFS to trace path
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new LinkedList<>();
        Set<String> words = new HashSet<>(wordList);
        if(!words.contains(endWord)) return ans;
        Map<String, List<String>> map = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int min = Integer.MAX_VALUE;
        int step = 0;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        boolean flag = false;
        while(!queue.isEmpty()){
            int size = queue.size();
            step++;
            if(step > min) break;
            List<String> l3 = new LinkedList<>(); //l3 is ok
            while(size>0){
                String s = queue.remove();
                List<String> neighbors = generate(s, words, visited);
                // System.out.println(neighbors);
                for(String neighbor : neighbors){
                    l3.add(neighbor);
                    if(neighbor.equals(endWord)){
                        min = step;
                        flag = true;
                    }
                    List<String> l2 = map.getOrDefault(neighbor, new LinkedList<String>());
                    l2.add(s);
                    map.put(neighbor, l2);
                }
                size--;
            }
            for(String s : l3){
                if(!visited.contains(s)){
                    queue.add(s); 
                    visited.add(s);
                } 
            }
            // for(String neighbor : neighbors){
            //     if(!visited.contains(neighbor)){
            //         queue.add(neighbor); 
            //         visited.add(neighbor);
            //     } 
            // }
            // System.out.println(map);
            if(flag) break;
        }
        List<String> l = new LinkedList<String>();
        l.add(endWord);
        backtracking(endWord, beginWord, l, ans, map);
        return ans;
    }
    
    private List<String> generate(String s, Set<String> words, Set<String> visited){
        List<String> res = new LinkedList<>();
        char[] chs = s.toCharArray();
        for(int i=0;i<chs.length;i++){
            char old = chs[i];
            for(char c='a';c<='z';c++){
                if(c==old) continue;
                chs[i] = c;
                String tmp = String.valueOf(chs);
                if(words.contains(tmp) && !visited.contains(tmp)) res.add(tmp);
            }
            chs[i] = old;
        }
        return res;
    }
    
    private void backtracking(String end, String start, List<String> l, List<List<String>> ans, Map<String, List<String>> map){
        if(end.equals(start)){
            ans.add(new LinkedList<String>(l));
            return;
        }
        List<String> tmp = map.get(end);
        if(tmp!=null){
            for(String s : tmp){
                l.add(0, s);
                backtracking(s, start, l, ans, map);
                l.remove(0);
            }
        }
    }
}