// 1.my solution, the time complexity may not be O(log(m+n))
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        int mid = (l1+l2)/2;
        if(l1==0) return (l1+l2)%2==1 ? nums2[mid] : (nums2[mid-1]+nums2[mid]+0.0)/2;
        if(l2==0) return (l1+l2)%2==1 ? nums1[mid] : (nums1[mid-1]+nums1[mid]+0.0)/2;
        int[] num = new int[mid+1];
        int count = 0;
        for(int i=0,j=0;count<=mid;){
            if(nums1[i] <= nums2[j]){
                num[count++] = nums1[i];
                if(i!=l1-1) i++;
                else nums1[i] = Integer.MAX_VALUE;
            }else if(nums1[i] > nums2[j]){
                num[count++] = nums2[j];
                if(j!=l2-1) j++;
                else nums2[j] = Integer.MAX_VALUE;
            }
        }
        for(int i=0;i<num.length;i++){
            System.out.print(num[i]+" ");
        }
        return (l1+l2)%2==1 ? num[mid] : (num[mid-1]+num[mid]+0.0)/2;
    }
}

// 2.Binary Search - O(log(m+n))
// https://leetcode.com/articles/median-of-two-sorted-arrays/
class Solution {
    //Minds: non-trivial
    //A[0] A[1] ... A[i-1] | A[i] A[i+1] ... A[m-1]
    //B[0] B[1] ... B[j-1] | B[j] B[j+1] ... B[n-1]
    //To find the Median, we must ensure length(left) == length(left) == length(right) or + 1, namely i+j=m-i+n-j(+1)
    //and A[i-1] <= B[j] and B[j-1] <= A[i]
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if(m > n){  //ensure n >= m, since j can not be negative integer.
            int[] temp = nums1;nums1 = nums2;nums2 = temp;
            int tmp = m;m = n;n = tmp; 
        }
        if(m == 0) return n%2==0 ? (nums2[n/2-1]+nums2[n/2])/2.0 : nums2[n/2];
        int low = 0, high = m, i = 0, j = 0;
        while(low <= high){  //binary search
            i = (low + high) / 2;
            j = (m+n+1)/2 - i;  //ensure length(left) == length(right) or + 1
            //corner case j=0 or j=n exists iff n=m or n=m+1, and in this case, i must also equal to 0 or m,
            //otherwise impossible since n>=m (figure it out by graphing)
            //so what we need to consider is just i=0 and i=m.
            if(i!=0 && nums1[i-1] > nums2[j]) high = i - 1;
            else if(i!=m && nums2[j-1] > nums1[i]) low = i + 1;
            else {
                if(i == 0){
                    int tmp = j == n ? Integer.MAX_VALUE : nums2[j];
                    return (m+n)%2==0 ? (Math.min(nums1[i],tmp)+nums2[j-1])/2.0 : nums2[j-1];
                } 
                if(i == m){
                    int tmp = j == 0 ? Integer.MIN_VALUE : nums2[j-1];
                    return (m+n)%2==0 ? (Math.max(nums1[i-1],tmp)+nums2[j])/2.0 : Math.max(nums1[i-1],tmp);
                } 
                break;
            }
        }
        return (m+n)%2==0 ? (Math.max(nums1[i-1],nums2[j-1])+Math.min(nums1[i],nums2[j]))/2.0 : Math.max(nums1[i-1],nums2[j-1]);
    }
}