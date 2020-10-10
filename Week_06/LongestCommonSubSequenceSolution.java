/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 最长公共子序列
 * @date Date : 2020年10月10日 21:20
 */
public class LongestCommonSubSequenceSolution {

    /**
     * 动态规划法 【穷举+剪枝】
     * 相同的时候就在之前的状态上加上新的值
     * 不同的时候需要考虑之前的状态，将最优解转移过来
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = (text1 == null ? 0 : text1.length());
        int len2 = (text2 == null ? 0 : text2.length());
        if (len1 == 0 || len2 == 0) return 0;
        // 状态数组 防止索引越界 所以加1
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                // 获取当前位置的字符
                char c1 = text1.charAt(i), c2 = text2.charAt(j);
                if (c1 == c2) {
                    // 相等的时候公共子序列加1  当前位置是前一个位置的值+1
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    // 如果不相等 则公共子序列是text1往前退一格或text2往前退一格的最大值
                    dp[i + 1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
                
            }
            
        }
        return dp[len1][len2];
    }
}
