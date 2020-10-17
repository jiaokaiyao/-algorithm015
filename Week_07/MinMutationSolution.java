import java.util.*;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 最小基因变化
 * @date Date : 2020年10月15日 22:28
 */
public class MinMutationSolution {


    int res = Integer.MAX_VALUE;

    /**
     * 回溯 + 剪枝
     * 时间复杂度：O(len(bank) * len(start))
     * @param start
     * @param end
     * @param bank
     * @return
     */
    public int minMutation(String start, String end, String[] bank) {
        dfs(new HashSet<String>(), 0, start, end, bank);
        return (res == Integer.MAX_VALUE ? -1 : res);
    }

    private void dfs(HashSet<String> step, int i, String startChange, String end, String[] bank) {
        // 如果当前变化次数已经比结果大了  则直接结束递归【剪枝】
        if (i >= res) {
            return ;
        }
        if (startChange.equals(end)) {
            res = Math.min(i, res);
            return;
        }
        char[] c = startChange.toCharArray();
        for (String s : bank) {
            char[] sArr = s.toCharArray();
            int num = 0;
            for (int j = 0; j < 8 ; j++) {
                if (c[j] != sArr[j]) {
                    num++;
                }
                
            }
            if (num == 1 && !step.contains(s)) {
                step.add(s);
                dfs(step, i + 1, s, end, bank);
                // 回溯
                step.remove(s);
            }
            
        }
    }
}
