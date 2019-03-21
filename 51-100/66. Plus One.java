// 1.my solution
class Solution {
    public int[] plusOne(int[] digits) {
        int m = digits.length;
        int remain = 1;
        for(int i=m-1;i>=0;i--){
            int sum = digits[i] + remain;
            digits[i] = sum % 10;
            remain = sum / 10;
        }
        if(remain == 0) return digits;
        else{ //add one digit
            int[] ans = new int[m+1];
            ans[0] = remain;
            for(int i=1;i<=m;i++){
                ans[i] = digits[i-1];
            }
            return ans;
        }
    }
}

// 2.really smart!!
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] newNumber = new int [n+1];
        newNumber[0] = 1;
        return newNumber;
    }
}