// 1.TLE - based on problem 46 and 47
class Solution {
    int count;
    
    public String getPermutation(int n, int k) {
        String ans = "";
        List<Integer> ll = new LinkedList<>();
        for(int i=1;i<=n;i++) ll.add(i);
        ll = dfs(ll, 0, k, n);
        for(int i=0;i<ll.size();i++){
            ans += ll.get(i);
        }
        return ans;
    }
    
    private List<Integer> dfs(List<Integer> ll, int cur, int k, int n){
        if(cur == n){
            count++;
            if(count == k) return ll;
            return null;
        }
        for(int i=cur;i<n;i++){
            List<Integer> tmp = new LinkedList<Integer>(ll);
            Collections.swap(ll, cur, i);
            Collections.sort(ll.subList(cur+1, n));
            List<Integer> ans = dfs(ll, cur+1, k, n);
            if(ans!=null) return ans;
            ll = tmp;
        }
        return null;
    }
}

//based on the num of permutations of each n
// https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
class Solution {
    public String getPermutation(int n, int k) {
        StringBuilder ans = new StringBuilder("");
        List<Integer> nums = new LinkedList<>();
        for(int i=1;i<=n;i++){
            nums.add(i);
        }
        k = k - 1;
        // create an array of factorial lookup
        int sum = 1;
        int[] factorial = new int[n+1];
        factorial[0] = 1;
        for(int i=1; i<=n; i++){
            sum *= i;
            factorial[i] = sum;
        }
        
        for(int i=1;i<=n;i++){
            int tmp = k/factorial[n-i];
            ans.append(nums.get(tmp));
            nums.remove(tmp);
            k = k - tmp*factorial[n-i];
        }
        
        return ans.toString();
    }
}