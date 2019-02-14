// 1.my solution - Time Limit Exceeded
class Solution {
    public String intToRoman(int num) {
        String ans = "";
        int weight = 1,temp1 = num;
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"I");map.put(5,"V");map.put(10,"I");map.put(50,"L");map.put(100,"C");map.put(500,"D");map.put(1000,"M");
        map.put(400,"CD"); map.put(900,"CM");map.put(40,"XL");map.put(90,"XC");map.put(4,"IV");map.put(9,"IX");
        while(temp1 > 10){
            temp1 /= 10;
            weight *= 10; 
        }
        for(;weight>0;weight/=10){
            int temp = num - num % weight; //994-994%100=900
            num = num % weight; //94
            if(map.containsKey(temp)){ //900 or 400
                ans += map.get(temp);
                continue;
            }else if(temp > 5*weight){ //600
                ans += map.get(5*weight);
                temp -= 5*weight;
            }
            int res = temp/weight;
            for(;temp>0;res--){
                ans += map.get(weight);
            }
        }
        return ans;
    }
}

// 2.straightforward method
class Solution {
    public static String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
    }
}

//3.perfect solution
public class Solution {
    public String intToRoman(int number) {
        int[] numerals = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder str = new StringBuilder();
        for(int i=0;i<numerals.length;i++){
            while(number>=numerals[i]){
                number -= numerals[i];
                str.append(symbols[i]);
            }
        }
        return str.toString();
    }
}

