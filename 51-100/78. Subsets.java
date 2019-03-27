// https://leetcode.com/problems/subsets/discuss/27281/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
// https://leetcode.com/problems/subsets/discuss/27278/C%2B%2B-RecursiveIterativeBit-Manipulation

// 1.my solution - backtracking
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>()
        for(int i=0;i<=nums.length;i++){
            List<Integer> tmp = new ArrayList<>();
            backtracking(ans, tmp, nums, i, 0, 0);
        } 
        return ans;
    }
    
    private void backtracking(List<List<Integer>> ans, List<Integer> tmp, int[] nums, int len, int start, int count){
        if(count == len){
            ans.add(new ArrayList(tmp));
            return;
        }
        for(int i=start;i<nums.length;i++){
            tmp.add(nums[i]);
            backtracking(ans, tmp, nums, len, i+1, count+1);
            tmp.remove(tmp.size()-1);
        }
    }
    
}

// 2.backtracking optimization
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        backtracking(ans, new ArrayList(), nums, 0);
        return ans;
    }
    
    private void backtracking(List<List<Integer>> ans, List<Integer> tmp, int[] nums, int start){
        ans.add(new ArrayList(tmp));
        for(int i=start;i<nums.length;i++){
            tmp.add(nums[i]);
            backtracking(ans, tmp, nums, i+1);
            tmp.remove(tmp.size()-1);
        }
    }
}

// 3.Iterative
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList());
        for(int num : nums){
            int n = ans.size();
            for(int i=0;i<n;i++){
                List<Integer> tmp = new ArrayList(ans.get(i));
                tmp.add(num);
                ans.add(tmp);
            }
        }
        return ans;
    }
}

// 4.Bit Manipulation
// To give all the possible subsets, we just need to exhaust all the possible combinations of the numbers. 
// And each number has only two possibilities: either in or not in a subset. And this can be represented using a bit.
// Using [1, 2, 3] as an example, 1 appears once in every two consecutive subsets, 2 appears twice in every four consecutive subsets, 
// and 3 appears four times in every eight subsets (initially all subsets are empty):
// 000, 001, 010, 011, 100, 101, 110, 111 (3->2->1)
// [], [ ], [ ], [    ], [ ], [    ], [    ], [       ]
// [], [1], [ ], [1   ], [ ], [1   ], [    ], [1      ]
// [], [1], [2], [1, 2], [ ], [1   ], [2   ], [1, 2   ]
// [], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length, p = 1 << n;
        for(int i=0;i<p;i++){
            ans.add(new ArrayList());
        }
        for(int i=0;i<p;i++){
            for(int j=0;j<n;j++){
                // System.out.println((i >> j)+" "+((i >> j) & 1));
                if(((i>>j) & 1) != 0){  //whether the (j+1)th bit pos is 1 or not
                    ans.get(i).add(nums[j]);
                }
            }
        }
        return ans;
    }
}
