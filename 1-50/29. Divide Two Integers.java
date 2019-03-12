// 1.my solution - 2361ms
// have bugs: -1*Integer.MIN_VALUE = Integer.MIN_VALUE, and when divisor < 0 cause overflow.
class Solution {
    public int divide(int dividend, int divisor) {
        if(dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        int count = 0, weight = 1;
        if(dividend < 0){
            dividend = -1*dividend;
            weight *= -1;
        }
        if(divisor < 0){
            divisor = -1*divisor;
            weight *= -1;
        } 
        while(dividend-divisor >= 0){
            dividend -= divisor;
            count++;
        }
        return weight*count;
    }
}

// 2.bit operation - 15ms
class Solution {
    public int divide(int dividend, int divisor) {
        int sign = (dividend<0)^(divisor<0) ? -1 : 1, quotient = 0;
        if(dividend == Integer.MIN_VALUE){
            if(divisor == -1) return Integer.MAX_VALUE;
            if(divisor == Integer.MIN_VALUE) return 1;
            dividend += Math.abs(divisor);
            quotient++;
        }
        if(divisor == Integer.MIN_VALUE) return 0;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor); //Math.abs(-2147483648)=-2147483648
        while(dividend >= divisor){
            int tmp = divisor, count = 1;
            while(dividend - tmp >= tmp){ //(dividend >= tmp<<1 or >= 2*tmp) may cause overflows
                tmp <<= 1;
                count <<= 1;
            }
            dividend -= tmp;
            quotient += count;
        }
        return sign * quotient;
    }
}