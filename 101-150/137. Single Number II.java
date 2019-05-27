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