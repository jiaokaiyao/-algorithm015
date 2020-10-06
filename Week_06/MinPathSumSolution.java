import java.lang.Math;
/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 最小路径和
 * @date Date : 2020年09月28日 21:35
 */
public class MinPathSumSolution {
    /**
     * 动态规划  时间复杂度 O(lenX*lenY)
     */
    public int minPathSum(int[][] grid) {
        int lenX = grid == null ? 0 : grid.length;
        if (lenX == 0) {
            return 0;
        }
        int lenY = grid[0].length;
        if (lenY == 0) return 0;
        int[][] dp = new int[lenX][lenY];
        dp[0][0] = grid[0][0];
        // 计算横向的和
        for (int i = 1; i <lenX; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        // 计算纵向的和
        for (int j = 1; j <lenY; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }
        // 获取最小路径和 取横向路径和和纵向路径和中的较小值与当前位置的和
        for (int i = 1; i < lenX; i++) {
            for (int j = 1; j < lenY; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[lenX - 1][lenY - 1];

    }
}
