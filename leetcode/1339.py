'''
Java version

class Solution {
    long total_sum = 0;
    long grand_max = -1;
    
    public int maxProduct(TreeNode root) {
        getTotal(root);
        long dummty = getMax(root);
        
        return (int)(grand_max % ((int)Math.pow(10, 9) + 7));
    }
    
    private void getTotal(TreeNode root){
        if(root != null){
            total_sum += root.val;
            getTotal(root.left);
            getTotal(root.right);
        }
    }
    
    private long getMax(TreeNode root){
        if(root == null) return 0;
        
        long left = getMax(root.left);
        long right = getMax(root.right);
        grand_max = Math.max(grand_max, (total_sum-left-right-root.val)*(left+right+root.val));
        return left + right + root.val;
    }
}
'''

# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right


class Solution(object):

    def maxProduct(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.res = 0
        self.total = 0

        def getTotal(root):
            if root:
                self.total += root.val
                getTotal(root.left)
                getTotal(root.right)

        def getMax(root):
            if root == None:
                return 0

            left = getMax(root.left)
            right = getMax(root.right)
            self.res = max(self.res, (self.total-left-right -
                                      root.val)*(right+left+root.val))
            return left + right + root.val

        getTotal(root)
        getMax(root)
        return self.res % (10**9+7)
