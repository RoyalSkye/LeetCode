// 1.my solution
class Solution {
    public void nextPermutation(int[] nums) {
        int maxindex = nums.length - 1, minindex = 0;
        while(maxindex > 0){
            if(nums[maxindex] <= nums[maxindex - 1]){
                maxindex--;
            }else{
                minindex = maxindex - 1;
                break;
            }
        }
        if(maxindex == 0){  //arrangement is not possible
            Arrays.sort(nums);
            return;
        }
        int pos = 0, val = Integer.MAX_VALUE;
        for(int i=nums.length-1;i>=maxindex;i--){
            if(nums[i] > nums[minindex] && val > (nums[i]-nums[minindex])){
                val = nums[i]-nums[minindex];
                pos = i;
            }
        }
        int tmp = nums[minindex];
        nums[minindex] = nums[pos];
        nums[pos] = tmp;
        Arrays.sort(nums, maxindex, nums.length);
    }
}

// 2.optimization - ele at the right of nums[minindex] - descending
class Solution {
    public void nextPermutation(int[] nums) {
        int maxindex = nums.length - 1, minindex = 0;
        while(maxindex > 0){
            if(nums[maxindex-1] < nums[maxindex]){
                minindex = maxindex - 1;
                break;
            }else maxindex--;
        }
        //arrangement is not possible
        if(maxindex == 0){  
            reverse(nums, 0);
            return;
        }
        //arrangement is possible
        int pos = nums.length - 1;
        while(pos >= maxindex){
            if(nums[pos] > nums[minindex]) break;
            pos--;
        }
        swap(nums, minindex, pos);
        reverse(nums, maxindex);
    }
    
    private void reverse(int[] nums, int start){
        int end = nums.length - 1;
        while(start < end) swap(nums, start++, end--);
    }
    private void swap(int nums[], int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}