import java.util.Arrays;

/**
 * @author kaiya
 * @title: MinPathsSumSolution
 * @projectName -algorithm015
 * @description: TODO 最小路径和
 *给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小
 * @date 2020/10/28  21:08
 */
public class MinPathsSumSolution {
    /**
     * 动态规划
     * 时间复杂度：O(row*col)
     * 空间复杂度：O(row*col)
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int row = (grid == null ? 0 : grid.length);
        if (row == 0) {
            return 0;
        }
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j > 0) {
                    // 第一行 当前位置 = 当前位置+前一个位置
                    dp[i][j] = grid[i][j] + dp[i][j-1];
                } else if (i > 0 && j == 0) {
                    // 第一列 当前位置 = 当前位置 + 上一个位置
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else if (i > 0 && j > 0){
                    // 非第一行和第一列， 当前位置 = Math.min(上边位置, 左边位置) + 当前位置
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }
        return dp[row - 1][col - 1];

    }
}
