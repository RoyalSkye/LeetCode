// 1.HashTable
class TwoSum {

    Map<Integer, Integer> map;
    
    /** Initialize your data structure here. */
    public TwoSum() {
        this.map = new HashMap<Integer, Integer>();
    }
    
    /** Add the number to an internal data structure.. */
    public void add(int number) {
        this.map.put(number, map.getOrDefault(number, 0) + 1);
    }
    
    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for(Map.Entry<Integer, Integer> entry: this.map.entrySet()) {
            int target = value - entry.getKey();
            if (target != entry.getKey()) {
                if(this.map.containsKey(target)) 
                    return true;
            } else {
                if(this.map.get(target) > 1)
                    return true;
            }
        }
        return false;
    }
}

// Python
class TwoSum:

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.map = {}

    def add(self, number: int) -> None:
        """
        Add the number to an internal data structure..
        """
        if number in self.map:
            self.map[number] += 1
        else:
            self.map[number] = 1

    def find(self, value: int) -> bool:
        """
        Find if there exists any pair of numbers which sum is equal to the value.
        """
        for num in self.map:
            if value - num in self.map and (value - num != num or self.map[num] > 1):
                return True
        return False


# Your TwoSum object will be instantiated and called as such:
# obj = TwoSum()
# obj.add(number)
# param_2 = obj.find(value)