// 1.same as lc118 elegant solution 2
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        for(int i=0;i<=rowIndex;i++){
            ans.add(0, 1);
            for(int j=1;j<ans.size()-1;j++) ans.set(j, ans.get(j) + ans.get(j+1));
        }
        return ans;
    }
}