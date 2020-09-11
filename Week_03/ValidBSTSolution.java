/**
 * @author kaiya
 * @Desc 验证二叉搜索树
 * @date 2020/9/9 20:46
 */
public class ValidBSTSolution {
    /**
     * 递归求解  中序遍历是一个升序的序列 左<根<右
     * 时间复杂度O(N) 空间复杂度O(N)
     * @param root
     * @return
     */
    long last = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        // 空树属于二叉搜索树
        if (root == null) return true;
        // 如果左不小于根  则不是
        if (!isValidBST(root.left)) return false;
        if (last >= root.val) return false;
        last = root.val;
        // 右子树一定是大于根和左子树的
        return isValidBST(root.right);

    }
}
