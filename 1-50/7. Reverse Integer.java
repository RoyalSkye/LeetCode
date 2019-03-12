// 1. Wrong
// class Solution {
//     public int reverse(int x) {
//         int ans = 0, temp = x, weight = 1;
//         if(x<Integer.MIN_VALUE || x>Integer.MAX_VALUE) return 0;
//         while((temp/10)!=0){
//             temp /= 10;
//             weight *= 10;
//         }
//         temp = x;
//         while(temp!=0){
//             int remainder = temp % 10;  //remainder is negative.
//             ans += weight * remainder;
//             weight /= 10;
//             temp /= 10;
//         }
//         return ans;
//     }
// }

// 2. if the input number can overflow(eg:input “8463847412” since -2147483648 - 1 = 2147483647), error
// it's not a perfect solution to check whether a number overflows or not. 
// eg: check 2147483648 overflows: the final step: 2147483640 + 8 = -2147483648, then (-2147483648 - 8) / 10 = 2147483640 / 10 =  214748364
class Solution {
    public int reverse(int x) {
        int ans = 0, newans = 0;
        while(x!=0){
            int remainder = x % 10;
            newans = ans * 10 + remainder;
            if((newans - remainder)/10 != ans) return 0;  //overflow
            ans = newans;
            x = x/10;
        }
        return ans;
    }
}

// 3. perfect solution
class Solution {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}