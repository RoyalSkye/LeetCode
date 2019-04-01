// 1.use extra space - two pointers start from the beginning
// my solution: Time: O(M+N) Space: O(M+N)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(n==0) return;
        int[] tmp = new int[m+n];
        int count = 0;
        for(int i=0,j=0;i<m||j<n;){
            if(i>=m){  //nums1 end
                while(j<n) tmp[count++] = nums2[j++];
                break;
            }
            if(j>=n){  //nums2 end
                while(i<m) tmp[count++] = nums1[i++];
                break;
            }
            if(nums1[i] <= nums2[j]){
                tmp[count++] = nums1[i++];
            }
            else tmp[count++] = nums2[j++];
        }
        for(int i=0;i<m+n;i++){
            nums1[i] = tmp[i];
        }
    }
}

// Time: O(M+N) Space: O(M)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1_copy = new int[m];
        System.arraycopy(nums1, 0, nums1_copy, 0, m);
        int i = 0, j = 0, count = 0;
        while(i < m && j < n){
            nums1[count++] = (nums1_copy[i] <= nums2[j]) ? nums1_copy[i++] : nums2[j++];
        }
        if(i < m) System.arraycopy(nums1_copy, i, nums1, count, m-i);
        if(j < n) System.arraycopy(nums2, j, nums1, count, n-j);
        // while(i < m) nums1[count++] = nums1_copy[i++];
        // while(j < n) nums1[count++] = nums2[j++];
    }
}

// 2. two pointers start from the end
// Time: O(M+N) Space: O(1)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m-1, p2 = n-1, p = m+n-1;
        while(p1>=0 && p2>=0){
            if(nums2[p2] >= nums1[p1]){
                nums1[p--] = nums2[p2--];
            }else{
                nums1[p--] = nums1[p1--];
            }
        }
        if(p2>=0) System.arraycopy(nums2, 0, nums1, 0, p2+1);
    }
}