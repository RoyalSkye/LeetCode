class Solution {
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder("");
        if(a.length() > b.length()){
            String tmp = a;
            a = b;
            b = tmp;
        }
        int m1 = a.length();
        int m2 = b.length();
        int remain = 0, sum = 0, i = 0, j = 0;
        for(i=m1-1,j=m2-1;i>=0;i--,j--){
            sum = a.charAt(i) + b.charAt(j) - '0' - '0' + remain;
            ans.insert(0, String.valueOf(sum%2));
            remain = sum/2;
        }
        for(;j>=0;j--){
            sum = b.charAt(j) - '0' + remain;
            ans.insert(0, String.valueOf(sum%2));
            remain = sum/2;
        }
        if(remain==1) ans.insert(0, '1');
        return ans.toString();
    }
}

// or 

class Solution {
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder("");
        int m1 = a.length() - 1;
        int m2 = b.length() - 1;
        int carry = 0;
        while(m1 >= 0 || m2 >= 0){
            int sum = carry;
            if(m1 >= 0) sum += a.charAt(m1--) - '0';
            if(m2 >= 0) sum += b.charAt(m2--) - '0';
            ans.insert(0, sum%2);
            carry = sum/2;
        }
        if(carry!=0) ans.insert(0, 1);
        return ans.toString();
    }
}