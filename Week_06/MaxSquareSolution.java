/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 最大正方形
 * @date Date : 2020年09月30日 21:17
 */
public class MaxSquareSolution {

    /**
     * 时间复杂度 O（len(matrix)len(matrix[0].length)）
     * 找最大边长
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        int lenX = (matrix == null ? 0 : matrix.length);
        if (lenX == 0) return 0;
        int lenY = matrix[0].length;
        if (lenY == 0) return 0;
        int maxSide = 0;
        // dp二维数组保存dp[i][j] 位置的值
        int dp[][] = new int[lenX][lenY];
        for (int i = 0; i < lenX; i++) {
            for (int j = 0; j < lenY; j++) {
                if (matrix[i][j] == '1') {
                    // 在矩阵最顶边和矩阵最左边 则[i,j]为右下角的边长为1
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        // [i,j]为右下角的边长由他左边、左上、上边的dp决定
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j - 1]), dp[i-1][j-1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }

            }
            
        }
        int maxArea = maxSide * maxSide;
        return maxArea;
    }

}
