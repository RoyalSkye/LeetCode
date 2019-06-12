class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new LinkedList<>();
        int len = nums.length;
        if(len==0){  //test case: [], 1, 1
            helper(ans, lower, upper);
            return ans;
        } 
        if(lower < nums[0]) helper(ans, lower, nums[0]-1);
        for(int i=0;i<len-1;i++){
            // nums[i+1]-nums[i]>=2 will cause overflow. test case:[-2147483648,2147483647]
            if(nums[i]!=nums[i+1] && nums[i]+1!=nums[i+1]) helper(ans, nums[i]+1, nums[i+1]-1);
        }
        if(upper > nums[len-1]) helper(ans, nums[len-1]+1, upper);
        return ans;
    }
    
    private void helper(List<String> ans, int low, int high){
        StringBuilder s = new StringBuilder("");
        s.append(low);
        if(low!=high){
            s.append("->");
            s.append(high);
        }
        ans.add(s.toString());
    }
}