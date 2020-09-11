/**
 * @author kaiya
 * @Desc
 * @date 2020/9/9 21:17
 */
public class MinDepthForBtSolution {
    /**
     *  递归求子树深度。 树的最小深度 = 最矮子树深度+1【加一是根节点】
     *  时间复杂度O(N)  空间复杂度O(N)
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        // 节点为空的时候说明已到终点，结束递归
        if (root == null) return 0;
        // 左子树深度
        int leftHeight = minDepth(root.left);
        // 右子树深度
        int rightHeight = minDepth(root.right);
        // 最终结果， 取两者中的最小值,当是一条链的时候， 就得取实际的有效长度
        int minHeight =  (leftHeight == 0 || rightHeight == 0) ? leftHeight + rightHeight + 1 : Math.min(leftHeight, rightHeight) + 1;
        return minHeight;
    }
}
