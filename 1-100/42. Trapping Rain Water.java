// 42. Trapping Rain Water - Solution 3 and 4 is readable and straightforward.

// 1.my stupid solution
// each time we find the num(non-0) as start, if larger num exists after start, the first num larget than height[start] will be end;
// else we find the second largest num(just smaller than height[start]) as end;
class Solution {
    public int trap(int[] height) {
        int ans = 0, cur = 0;
        while(cur < height.length){
            int start = -1, end = -1;
            while(cur < height.length){  //the first time to search start
                if(height[cur] != 0){
                    start = cur;
                    cur++;
                    break;
                }
                cur++;
            }
            int secondheight = 0, pos = -1;
            while(cur < height.length){
                if(height[cur] >= height[start]){
                    end = cur;
                    if(cur < height.length-1 && height[cur+1] >= height[cur]) cur++;  //cur(start) moves
                    break;
                }
                if(height[cur] >= secondheight && height[cur] != 0){
                    secondheight = height[cur];
                    pos = cur;
                }
                cur++;
            }
            if((start == -1 || end == -1) && pos == -1) break;
            else if((start == -1 || end == -1)){
                end = pos;
                cur = end;
            }
            int base = height[start] <= height[end] ? height[start] : height[end];
            int tmp = start+1;
            while(tmp < end){
                ans += base - height[tmp];
                tmp++;
            }
            //System.out.println(start+" "+end+" "+res);
        }
        return ans;
    }
}

// 2.brute force - O(N^2)
// for every bar i, we find the maximum height on both the sides([0,i],[i,len-1])
// then ans += Math.min(rmaximum, lmaximum) - height[i]
// for every ele, we iterate the whole array

// 3.dp - based on brute force - we use extra space to store rightmax and leftmax of each ele, 
// and get current result based on the previous ele. - O(N) ~3N
// space exchange for time
class Solution{
    public int trap(int[] height) {
        if(height.length <= 2) return 0;
        int ans = 0;
        int n = height.length;
        int[] rightmax = new int[n];
        int[] leftmax = new int[n];
        leftmax[0] = height[0];
        rightmax[n-1] = height[n-1];
        for(int i=1;i<n;i++){
            leftmax[i] = Math.max(leftmax[i-1], height[i]);
        }
        for(int i=n-2;i>=0;i--){
            rightmax[i] = Math.max(rightmax[i+1], height[i]);
        }
        for(int i=1;i<n-1;i++){  //i=0 and i=n-1, either leftmax or rightmax must be itself     
            ans += Math.min(leftmax[i], rightmax[i]) - height[i];
        }
        return ans;
    }
}

// 4.optimize dp - not use extra space - use two pointer
class Solution{
    public int trap(int[] height) {
        if(height.length <= 2) return 0;
        int n = height.length;
        int ans = 0, leftmax = 0, rightmax = 0, left = 0, right = n - 1;
        while(left < right){ //left <= right also works, since the last ele is the largest ele, and the leftmax = rightmax = height[left/right], so ans += 0
            if(height[left] < height[right]){
                if(height[left] >= leftmax) leftmax = height[left];
                else ans += leftmax - height[left];
                left++;
            }else{
                if(height[right] >= rightmax) rightmax = height[right];
                else ans += rightmax - height[right];
                right--;
            }
        }
        return ans;
    }
}

// 5.use stack - O(N)
class Solution
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0, cur = 0, n = height.length;
        while(cur < n){
            while(!stack.empty() && height[stack.peek()] < height[cur]){
                int top = stack.pop();
                if(stack.empty()) break;
                int distance = cur - stack.peek() - 1;
                int minbar = Math.min(height[cur], height[stack.peek()]) - height[top];
                ans += minbar*distance;
            }
            stack.push(cur++);
        }
        return ans;
    }
}