/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 2的幂
 * @date Date : 2020年10月21日 21:27
 */
public class IsPowerOfTwoSolution {

    /**
     * 位运算  获取二进制中最右边的1
     * 通过位运算特征 x & (-x)获取二进制中最右边的1，并且其他位被置为0
     * 2的幂的数以二进制表示肯定是仅有一个1中其他位都为0
     * 所以获取到最右边的1之后得到的数x与（-x)做与运算，得到的应该还是原来的数
     * 时间复杂度：O(1)
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        // 边界处理
        if (n == 0) return false;
        long k = (long)n;
        return (k & (-k)) == k;
    }
}
