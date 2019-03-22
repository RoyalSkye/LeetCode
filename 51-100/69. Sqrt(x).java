// 1.brute force
class Solution {
    public int mySqrt(int x) {
        int ans = 0;
        if(x >= 2147395600) return 46340;
        for(int i=0;;i++){
            if(i*i > x) break;
            ans = i;
        }
        return ans;
    }
}

// 2.Newton's Method - 牛顿迭代法
// 用于在实数域和复述域近似求解方程, 运用到高等数学中泰勒级数等知识
class Solution {
    public int mySqrt(int x) {
        double ans = x;
        double variance = 0.00001;
        // f(X) = X^2-C --> 迭代式: Xn+1 = (Xn+C/Xn)/2
        while(Math.abs(Math.pow(ans, 2) - x) > variance){
            ans = (ans + x/ans)/2;
        }
        return (int)ans;
    }
}

// 3.Binary Search
class Solution {
    public int mySqrt(int x) {
        if(x == 0) return 0;
        int left = 1, right = Integer.MAX_VALUE;
        while(true){
            int mid = left + (right - left)/2;
            // System.out.println(mid);
            if(mid > x/mid) right = mid - 1;
            else{
                if(mid+1 > x/(mid+1)) return mid;
                left = mid + 1;
            }
        }
    }
}
