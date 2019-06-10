// the same as lc151
class Solution {
    public void reverseWords(char[] str) {
        reverse(str, 0, str.length-1);
        int index = 0;
        for(int i=0;i<str.length;i++){
            if(str[i] != ' '){
                if(index != 0) str[index++] = ' ';
                int j = i;
                while(j < str.length && str[j] != ' ') str[index++] = str[j++];
                reverse(str, index-(j-i), index-1);
                i = j;
            }
        }
    }
    
    private void reverse(char[] str, int begin, int end){
        for(;begin<end;begin++,end--){
            char tmp = str[begin];
            str[begin] = str[end];
            str[end] = tmp;
        }
    }
}