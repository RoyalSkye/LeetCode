// 1. my solution - find the smallest ele -O(N) + binary search - O(logN)
class Solution {
    public int search(int[] nums, int target) {  // binary search - o(logN)
        int ans = -1, low = 0, high = nums.length - 1;
        while(low <= high){
            int mid = (low + high)/2;
            if(target == nums[mid]) return mid;
            if(nums[low] > nums[high]){ // 4 5 6 7 0 1 2
                int i = 0;
                for(i=nums.length-1;i>=0;i--){
                    if(nums[i] > nums[high]) break;
                }
                if(target > nums[high]){
                    high = i;
                }else{
                    low = i + 1;
                }    
            }else{ // sorted - ascending order
                if(nums[mid] > target) high = mid - 1;
                else low = mid + 1;
            }
        }
        return ans;
    }
}

// 2. both O(logN)
class Solution {
    public int search(int[] nums, int target) {  // binary search - o(logN)
        if(nums.length==0) return -1;
        if(nums.length==1) return nums[0] == target ? 0 : -1;
        int ans = -1, low = 0, high = nums.length - 1;
        int rotate = find_rotate_index(nums); // rotate is the smallest element in the given array
        if(rotate == 0){   //array not rotate
            // do nothing
        }else if(nums[0] < target) high = rotate - 1;
        else if(nums[0] > target) low = rotate;
        else return 0;
        while(low <= high){
            int mid = low+(high-low)/2;
            if(nums[mid] == target) return mid;
            else if(nums[mid] > target) high = mid - 1;
            else low = mid + 1;
        }
        return ans;
    }
    
    private int find_rotate_index(int[] nums){  //the length of nums >=2
        int rotate = 0;
        int low = 0, high = nums.length -1;
        if(nums[low] < nums[high]) return 0;
        while(low <= high){
            int mid = low+(high-low)/2;
            if(nums[mid] > nums[mid+1]) return mid + 1;
            else{
                if(nums[mid] < nums[0]) high = mid - 1;
                else low = mid + 1;
            }
        }
        return rotate;
    }
}