// 1.backtracking
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new LinkedList<>();
        String tmp = "";
        backtracking(ans, tmp, s, 0, 0, 0);
        return ans;
    }
    
    private void backtracking(List<String> ans, String tmp, String s, int start, int end, int count){
        // System.out.println(start+" "+end+" ====="+tmp);
        if(start >= s.length() || end > s.length() || count > 4) return;
        if((start!=0 || end!=0) && Integer.valueOf(s.substring(start, end)) > 255) return;
        if(end == s.length() && count == 4){
            tmp += s.substring(start, end); 
            ans.add(tmp);
            return;
        }
        if(start != 0 || end != 0) tmp += s.substring(start, end) + "."; 
        if(end < s.length() && s.charAt(end) == '0'){
            backtracking(ans, tmp, s, end, end+1, count+1);
        }else{
            backtracking(ans, tmp, s, end, end+1, count+1);
            backtracking(ans, tmp, s, end, end+2, count+1);
            backtracking(ans, tmp, s, end, end+3, count+1);
        }
    }
}

// 2.three for loop
class Solution {
    public class Solution {
        public List<String> restoreIpAddresses(String s) {
            List<String> res = new ArrayList<String>();
            int len = s.length();
            for(int i = 1; i<4 && i<len-2; i++){
                for(int j = i+1; j<i+4 && j<len-1; j++){
                    for(int k = j+1; k<j+4 && k<len; k++){
                        String s1 = s.substring(0,i), s2 = s.substring(i,j), s3 = s.substring(j,k), s4 = s.substring(k,len);
                        if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)){
                            res.add(s1+"."+s2+"."+s3+"."+s4);
                        }
                    }
                }
            }
            return res;
        }
        public boolean isValid(String s){
            if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
                return false;
            return true;
        }
    }
}