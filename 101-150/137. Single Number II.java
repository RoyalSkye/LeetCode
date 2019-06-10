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

// 2.bit manipulation - hard to understand
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
        int na = 0, nb = 0, nc = 0;
        for(int i = 0; i < n; i++){
            nb = nb ^ (nums[i] & na);
            na = (na ^ (nums[i]) & ~nc;
            nc = nc ^ (nums[i] & ~na & ~nb);
        }
        return na & ~nb & ~nc;
    }
}

// 3.HashMap