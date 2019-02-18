// 1.O(n^3) - 34ms
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3;i++){  //4Sum
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int target1 = target - nums[i];
            for(int j=i+1;j<nums.length-2;j++){  //3Sum
                if(j > i+1 && nums[j] == nums[j-1]) continue;
                int target2 = target1 - nums[j];
                int low = j+1, high = nums.length-1;
                while(low < high){  //2Sum
                   if(nums[low] + nums[high] == target2){
                       ans.add(Arrays.asList(nums[j], nums[i], nums[low], nums[high]));
                       while(low < high && nums[low] == nums[low+1]) low++;
                       while(low < high && nums[high] == nums[high-1]) high--;
                       low++;high--;
                   }else if(nums[low] + nums[high] < target2) low++;
                    else high--;
                }
            }
        }
        return ans;
    }
}

// 2.O(n^3) add four lines code to optimize the algorithm - 11ms
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3;i++){  //4Sum
            if(nums[i]+nums[i+1]+nums[i+2]+nums[i+3]>target) break; //first candidate too large, search finished
            if(nums[i]+nums[nums.length-1]+nums[nums.length-2]+nums[nums.length-3]<target) continue; //first candidate too small
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int target1 = target - nums[i];
            for(int j=i+1;j<nums.length-2;j++){  //3Sum
                if(nums[i]+nums[j]+nums[j+1]+nums[j+2]>target) break; //second candidate too large
                if(nums[i]+nums[j]+nums[nums.length-1]+nums[nums.length-2]<target) continue; //second candidate too small
                if(j > i+1 && nums[j] == nums[j-1]) continue;
                int target2 = target1 - nums[j];
                int low = j+1, high = nums.length-1;
                while(low < high){  //2Sum
                   if(nums[low] + nums[high] == target2){
                       ans.add(Arrays.asList(nums[j], nums[i], nums[low], nums[high]));
                       while(low < high && nums[low] == nums[low+1]) low++;
                       while(low < high && nums[high] == nums[high-1]) high--;
                       low++;high--;
                   }else if(nums[low] + nums[high] < target2) low++;
                    else high--;
                }
            }
        }
        return ans;
    }
}
