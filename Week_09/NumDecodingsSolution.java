/**
 * @author kaiya
 * @title: NumDecodingsSolution
 * @projectName -algorithm015
 * @description: TODO 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 题目数据保证答案肯定是一个 32 位的整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入："12"
 * 输出：2
 * 解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
 * @date 2020/10/30  21:12
 */
public class NumDecodingsSolution {

    /**
     * 动态规划
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int len = s.length();
        // 保存第i位置的解码方式,长度加一  不用关心边界越界
        int[] dp = new int[len + 1];
        dp[len] = 1;
        // 最后一个位置不为0字符，就初始化为1
        if (s.charAt(len - 1) != '0') {
            dp[len - 1] = 1;
        }
        for (int i = len - 2; i >= 0 ; i--) {
            // 如果数字字符为0，不代表任何字符
            if (s.charAt(i) == '0') {
                continue;
            }
            int res = dp[i + 1];
            int res1 = 0;
            // 十位
            int ten = (s.charAt(i) - '0') * 10;
            // 个位
            int one = (s.charAt(i+ 1) - '0');
            // 如果s [ i ] 和 s [ i + 1 ] 组成的数字小于等于 26，那么dp [ i ] = dp[ i + 1 ] + dp [ i + 2 ]
            if (one + ten <= 26) {
                res1 = dp[i + 2];
            }
            dp[i] = res + res1;
            
        }
        // 从后往前的  所以取0位置
        return dp[0];
    }
}
