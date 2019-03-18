/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
// 1.Sorting - same as 56 
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        int n = intervals.size();
        int[] starts = new int[n+1];
        int[] ends = new int[n+1];
        List<Interval> ans = new ArrayList<>();
        for(int i=0;i<n;i++){
            starts[i] = intervals.get(i).start;
            ends[i] = intervals.get(i).end;
        }
        starts[n] = newInterval.start;
        ends[n] = newInterval.end;
        Arrays.sort(starts);
        Arrays.sort(ends);
        for(int i=0,j=0;i<n+1;i++){
            if(i==n || starts[i+1] > ends[i]){
                ans.add(new Interval(starts[j], ends[i]));
                j = i+1;
            }
        }
        return ans;
    }
}

// 2. merge as traversal
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> ans = new LinkedList<>();
        for(Interval interval : intervals){
            if(newInterval == null || interval.end < newInterval.start){
                ans.add(interval);
            }else if(interval.start > newInterval.end){
                ans.add(newInterval);
                ans.add(interval);
                newInterval = null;
            }else{
                newInterval.start = Math.min(interval.start, newInterval.start);
                newInterval.end = Math.max(interval.end, newInterval.end);
            }
        }
        if(newInterval!=null) ans.add(newInterval);
        return ans;
    }
}