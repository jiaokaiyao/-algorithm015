import java.util.Arrays;

/**
 * @author kaiya
 * @title: UniquePathsSolution
 * @projectName -algorithm015
 * @description: TODO 不同路径
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？
 * @date 2020/10/27  21:36
 */
public class UniquePathsSolution {

    /**
     * 动态规划
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(m*n)
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // 当前坐标的值只和左边和上边的值有关
                dp[j] = dp[j - 1] + dp[j];
            }
        }
        // m-1位置即为到达目标的路径总数
        return dp[m - 1];
    }
}
