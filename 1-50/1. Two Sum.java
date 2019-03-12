//Brute Force - O(n^2)
//class Solution {
//	public int[] twoSum(int[] nums, int target) {
//		int len = nums.length;
//		int[] result = new int[2];
//		boolean flag = false;
//		for(int i=0;i<len;i++){
//			for(int j=i+1;j<len;j++){
//				if(nums[i]+nums[j]==target){
//					result[0]=i;
//					result[1]=j;
//					flag = true;
//					break;
//				}
//				if(flag) break;
//			}
//		}
//		return result;
//	}
//}

//One-pass Hash Table - O(n)
class Solution {
	public int[] twoSum(int[] nums, int target) {
		int[] result = new int[2];
		int len = nums.length;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i<len; i++){
			if(map.containsKey(target-nums[i])){
				result[0] = map.get(target-nums[i]);
				result[1] = i;
				break;
			}
			map.put(nums[i], i);  //can not put before checking the key
		}
		return result;
	}
}