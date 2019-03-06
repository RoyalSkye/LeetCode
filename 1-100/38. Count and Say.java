// 1.my solution
class Solution {
    public String countAndSay(int n) {
        StringBuilder start = new StringBuilder("1");
        for(int i=1;i<n;i++){
            int j = 0;
            StringBuilder tmp = new StringBuilder("");
            while(j <= start.length()-1){
                int length = 1;
                while(j<start.length()-1 && start.charAt(j)==start.charAt(j+1)){
                    length++;
                    j++;
                }
                tmp.append(length).append(start.charAt(j));
                j++;
            }
            start = tmp;
        }
        return start.toString();
    }
}

// 2.I don't understand
//https://leetcode.com/problems/count-and-say/discuss/15999/4-5-lines-Python-solutions