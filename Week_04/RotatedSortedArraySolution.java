/**
 * @author kaiya
 * @Desc 寻找旋转排序数组中的最小值
 * @date 2020/9/17 22:15
 */
public class RotatedSortedArraySolution {
    /**
     * 二分查找
     * 时间复杂度：O(logN) N为数组的长度
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int len = nums.length;
        // 边界情况
        if(len == 1) {
            return nums[0];
        }
        int left = 0, right = len - 1;
        // 如果数组的最后一个元素大于第一个元素，说明这个数组没有旋转，就本身是一个升序的数组
        if (nums[right] > nums[0]) {
            return nums[0];
        }
        int res = 0;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            // 因为是升序的 所以如果当前中点位置大于它的下一个位置，说明下一个位置这个点就是翻转的起点
            if (nums[mid] > nums[mid+1]) {
                res = nums[mid+1] ;
                break;
            }
            // 乱序的点 如果当前中点位置比他的前一个位置小，本来是升序的，所以这个点就是最小的点
            if (nums[mid] < nums[mid-1]) {
                res = nums[mid] ;
                break;
            }
            // 当前中点位置比第一个元素打，说明从第一个位置到当前位置都是升序的，所以左边往后移
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                // 相反往左移
                right = mid - 1;
            }
        }
        return res;
    }
}
