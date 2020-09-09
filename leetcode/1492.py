'''
class Solution {
    public int kthFactor(int n, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        
        int half = n/2;
        
        for(int i = 2; i <= half; i++){
            if(n % i == 0) list.add(i);
        }
        list.add(n);
        
        if(list.size() < k) return -1;
        
        return list.get(k-1);
    }
}
'''


class Solution(object):
    def kthFactor(self, n, k):
        count = 0
        for i in range(1, n+1):
            if n % i == 0:
                count += 1
            if count == k:
                return i
        return -1
