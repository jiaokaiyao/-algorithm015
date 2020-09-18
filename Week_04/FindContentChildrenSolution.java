import java.util.Arrays;

/**
 * @author kaiya
 * @Desc 分发饼干
 * @date 2020/9/17 21:25
 */
public class FindContentChildrenSolution {
    /**
     * 贪心 时间复杂度：O(M*N) M和N分别是孩童的数量和饼干的数量
     * 先排序后迭代查找
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int sLen = s.length;
        int gLen = g.length;
        int res = 0;
        int start = 0;
        for (int i = 0; i < gLen; i++) {
            while (start < sLen) {
                // 找到一个可以分配的饼干
                if (s[start] >= g[i]) {
                    res++;
                    start++;
                    break;
                }
                // 没有符合胃口的饼干  就看下一个
                start++;
            }
            // 饼干已经分完了
            if (start == s.length) {
                return res;
            }
        }
        return res;
    }
}
