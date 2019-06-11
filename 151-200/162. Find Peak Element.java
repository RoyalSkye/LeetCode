// 1.O(N)
class Solution {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if(len==1 || nums[0]>nums[1]) return 0;
        if(nums[len-1]>nums[len-2]) return len-1;
        for(int i=1;i<len-1;i++){
            if(nums[i]>nums[i-1] && nums[i]>nums[i+1]) return i;
        }
        return -1;
    }
}

// or
class Solution {
    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return i;
        }
        return nums.length - 1;
    }

// 2.Binary Search - O(logN)
class Solution {
    public int findPeakElement(int[] nums) {
        int low = 0, high = nums.length-1, mid = 0;
        while(low < high){
            mid = low + (high-low)/2;
            if(nums[mid] > nums[mid+1]) high = mid;
            else low = mid+1;
        }
        return low;
    }
}