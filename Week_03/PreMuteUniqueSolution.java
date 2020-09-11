import java.util.*;

/**
 * @author kaiya
 * @Desc 不重复全排列
 * @date 2020/9/11 18:07
 */
public class PreMuteUniqueSolution {
    /**
     * 回溯 + 剪枝[需要先对数组排序]
     * 时间复杂度： O (N * N!)
     * 空间复杂度： O（N* N!）
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        //
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len == 0) return res;
        boolean used[] = new boolean[len];
        // 先排序  排序是剪枝的前提
        Arrays.sort(nums);
        Deque<Integer> path = new ArrayDeque<>();
        dfs(0, len, path, nums, used, res);
        return res;
    }

    /**
     * 与全排列不同的是需要考虑重复元素 即增加了剪枝
     * @param depth
     * @param len
     * @param path
     * @param nums
     * @param used
     * @param res
     */
    private void dfs(int depth, int len, Deque<Integer> path, int nums[], boolean used[], List<List<Integer>> res) {
        // terminator
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len ; ++i) {
            if (used[i]) {
                // 已经使用过了  则结束这一次选择的处理  到下一个元素
                continue;
            }
            /**
             * 因为存在重复元素，还需要判断是否存在重复元素,
             * 如果存在重复元素，且上一个元素还没有被选中，证明选中已经撤销，则当前的选择会与之前的选择重复
             * 所以也跳过不选
             */
            if (i > 0 && (nums[i] == nums[i-1]) && (used[i-1] == false)) {
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true; // used
            dfs(depth + 1, len, path, nums, used, res);
            // reverse current level status
            path.removeLast();
            used[i] = false;
        }
    }
}
