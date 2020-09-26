'''
class Solution {
    public boolean canJump(int[] nums) {
        int last = nums.length-1;
        
        int max = 0;
        for(int i = 0; i < last+1; i++){
            if(i > max) return false;
            max = Math.max(nums[i]+i, max);
        }
        
        return true;
    }
}
'''


class Solution(object):
    def canJump(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """

        max_idx = 0
        for i in range(0, len(nums)):
            if i > max_idx:
                return False
            max_idx = max(max_idx, i+nums[i])

        return True
