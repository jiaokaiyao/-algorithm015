/**
 * @author kaiya
 * @Desc 岛屿数量
 * @date 2020/9/18 21:31
 */
public class NumIslandsSolution {
    /**
     * 深度优先搜索，搜索到一个陆地之后岛屿+1 然后把相邻的陆地都替换为0
     * 时间复杂度：O(MN) M是行数 N是列数
     * 空间复杂度：O(MN) 最坏情况下需要深度优先搜索整个网格
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        // 如果二位数组为空  则没有岛屿
        int x = (grid == null ? 0 : grid.length);
        if (x == 0) {
            return 0;
        }
        int y = grid[0].length;
        int numsIslands = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == '1') {
                    // 有一个陆地则岛屿+1
                    numsIslands++;
                    // 将相邻的陆地1【横向相邻  竖向相邻】的陆地都变成0
                    zeroFilling(grid, i, j);
                }

            }

        }
        return  numsIslands;
    }

    /**
     * 1替换0
     * @param grid
     * @param i
     * @param j
     */
    private void zeroFilling(char[][] grid, int i, int j) {
        int x = grid.length;
        int y = grid[0].length;
        // 如果索引小于或大于等于实际的索引边界 或者原本就是0的时候不做处理
        if (i < 0 || j < 0 || i >= x || j >= y || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        // 当前位置左边位置1替换0
        zeroFilling(grid, i - 1, j);
        // 当前位置右边位置1替换0
        zeroFilling(grid, i + 1, j);
        // 当前位置上边位置1替换0
        zeroFilling(grid, i, j - 1);
        // 当前位置下边位置1替换0
        zeroFilling(grid, i, j + 1);
    }
}
