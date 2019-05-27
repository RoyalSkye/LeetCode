// 1.my solution - 45ms
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        boolean res = true;
        String s1 = s.toLowerCase();
        String regex = "^[a-z0-9]";
        while(left <= right){
            if(!s1.substring(left, left+1).matches(regex)) {
                left++;
                continue;
            }
            if(!s1.substring(right, right+1).matches(regex)){
                right--;
                continue;
            }
            if(s1.charAt(left++) != s1.charAt(right--)){
                res = false;
                break;
            }
        }
        return res;
    }
}

// 2.5ms
class Solution {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) return true;
        String s1 = s.toLowerCase();
        for(int i = 0, j = s1.length()-1;i<j;i++,j--){
            while(i<j && !Character.isLetterOrDigit(s1.charAt(i))) i++;
            while(i<j && !Character.isLetterOrDigit(s1.charAt(j))) j--;
            if(s1.charAt(i) != s1.charAt(j)) return false;
        }
        return true;
    }
}