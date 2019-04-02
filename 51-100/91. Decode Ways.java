// 1.dp
class Solution {
    public int numDecodings(String s) {
        if(s.length()==0) return 0;
        char[] cc = s.toCharArray();
        int[] dp = new int[s.length()+1];
        dp[0] = 1; //[]
        dp[1] = cc[0] != '0' ? 1 : 0;
        for(int i=2;i<=s.length();i++){
            if(cc[i-1] >= '1' && cc[i-1] <='9') dp[i] = dp[i-1];
            int num = Integer.valueOf(s.substring(i-2,i));
            if(num >=10 && num <=26) dp[i] += dp[i-2];
        }
        return dp[s.length()];
    }
}

// or

class Solution {
    public int numDecodings(String s) {
        if(s.length()==0) return 0;
        int n = s.length();
        int[] dp = new int[n+1];
        dp[n] = 1;
        dp[n-1] = s.charAt(n-1) != '0' ? 1 : 0;
        for(int i=n-2;i>=0;i--){
            if(s.charAt(i) == '0') continue;
            else{
                int num = Integer.valueOf(s.substring(i,i+2));
                dp[i] = (num>=10&&num<=26) ? dp[i+1] + dp[i+2] : dp[i+1];
            }
        }
        return dp[0];
    }
}