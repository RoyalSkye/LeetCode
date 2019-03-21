// 1.mess...
class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        if(s.length()==0) return false;
        Map<Character, Integer> map = new HashMap<>();
        map.put('0',1);map.put('1',1);map.put('2',1);map.put('3',1);map.put('4',1);map.put('5',1);map.put('6',1);
        map.put('7',1);map.put('8',1);map.put('9',1);map.put('.',1);
        int e = s.indexOf("e");
        if(e == s.length()-1) return false;
        int end = (e==-1) ? s.length() : e;
        int start = s.charAt(0)=='+'||s.charAt(0)=='-' ? 1 : 0;
        int numcount = 0;
        for(int i=start;i<end;i++){
            if(s.charAt(i)=='.') map.put('.',map.get('.')-1);
            else numcount++;
            if(!map.containsKey(s.charAt(i)) || map.get('.')<0) return false;
        }
        if(numcount == 0) return false;
        if(end == s.length()) return true;
        start = s.charAt(end+1)=='+'||s.charAt(end+1)=='-' ? end+2 : end+1;
        map.put('.',0);
        if(start>=s.length()) return false;
        numcount = 0;
        for(int i=start;i<=s.length()-1;i++){
            if(s.charAt(i)=='.') map.put('.',map.get('.')-1);
            else numcount++;
            if(!map.containsKey(s.charAt(i)) || map.get('.')<0) return false; 
        }
        if(numcount == 0) return false;
        return true;
    }
}

// 2.clear solution
class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }
        
        return numberSeen && numberAfterE;
    }
}