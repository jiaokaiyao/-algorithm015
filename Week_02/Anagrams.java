import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kaiya
 * @Desc 字母异位词分组
 * @date 2020/9/6 22:06
 */
public class Anagrams {

    // 计数分类法
    // 时间复杂度和空间复杂度均是O(MN) 其中M是字符串数组的长度  N是字符数组中最长字符串的长度
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList();
        Map<String, List<String>> temp = new HashMap<>();
        for (String s : strs) {
            char[] ch = new char[26];
            for (char ch1 : s.toCharArray()) ch[ch1 - 'a']++;
            String key = String.valueOf(ch);
            if (!temp.containsKey(key)) {
                temp.put(key, new ArrayList());
            }
            temp.get(key).add(s);

        }
        return new ArrayList<>(temp.values());

    }
}
