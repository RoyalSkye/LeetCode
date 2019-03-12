// 1.my solution - O(NlogN)
class Solution {
    public int removeDuplicates(int[] nums) {
        int ans = nums.length;
        for(int i=0;i<nums.length-1;i++){
            if(nums[i] == nums[i+1]){
                nums[i] = Integer.MAX_VALUE;
                ans--;
            } 
        }
        Arrays.sort(nums);
        return ans;
    }
}

// 2.Two Pointers - O(N)
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
        }
        return i + 1;
    }
}