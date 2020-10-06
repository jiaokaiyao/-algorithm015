/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 解码方法
 * @date Date : 2020年09月29日 22:18
 */
public class DocodeWaysSolution {

    /**
     * 时间复杂度： O(n) n 是需要解码字符串的长度
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int len = (s == null ? 0 : s.length());
        if (len == 0) return 0;
        int dp[] = new int[len];
        char[] sArr = s.toCharArray();
        // 如果是字符0  没有对应的解码
        if (sArr[0] == '0') {
            return 0;
        }
        // dp方程
        /**
         * dp[i]  s[i]的前缀子串的解码方法
         * when s[i] != '0' dp[i] = dp[i - 1] 如果s[i] 不等于0 则s[i]的前缀子串的解码方法是第i-1子串的解码方法*1
         */
        dp[0] = 1;
        for (int i = 1; i < len; i++) {
            if (sArr[i] != '0') {
                dp[i] = dp[i-1];
            }
            int num = 10 * (sArr[i-1] - '0') + sArr[i] - '0';
            if (num >= 10 && num <= 26) {
                if (i == 1) {
                    dp[i]++;
                } else {
                    dp[i] += dp[i - 2];
                }
            }
            
        }
        return dp[len - 1];


    }
}
