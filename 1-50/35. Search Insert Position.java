// 1.my solution - binary search - O(logN)
class Solution {
    public int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        if(target <= nums[lo]) return 0;
        if(target > nums[hi]) return hi + 1;
        while(lo < hi){
            int mid = lo + (hi - lo)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) hi = mid - 1;
            else lo = mid + 1;
        }
        if(target <= nums[lo]) return lo;
        else return lo + 1;
    }
}