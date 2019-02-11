class Solution {
    public int myAtoi(String str) {
        int ans = 0, weight = 1, start = 0, temp = 0;
        int len = str.length();
        if(str==""||str.trim()=="") return 0;
        
        //find the first non-whitespace character
        for(int i=0;i<len;i++){
            if(str.charAt(i)!=' '){
                if(str.charAt(i)!='-'&& str.charAt(i)!='+' && !Character.isDigit(str.charAt(i)))
                    return 0;   
                if(Character.isDigit(str.charAt(i))){
                    start = i;
                    break;
                }
                if(str.charAt(i)=='-') weight = -1;
                if(i+1 >=len || !Character.isDigit(str.charAt(i+1))) return 0;
                start = i+1;
                break;
            }
        }
        //System.out.println(-2147483648 - 1); = 2147483647
        //must be numerical digits starting from index start
        for(int i=start;i<len;i++){
            if(!Character.isDigit(str.charAt(i))) break;
            
            // temp = temp*10 + (str.charAt(i) - '0');
            // if((temp - (str.charAt(i) - '0')) / 10 != ans){
            //     return weight==-1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            // }
            
            //check overflow before calculating temp
            if(weight==1 && (temp>Integer.MAX_VALUE/10 || (temp==Integer.MAX_VALUE/10 && str.charAt(i) - '0'>7)))
                return Integer.MAX_VALUE;
            if(weight==-1 && (temp>Integer.MAX_VALUE/10 || (temp==Integer.MAX_VALUE/10 && str.charAt(i) - '0'>8)))
                return Integer.MIN_VALUE;
            temp = temp*10 + (str.charAt(i) - '0');
            ans = temp;
        }
        ans *= weight;
        return ans;
    }
}