// 1.Sliding Window
// https://leetcode.com/problems/minimum-window-substring/discuss/26808/Here-is-a-10-line-template-that-can-solve-most-'substring'-problems
class Solution {
    public String minWindow(String s, String t) {
        if(t.length() == 0 || s.length() == 0) return "";
        int[] arr = new int[128];
        int counter = t.length(), length = Integer.MAX_VALUE, left = 0, right = 0, head = 0;
        for(int i=0;i<t.length();i++) arr[t.charAt(i)]++;
        while(right < s.length()){
            char c = s.charAt(right++);
            if(arr[c] > 0) counter--;
            arr[c]--;
            while(counter == 0){
                if((right - left) < length){  //error: right - left + 1, since right has been increased(right++)
                    length = right - left;
                    head = left;
                }
                char cc = s.charAt(left++);
                if(arr[cc] == 0) counter++;
                arr[cc]++;
            }
        }
        return length == Integer.MAX_VALUE ? "" : s.substring(head, head+length);
    }
}