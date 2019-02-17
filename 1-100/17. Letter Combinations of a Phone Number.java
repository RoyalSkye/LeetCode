// 1.Backtracking(DFS)
class Solution {
    Map<String,String> map = new HashMap<>();
    List<String> ans = new ArrayList<>();
    
    public List<String> letterCombinations(String digits) {
        map.put("2", "abc");
        map.put("3", "def");
        map.put("4", "ghi");
        map.put("5", "jkl");
        map.put("6", "mno");
        map.put("7", "pqrs");
        map.put("8", "tuv");
        map.put("9", "wxyz");
        if(digits.length() > 0) backtrack("", digits);
        return ans;
    }
    
    private void backtrack(String combination, String next_digit){
        if(next_digit.isEmpty()){
            ans.add(combination);
            return;
        }else{
            String cur = map.get(next_digit.substring(0,1));
            for(int i=0;i<cur.length();i++){
                String letter = cur.substring(i,i+1);
                backtrack(combination + letter, next_digit.substring(1));
            }
        }
    }
}

// 2.BFS(FIFO)
class Solution {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
    }
}

// LinekedList.remove() O(1) ? No
// https://www.cnblogs.com/NickyYe/p/4461454.html

