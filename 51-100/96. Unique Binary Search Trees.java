// 看了hint才有思路
// Given a sorted sequence 1 ... n, to construct a Binary Search Tree (BST) out of the sequence, 
// we could enumerate each number i in the sequence, and use the number as the root, 
// then, the subsequence 1 ... (i-1) on its left side would lay on the left branch of the root, 
// and similarly the right subsequence (i+1) ... n lay on the right branch of the root. 
// We then can construct the subtree from the subsequence recursively. Through the above approach, 
// we could be assured that the BST that we construct are all unique, since they start from unique roots.
// As we can see, the problem can be reduced into problems with smaller sizes, 
// instead of recursively (also repeatedly) solve the subproblems, we can store the solution of subproblems and reuse them later, i.e. the dynamic programming way.

// 1.dp
// Time: O(N^2) Space: O(N)
class Solution {
    public int numTrees(int n) {
        if(n == 0) return 0;
        int[] arr = new int[n+1];
        arr[0] = 1; arr[1] = 1;  //arr[0] = 1, since we will multiply it with arr[n-i]
        return dp(n, arr);
    }
    
    private int dp(int n, int[] arr){
        if(arr[n] != 0) return arr[n];
        int ans = 0;
        for(int i=1;i<=n;i++){
            ans += dp(i-1, arr) * dp(n-i, arr);  // cartesian product 笛卡尔积
        }
        arr[n] = ans;
        return ans;
    }
}

// without recursion
class Solution {
    public int numTrees(int n) {
        if(n == 0) return 0;
        int[] arr = new int[n+1];
        arr[0] = 1; arr[1] = 1;
        for(int i=2;i<=n;i++){
            for(int j=1;j<=i;j++)
                arr[i] += arr[j-1] * arr[i-j];
        }
        return arr[n];
    }
}

// 2.Mathematical Deduction - Catalan number - not understand
// Time: O(N) Space: O(1)
// https://en.wikipedia.org/wiki/Catalan_number
class Solution {
    public int numTrees(int n) {
        // Note: we should use long here instead of int, otherwise overflow
        long C = 1;
        for (int i = 0; i < n; ++i) {
          C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int) C;
    }
}