// 1.my solution - worst case - O(N)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1,-1};
        if(nums.length < 1) return ans;
        if(nums.length == 1) return nums[0] == target ? new int[]{0,0} : ans;
        int low = 0, high = nums.length - 1;
        if(nums[low] == target && nums[high] == target) return new int[]{low, high};
        while(low <= high){
            int mid = low + (high - low)/2;
            if(nums[mid] == target){
                int left = mid, right = mid;
                while(left<nums.length-1){
                    if(nums[left]!=nums[left+1]) break;
                    left++;
                }
                while(right>0){
                    if(nums[right]!=nums[right-1]) break;
                    right--;
                }
                return new int[]{right,left};
            }else if(nums[mid] > target){
                high = mid - 1;
            }else{ // nums[mid] < target
                low = mid + 1;
            }
        }
        return ans;
    }
}

// 2.two binary search - O(logN)
class Solution {
// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/14699/Clean-iterative-solution-with-two-binary-searches-(with-explanation)
// search in leftmost target:
// 1.If A[mid] < target, then the range must begins on the right of mid (hence low = mid+1 for the next iteration)
// 2.If A[mid] > target, it means the range must begins on the left of mid (high = mid-1)
// 3.If A[mid] = target, then the range must begins on the left of or at mid (high = mid)
// Since we would move the search range to the same side for case 2 and 3 ==> high = mid
// the mid always biased towards the left(low) eg: 0,1->0; 1,2->1; so the while loop will end eventually
// the same algorithm for searching rightmost target(prerequisite: we can find the target in the last search)
// but this time, we are at the risk that low gets stuck(3,4->3;4,5->4), which means the while loop doesn't end at all
// thus make sure mid towards high: mid = low + (high - low)/2 + 1
    
    public int[] searchRange(int[] nums, int target) {
        int[] ans = {-1,-1};
        if(nums.length < 1) return ans;
        if(nums.length == 1) return nums[0] == target ? new int[]{0,0} : ans;
        //find the leftmost target
        int low = 0, high = nums.length - 1;
        while(low<high){ 
            int mid = low + (high - low)/2;
            if(nums[mid] < target) low = mid + 1;
            else high = mid;
        }
        if(nums[low] == target) ans[0] = low; 
        else return ans;
        //find the rightmost target, low starts from the leftmost target pos
        high = nums.length - 1;
        while(low<high){  
            int mid = low + (high - low)/2 + 1;
            if(nums[mid] > target) high = mid - 1;
            else low = mid;
        }
        ans[1] = nums[high] == target ? high : high - 1;  // we have already make sure that rightmost part contains target
        return ans;
    }
}