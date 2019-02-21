// 1.my solution
class Solution {
    Map<Integer, String> map = new HashMap<>();
    List<String> ans = new LinkedList<>();
    
    public List<String> generateParenthesis(int n) { 
        List<String> answer = new LinkedList<>();
        for(int i=1;i<=n;i++) map.put(i,"()");
        String combination = "";
        generateNormal(combination, 2*n);
        for(String s : ans){
            if(isParenthesis(s)) answer.add(s);
        }
        return answer;
    }
    
    private void generateNormal(String combination, int n){
        if(n == 0){
            ans.add(combination);
            return;
        }else{
            generateNormal(combination+'(' , n-1);
            generateNormal(combination+')' , n-1);
        }
    }
    
    private boolean isParenthesis(String s){
        int count = 0;
        for(char c : s.toCharArray()){
            if(c == '(') count++;
            else if(count>0){
                count--;
            }else return false;
        }
        return count == 0;
    }
}