/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 位1的个数
 * @date Date : 2020年10月21日 20:35
 */
public class HammingWeightSolution {

    /**
     * 暴力求解 循环和位移
     * 直接遍历数的32位，如果存在1，计数加1
     * 任何数字跟掩码1逻辑与，得到的都是这个数的最低位
     * 检查下一位时 掩码左移1位
     * @param n
     * @return
     */
    /*public int hammingWeight(int n) {
        int mask = 1;
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                res++;
            }
            mask <<= 1;
        }
        return res;
    }*/

    /**
     * 位运算操作  每次清零最低位的1
     * 时间复杂度：O(32) => O(1)
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int res = 0;
        // 当n为0时，结束循环
        while (n != 0) {
            res++;
            // 清零最低位的1
            n &= (n-1);
        }
        return res;
    }
}
