import java.util.HashMap;
import java.util.Map;

/**
 * @author kaiya
 * @title: IsIsomorphicSolution
 * @projectName -algorithm015
 * @description: TODO 205 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 *
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 *
 * 示例 1:
 *
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 *
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 *
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 * @date 2020/10/29  13:34
 */
public class IsIsomorphicSolution {

    /**
     *  hash表映射解决
     *  时间复杂度 ： O(len)
     *  空间复杂度：O(len)
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        return hashMapping(s, t) && hashMapping(t, s);
    }

    /**
     *  hash映射匹配
     * @param s
     * @param t
     * @return
     */
    private boolean hashMapping(String s, String t) {
        int len = s.length();
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (map.containsKey(sChar)) {
                // 存在且映射的字符不是之前映射的字符的话， 则不是同构
                if (map.get(sChar) != tChar) {
                    return false;
                }
            } else {
                map.put(sChar, tChar);
            }

        }
        return true;
    }
}
