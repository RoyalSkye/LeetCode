// 1.my Error solution
class Solution {
    public int maxProfit(int[] prices) {
        int firstprofit = 0, secondprofit = 0, cur = 0;
        for(int i=0;i<prices.length;i++){
            if(i<prices.length-1 && prices[i+1] >= prices[i]){
                cur += prices[i+1] - prices[i];
            }else{
                if(cur != 0){ //update profit
                    if(cur > firstprofit){
                        secondprofit = firstprofit;
                        firstprofit = cur;
                    } 
                    else if(cur > secondprofit) secondprofit = cur;
                }
                cur = 0;
            }
        }
        return firstprofit + secondprofit;
    }
} 

// 2.amazing solution with Time O(N) and Space O(1) - best solution - difficult to understand
class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MAX_VALUE, buy2 = Integer.MAX_VALUE, sell1 = 0, sell2 = 0;
        for (int v: prices){
            buy1 = Math.min(buy1, v);
            sell1 = Math.max(sell1, v-buy1);
            buy2 = Math.min(buy2, v-sell1);
            sell2 = Math.max(sell2, v-buy2);
        }
        return sell2;
    }
}

// 3.dp with k transactions - difficult to understand
// f[k, ii] represents the max profit up until prices[ii] using at most k transactions.
// f[k, i] = max(f[k, i-1], prices[i] - prices[j] + f[k-1, j]), j ∈ [0, i)
//         = max(f[k, i-1], prices[i] + max(f[k-1, j] - prices[j])), j ∈ [0, i)
class Solution {
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int transactions = 2;
        int[][] dp = new int[transactions+1][prices.length];
        for(int k = 1;k <= transactions;k++){
            int tmpMax = dp[k-1][0] - prices[0];
            for(int i = 1;i < prices.length;i++){
                dp[k][i] = Math.max(dp[k][i-1], tmpMax + prices[i]);
                tmpMax = Math.max(tmpMax, dp[k-1][i]-prices[i]);
            }
        }
        return dp[transactions][prices.length-1];
    }
} 
