// 1.my code - not concise
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new LinkedList<>();
        if(numRows == 0) return ans;
        List<Integer> ll = new ArrayList<>();
        ll.add(1);
        ans.add(new ArrayList<Integer>(ll));
        if(numRows == 1) return ans;
        ll.add(1);
        ans.add(new ArrayList<Integer>(ll));
        if(numRows == 2) return ans;
        for(int i=3;i<=numRows;i++){
            List<Integer> tmp = new ArrayList<>(ll);
            for(int j=1;j<i-1;j++){
                tmp.set(j, ll.get(j-1) + ll.get(j));
            }
            tmp.add(1);
            ans.add(tmp);
            ll = tmp;
        }
        return ans;
    }
}

// elegant code!
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        ArrayList<Integer> row = new ArrayList<Integer>();
        for(int i=0;i<numRows;i++) {
            row.add(0, 1);
            for(int j=1;j<row.size()-1;j++) row.set(j, row.get(j)+row.get(j+1));
            ans.add(new ArrayList<Integer>(row));
        }
        return ans;
    }
}