import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO N皇后
 * @date Date : 2020年10月21日 22:31
 */
public class SolveNQueensSolution {

    /**
     * 时间复杂度：O(N!) N是皇后的数量
     * 空间复杂度：O(N)
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        List<List<String>> res = new ArrayList<>();
        solve(res, queens, n, 0, 0, 0, 0);
        return res;
    }


    /**
     *
     * @param res
     * @param queens
     * @param n
     * @param columns  列标记
     * @param rows 行标记
     * @param positives 正向对角线【左上顶点->右下顶点】
     * @param negatives 反向对角线【右上顶点->左下顶点】
     */
    private void solve(List<List<String>> res, int[] queens, int n, int columns, int rows, int positives, int negatives) {
        if (rows == n) {
            List<String> board = generateBoard(queens, n);
            res.add(board);
        } else {
            // 获取可能可用的位置 1 << n => 2^n
            int availablePositions = ((1 << n) - 1) & (~(columns | positives | negatives));
            while (availablePositions != 0) {
                // 获取最低的一位1
                int position = availablePositions & (-availablePositions);
                // 使用过后清零最低一位1
                availablePositions = availablePositions & (availablePositions -1);
                // 将指定位置设置为1， 即皇后的位置
                // 当前行放置皇后, 如果皇后放置在第 i 列，则将三个整数的第 i 个二进制位
                // （指从低到高的第 i 个二进制位）的值设为 1；
                int column = Integer.bitCount(position - 1);
                queens[rows] = column;
                /**
                 * 查找下一行可能的解，columns的值保持不变， negatives左移一位
                 * positives右移一位，因为棋盘的最左列对应每个整数的最低二进制位
                 * ，即每个整数的最右二进制位，因此对整数的移位操作方向和对棋盘的
                 * 移位操作方向相反，即negatives右移一位，positives左移一位
                 */
                solve(res, queens, n, columns | position
                        , rows + 1, (positives | position) << 1
                        , (negatives | position) >> 1);
                queens[rows] = -1;
            }
        }

    }

    /**
     * 生成一行数据【解】
     * @param queens
     * @param n
     * @return
     */
    private List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            // queen的位置
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
