// 1.brute force O(n^2)
class Solution {
    public int maxArea(int[] height) {
        int ans = 0;
        int len = height.length;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                ans = Math.max(ans, Math.min(height[i],height[j])*(j-i));
            }
        }
        return ans;
    }
}

// 2.Two Pointer Approach - O(n)
class Solution {
    public int maxArea(int[] height) {
        int len = height.length;
        int start = 0, end = len-1;
        int ans = 0;
        while(start < end){
            ans = Math.max(ans, Math.min(height[start],height[end])*(end-start));
            if(height[start] <= height[end]) start++;
            else end--;
        }
        return ans;
    }
}