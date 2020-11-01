/**
 * @author kaiya
 * @title: LongValidParentesesSolution
 * @projectName -algorithm015
 * @description: TODO 32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 *
 * 示例 1:
 *
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 *
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * @date 2020/10/31  22:27
 */
public class LongValidParentesesSolution {

    /**
     * 给定一个字符串  判断包含多少有效的括号
     * 时间复杂度O(N)
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int sLen = (s == null ? 0 : s.length());
        int result = 0;
        if (sLen == 0) return result;
        // 动态规划求解 dp[i] 表示第i个位置的最长有效括号长度
        int dp[] = new int[sLen];
        for (int i = 1; i < sLen; i++) {

            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // s的i和i-1个位置组合是（）时 dp[i] = dp[i -2] + 2;
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    // 当s的i位置为）且i-1也是） 那么dp[i] = dp[i - 1] + dp[i - dp[i - 1] -2] + 2
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                result = Math.max(result, dp[i]);
            }
        }
        return result;
    }
}
