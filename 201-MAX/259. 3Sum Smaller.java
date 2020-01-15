// 1.two pointers - O(N^2)
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        int ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            ans += twoSumSmaller(nums, i + 1, target - nums[i]);
        }
        return ans;
    }
    
    private int twoSumSmaller(int[] nums, int start, int target) {
        int tmp = 0;
        int left = start, right = nums.length - 1;
        while(left < right) {
            if(nums[left] + nums[right] >= target) {
                right--;
            } else {
                tmp += right - left;
                left++;
            }
        }
        return tmp;
    }
}