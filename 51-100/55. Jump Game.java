// This is a dynamic programming question. Usually, solving and fully understanding a dynamic programming problem is a 4 step process:
// 1. Start with the recursive backtracking solution
// 2. Optimize by using a memoization table (top-down dynamic programming)
// 3. Remove the need for recursion (bottom-up dynamic programming)
// 4. Apply final tricks to reduce the time / memory complexity

// 1.my solution - O(N)
class Solution {
    public boolean canJump(int[] nums) {
        if(nums.length == 0) return false;
        int end = 0, tmp = 0, max = 0;
        for(int i=0;i<nums.length;i++){
            tmp = i + nums[i];
            max = tmp > max ? tmp : max;
            if(i == end){
                if(max == i){
                    if(i == nums.length - 1) return true;
                    else return false;
                }else{
                    end = max;
                } 
            }
        }
        return true; 
    }
}
// or
class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        for(int i=0;i<nums.length;i++){
            if(i > max) return false;
            max = Math.max(i + nums[i], max);
            if(max >= nums.length-1) return true;
        }
        return false;
    }
}

// 2.Greedy - Coooooooooool
class Solution {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}

