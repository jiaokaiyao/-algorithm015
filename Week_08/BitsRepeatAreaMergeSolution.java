import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 合并区间
 * @date Date : 2020年10月22日 22:12
 */
public class BitsRepeatAreaMergeSolution {

    /**
     * 排序
     * 时间复杂度：O(nlogn)，其中 n 为区间的数量
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len == 0) {
            return new int[0][2];
        }
        // 左端点排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> merged = new ArrayList<>();
        // 如过可以合并，则左端点一定是连续的 及区间1的left<=区间2的left【在已经排序的情况下】
        for (int i = 0; i < len; i++) {
            int left = intervals[i][0], right = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < left) {
                // 不能合并 直接加入结果
                merged.add(new int[]{left, right});

            } else {
                // 可以合并， 右端点更新为最大的那个
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], right );
            }
        }
        return merged.toArray(new int[merged.size()][]);

    }
}
