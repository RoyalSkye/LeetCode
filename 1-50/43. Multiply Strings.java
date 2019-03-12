// 1.模拟竖式计算 
class Solution {  //multiply two big num and then add two big num
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        StringBuilder ans = new StringBuilder("");
        char[] s1 = num1.toCharArray();
        char[] s2 = num2.toCharArray();
        int zero = 0;  
        for(int i=s2.length-1;i>=0;i--){
            StringBuilder tmpans = new StringBuilder("");
            int count = 0; //进位
            for(int j=s1.length-1;j>=0;j--){
                int mulans = (s2[i] - '0')*(s1[j] - '0');
                tmpans.insert(0,(mulans + count)%10);
                count = (mulans + count)/10;
            }
            if(count>0) tmpans.insert(0,count);
            int tmp = zero;  //补0
            while(tmp-- > 0){  
                tmpans.append(0);
            }
            zero++;
            ans = addtwonum(ans,tmpans);
        }
        return ans.toString();
    }
                          
    private StringBuilder addtwonum(StringBuilder s1, StringBuilder s2){
        StringBuilder res = new StringBuilder("");
        int s1_index = s1.length()-1;
        int s2_index = s2.length()-1;
        int count = 0;
        while(s1_index >= 0 || s2_index >= 0){
            int n1 = s1_index >= 0 ? s1.charAt(s1_index) - '0' : 0;
            int n2 = s2_index >= 0 ? s2.charAt(s2_index) - '0' : 0;
            int addans = n1+n2;
            res.insert(0, (addans+count)%10);
            count = (addans + count)/10;
            s1_index--;
            s2_index--;
        }
        if(count > 0) res.insert(0,count);
        return res;
    }
}

// 2.`num1[i] * num2[j]` will be placed at indices `[i + j`, `i + j + 1]` 
class Solution{
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }  
        StringBuilder sb = new StringBuilder();
        for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

or

class Solution {  //more readable
    public String multiply(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }
        int carry = 0;
        for (int i = products.length - 1; i >= 0; i--) {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : products) sb.append(num);
        while (sb.length() != 0 && sb.charAt(0) == '0') sb.deleteCharAt(0);
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

