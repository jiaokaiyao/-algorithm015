import java.util.LinkedList;
import java.util.Queue;

/**
 * @author kaiya
 * @Desc  翻转二叉树
 * @date 2020/9/9 20:24
 */
public class InvertTreeSolution {
    /**
     * 递归求解 时间复杂度O(n) n是节点的个数空间复杂度O(n) n是树的高度
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return root;
        // 左右节点交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        // 递归处理左子树
        invertTree1(root.left);
        // 递归处理右子树
        invertTree1(root.right);
        return root;

    }

    /**
     * 迭代求解  时间复杂度O(N) 空间复杂度O(N) 其中N为节点的个数
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 依次假如队列 然后遍历取出交换
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
            // 只将非空的节点加入
            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
        return root;

    }
}
