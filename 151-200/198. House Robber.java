// 1.dp
class Solution {
    // dp:f(k) = max(f(k-1), f(k-2)+nums[k])
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length==1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for(int i=2;i<nums.length;i++){
            int tmp = first;
            first = second;
            second = Math.max(tmp + nums[i], first);
        }
        return second;
    }
}