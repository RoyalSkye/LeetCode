//  1.my solution - backtracking - O(kCnk)
// A general approach to backtracking questions in Java (Subsets, Permutations, Combination Sum, Palindrome Partitioning)
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        int[] arr = new int[n];
        for(int i=1;i<=n;i++) arr[i-1] = i;
        dfs(ans, tmp, arr, 0, 0, k);
        return ans;
    }
    
    private void dfs(List<List<Integer>> ans, List<Integer> tmp, int[] arr, int len, int cur, int k){
        if(len == k){
            ans.add(new ArrayList(tmp));  
            return;
        }
        for(int i=cur;i<arr.length;i++){
            tmp.add(arr[i]);
            dfs(ans, tmp, arr, len+1, i+1, k);
            tmp.remove(tmp.size()-1);
        }
    }
}

// 2.the second algorithm is much faster than the first one despite they both have the same time complexity.
// It's a consequence of dealing with the recursive call stack frame for the first algorithm, and the effect is much more pronounced in Python than in Java.
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] arr = new int[k];
        int cur = 0;
        while(cur >= 0){
            arr[cur]++;
            // for(int i=0;i<k;i++) System.out.print(arr[i]+" ");
            // System.out.println();
            if(arr[cur] > n){
                cur--;
            }else if(cur == k-1){
                // ans.add(new ArrayList<>(Arrays.asList(arr)));
                List<Integer> tmp = new ArrayList<>();
                for(int i=0;i<k;i++) tmp.add(arr[i]);
                ans.add(tmp);
            }else{
                arr[++cur] = arr[cur-1];
            }
        }
        return ans;
    }
}
