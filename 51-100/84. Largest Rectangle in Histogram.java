// 1.my solution - 616ms - 0(N^2)
// derive from each ele heights[i], expand it to both side(find the first ele less than heights[i] on both side)
class Solution {
    // use extra space to store the index of the first num less than heights[i], left and right respectively
    public int largestRectangleArea(int[] heights) {
        int max = 0, len = heights.length;
        if(len == 0) return 0;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = -1; right[len-1] = len;
        for(int i=1;i<len;i++){
            if(heights[i] > heights[i-1]) left[i] = i-1;
            else{  //if not find, set to 0: the head of the array
                int tmp = i-1;
                while(tmp>=1){
                    if(heights[tmp] < heights[i]) break;
                    tmp--;
                }
                left[i] = heights[tmp] < heights[i] ? tmp :-1;
            }
            if(heights[len-i-1] > heights[len-i]) right[len-i-1] = len-i;
            else{
                int tmp = len-i;
                while(tmp<=len-2){
                    if(heights[tmp] < heights[len-i-1]) break;
                    tmp++;
                }
                right[len-i-1] = heights[tmp] < heights[len-i-1] ? tmp : len; 
            }
        }
        for(int i=0;i<len;i++){
            max = Math.max(max, heights[i]*(i-left[i]+right[i]-i+1-2));
            System.out.print(left[i]+" ");
        }
        System.out.println();
        for(int i=0;i<len;i++){
            System.out.print(right[i]+" ");
        }
        return max;
    }
}

// optimize - dp - 2ms - may be nearly O(N) - best solution!!!
// instead of moving one step each time, jump to the right[p]/left[p] directly
class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = 0, len = heights.length;
        if(len == 0) return 0;
        int[] left = new int[len];  //the first left ele less than heights[i]
        int[] right = new int[len]; //the first right ele less than heights[i]
        left[0] = -1; right[len-1] = len;
        for(int i=1;i<len;i++){
            int p = i - 1;
            while(p>=0 && heights[p] >= heights[i]){
                // p--;
                p = left[p]; // coooool!!
            }
            left[i] = p;
        }
        for(int i=len-2;i>=0;i--){
            int p = i + 1;
            while(p<=len-1 && heights[p] >= heights[i]){
                // p++;
                p = right[p];
            }
            right[i] = p;
        }
        for(int i=0;i<len;i++){
            max = Math.max(max, heights[i]*(i-left[i]+right[i]-i+1-2));
        }
        return max;
    }
}

// 2.brute force - O(N^2) 
// find each pair

class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        for(int i=0;i<heights.length;i++){
            int minheight = Integer.MAX_VALUE;
            for(int j=i;j<heights.length;j++){
                minheight = Math.min(minheight, heights[j]);
                max = Math.max(max, minheight*(j-i+1));
            }
        }
        return max;
    }
}

// 3.divide and conquer
// Time: Average :O(Nlog(N)) Worst case: O(N^2) If the numbers in the array are sorted, we don't gain the advantage of divide and conquer.
// Space: O(NRecursion with worst case depth N

// This approach relies on the observation that the rectangle with maximum area will be the maximum of:
// a.The widest possible rectangle with height equal to the height of the shortest bar.
// b.The largest rectangle confined to the left of the shortest bar(subproblem).
// c.The largest rectangle confined to the right of the shortest bar(subproblem).

// Intuition: start from the lowest bar, 
// and the second, third and so forth, lowest bar can form the rectangle only at the left or right of the lowest bar
// if the length of the second lowest bar is equal to that of the lowest bar, its result has been calculated when we cal the lowest bar, and doesn't matter at all.
class Solution {
    public int largestRectangleArea(int[] heights) {
        return dc(heights, 0, heights.length-1);
    }
    
    private int dc(int[] heights, int start, int end){
        // System.out.println(start+" "+end);
        if(start > end) return 0;
        if(start == end) return heights[start];
        int minindex = start;
        for(int i=start;i<=end;i++){
            if(heights[i] < heights[minindex]){
                minindex = i;
            }
        }
        return Math.max(heights[minindex]*(end-start+1), Math.max(dc(heights, start, minindex-1), dc(heights, minindex+1, end)));
    }
}
// optimize: We can reduce the time complexity by using a Segment Tree to find the minimum every time which can be done in O(log(N))

// 4.Stack - O(N) ~2N
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);stack.push(0);
        int max = 0, left = -1;
        for(int i=1;i<heights.length;i++){
        	// note: when heights[i] == heights[stack.peek()], also need to pop(), 
        	// but the area result is inaccurate(doesn't matter, when cal the next ele heights[i] which is the same val as heights[stack.peek], tha ans will towards the correct ans)
        	// in this way, we keep all ele in stack in ascending order, namely each previous ele is the first left ele less than heights[stack.peek()], 
        	// and heights[i] is the first right ele less than heights[stack.peek()]
            while(stack.peek()!=-1 && heights[stack.peek()] >= heights[i]){  
                max = Math.max(max, heights[stack.pop()]*(i-stack.peek()-1));
            } 
            stack.push(i);
        }
        while(stack.peek()!=-1){
            max = Math.max(max, heights[stack.pop()]*(heights.length-stack.peek()-1));
        }
        return max;
    }
}











