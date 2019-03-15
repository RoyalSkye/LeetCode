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