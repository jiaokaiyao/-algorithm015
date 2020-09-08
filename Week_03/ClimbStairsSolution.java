/**
 * @author kaiya
 * @Desc 爬楼梯问题
 * @date 2020/9/8 17:27
 */
public class ClimbStairsSolution {
    /**
     * 动态规划
     * f(n) = f(n-1) + f(n-2)
     * @param n
     * @return
     */
   /* public int climbStairs(int n) {
        if (n <= 2) return n;
        int fn1 = 2;
        int fn2 = 1;
        int fn = 0;
        for (int i = 3; i <= n; i++) {
            fn = fn1 + fn2;
            fn2 = fn1;
            fn1 = fn;
        }
        return fn;
    }*/

    /**
     * 通项公式
     * sqrt5 = Math.sqrt(5)
     * S(n) = (Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1)) / sqrt5
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        double sqrt5 = Math.sqrt(5);
        double sn = (Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1)) / sqrt5;
        return (int)sn;
    }
}
