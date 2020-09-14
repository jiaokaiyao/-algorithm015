import java.util.HashSet;

/**
 * @author kaiya
 * @Desc 最小基因变化
 * @date 2020/9/14 20:23
 */
public class MinMutationSolution {
    int minSteps = Integer.MAX_VALUE;
    public int minMutation(String start, String end, String[] bank) {
        HashSet<String> temp = new HashSet<>();
        dfs(0, start, end, bank, temp);
        return (minSteps == Integer.MAX_VALUE) ? -1 : minSteps;

    }

    /**
     *  深度优先搜索【回溯】  时间复杂度：O(N * K) N：基因库中的基因序列数  K序列的长度
     * @param step 变换到目标序列需要的步数
     * @param current 当前的原序列
     * @param end 目标序列
     * @param bank 基因库
     * @param temp 保存已经比较过得序列
     */
    private void dfs(int step, String current, String end, String[] bank, HashSet<String> temp) {
        // 如果原序列与目标序列相同  则变化完毕  得到需要的变化次数
        if (current.equals(end)) {
            minSteps = Math.min(step, minSteps);
        }
        // 因为目标序列实在基因库中， 所以从基因库中查找就可以了
        for (String s : bank) {
            int diff = 0;
            for (int i = 0; i < s.length(); i++) {
                // 变化一位，然后下探到下一层，
                if (current.charAt(i) != s.charAt(i)) {
                    if (++diff > 1) break;
                }
            }
            if (diff == 1 && !temp.contains(s)) {
                temp.add(s);
                // 进入下一次查找 将变化后的序列作为新的原始序列 变化次数加一
                dfs(step + 1, s, end, bank, temp);
                // 移除已经操作完的序列， 查找下一个变化步数最小的序列
                temp.remove(s);
            }

        }
    }
}
