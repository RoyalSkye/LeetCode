class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        backtracking(candidates,ans,new ArrayList<Integer>(), target,0,0);
        return ans;
    }
    
    private void backtracking(int[] candidates, List<List<Integer>> ans, List<Integer> tmp, int target, int sum, int curr){
        if(sum > target) return;
        if(sum == target){
            ans.add(new ArrayList<Integer>(tmp));
            return;
        }
        for(int i=curr;i<candidates.length;i++){
            tmp.add(candidates[i]);
            backtracking(candidates, ans, tmp, target, sum+candidates[i], i);
            tmp.remove(tmp.size()-1);
        }
    }
}