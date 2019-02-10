//1. Wrong
// class Solution {
//     public int reverse(int x) {
//         int ans = 0, newans = 0;
//         while(x!=0){
//             int remainder = x % 10;
//             newans = ans * 10 + remainder;
//             if((newans - remainder)/10 != ans) return 0;  //overflow
//             ans = newans;
//             x = x/10;
//         }
//         return ans;
//     }
// }

class Solution {
    public int reverse(int x) {
        int ans = 0, newans = 0;
        while(x!=0){
            int remainder = x % 10;
            newans = ans * 10 + remainder;
            if((newans - remainder)/10 != ans) return 0;  //overflow
            ans = newans;
            x = x/10;
        }
        return ans;
    }
}