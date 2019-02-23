// 1.Two Pointers
class Solution {
    public int removeElement(int[] nums, int val) {
        int i=0;
        for(int j=0;j<nums.length;j++){
            if(nums[j]!=val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}

// Now consider cases where the array contains few elements to remove. 
// For example, nums=[1,2,3,5,4],val=4. The previous algorithm will do unnecessary copy operation of the first four elements. Another example is 
// nums=[4,1,2,3,5],val=4. It seems unnecessary to move elements [1,2,3,5] one step left as the problem description mentions that the order of elements could be changed.

// 2.Two Pointers - more efficient when elements to remove are rare
class Solution {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while(i < n){
            if(nums[i] == val){
                nums[i] = nums[n - 1];
                n--;
            }else{
                i++;
            }
        }
        return n;
    }
}