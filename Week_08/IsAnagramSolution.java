/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * @date Date : 2020年10月23日 9:34
 */
public class IsAnagramSolution {

    /**
     * 计数解决
     * 时间复杂度 : O(N) N为字符串的长度
     * 空间复杂度：O(26) => O（1）
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        if (sLen != tLen) return false;
        int[] count = new int[26];
        // s 计数加
        for (int i = 0; i < sLen; i++) {
            count[s.charAt(i) - 'a']++;
        }
        // t计数减
        for (int i = 0; i < tLen ; i++) {
            count[t.charAt(i) - 'a']--;
        }
        // 判断计数中是否存在非零的
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
