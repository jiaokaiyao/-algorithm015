import java.util.*;

/**
 * @author kaiya
 * @Desc 在每个树行中找最大值
 * @date 2020/9/15 21:31
 */
public class LargestValueSolution {
    /**
     * 按层遍历查找  广度优先遍历
     * 时间复杂度 O (N) 空间复杂度O(N)
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> path = new LinkedList<>();
        path.add(root);

        while (!path.isEmpty()) {
            int size = path.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = path.poll();
                // 计算最大值
                max = Math.max(node.val, max);
                // 按层加入队列
                if (node.left != null) path.add(node.left);
                if (node.right != null) path.add(node.right);
            }
            res.add(max);
        }
        return res;
    }
}
