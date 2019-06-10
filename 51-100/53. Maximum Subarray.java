// 1.my O(N^2) solution
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int n = nums.length;
        for(int i=0;i<n;i++){
            int init = nums[i];
            ans = ans < init ? init : ans;
            if(init < 0) continue;  //negetive + number < 0 + number
            for(int j=i+1;j<n;j++){
                init += nums[j];
                if(init < 0) break;
                ans = ans < init ? init : ans;
            }
        }
        return ans;
    }
}

// 2.Dynamic Programming - O(N) - perfect and straightforward
// maxSubArray(A, i) = maxSubArray(A, i - 1) > 0 ? maxSubArray(A, i - 1) + A[i] : 0 + A[i]
class Solution {
    public int maxSubArray(int[] A) {
        int n = A.length;
        int[] dp = new int[n];//dp[i] means the maximum subarray ending with A[i];
        dp[0] = A[0];
        int max = dp[0];
        for(int i = 1; i < n; i++){
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

// or (the same way) Kadane's algorithm - inspired by lc152
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums.length == 0) return 0;
        int ans = nums[0], max = nums[0];
        for(int i=1;i<nums.length;i++){
            max = Math.max(max+nums[i], nums[i]);
            ans = Math.max(ans, max);
        }
        return ans;
    }
}

// 3.divide and conquer - maybe O(NlogN)
// any contiguous subarray A[i...j 􏰀of A[low...high􏰀] must lie in exactly one of the following places:
// entirely in the subarray A[low, mid], so that low 􏰎 i 􏰎<= j 􏰎<= mid, 
// entirely in the subarray A[mid+1, high]􏰀, so that mid < i <= 􏰎j <= 􏰎high, 
// or crossing the midpoint, so that low 􏰎<= i 􏰎<= mid < j 􏰎<= high.
 

// FIND-MAXIMUM-SUBARRAY(A, low, high)
// if high == low
// return (low, high, A[])
// else mid =(low + high)/2
// (left-low, left-high, left-sum) = FIND-MAXIMUM-SUBARRAY(A, low, mid)
// (right-low, right-high, right-sum) = FIND-MAXIMUM-SUBARRAY(A, mid+1, high)
// (cross-low cross-high, cross-sum) = FIND-MAX-CROSSING-SUBARRAY(A, low ,mid, high)
// if left-sum >= right-sum and left-sum 􏰃>= cross-sum
// return (left-low, left-high, left-sum)
// elseif right-sum 􏰃>= left-sum and right-sum 􏰃>= cross-sum
// return (right-low, right-high, right-sum)
// else return (cross-low, cross-high, cross-sum)

// 4.Greedy - cannot understand
class Solution {
public:
    int maxSubArray(int A[], int n) {
        int sum = 0, min = 0, res = A[0];
        for(int i = 0; i < n; i++) {
            sum += A[i];
            if(sum - min > res) res = sum - min;
            if(sum < min) min = sum;
        }
        return res;
    }
};
