/**
 * @author kaiya
 * @Desc   Pow(x, n)
 * @date 2020/9/11 14:59
 */
public class PowXNSolution {
    // 分治  时间复杂度O(LOGn)
    public double myPow(double x, int n) {
        // 边界情况
        if (x == 0) return 0.0;
        if (n == 0) return 1.0;
        return ((n > 0)? quickPow(x, n) :  1.0/quickPow(x, -n));
    }

    private double quickPow(double x, int n) {
        // 递归终止
        if (n == 0) return 1.0;
        // 问题分解
        double temp = quickPow(x, n / 2);
        // 解合并
        return  (n % 2 == 0) ?  temp * temp :  temp * temp * x;
    }
}
