// 1.wrong solution - test case contains Math.MAX_VALUE/Math.MIN_VALUE can not pass.
class Solution {
    public int singleNumber(int[] nums) {
        int setsum = 0, arraysum = 0;
        Set<Integer> num = new HashSet<>();
        for(int i : nums){
            arraysum += i;
            num.add(i);
        }
        for(int i : num) setsum += i;
        return (3 * setsum - arraysum)/2;
    }
}

// 2.bit manipulation
class Solution {
    //00 - 10 - 01 - 00
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int i : nums){
            ones = (ones ^ i) & ~twos;  
            twos = (twos ^ i) & ~ones;
        }
        return ones;
    }
}

// follow up: if 3 times -> 5 times
// 000 - 100 - 010 - 001 -  - 000
class Solution {
    public int singleNumber(int[] nums) {
        
    }
}

// 3.HashMap