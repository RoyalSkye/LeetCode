// 1.my solution - mess...
class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        boolean overflow = false;
        if(n == Integer.MIN_VALUE){
            n = Integer.MAX_VALUE;
            overflow = true;
        }
        int weight = n < 0 ? -1 : 1;
        n = Math.abs(n);
        Map<Integer, Double> map = new HashMap<>();
        double ans = x;
        int power = 1; 
        n -= 1;
        map.put(1, x); //x^(2^n)
        // x^1, x^2, x^4, x^8, x^16 ...
        while(n - power >= power){
            power *= 2;
            x *= x;
            map.put(power, x);
            n -= power;
            ans *= x;
        }
        while(n > 0){
            int tmp = 1;
            while(tmp*2 <= n){
                tmp *= 2; 
            }
            n -= tmp;
            ans *= map.get(tmp);
        }
        if(overflow) return 1/(ans*map.get(1)); // n == Integer.MIN_VALUE
        return weight == -1 ? 1/ans : ans;
    }
}

// 2.Fast Power Algorithm Recursive - O(logN) for both time and space complexity - perfect and readable
class Solution {
    public double myPow(double x, int n) {
        if(n < 0){
            x = 1/x;
            if(n == Integer.MIN_VALUE) {
                n = Integer.MAX_VALUE;
                x *= x;
            }else n = -n;
        } 
        return fastPow(x, n);
    }
    
    private double fastPow(double x, int n){
        if(n == 0) return 1.0;
        double half = fastPow(x, n/2);
        if(n%2 == 0){
            return half*half;
        }else{
            return half*half*x;
        }
    }
}

// 3.Fast Power Algorithm Iterative - time O(logN) space O(1)
class Solution {
    public double myPow(double x, int n) {
        double ans = 1;
        if(n < 0){
            x = 1/x;
            if(n == Integer.MIN_VALUE) {
                n = Integer.MAX_VALUE;
                x *= x;
            }else n = -n;
        } 
        double cur = x;
        for(int i=n;i>0;i/=2){
            if(i%2 == 1) ans *= cur;
            cur *= cur;
        }
        return ans;
    }
}