// 1.my solution - 3496ms
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            int target = 0 - nums[i];
            Map<Integer,Integer> map = new HashMap<>();
            for(int j=i+1;j<nums.length;j++){  //2Sum
                if(map.containsKey(target-nums[j])){
                    List<Integer> ele = new ArrayList<>();
                    ele.add(nums[i]);
                    ele.add(nums[j]);
                    ele.add(target-nums[j]);
                    boolean flag = true;
                    for(List<Integer> ll : ans){
                        //ll.containsAll(ele) (0,0,0) fail
                        if(isEquals(ll,ele)){
                            flag = false;
                            break;
                        }
                    }
                    if(flag) ans.add(ele);
                }
                map.put(nums[j],j); 
            }
        }
        return ans;
    }
    
    private boolean isEquals(List<Integer> a,List<Integer> b){
        boolean flag = false;
        Collections.sort(a);
        Collections.sort(b);
        for (int i = 0; i <a.size(); i++) {
            if(!a.get(i).equals(b.get(i)))
               return false;
        }
        return true;
    }
}

// 2.perfect solution
class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        Arrays.sort(num);  
        List<List<Integer>> res = new LinkedList<>(); 
        for (int i = 0; i < num.length-2; i++) {  // the result of i=length-2 and length-1 must larger than i=length-3, since the array has been sorted.
            if (i == 0 || (i > 0 && num[i] != num[i-1])) {
                int lo = i+1, hi = num.length-1, sum = 0 - num[i];
                while (lo < hi) {
                    if (num[lo] + num[hi] == sum) {
                        res.add(Arrays.asList(num[i], num[lo], num[hi]));
                        while (lo < hi && num[lo] == num[lo+1]) lo++;
                        while (lo < hi && num[hi] == num[hi-1]) hi--;
                        lo++; hi--;
                    } else if (num[lo] + num[hi] < sum) lo++;
                    else hi--;
               }
            }
        }
        return res;
    }
}