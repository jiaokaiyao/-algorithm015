import java.util.ArrayList;
import java.util.List;

/**
 * @author kaiya
 * @Desc 求子集
 * @date 2020/9/13 12:45
 */
public class SubsetsSolution {
    int subLen;

    /**
     * 回溯
     * 时间复杂度：O(N*2^N) 空间复杂度 :O(N*2^N)
     * 产生所有子集需要2^N 共需要循环N次去生成所有的子集
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = (nums == null ? 0 : nums.length);
        if (len == 0) {
            return res;
        }
        // 子集长度分类
        for (subLen = 0; subLen <= len; ++subLen) {
            backTracing(len, 0, new ArrayList<>(), nums, res);
        }
        return res;
    }

    private void backTracing(int len, int depth, ArrayList<Integer> path, int nums[], List<List<Integer>> res) {
        // 达到子集需要的长度  就加入结果集众并终止递归
        if (path.size() == subLen) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = depth; i < len; ++i) {
            // 加入当前遍历结果中
            path.add(nums[i]);
            backTracing(len, i + 1, path, nums, res);
            // 撤销弟子规之前的操作
            path.remove(path.size() - 1);
        }
    }
}
