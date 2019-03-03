// 1.Sort
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new LinkedList<>();
        Arrays.sort(candidates);
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
            if(i>curr && candidates[i-1]==candidates[i]) continue;
            tmp.add(candidates[i]);
            backtracking(candidates, ans, tmp, target, sum+candidates[i], i+1);
            tmp.remove(tmp.size()-1);
        }
    }
}