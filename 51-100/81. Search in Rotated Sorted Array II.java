// 1.worst case - O(N)
class Solution {
    public boolean search(int[] nums, int target) {
        boolean ans = false;
        int low = 0, high = nums.length - 1;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(nums[mid]==target) return true;
            if(nums[low] < nums[mid] || nums[mid] > nums[high]){  // left part is sorted
                if(nums[mid] > target && nums[low] <= target) high = mid - 1;
                else low = mid + 1;
            }else if(nums[mid] < nums[high] || nums[mid] < nums[low]){ // right part is sorted
                if(nums[mid] < target && target <= nums[high]) low = mid + 1;
                else high = mid - 1;
            }else{  //nums[low] = nums[mid] = nums[high]
                low++;high--;
            }
        }
        return ans;
    }
}