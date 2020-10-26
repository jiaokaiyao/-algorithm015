import java.util.Arrays;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO N皇后II
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * 思路：通N皇后，只是将具体解改为计数解的数量
 * @date Date : 2020年10月24日 20:58
 */
public class SolveNQueenTwoSoltution {
    /**
     * 时间复杂度：O(N!) N是皇后的数量
     * 空间复杂度：O(N)
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        int count = solve(queens, n, 0, 0, 0, 0);
        return count;
    }


    /**
     *
     * @param queens
     * @param n
     * @param columns  列标记
     * @param rows 行标记
     * @param positives 正向对角线【左上顶点->右下顶点】
     * @param negatives 反向对角线【右上顶点->左下顶点】
     */
    private int solve(int[] queens, int n, int columns, int rows, int positives, int negatives) {
        if (rows == n) {
            return 1;
        } else {
            int count = 0;
            // 获取可能可用的位置 1 << n => 2^n
            int availablePositions = ((1 << n) - 1) & (~(columns | positives | negatives));
            while (availablePositions != 0) {
                // 获取最低的一位1
                int position = availablePositions & (-availablePositions);
                // 使用过后清零最低一位1
                availablePositions = availablePositions & (availablePositions -1);
                // 将指定位置设置为1， 即皇后的位置
                int column = Integer.bitCount(position - 1);
                queens[rows] = column;
                count += solve(queens, n, columns | position
                        , rows + 1, (positives | position) << 1
                        , (negatives | position) >> 1);
                queens[rows] = -1;
            }
            return count;
        }

    }

}
