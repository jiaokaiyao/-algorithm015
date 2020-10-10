import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 最小覆盖子串
 * @date Date : 2020年10月09日 22:22
 */
public class MinWindowSolution {

    Map<Character, Integer> ori = new HashMap<>();
    Map<Character, Integer> cnt = new HashMap<>();
    public String minWindow(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        // 查找一个合适的窗口 【左起点  右终点】
        while (right < sLen) {
            ++right;
            if (right < sLen && ori.containsKey( s.charAt(right))) {
                char ch = s.charAt(right);
                cnt.put(ch, cnt.getOrDefault(ch, 0) + 1);
            }
            while (check() && left <= right) {
                char ch1 = s.charAt(left);
                if ((right - left + 1) < len) {
                    len = right - left + 1;
                    ansL = left;
                    ansR = left + len;
                }

                if (ori.containsKey(ch1)) {
                    cnt.put(ch1, cnt.getOrDefault(ch1, 0) - 1);
                }
                ++left;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    /**
     * 检测当前窗口是否可用
     * @return
     */
    public boolean check() {
        Iterator ite = ori.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry entry = (Map.Entry) ite.next();
            Character key = (Character)entry.getKey();
            Integer val = (Integer)entry.getValue();
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        }
        return true;
    }
}
