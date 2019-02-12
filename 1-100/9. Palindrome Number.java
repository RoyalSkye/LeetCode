// 1. my solution
class Solution {
    public boolean isPalindrome(int x) {
        boolean ans = false;
        int reverse = 0, temp = x;
        if(x < 0) return false;
        while(temp > 0){
            int remainder = temp % 10;
            reverse = reverse*10 + remainder;
            temp = temp/10;
        }
        if(reverse == x) ans = true;
        return ans;
    }
}

// 2. Revert half of the number 
public class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNumber = 0;
        while(x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        //4321 -> revertedNumber = 123, x = 4
        //1221 -> revertedNumber = 12, x = 12
        return x == revertedNumber || x == revertedNumber/10;
    }
}