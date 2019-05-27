// 1.my solution - O(N) - 6ms
class Solution {
    public int longestConsecutive(int[] nums) {
        int ans = 0;
        Set<Integer> num = new HashSet<>();
        for(int n : nums){
            num.add(n);
        }
        Set<Integer> visited = new HashSet<>();
        for(int n : num){
            if(visited.contains(n)) continue;
            visited.add(n);
            int tmp1 = n, tmp2 = n;
            int count = 1;
            while(num.contains(++tmp1)){
                count++;
                visited.add(tmp1);
            } 
            while(num.contains(--tmp2)){
                count++;
                visited.add(tmp2);
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }
}

// or we do not use visited set
class Solution {
    public int longestConsecutive(int[] nums) {
        int ans = 0;
        Set<Integer> num = new HashSet<>();
        for(int n : nums){
            num.add(n);
        }
        for(int n : num){
            if(!num.contains(n-1)){
                int tmp = n;
                int count = 1;
                while(num.contains(++tmp)){
                    count++;
                }
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }
}