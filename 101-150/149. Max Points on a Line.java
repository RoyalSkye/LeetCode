// O(N^2)
class Solution {
    public int maxPoints(int[][] points) {
        if(points.length <= 2) return points.length;
        int ans = 2;
        for(int i=0;i<points.length-1;i++){
            int overlap = 0;
            int curmax = 0;
            Map<String, Integer> map = new HashMap<>();
            for(int j=i+1;j<points.length;j++){
                int x = points[i][0] - points[j][0];
                int y = points[i][1] - points[j][1];
                if(x==0 && y==0){
                    overlap++;
                    continue;
                }
                int gcd = generategcd(x, y);
                x /= gcd;
                y /= gcd;
                String key = String.valueOf(x) + String.valueOf(y);
                int tmp = map.getOrDefault(key, 0) + 1;
                map.put(key, tmp);
                curmax = Math.max(curmax, tmp);
            }
            ans = Math.max(ans, overlap + curmax + 1);
        }
        return ans;
    }
    
    // 求最大公约数
    // 若求x与y的最小公倍数：x*y/gcd
    private int generategcd(int x, int y){
        if(y==0) return x;
        return generategcd(y, x%y);
    }
}