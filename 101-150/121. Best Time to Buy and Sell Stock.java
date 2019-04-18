// 1.one-pass - Time: O(N) Space: O(1)
class Solution {
    public int maxProfit(int[] prices) {
        int max = Integer.MIN_VALUE, ans = 0;
        int len = prices.length;
        for(int i=len-1;i>=0;i--){
            max = (prices[i] > max) ? prices[i] : max;
            ans = (max - prices[i] > ans) ? max - prices[i] : ans;
        }
        return ans;
    }
}

// 2.Peak Valley Approach
public class Solution {
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] <= minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}