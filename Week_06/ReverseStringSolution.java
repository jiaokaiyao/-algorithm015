/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 反转字符串
 * @date Date : 2020年10月08日 22:38
 */
public class ReverseStringSolution {

    /**
     * 双指针求解
     * 时间复杂度：O(N)
     * @param s
     */
    public void reverseString(char[] s) {
        int len = (s == null ? 0 : s.length);
        if (len == 0) return;
        int left = 0, right = len -1;
        // 双指针往中间访问 逐一交换
        while (left <= right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            // 左指针右移  右指针左移
            left++;
            right--;
        }
    }

}
