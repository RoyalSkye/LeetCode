// 1.my solution - Vertical scanning better than Horizontal scanning
class Solution {
    public String longestCommonPrefix(String[] strs) {
        String ans = "";
        char current = ' ';
        int len = strs.length;
        if(len < 1) return ans; 
        for(int i=0;i<strs[0].length();i++){
            current = strs[0].charAt(i);
            for(int j=1;j<len;j++){
                if(i < strs[j].length() && strs[j].charAt(i) == current);
                else return ans;
            }
            ans += current;
        }
        return ans;
    }
}

// 2.Horizontal scanning LCP(S1,S2,...,Sn) = LCP(LCP(LCP(S1,S2),S3),...Sn)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }        
        return prefix;
    }
}

// 3.Divide and conquer LCP(S1,S2,...,Sn) = LCP(LCP(S1,Smid),LCP(Smid+1,Sn))
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";    
            return longestCommonPrefix(strs, 0 , strs.length - 1);
    }
    private String longestCommonPrefix(String[] strs, int l, int r) {
        if (l == r) {
            return strs[l];
        }
        else {
            int mid = (l + r)/2;
            String lcpLeft =   longestCommonPrefix(strs, l , mid);
            String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
            return commonPrefix(lcpLeft, lcpRight);
       }
    }
    String commonPrefix(String left,String right) {
        int min = Math.min(left.length(), right.length());       
        for (int i = 0; i < min; i++) {
            if ( left.charAt(i) != right.charAt(i) )
                return left.substring(0, i);
        }
        return left.substring(0, min);
    }
}

// 4.Binary search
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            minLen = Math.min(minLen, str.length());
        int low = 1;
        int high = minLen;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (isCommonPrefix(strs, middle))
                low = middle + 1;
            else
                high = middle - 1;
        }
        return strs[0].substring(0, (low + high) / 2);
    }
    private boolean isCommonPrefix(String[] strs, int len){
        String str1 = strs[0].substring(0,len);
        for (int i = 1; i < strs.length; i++)
            if (!strs[i].startsWith(str1))
                return false;
        return true;
    }
}

// 5.follow up - Implement a trie (Prefix trie)