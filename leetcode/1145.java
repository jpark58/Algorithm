class Solution {
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        if(root == null) return false;
        
        if(root.val == x){
            int left = count(root.left);
            int right = count(root.right);
            int parent = n - left - right -1;
            
            return (parent > left + right) || (right > parent+left) || (left > parent+right);
        }
        
        return btreeGameWinningMove(root.left, n , x) || btreeGameWinningMove(root.right, n, x);
    }
    
    private int count(TreeNode root){
        if(root == null) return 0;
        else return 1 + count(root.left) + count(root.right);
    }
}