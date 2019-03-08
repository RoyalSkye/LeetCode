// 1.Time Limit Exceeded - mess
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        int count = 0;
        for(int i=0;i<strs.length;i++){
            if(strs[i].length() == 0){ //""
                count++;
            }else group(strs[i], ans, 0);
        }
        if(count != 0){
            List<String> ll = new ArrayList<>();
            while(count-- > 0) ll.add("");
            ans.add(ll);
        }
        return ans;
    }
    
    private void group(String str, List<List<String>> ans, int cur){
        if(cur == ans.size()){
            List<String> ll = new ArrayList<>();
            ll.add(str);
            ans.add(ll);
            return;
        }
        Map<Character, Integer> map = new HashMap<>();
        String tmp = ans.get(cur).get(0);
        for(char c : tmp.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1);
        }
        for(char c : str.toCharArray()){
            if(map.get(c) == null || map.get(c) <= 0 || str.length() != tmp.length()){
                group(str, ans, cur+1);
                return;
            } 
            map.put(c, map.get(c) - 1);
        }
        ans.get(cur).add(str);
    }
}

// 2.By Sorted String - O(NKlogK) - N: num of string in strs K: the maximum length of a string in strs
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List> map = new HashMap<>();
        for(String s : strs){
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);
            if(!map.containsKey(key)) map.put(key, new ArrayList());
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
}

// 3.By Count of each Character - O(NK)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List> map = new HashMap<>();
        for(String s : strs){
            int[] count = new int[26];
            for(char c : s.toCharArray()) count[c - 'a']++;
            StringBuilder tmp = new StringBuilder("");
            for(int i=0;i<26;i++){
                tmp.append(count[i]);
            }
            String key = tmp.toString();
            if(!map.containsKey(key)) map.put(key, new ArrayList());
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
}