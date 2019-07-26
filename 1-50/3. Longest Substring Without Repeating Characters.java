class Solution {
    public int lengthOfLongestSubstring(String s) {
        int result = 0, begin = 0, end = 0;
        int len = s.length();
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        char[] c = s.toCharArray();
        for(int i=0;i<len;i++){
            if(map.containsKey(c[i]) && map.get(c[i])>=begin){
                end = i-1;
                result = result>=(end-begin+1) ? result : end-begin+1;
                begin = map.get(c[i])+1;
            } 
            map.put(c[i],i);
        }
        result = result>=(len-begin) ? result : len-begin;
        return result;
    }
}

// the same approach, diff style
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int start = 0, end = 0, max = 0;
        char[] c = s.toCharArray();
        while(end < s.length()){
            if(map.containsKey(c[end]) && map.get(c[end]) >= start){
                max = Math.max(max, end - start);
                start = map.get(c[end]) + 1;
            }
            map.put(c[end], end);
            end++;
        }
        return Math.max(max, end-start);
    }
}