/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 颠倒二进制位
 * @date Date : 2020年10月21日 21:42
 */
public class ReverseBitsSolution {

    /**
     *  TODO  =================逐位颠倒 超出时间限制=======================================
     */

    /**
     * 逐位颠倒
     * @param n
     * @return
     */
    public int reverseBitsOld(int n) {
        Long res = 0L;
        int power = 31;
        while (n != 0) {
            // 在索引i位置的数，翻转之后在31-i位置【假定索引是以0开始的】
            res += (n & 1) << power;
            // 从右到左遍历输入的字符串
            n = n >> 1;
            power -= 1;
        }
        return res.intValue();

    }

    /**
     * 完全位运算 分治  32[16,16[8,8[4,4[2,2[1,1]]]]]
     * >>> 无符号右移  最高位无论怎样都补零
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);

       return n;
    }


}
