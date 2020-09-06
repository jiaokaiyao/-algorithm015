import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author kaiya
 * @Desc N叉树的层序遍历
 * @date 2020/9/6 22:01
 */
public class NTree {
    public int val;
    public List<NTree> children;

    public NTree() {}

    public NTree(int _val) {
        val = _val;
    }

    public NTree(int _val, List<NTree> _children) {
        val = _val;
        children = _children;
    }
}

class Solution {

    // 基于队列的广度优先搜索 时空复杂度：O(n) n是节点的数量
    public List<List<Integer>> levelOrder(NTree root) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<NTree> queue = new LinkedList<>();
        if(root == null) return res;
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NTree node = queue.poll();
                level.add(node.val);
                queue.addAll(node.children);
            }
            res.add(level);

        }
        return res;



    }
}
