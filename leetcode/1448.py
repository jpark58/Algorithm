'''
class Solution {
    public int goodNodes(TreeNode root) {
        return preorder(root, root.val);
    }
    private int preorder(TreeNode n, int v) {
        if (n == null) // base cases.
            return 0;
        int max = Math.max(n.val, v); // maximum so far on the path.
        return (n.val >= v ? 1 : 0) + preorder(n.left, max) + preorder(n.right, max); // recurse to children.
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
    def goodNodes(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        def helper(root, v):
            if root == None:
                return 0

            max_so_far = max(v, root.val)

            return (1 if root.val >= v else 0) + helper(root.left, max_so_far) + helper(root.right, max_so_far)

        return helper(root, root.val)
