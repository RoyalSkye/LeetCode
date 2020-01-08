// 1.brute force - O(N^2)
class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        int max = -1;
        for(int i = 0; i < A.length; i++) {
            for(int j = i + 1; j < A.length; j++) {
                int tmp = A[i] + A[j];
                if(tmp < K)
                    max = Math.max(tmp, max);
            }
        }
        return max;
    }
}

// 2.sort with two pointers - O(NlogN)
class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        int max = -1;
        Arrays.sort(A);
        int low = 0, high = A.length - 1;
        while (low < high) {
            int sum = A[low] + A[high];
            if (sum < K) {
                low++;
                max = Math.max(sum, max);
            } 
            else
                high--;
        }
        return max;
    }
}

// python
class Solution:
    def twoSumLessThanK(self, A: List[int], K: int) -> int:
        A.sort()
        res, low, high = -1, 0, len(A) - 1
        while low < high:
            sum = A[low] + A[high]
            if sum < K:
                low += 1
                res = max(sum, res)
            else:
                high -= 1
        return res