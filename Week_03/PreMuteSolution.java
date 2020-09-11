import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author kaiya
 * @Desc 全排列问题
 * @date 2020/9/9 23:35
 */
public class PreMuteSolution {
    /**
     * 回溯
     * 假如有1,2,3 则所有的全排列 是1 + [2,3]的全排列
     * 2 + [1,3]的全排列
     * 3 + [1,2]的全排列
     * 由上可知, 这是一个树形结构，可以按照树的解法来解决此题
     * 时间复杂度：O(N * N!)
     * 空间复杂度：O(N * N!)
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        // 结果
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        // 边界
        if (nums == null || length == 0) return res;
        // 记录有没有被选过， 同一个元素不能使用两次
        boolean used[] = new boolean[length];
        Deque<Integer> cur = new ArrayDeque<>();
        backTracing(length,0, nums, cur, res, used);
        return res;
    }
    private void backTracing(int length, int depth, int[] nums
            , Deque<Integer> cur , List<List<Integer>> res, boolean used[] ) {
        // 得到需要长度的排列的时候 将其假如结果中， 然后终止递归
        if (depth == length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < length; i++) {
            // 使用的元素不能再使用
            if (!used[i]) {
                // 没有使用过加入当前路径中并将这个元素使用状态设置为已使用
                cur.addLast(nums[i]);
                used[i] = true;
                backTracing(length, depth + 1, nums, cur, res, used);
                // 需要回退递归之前执行过的操作，这是回溯的特征
                cur.removeLast();
                used[i] = false;
            }
        }
    }
}
