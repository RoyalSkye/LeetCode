// 1.backtracking - Time Limit Exceeded - 71 / 92 test cases passed.
class Solution {
    public int jump(int[] nums) {
        return jumptoend(nums, nums.length-1);
    }
    
    private int jumptoend(int[] nums, int end){
        if(end == 0) return 0;
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<end;i++){
            int tmp = Integer.MAX_VALUE;
            if(nums[i] >= end - i){
                tmp = jumptoend(nums, i) + 1; 
            }
            ans = tmp < ans ? tmp : ans;
        }
        return ans;
    }
}
// AC: 111ms - O(N^2)
// based on the mind shown above eg:[2,3,1,1,4] num1 = 3(nums[1]), num2 =1(nums[3])
// we could only check num1 (the longest distance away from the end)
// since both num1 and num2 can reach the end, but num1 is closer to the start point.
// somewhat like the greedy algorithm
class Solution {  //Recursive
    public int jump(int[] nums) {
        return jumptoend(nums, nums.length-1);
    }
    
    private int jumptoend(int[] nums, int end){
        if(end == 0) return 0;
        int ans = Integer.MAX_VALUE;
        for(int i=0;i<end;i++){
            //int tmp = Integer.MAX_VALUE;
            if(nums[i] >= end - i){
                ans = jumptoend(nums, i) + 1; 
                break;
            }
            //ans = tmp < ans ? tmp : ans;
        }
        return ans;
    }
}
class Solution {  //Non-Recursive
    public int jump(int[] nums) {
        int steps = 0, end = nums.length - 1;
        while(end > 0){
            for(int i=0;i<end;i++){
                if(nums[i] >= end - i){
                    steps++;
                    end = i;
                    break;
                }
            }
        }
        return steps;
    }
}

// 2.Greedy - 4ms - O(N) - 每次找局部最优，最后达到全局最优
class Solution {
    public int jump(int[] nums) {
        int ans = 0, curEnd = 0, curFarthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curFarthest = Math.max(curFarthest, i + nums[i]);
            //if(curfarthest >= nums.length-1) return steps+1;
            if (i == curEnd) {
                ans++;
                curEnd = curFarthest;
            }
        }
        return ans;
    }
}