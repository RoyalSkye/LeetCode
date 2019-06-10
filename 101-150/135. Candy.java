// 1.brute force - O(N^2) - worst case: descending order eg:87654321
class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] candies = new int[len];
        Arrays.fill(candies, 1);
        boolean flag = true;
        while(flag){
            flag = false;
            for(int i=0;i<len;i++){
                if(i > 0 && ratings[i] > ratings[i-1] && candies[i] <= candies[i-1]){
                    candies[i] = candies[i-1] + 1;
                    flag = true;
                }
                if(i < len - 1 && ratings[i] > ratings[i+1] && candies[i] <= candies[i+1]){
                    candies[i] = candies[i+1] + 1;
                    flag = true;
                }
            }
        }
        int sum = 0;
        for(int tmp : candies) sum += tmp;
        return sum;
    }
}

// 2.two-arrays - O(N)
class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);
        for(int i=1;i<len;i++){
            if(ratings[i] > ratings[i-1]) left[i] = left[i-1] + 1;
        }
        for(int i=len-2;i>=0;i--){
            if(ratings[i] > ratings[i+1]) right[i] = right[i+1] + 1;
        }
        int sum = 0;
        for(int i=0;i<len;i++){
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }
}

// or use one array instead of two arrays - in the second traversal, modify left[i] = Math.max(left[i], left[i+1] + 1)

// 3.Single Pass - Time:O(N) Space:O(1) - difficult to understand
class Solution {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int candies = 0;
        int up = 0, down = 0, old_slope = 0;
        for(int i=1;i<len;i++){
            int new_slope = (ratings[i] > ratings[i-1] ? 1 : (ratings[i] < ratings[i-1] ? -1 : 0));
            if((old_slope>0 && new_slope==0) || (old_slope<0 && new_slope>=0)){
                candies += count(up) + count(down) + Math.max(up, down);
                up = 0;
                down = 0;
            }
            if(new_slope > 0) up++;
            else if(new_slope < 0) down++;
            else candies++;
            old_slope = new_slope;
        }
        candies += count(up) + count(down) + Math.max(up, down) + 1;
        return candies;
    }
    
    private int count(int n){
        return n * (n + 1) / 2;
    }
}