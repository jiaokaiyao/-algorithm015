import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author kaiya
 * @Desc  二叉树的中序遍历
 * @date 2020/9/6 21:57
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
    class Solution {
        // 时间复杂度 O(n) n是树的深度
        // 空间复杂度  O(n)
    /*List<Integer> res = new ArrayList();
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return res;
        // 左子树、根、右子树
        if (root.left != null) {
            inorderTraversal(root.left);
        }
        res.add(root.val);
        if (root.right != null) {
            inorderTraversal(root.right);
        }
        return res;

    }*/

        // 时间复杂度 O(n) n是树的深度
        // 空间复杂度  O(n)
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = root;
            while (current != null || !stack.isEmpty()) {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                current = stack.pop();
                res.add(current.val);
                current = current.right;

            }
            return res;
        }
    }

}
