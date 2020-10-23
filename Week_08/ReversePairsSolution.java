/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 翻转对
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 *
 * 你需要返回给定数组中的重要翻转对的数量。
 *
 * @date Date : 2020年10月23日 10:07
 */
public class ReversePairsSolution {

    /**
     * 归并排序
     * 时间复杂度： O(NlogN)
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        return mergesortAndCount(nums, 0 , nums.length - 1);
    }

    /**
     * 归并排序
     * nums[start .. mid] 和 nums[mid + 1 .. end]
     * 对于nums[i]，满足 nums[i] > 2 * nums[j] 的 j 的区间在第二个序列的左端点开始查找
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */
    private int mergesortAndCount(int[] nums, int start, int end) {
        if (start < end) {
            int mid = (start + end) >> 1;
            // 分成两个子序列 对这两个子序列分别采用归并排序
            int count = mergesortAndCount(nums, start, mid) + mergesortAndCount(nums, mid + 1, end);
            int j = mid + 1;
            for (int i = start; i <= mid ; i++) {
                while (j <= end && nums[i] > (nums[j] << 1)) {
                    j++;
                }
                count += j - (mid + 1);
                
            }
            // 合并
            merge(nums, start, mid, end);
            return count;
        }
        return 0;
    }

    /**
     * 结果合并
     * @param nums
     * @param start
     * @param mid
     * @param end
     */
    private void merge(int[] nums, int start, int mid, int end) {
        int n1 = (mid - start + 1);
        int n2 = (end - mid);
        int[] left = new int[n1], right = new int[n2];
        for (int i = 0; i < n1; i++) {
           left[i] = nums[start + i];
        }
        for (int j = 0; j < n2; j++) {
            right[j] = nums[mid + 1 + j];
        }
        int i = 0, j = 0;
        for (int k = start; k <= end; k++) {
            if (j >= n2 || (i < n1 && left[i] <= right[j])) {
                nums[k] = left[i++];
            } else {
                nums[k] = right[j++];
            }
            
        }
    }
}
