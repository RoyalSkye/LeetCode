/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
// 1.Sorting - O(NlogN)
class Solution {
    // private class IntervalComparator implements Comparator<Interval> {
    //     @Override
    //     public int compare(Interval a, Interval b) {
    //         return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
    //     }
    // }
    
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() <= 1) return intervals;
        // Sort by ascending starting point using an anonymous Comparator
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
        LinkedList<Interval> result = new LinkedList<Interval>();
        for (Interval interval : intervals) {
            if(result.isEmpty() || result.getLast().end < interval.start){
                result.add(interval);
            }else{
                result.getLast().end = Math.max(result.getLast().end, interval.end);
            }
        }
        return result;
    }
}

// 2.Sort start & end respectively - O(NlogN) - 聪明的解法！
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        // sort start&end
        int n = intervals.size();
        int[] starts = new int[n];
        int[] ends = new int[n];
        for (int i = 0; i < n; i++) {
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        // loop through
        List<Interval> res = new ArrayList<Interval>();
        for (int i = 0, j = 0; i < n; i++) { // j is start of interval.
            if (i == n - 1 || starts[i + 1] > ends[i]) {
                res.add(new Interval(starts[j], ends[i]));
                j = i + 1;
            }
        }
        return res;
    }
}