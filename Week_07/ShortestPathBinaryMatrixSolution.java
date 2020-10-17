import javax.sound.midi.SoundbankResource;
import java.security.ProtectionDomain;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 二进制矩阵中的最短路径
 * @date Date : 2020年10月17日 8:47
 */
public class ShortestPathBinaryMatrixSolution {

    // 每个方向上的索引变化
    private static int[][] directions = {{0,1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
    private static int row, col;

    /**
     * BFS搜索
     * 时间复杂度：O(N)
     * @param grid
     * @return
     */
    public static int shortestPathBinaryMatrix(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        // 边界处理 当左上顶点和右下顶点为不可行时  一定没有路径到达
        if (grid[0][0] == 1 || grid[row - 1][col - 1] == 1) {
            return -1;
        }
        // 记录当前位置的索引
        Queue<int[]> pos = new LinkedList<>();
        // 用原数组记录起点到当前位置的最短路径长度 第一个位置为1【可走】
        grid[0][0] = 1;
        pos.add(new int[]{0, 0});
        // 走一个位置  就把那个位置标记为1
        while (!pos.isEmpty() && grid[row - 1][col - 1] == 0) {
            int[] pL = pos.remove();
            // 当前位置路径长度
            int currentL = grid[pL[0]][pL[1]];
            for (int i = 0; i < 8; i++) {
                int newRow = pL[0] + directions[i][0];
                int newCol = pL[1] + directions[i][1];
                // 索引有效且当前位置可走的情况下  路径长度+1
                if (inGrid(newRow, newCol) && grid[newRow][newCol] == 0) {
                    pos.add(new int[]{newRow, newCol});
                    // 下一个位置的路径长度
                    grid[newRow][newCol] = currentL + 1;
                }
            }
        }
        // 如果最终右下顶点值为0 则说明没有路径通往右下顶点
        return grid[row - 1][col - 1] == 0 ? -1 : grid[row - 1][col - 1];
    }

    /**
     * 索引不能越界
     * @param newRow
     * @param newCol
     * @return
     */
    private static boolean inGrid(int newRow, int newCol) {
        return (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col);
    }

    public static void main(String[] args) {
        int[][] grid = {{0,0,0},{1,1,0},{1,1,0}};
        int i = shortestPathBinaryMatrix(grid);
        System.out.println("args = [" + i + "]");

    }
}
