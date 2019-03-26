// 1.two-pass algorithm
// Time: O(N) ~2*N Space: O(1)
class Solution {
    public void sortColors(int[] nums) {
        int count0 = 0, count1 = 0, count2 = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] == 0) count0++;
            else if(nums[i] == 1) count1++;
            else count2 ++;
        }
        for(int j=0;j<count0;j++) nums[j] = 0;
        for(int j=count0;j<count0+count1;j++) nums[j] = 1;
        for(int j=count0+count1;j<nums.length;j++) nums[j] = 2;
    }
}

// 2.one-pass algorithm
// Time: O(N) ~N Space: O(1)
// be careful: i<=end and i-- in swap(nums, i--, end--)
class Solution {
    public void sortColors(int[] nums) {
        int begin = 0, end = nums.length - 1;
        for(int i=0;i<=end;i++){
            if(nums[i] == 0){
                swap(nums, i, begin++);
            }
            if(nums[i] == 2){
                swap(nums, i--, end--);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// 3.tricky solution - wow
class Solution {
    void sortColors(int A[], int n) {
        int n0 = -1, n1 = -1, n2 = -1;
        for (int i = 0; i < n; ++i) {
            if (A[i] == 0) 
            {
                A[++n2] = 2; A[++n1] = 1; A[++n0] = 0;
            }
            else if (A[i] == 1) 
            {
                A[++n2] = 2; A[++n1] = 1;
            }
            else if (A[i] == 2) 
            {
                A[++n2] = 2;
            }
        }
    }
}
