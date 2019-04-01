// 1.change the highest bit
// main idea: notice two successive values differ in only one bit.
// so each time we only change the highest bit, namely, reverse traverse the previous existing ele and push 1 at highest bit
// n = 0 : 0(0)
// n = 1 : 1(1)
// n = 2 : 3(11)
// n = 2 : 2(10)
// n = 3 : 6(110)
// n = 3 : 7(111)
// n = 3 : 5(101)
// n = 3 : 4(100)
// my code:
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new LinkedList<>();
        int max = cal(n), start = 1, len = 1;
        int[] tmp = new int[max];
        while(n!=0){
            for(int i = start,j = start-1;i<max&&j>=0;i++,j--){
                tmp[i] = tmp[j] + len;
            }
            start += len;
            len *= 2;
            n--;
        }
        for(int i=0;i<max;i++){
            ans.add(tmp[i]);
        }
        return ans;
    }
    
    private int cal(int n){
        if(n==0) return 1;
        return 2*cal(n-1);
    }
}

// no extra space - bit operation
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> rs=new ArrayList<Integer>();
        rs.add(0);
        for(int i=0;i<n;i++){
            int size=rs.size();
            for(int k=size-1;k>=0;k--)
                rs.add(rs.get(k) | 1<<i);
        }
        return rs;
    }
}