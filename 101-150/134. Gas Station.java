// 1.brute force
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        for(int i=0;i<len;i++){
            if(gas[i] < cost[i]) continue;
            int cur = 0, step = 0;
            for(int j=i;cur>=0 && step < len;j=(j+1)%len, step++){
                cur = cur + gas[j] - cost[j];
            }
            if(step == len && cur >= 0) return i;
        }
        return -1;
    }
}

// 2.two points: - Time:O(N) Space:O(1)
// If car starts at A and can not reach B. Any station between A and B (as start) can not reach B.(B is the first station that A can not reach.)
// If the total number of gas is bigger than the total number of cost. There must be a solution.
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int sumgas=0, sumcost=0, cur = 0, start = 0;
        for(int i=0;i<gas.length;i++){
            sumgas += gas[i];
            sumcost += cost[i];
            cur += gas[i] - cost[i];
            if(cur < 0){
                start = i + 1;
                cur = 0;
            }
        }
        if(sumgas < sumcost) return -1;
        else return start;
    }
}
