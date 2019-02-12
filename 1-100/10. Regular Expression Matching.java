// 1.Recursion
class Solution {
    public boolean isMatch(String s, String p) {
        //System.out.println("isMatch("+s+", "+p+")");
        if(p.isEmpty()) return s.isEmpty();
        if(p.charAt(0) == '*') return false;
        //p is non-empty
        boolean firstmatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        
        if(p.length()>=2 && p.charAt(1)=='*'){
            return isMatch(s,p.substring(2)) || (firstmatch && isMatch(s.substring(1),p));
        }else{
            return firstmatch && isMatch(s.substring(1),p.substring(1));
        }
    }
}