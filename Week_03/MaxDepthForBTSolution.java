/**
 * @author kaiya
 * @Desc  二叉树的最大深度
 * @date 2020/9/9 21:01
 */
public class MaxDepthForBTSolution {
    /**
     * 递归求子树深度。 树的最大深度 = 最深子树深度+1【加一是根节点】
     * 时间复杂度O(N)  空间复杂度O(N)
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        // 当节点为空的时候，说明已经到最深了  终止递归
        if  (root == null) return 0;
        // 左子树深度
        int leftHeight = maxDepth(root.left);
        // 右子树深度
        int rightHeight = maxDepth(root.right);
        // 两者之中较大者
        int height = Math.max(leftHeight, rightHeight) + 1;
        return height;
    }
}
