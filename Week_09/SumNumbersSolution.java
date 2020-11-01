/**
 * @author kaiya
 * @title: SumNumbersSolution
 * @projectName -algorithm015
 * @description: TODO 129. 求根到叶子节点数字之和
 * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
 *
 * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
 *
 * 计算从根到叶子节点生成的所有数字之和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 *     1
 *    / \
 *   2   3
 * 输出: 25
 * 解释:
 * 从根到叶子节点路径 1->2 代表数字 12.
 * 从根到叶子节点路径 1->3 代表数字 13.
 * 因此，数字总和 = 12 + 13 = 25.
 * 示例 2:
 *
 * 输入: [4,9,0,5,1]
 *     4
 *    / \
 *   9   0
 *  / \
 * 5   1
 * 输出: 1026
 * 解释:
 * 从根到叶子节点路径 4->9->5 代表数字 495.
 * 从根到叶子节点路径 4->9->1 代表数字 491.
 * 从根到叶子节点路径 4->0 代表数字 40.
 * 因此，数字总和 = 495 + 491 + 40 = 1026.
 * @date 2020/10/29  13:22
 */
public class SumNumbersSolution {

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);

    }

    /**
     * 深度优先搜索：每条路径代表的数字之和
     * 时间复杂度：O(N) 其中N为二叉树的节点数
     * 空间复杂度：O(N) 其中N为二叉树的节点数
     * @param root
     * @param preSum
     * @return
     */
    private int dfs(TreeNode root, int preSum) {
        // 遇到空 即叶子节点  返回0为
        if(root == null) return 0;
        int sum = preSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }
}
