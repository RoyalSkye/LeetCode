// 1.Bit Manipulation 异或^
// 0 ^ x = x
// a ^ b = b ^ a
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i : nums) ans ^= i;
        return ans;
    }
}

// 2.Math (Set) - 2(a + b + c) - (a + a + b + b + c) = c
// bugs: Math.MAX_VALUE/Math.MIN_VALUE

// 3.HashMap 1st put then 2nd remove