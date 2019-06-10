// 1.my solution
class Solution {
    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        char[] chs = s.toCharArray();
        int start = 0, end = 0, cur = chs.length-1;
        boolean first = true;
        while(cur>=0){
            while(cur >=0 && chs[cur] == ' ') cur--;
            if(cur<0) break;
            end = cur;
            while(cur >=1 && chs[cur-1] != ' ') cur--;
            start = cur--;
            if(!first) ans.append(" ");
            // System.out.println(start+" "+end);
            ans.append(s.substring(start, end+1));
            first = false;
        }
        return ans.toString();
    }
}

// 2.basic idea:
// firstly, reverse the entire String
// then, reverse each word and add sapces
class Solution {
    public String reverseWords(String s) {
        if (s.length() < 1) return s; // empty string
        int startIdx = 0;
        char[] str = s.toCharArray();
        // reverse whole string
        reverse(str, 0, str.length - 1);
        // reverse word one by one
        for (int i = 0; i < str.length; i++) {
            if (str[i] != ' ') {
                if (startIdx != 0) str[startIdx++] = ' ';
                int j = i;
                while (j < str.length && str[j] != ' ')
                    str[startIdx++] = str[j++];
                reverse(str, startIdx - (j - i), startIdx - 1); // startIdx - 1, NOT startIdx; 
                i = j;
            }
        }
        return new String(str, 0, startIdx);
    }

    private void reverse(char[] str, int begin, int end) {
        for (; begin < end; begin++, end--) {
            char tmp = str[begin];
            str[begin] = str[end];
            str[end] = tmp;
        }
    }
}