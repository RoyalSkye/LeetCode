// 1.next permutation - O(N*N!)
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {
        if(nums.length == 0 || nums.length == 1){
            List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
            ans.add(list);
            return ans;
        } 
        while(true){
            List<Integer> list = nextPermutation(nums);
            if(ans.contains(list)) break;
            ans.add(list);
        } 
        return ans;
    }
    
    private List<Integer> nextPermutation(int[] nums){ 
        List<Integer> res = new ArrayList<>();
        int maxindex = nums.length - 1, minindex = 0;
        while(maxindex > 0){
            if(nums[maxindex - 1] < nums[maxindex]){
                minindex = maxindex - 1;
                break;
            }
            maxindex--;
        }
        if(maxindex == 0){
            reverse(nums, 0); 
            return Arrays.stream(nums).boxed().collect(Collectors.toList());
        } 
        int pos = nums.length - 1;
        while(pos >= maxindex){
            if(nums[pos] > nums[minindex]) break;
            pos--;
        }
        swap(nums, minindex, pos);
        reverse(nums, maxindex);
        return Arrays.stream(nums).boxed().collect(Collectors.toList());
    }
    private void swap(int[] nums, int i, int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    private void reverse(int[] nums, int start){
        int end = nums.length - 1;
        while(start < end) swap(nums, start++, end--);
    }
}

// 2.backtracking better than O(N*N!), slower than O(N!)
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> output = new LinkedList<>();
        List<Integer> nums_lst = new ArrayList<>();
        for (int num : nums) nums_lst.add(num);
        int n = nums.length;
        backtrack(n, nums_lst, output, 0);
        return output;
    }
    
    private void backtrack(int n,List<Integer> nums,List<List<Integer>> output,int current) {
        if (current == n){
            //output.add(nums); result error
            output.add(new ArrayList<Integer>(nums));
        } 
        for (int i = current; i < n; i++) {
            Collections.swap(nums, current, i);
            backtrack(n, nums, output, current + 1);
            Collections.swap(nums, current, i);
        }
    }
}