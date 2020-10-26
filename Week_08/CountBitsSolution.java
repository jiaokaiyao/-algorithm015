/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，
 * 计算其二进制数中的 1 的数目并将它们作为数组返回。
 * @date Date : 2020年10月24日 10:24
 */
public class CountBitsSolution {

    /**
     * 循环找每个数位1的个数，相当于多个位1个数【汉明权重】问题求解
     * 时间复杂度：O((num+1)*N) 其中N为数x的位数
     * 空间复杂度：O(num+1):开辟了num+1长度的数组
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] res= new int[num + 1];
        for (int i = 0; i <= num ; i++) {
            res[i] = getOneBit(i);
        }
        return res;
    }

    private int getOneBit(int n) {
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
