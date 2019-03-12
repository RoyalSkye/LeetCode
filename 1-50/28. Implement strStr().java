// 1.O(m*n)
class Solution {
    public int strStr(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }
}

// 2.KMP - non-trivial - O(m+n)
class Solution {
    public int strStr(String haystack, String needle) {  //KMP-O(N+M)
        if(needle.length() == 0) return 0;
        if(haystack.length() == 0 || haystack.length() < needle.length()) return -1;
        char[] hh = haystack.toCharArray();
        char[] mm = needle.toCharArray();
        int[] nextArr = getNextArr(mm);
        int hi = 0, mi = 0;
        while(hi < hh.length && mi < mm.length){
            if(hh[hi] == mm[mi]){
                hi++;
                mi++;
            }else if(nextArr[mi] == -1){  //nextArr[mi] == 0 means compare hh[hi] with the start of needle
                hi++;
            }else{
                mi = nextArr[mi];
            }
        }
        return mi == mm.length ? hi - mi : -1;
    }
    
    private int[] getNextArr(char[] match){
        if(match.length == 1) return new int[]{-1};
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2, cn = 0;
        while(pos < match.length){
            if(match[pos-1] == match[cn]){
                next[pos++] = ++cn;
            }else if(cn > 0){
                cn = next[cn];
            }else{
                next[pos++] = 0;
            }
        }
        return next;
    }
}