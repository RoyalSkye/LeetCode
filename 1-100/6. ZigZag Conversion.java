// 1. my solution - clsoe to Visit by Row
class Solution {
    public String convert(String s, int numRows) {
        String ans = "";
        char[] zigzag = s.toCharArray();
        int len = s.length();
        if(numRows == 1) return s;
        for(int i=0;i<numRows;i++){
            //System.out.println("i="+i);
            for(int j=0;j<=(len-1)/(numRows-1)+1;j+=2){
                int index1 = j*(numRows-1) + i;
                //index2 will not exist if its position is in Row one, numRows-1 or Column 1
                int index2 = (index1 <= numRows-1 || i == numRows-1 || i == 0) ? 0 : index1-2*i;
                //System.out.print("index1="+index1+" ");
                //System.out.println("index2="+index2);
                if(index2 !=0 && index2 <= len-1) ans += zigzag[index2];
                if(index1 <= len-1) ans += zigzag[index1];
            }
        }
        return ans;
    }
}

// 2. Visit by Row 
// k from 0, 1, ..., n
// Characters in row 0 are located at indexex: k(2numRows-2)
// Characters in row numRows-1 are located at indexex: k(2numRows-2)+numRows-1
// Characters in row i(except row 0 and numRows-1) are located at indexex: k(2numRows-2)+i and (k+1)(2numRows-2)-i
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }
}

// 3. Sort by Row
class Solution {
    public String convert(String s, int numRows) {
        StringBuilder ans = new StringBuilder();
        List<StringBuilder> list = new ArrayList<>();
        int len = s.length();
        if(numRows == 1) return s;
        char[] zigzag = s.toCharArray();
        for(int i=0;i<Math.min(numRows, len);i++){
            list.add(new StringBuilder());
        }
        int current = 0;
        boolean godown = false;
        for(int i=0;i<len;i++){
            list.get(current).append(zigzag[i]);
            if(current == numRows-1 || current == 0) godown = !godown;
            current += godown ? 1 : -1;
        }
        for(StringBuilder ss : list){
            ans.append(ss);
        }
        return ans.toString();
    }
}