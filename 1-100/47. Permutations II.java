class Solution {
    List<List<Integer>> ans = new LinkedList<>();
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> nums_list = new LinkedList<>();
        for(int num : nums) nums_list.add(num);
        backtracking(nums_list, 0);
        return ans;
    }
    
    private void backtracking(List<Integer> nums_list, int curr){
        if(curr == nums_list.size()){
            ans.add(new ArrayList<Integer>(nums_list));
            return;
        }
        Set<Integer> set = new HashSet<>();
        for(int i=curr;i<nums_list.size();i++){
            if(set.contains(nums_list.get(i))) continue;
            set.add(nums_list.get(i));
            Collections.swap(nums_list,curr,i);
            backtracking(nums_list,curr+1);
            Collections.swap(nums_list,curr,i);
        }
    }
}

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) map.put(num, map.getOrDefault(num,0)+1);
        backtracking(nums.length, map, new ArrayList<Integer>(), ans, 0);
        return ans;
    }
    
    private void backtracking(int len, Map<Integer, Integer> map, List<Integer> tmp, List<List<Integer>> ans, int curr){
        if(curr == len){
            ans.add(new ArrayList<Integer>(tmp));
            return;
        }
        for(int temp : map.keySet()){
            if(map.get(temp) > 0){
                tmp.add(temp);
                map.put(temp, map.get(temp)-1);
                backtracking(len,map,tmp,ans,curr+1);
                map.put(temp, map.get(temp)+1);
                tmp.remove(curr);
            }
        }
    }
}