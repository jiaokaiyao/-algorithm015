/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 回文子串
 * @date Date : 2020年10月07日 21:28
 */
public class CountSubstringsSolution {
    /**
     * Manacher 算法
     * 时间复杂度：O(sLen)
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int result = 0;
        int sLen = (s == null ? 0 : s.length());
        if (sLen == 0) {
            return result;
        }
        // Manacher 算法 需要构造新的字符串
        StringBuffer sb = new StringBuffer("$#");
        for (int i = 0; i < sLen; i++) {
            sb.append(s.charAt(i)).append("#");
        }
        sLen = sb.length();
        sb.append("!");
        // 记录以 s 的第i位为回文中心的最大回文半径
        int[] helper = new int[sLen];
        int  lMax = 0, rMax = 0;
        for (int i = 1; i < sLen ; ++i) {
            helper[i] = i <= rMax? Math.min(rMax - i + 1, helper[2 * lMax - i]) : 1;
            // 中心向两边拓展
            while (sb.charAt(i + helper[i]) == sb.charAt(i - helper[i])) {
                ++helper[i];

            }
            // 动态维护lMax 与 rMax
            if (i + helper[i] - 1 > rMax) {
                lMax = i;
                rMax = i + helper[i] - 1;
            }
            // 答案统计 当前贡献值为(helper[i]) / 2
            result += helper[i] / 2;

        }
        return result;
    }
}
