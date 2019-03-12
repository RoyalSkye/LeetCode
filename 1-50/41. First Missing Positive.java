// 1.取相反数 O(N) (4N)
class Solution {
    public int firstMissingPositive(int[] nums) {
        int count = 0, ans = -1;
        for(int i=0;i<nums.length;i++){
            if(nums[i] == 1 ){
                count++;
                break;
            } 
        }
        if(count == 0) return 1;
        for(int i=0;i<nums.length;i++){
            if(nums[i] <= 0 || nums[i] > nums.length) nums[i] = 1;
        }
        for(int i=0;i<nums.length;i++){
            int a = Math.abs(nums[i]);
            if(a == nums.length){
                nums[0] = -1*nums[0];
                continue;
            } 
            if(nums[a] > 0) nums[a] = -1*nums[a];
        }
        for(int i=1;i<nums.length;i++){
            if(nums[i] > 0){
                ans = i;
                break;
            }
        }
        if(ans != -1) return ans;
        else if(ans == -1 && nums[0] > 0) return nums.length;
        else return nums.length+1;
    }
}

// 2.swap - O(N) (2N)
class Solution {
    public int firstMissingPositive(int[] nums) {
        for(int i=0;i<nums.length;i++){
            while(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]){  //be careful, nums[i] != i+1 - may cause endless loop eg:[1,1]
                int tmp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[tmp - 1] = tmp;  //be careful, nums[nums[i] - 1] nums[i] has been already changed
            }
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=i+1) return i+1;
        }
        return nums.length+1;
    }
}
