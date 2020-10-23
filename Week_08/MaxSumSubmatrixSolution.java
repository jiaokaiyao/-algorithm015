import java.util.TreeSet;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 矩形区域不超过 K 的最大数值和
 * 给定一个非空二维矩阵 matrix 和一个整数 k，找到这个矩阵内部不大于 k 的最大矩形和。
 *
 * 示例:
 *
 * 输入: matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出: 2
 * 解释: 矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 *
 * @date Date : 2020年10月23日 20:32
 */
public class MaxSumSubmatrixSolution {

    /**
     * 双指针
     * @param matrix
     * @param k
     * @return
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int x = matrix.length, y = matrix[0].length;
        int[][] sumMatrix = new int[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                // 计算列的和
                if (j == 0) {
                    sumMatrix[i][j] = matrix[i][j];
                } else {
                    sumMatrix[i][j] = sumMatrix[i][j - 1] + sumMatrix[i][j];
                }

            }

        }
        int res = Integer.MIN_VALUE;
        for (int l = 0; l < y; l++) {
            for (int r = l; r < x; r++) {
                TreeSet<Integer> set = new TreeSet<>();
                int cur = 0;
                // l. r固定两边的情况下，用p去扫描每一列，
                for (int p = 0; p < x; p++) {
                   if (l  > 0) {
                       cur += sumMatrix[p][r] - sumMatrix[p][l - 1];
                   } else {
                       cur += sumMatrix[p][r];
                   }
                    // ceiling:返回集合中大于或等于给定数字的数【最接近给定的数字的数字】
                   Integer sub = set.ceiling(cur - k);
                   if (sub != null) {
                       res = Math.max(sub - cur, res);
                   }
                   set.add(cur);
                }
            }
        }
        return res;

    }
}
