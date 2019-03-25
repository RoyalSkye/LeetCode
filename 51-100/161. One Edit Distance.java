// 1.my solution
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int l1 = s.length();
        int l2 = t.length();
        if(Math.abs(l1-l2) > 1 || s.equals(t)) return false;
        int cur = 0, cur1 = 0, count = 0;
        if(l1 == l2){  //replace
            while(cur < l1){
                if(s.charAt(cur) != t.charAt(cur)){
                    count++;
                    if(count > 1) return false;
                }  
                cur++;
            }
        }else{  //l1 = l2 - 1
            String s1 = s, t1 = t;
            if(l1 > l2){
                s1 = t;
                t1 = s;
            }
            while(cur < Math.min(l1, l2)){
                if(s1.charAt(cur) != t1.charAt(cur1)){
                    count++;
                    if(count > 1) return false;
                    cur1++;
                }else{
                    cur++;
                    cur1++;
                }
            }
        }
        return true;
    }
}

// 2.concise
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int l1 = s.length();
        int l2 = t.length();
        if(Math.abs(l1-l2) > 1 || s.equals(t)) return false;
        if(l1 > l2) return isOneEditDistance(t, s); //make sure String s is the smaller one.
        for(int i=0;i<l1;i++){  //l1 + 1 = l2 or l1 = l2
            if(s.charAt(i) != t.charAt(i)){
                if(l1 == l2) return s.substring(i+1).equals(t.substring(i+1));
                else return s.substring(i).equals(t.substring(i+1));
            }
        }
        return true;  //eg: ab abc
    }
}