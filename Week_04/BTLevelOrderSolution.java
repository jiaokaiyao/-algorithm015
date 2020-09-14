import java.util.*;

/**
 * @author kaiya
 * @Desc 二叉树的层序遍历
 * @date 2020/9/14 17:57
 */
public class BTLevelOrderSolution {
    /**
     * 使用广度优先搜索BFS  维护一个队列  按层加入
     * 时间复杂度O(N)
     * 空间浮渣度O(N)
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        // 维护一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> result = new ArrayList<>();
            // 按层加入队列中，队列是先进先出的
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                result.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(result);
        }
        return res;

    }
}
