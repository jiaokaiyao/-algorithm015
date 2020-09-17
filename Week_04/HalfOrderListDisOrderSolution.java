/**
 * @author kaiya
 * @Desc  使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方
 * 思路  :因为数组是半有序的，意思就是说存在一个元素，
 * 当先升后降的时候它同时大于它的前一个元素和后一个元素，
 * 当先降后升的时候它同时小于它的前一个元素和后一个元素
 * 所以二分查找符合这样条件的位置就可以了
 *
 * @date 2020/9/17 17:47
 */
public class HalfOrderListDisOrderSolution {
    public static void main(String[] args) {
//        int[] nums = new int[]{4, 5, 6, 7, 8, 0, 1, 2};
        int[] nums = new int[]{8, 7, 6, 5, 4, 9, 10, 11};
        int len = nums.length;
        int left = 0, right = len;
        while (left <= right) {
            int mid = (right - left) / 2 -left;
            // 先升后降
            if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid + 1]) {
                System.out.println("无序的位置为：：" + mid + "、" + mid+1);
//                System.out.println("无序的元素为：：" + nums[mid] + "、" + nums[mid+1]);
                break;
            }
            // 先降后升
            if (nums[mid] < nums[mid-1] && nums[mid] < nums[mid + 1]) {
                System.out.println("无序的位置为：：" + (mid) + "、" + (mid + 1));
//                System.out.println("无序的元素为：：" + nums[mid] + "、" + nums[mid+1]);
                break;
            }
            left = mid + 1;
            right = mid -1;
        }
    }
}
