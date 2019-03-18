// 1.这道题什么鬼？？
class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int length = s.length(), ans = 0;
        for(int i=length-1;i>=0;i--){
            if(s.charAt(i) ==' ') break;
            ans++;
        }
        return ans;
    }
}
// or
class Solution {
	public int lengthOfLastWord(String s) {
	    return s.trim().length()-s.trim().lastIndexOf(" ")-1;
	}
}
