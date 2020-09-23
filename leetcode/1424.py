'''
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int maxkey = 0;
        
        for(int i = nums.size()-1; i >= 0; i--){
            
            for(int j = 0; j < nums.get(i).size(); j++){
                if(map.containsKey(i+j)){
                    List<Integer> temp = map.get(i+j);
                    temp.add(nums.get(i).get(j));
                    map.put(i+j, temp);
                }else{
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums.get(i).get(j));
                    map.put(i+j, list);
                }
                
                maxkey = Math.max(maxkey, i+j);
            }
        }
        
        
        List<Integer> answer = new ArrayList<>();
        
        for(int i = 0; i <= maxkey; i++){
            if(map.containsKey(i)){
                for(int e: map.get(i)){
                    answer.add(e);
                }
            }
        }
        
        int[] res = new int[answer.size()];
        for(int i = 0; i < answer.size(); i++){
            res[i] = answer.get(i);
        }
        
        return res;
    }
}
'''


class Solution(object):
    def findDiagonalOrder(self, nums):
        """
        :type nums: List[List[int]]
        :rtype: List[int]
        """
        dic = {}
        for i in range(len(nums)):
            for j in range(len(nums[i])):
                dic[i+j] = dic.get((i+j), []) + [nums[i][j]]
        res = []
        for key in sorted(dic.keys()):
            res.extend(reversed(dic[key]))
        return res
