// 1.brute force - O(N^2)
class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            int product = 1;
            for(int j=i;j<nums.length;j++){
                product *= nums[j];
                max = Math.max(max, product);
            }
        }
        return max;
    }
}

// 2.dp - O(N)
class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;
        int tmpmax = nums[0], tmpmin = nums[0], ans = nums[0];
        int max, min;
        for(int i=1;i<nums.length;i++){
            max = Math.max(Math.max(tmpmax*nums[i], tmpmin*nums[i]), nums[i]);
            min = Math.min(Math.min(tmpmax*nums[i], tmpmin*nums[i]), nums[i]);
            ans = Math.max(ans, max);
            tmpmax = max;
            tmpmin = min;
        }
        return ans;
    }
}

// or
class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;
        int r = nums[0];
        // imax/imin stores the max/min product of subarray that ends with the current number A[i]
        for (int i = 1, imax = r, imin = r; i < n; i++) {
            if (nums[i] < 0) swap(imax, imin);
            imax = max(nums[i], imax * nums[i]);
            imin = min(nums[i], imin * nums[i]);
            r = max(r, imax);
        }
        return r;
    }
}