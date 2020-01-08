// 1.two pointers - Time O(N) Space O(1)
// java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            if (numbers[low] + numbers[high] == target) {
                return new int[] {low + 1, high + 1};
            } else if (numbers[low] + numbers[high] < target) {
                low++;
            } else {
                high--;
            }
        }
        throw new IllegalArgumentException("No solution");
    }
}

// python: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/discuss/51249/Python-different-solutions-(two-pointer-dictionary-binary-search).
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        low, high = 0, len(numbers) - 1
        while low < high:
            if numbers[low] + numbers[high] == target:
                return [low + 1, high + 1]
            elif numbers[low] + numbers[high] < target:
                low += 1
            else:
                high -= 1