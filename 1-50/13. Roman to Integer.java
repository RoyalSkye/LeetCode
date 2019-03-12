// 1.my solution
class Solution {
    public int romanToInt(String s) {
        int res = 0, i = 0;
        Map<String,Integer> map = new HashMap<>();
        map.put("I",1);map.put("V",5);map.put("X",10);map.put("L",50);map.put("C",100);map.put("D",500);map.put("M",1000);
        while(i<s.length()) {
            int start = map.get(s.substring(i,i+1));
            int end = i+1 < s.length() ? map.get(s.substring(i+1,i+2)) : start;  //check out of bounds
            if(start < end) {
                res += end - start;
                i += 2;
            } else {
                res += start;
                i++;
            } 
        }
        return res;
    }
}