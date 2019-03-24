// 1.垃圾题！！真的垃圾
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new LinkedList<>();
        int cur = 0, sum = 0, len = 0;
        for(int i=0;i<words.length;i++){
            int count = i - cur + 1;
            len += words[i].length();
            sum = len + count - 1;
            if(i == words.length-1 && sum <= maxWidth){
                StringBuilder res = new StringBuilder(words[cur++]);
                System.out.println(sum);
                int space = maxWidth - sum;
                while(cur<words.length){
                    res.append(" ");
                    res.append(words[cur++]);
                }
                for(int k=0;k<space;k++) res.append(" ");
                ans.add(res.toString());
                break;
            }
            if(sum > maxWidth){
                int spaces = maxWidth - sum + words[i].length() + count - 1;
                count--;
                int[] space = computespace(spaces, count - 1);
                StringBuilder res = new StringBuilder(words[cur]);
                if(count == 1) for(int k=0;k<space[0];k++){
                    res.append(" ");
                } 
                else{
                    for(int j=0;j<space.length;j++){
                        for(int k=0;k<space[j];k++) res.append(" ");
                        res.append(words[++cur]);
                    }
                }
                ans.add(res.toString());
                cur = i;
                i = i - 1;
                sum = 0;
                len = 0;
            }
        }
        return ans;
    }
    
    private int[] computespace(int space, int intervals){
        if(intervals <= 0) return new int[]{space};
        int[] ans = new int[intervals];
        int remain = space % intervals, tmp = space / intervals;
        if(remain!=0) ans[intervals-1] = tmp;
        else for(int i=0;i<intervals;i++) ans[i] = tmp;
        while(remain != 0){
            tmp += remain / --intervals;
            remain = remain % intervals;
            if(remain!=0) ans[intervals-1] = tmp;
            else for(int i=0;i<intervals;i++) ans[i] = tmp;
        }
        return ans;
    }
}

// 2.readable
//首先要做的就是确定每一行能放下的单词数，这个不难，就是比较n个单词的长度和加上n - 1个空格的长度跟给定的长度L来比较即可
//找到了一行能放下的单词个数，然后计算出这一行存在的空格的个数，是用给定的长度L减去这一行所有单词的长度和。
//得到了空格的个数之后，就要在每个单词后面插入这些空格，这里有两种情况，比如某一行有两个单词"to" 和 "a"，给定长度L为6
//如果这行不是最后一行，那么应该输出"to   a"，如果是最后一行，则应该输出 "to a  "，所以这里需要分情况讨论，最后一行的处理方法和其他行之间略有不同。
//最后一个难点就是，如果一行有三个单词，这时候中间有两个空，如果空格数不是2的倍数，那么左边的空间里要比右边的空间里多加入一个空格，那么我们只需要用总的空格数除以空间个数
//能除尽最好，说明能平均分配，除不尽的话就多加个空格放在左边的空间里"
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> lines = new ArrayList<String>();
        int index = 0;
        while (index < words.length) {
            //count：该行所有单词累计总长度
            int count = words[index].length();
            //last:该行最后一个词的index
            int last = index + 1;
            while (last < words.length) {
                //out of bound
                if (words[last].length() + count + 1 > maxWidth) break;
                //plus one for the space, if its a perfect fit it will fit
                count += 1 + words[last].length();
                last++;
            }
            StringBuilder builder = new StringBuilder();
            //append该行第一个单词
            builder.append(words[index]);
            //这一行除去第一个已经append的单词，共剩下几个词语：diff 个：从index到last-1
            int diff = last - index - 1;
           // if last line or number of words in the line is 1, left-justified
            //最后一行：每个单词中间一个空格， 剩余补上空白
            if (last == words.length || diff == 0) {
                for (int i = index+1; i < last; i++) {
                    builder.append(" ");
                    builder.append(words[i]);
                }
                for (int i = builder.length(); i < maxWidth; i++) {
                    builder.append(" ");
                }
            } else {
                //不是最后一行：middle justified
                //这一行总space的个数：（长度-累计单词总长度）
                //每个单词后面space的个数：（长度-累计单词总长度）/单词个数
                // r为需要平均分配到中间的空格总数
                int spaces = (maxWidth - count) / diff;
                int r = (maxWidth - count) % diff;
                for (int i = index+1; i < last; i++) {
                    for(int k=spaces; k > 0; k--) {
                        builder.append(" ");
                    }
                    if(r > 0) {
                        builder.append(" ");
                        r--;
                    }
                    builder.append(" ");
                    builder.append(words[i]);
                }
            }
            lines.add(builder.toString());
            index = last;
        }
        return lines;
    }
}