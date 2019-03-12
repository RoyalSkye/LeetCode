// 1.first wrong solution
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int low = 0, high = nums.length-1,mid;
        while(low < high){
            mid = (low + high) / 2;
            if(nums[mid] == target){
                if(mid == nums.length -1) return nums[mid] + nums[mid-1] + nums[mid-2];
                else if(mid == 0) return nums[mid] + nums[mid+1] + nums[mid+2];
                else return nums[mid] + nums[mid+1] + nums[mid-1];
            }
            if(nums[mid] < target) low = mid + 1;
            if(nums[mid] > target) high = mid - 1;
        }
        //now low = high
        //bound
        if(low == 0) return nums[0] + nums[1] + nums[2];
        if(high == nums.length-1) return nums[high] + nums[high-1] + nums[high-2];
        //internal
        if(nums[low] < target) return -1*(Math.min(target - nums[low] - nums[low+1] - nums[low+2],
                                              target - nums[low] - nums[low+1] - nums[low-1]) - target);
        else if(nums[low] > target) return -1*(Math.min(target - nums[low] - nums[low-1] - nums[low-2],
                                              target - nums[low] - nums[low-1] - nums[low+1]) - target);
        else return nums[low] + nums[low+1] + nums[low-1];
    }
}

// 2.second correct solution - 9ms (but I don't think it works when the target is too large or small)
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);  //-4,-1,1,2
        int res = 0;
        boolean flag = true; 
        int temp = target;
        for(int j=0;flag;j++){
            temp = j % 2 == 0 ? target - j/2 : target + j/2;
            for(int i=0;i<nums.length-2;i++){  //3Sum
                int temptarget = temp - nums[i];
                if(i > 0 && nums[i] == nums[i-1]) continue;
                int low = i + 1, high = nums.length - 1; 
                while(low < high){
                    if(nums[low] + nums[high] == temptarget){
                        flag = false;
                        return temptarget + nums[i];
                    }else if(nums[low] + nums[high] < temptarget){
                        low++;
                    }else high--;
                }
            }
        }
        return res;
    }
}

// 3.straightforward solution - 10ms
class Solution {
    public int threeSumClosest(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else if (sum < target){
                    start++;
                } else return sum;
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }
}