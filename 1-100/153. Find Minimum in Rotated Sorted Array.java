// binary search
class Solution {
    public int findMin(int[] nums) {
        int ans = 0, low = 0, high = nums.length - 1;
        if(nums.length == 1 || nums[low] < nums[high]) return nums[0];
        while(low<=high){
            int mid = low+(high-low)/2;  //(low+high)/2 can cause overflow
            if(nums[mid] > nums[mid+1]) return nums[mid+1];
            if(nums[0] <= nums[mid]) low = mid + 1;
            else if(nums[0] > nums[mid]) high = mid - 1;
        }
        return nums[ans];
    }
}