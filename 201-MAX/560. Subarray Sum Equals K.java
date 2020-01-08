// 1.Time: O(N^2) Space: O(1)

//MLE - 58 / 80 test cases passed.
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int[][] dp = new int[nums.length][nums.length];
        for(int i = 0; i < nums.length; i++) {
            for(int j = i; j < nums.length; j++) {
                if (i == j) 
                    dp[i][j] = nums[i];
                else
                    dp[i][j] = dp[i][j-1] + nums[j];
                if (dp[i][j] == k) count++;
            }
        }
        return count;
    }
}

// pass - 119ms
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for(int i = 0; i < nums.length; i++) {
            int sum = 0;
            for(int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }
        return count;
    }
}

// 2.HashMap - Time:O(N) Space:O(N)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}