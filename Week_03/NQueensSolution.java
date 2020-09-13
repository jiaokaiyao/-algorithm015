import java.util.Arrays;
import java.util.*;

/**
 * @author kaiya
 * @Desc N皇后问题
 * @date 2020/9/13 17:32
 */
public class NQueensSolution {
    /**
     * 集合 + 回溯
     * 时间复杂度：O(N!)
     * 空间浮渣度： O(N)
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int queens[] = new int[n];
        Arrays.fill(queens, -1);
        // 列
        Set<Integer> columns = new HashSet<>();
        // 正向斜线 从左下角向上斜的为正向 正向斜线上的点行下标+列下标相等
        Set<Integer> positive = new HashSet<>();
        // 反向斜线 从左上角向下斜的为反向 反向斜线上的点行下标-列下标相等
        Set<Integer> negative = new HashSet<>();
        backTracing(n, 0, queens, columns, positive, negative, res);
        return res;

    }

    private void backTracing(int n, int row, int[] queens, Set<Integer> columns, Set<Integer> positive, Set<Integer> negative, List<List<String>> res) {
        // terminater
        if (row == n) {
            res.add(generateSolve(queens, n));
            return;
        }
        for (int i = 0; i < n; i++) {
            // 列有没有 同列其他行没有才可以放
            if (columns.contains(i)) {
                continue;
            }
            // 正向斜线上没有，则可以放
            int positiveIndex = row + i;
            if (positive.contains(positiveIndex)) {
                continue;
            }
            // 反向斜线上没有，则可以放
            int negativeIndex = row - i;
            if (negative.contains(negativeIndex)) {
                continue;
            }
            // 记录放过queen的位置
            queens[row] = i;
            columns.add(i);
            positive.add(positiveIndex);
            negative.add(negativeIndex);
            backTracing(n, row + 1, queens, columns, positive, negative, res);
            // 撤销之前的操作
            queens[row] = -1;
            columns.remove(i);
            positive.remove(positiveIndex);
            negative.remove(negativeIndex);
        }
    }

    /**
     * 生成解
     * @param queens
     * @param n
     * @return
     */
    private List<String> generateSolve(int[] queens, int n) {
        List<String> solution = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            solution.add(new String(row));

        }
        return solution;
    }
}
