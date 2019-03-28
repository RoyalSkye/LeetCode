// 1.Iterative - O(N)
class Solution {
    public int removeDuplicates(int[] nums) {
        int ans = 0, cur = 0;
        while(cur < nums.length){
            int len = 1;
            while(cur < nums.length - 1 && nums[cur] == nums[cur+1]){
                len++;
                cur++;
            } 
            len = len >= 2 ? 2 : 1;
            nums[ans] = nums[cur];
            if(len == 2) nums[ans+1] = nums[cur];
            ans = ans + len;
            cur++;
        }
        return ans;
    }
}

// 2.这个人很smart
// https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/discuss/27976/3-6-easy-lines-C%2B%2B-Java-Python-Ruby
class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for(int num : nums){
            if(i < 2 || num > nums[i-2])
                nums[i++] = num;
        }
        return i;
    }
}