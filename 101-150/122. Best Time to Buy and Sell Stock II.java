// 1.basic idea: maxprofit equals to the sum of ascending stock
// if nextprice > preprice, add preprice-nextprice to maxprofit
class Solution {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, maxprofit = 0;
        for(int i=0;i<prices.length;i++){
            if(prices[i] <= min){
                min = prices[i];
            }else{
                maxprofit += prices[i] - min;
                min = prices[i];
            }
        }
        return maxprofit;
    }
}

// 还能更简单hhhh
public class Solution {
    public int maxProfit(int[] prices) {
        int total = 0;
        for (int i=0; i < prices.length-1; i++) {
            if (prices[i + 1] > prices[i]) total += prices[i+1] - prices[i];
        }
        return total;
}