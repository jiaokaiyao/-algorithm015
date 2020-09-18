/**
 * @author kaiya
 * @Desc 搜索旋转排序数组
 * @date 2020/9/18 19:38
 */
public class RotatedArraySearchTargetSolution {
    /**
     * 二分查找  时间复杂度O（LOGN）
     * 确定一段有序的子序列 然后在这段序列中二分查找
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len -1;
        if (len == 1) {
            if (nums[0] == target) return 0;
            return -1;
        }
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            // 如果存在相等  即找到了
            if (nums[mid] == target) {
                return mid;
            }
            // [left,mid]
            if (nums[mid] >= nums[left]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (nums[mid] <= nums[right]) {
                // [mid, right]
                if (nums[mid]  < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
