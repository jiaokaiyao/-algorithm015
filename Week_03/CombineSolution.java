import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author kaiya
 * @Desc 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合
 * @date 2020/9/8 22:57
 */
public class CombineSolution {
    // 回溯【深度优先遍历】 + 剪枝
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) return res;
        Deque<Integer> stack = new ArrayDeque<>();
        dfs(n, 1, k, stack, res);
        // 返回
        return res;
    }
    private void dfs(int n, int index, int k, Deque<Integer> stack, List<List<Integer>> res) {
        // 已经添加到看K个元素的时候 就将其加入结果中
        if (stack.size() == k) {
            res.add(new ArrayList<>(stack));
            return;
        }
        // n- (k-stack.size()) + 1 剪枝
        for (int i = index; i <= n - (k- stack.size()) + 1; i++) {
            stack.addLast(i);
            // 下探 往下遍历
            dfs(n, i + 1, k, stack, res);
            // 深度优先遍历可能回退， 即递归之前做了什么，递归之后要逆向操作
            stack.removeLast();
        }

    }
}
