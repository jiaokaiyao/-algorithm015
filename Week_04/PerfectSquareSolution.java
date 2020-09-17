/**
 * @author kaiya
 * @Desc  有效的完全平方数
 * @date 2020/9/16 19:40
 */
public class PerfectSquareSolution {

    /**
     * 二分查找
     * 时间复杂度O(LOGnum)
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int left = 0, right = num;
        while (left <= right) {
            // 考虑数字太大可能越界  所以用长整型存储
            long mid = (right-left) / 2 + left;
            long cur = mid * mid;
            if (cur == num) {
                return true;
            } else if (cur < num){
                // 强制类型转换
                left = (int)mid + 1;
            } else {
                right = (int)mid - 1;
            }
        }
        return false;

    }
}
