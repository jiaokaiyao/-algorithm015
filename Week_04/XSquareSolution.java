/**
 * @author kaiya
 * @Desc x 的平方根
 * @date 2020/9/16 19:42
 */
public class XSquareSolution {
    /**
     * 二分查找 时间复杂度O（logx）
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int left = 0, right = x, res = -1;
        while (left <= right) {
            // 中点  考虑数字太大的时候回越界 所以用长整型
            long mid = (right -left) / 2 + left;
            long cur = mid * mid;
            if (cur <= x) {
                // 如果小于就继续下一次中点查找  找最接近的那个数
                res = (int)mid;
                left  = (int)mid + 1;
            } else {
                right = (int)mid - 1;
            }
        }
        return res;

    }
}
