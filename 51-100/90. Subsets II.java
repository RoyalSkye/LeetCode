// 1.backtracking
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(ans, new ArrayList(), nums, 0);
        return ans;
    }
    
    private void backtracking(List<List<Integer>> ans, List<Integer> tmp, int[] nums, int start){
        ans.add(new ArrayList(tmp));
        for(int i=start;i<nums.length;i++){
            if(i>start && nums[i] == nums[i-1]) continue;
            tmp.add(nums[i]);
            backtracking(ans, tmp, nums, i+1);
            tmp.remove(tmp.size()-1);
        }
    }
}