// 1.backtracking - 21 / 45 test cases passed.
// Time: O(2^N) Space: O(N)
class Solution {
    public int climbStairs(int n) {
        return backtracking(0, n);
    }
    
    private int backtracking(int cur, int n){
        if(cur == n) return 1;
        if(cur > n) return 0;
        return backtracking(cur+1, n) + backtracking(cur+2, n);
    }
}

// 2.Recursion with memoization - Time: O(N) Space: O(N)
class Solution {
    public int climbStairs(int n) {
        int[] ways = new int[n+1];
        return dp(ways, 0, n);
    }
    
    private int dp(int[] ways, int cur, int n){
        if(cur == n) return 1;
        if(cur > n) return 0;
        if(ways[cur] != 0) return ways[cur];
        int tmp =  dp(ways, cur+1, n) + dp(ways, cur+2, n);
        ways[cur] = tmp;
        return tmp;
    }
}

// 3.dp - Time: O(N) Space: O(N)
class Solution {
    public int climbStairs(int n) {
        if(n == 1) return 1;
        int[] ways = new int[n+1];
        ways[1] = 1;
        ways[2] = 2;
        for(int i=3;i<=n;i++){
            ways[i] = ways[i-1] + ways[i-2];
        }
        return ways[n];
    }
}
//or we just use O(1) space, since Fibonacci Number: Fib(n)=Fib(n−1)+Fib(n−2)
class Solution {
    public int climbStairs(int n) {
        if(n == 1) return 1;
        int first = 1;
        int second = 2;
        for(int i=3;i<=n;i++){
        	int third = first + second;
        	first = second;
        	second = third;
        }
        return second;
    }
}

// 4.O(logN) - complicated mathematic method - see solution 5 and 6
// https://leetcode.com/problems/climbing-stairs/solution/