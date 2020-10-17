import java.util.HashSet;
import java.util.Set;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 有效的数独
 * @date Date : 2020年10月12日 19:33
 */
public class ValidSudokuSolution {

    /**
     * 时间复杂度O(1)只遍历了81次
     * hashset保存已经存在的元素 ，如果存在多次就不是有效的数独
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        Set<String> valid = new HashSet();
        String column = " column:";
        String row = " row:";
        String box = " box:";
        String split = "-";
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    char num = board[i][j];
                    // 如果已经存在的值 添加会返回false 则通过此可以判断行、列、九宫格中是否有重复数字
                    boolean flag = !valid.add(num + row + i) || !valid.add(num + column + j)
                             || !valid.add(num + box + (i/3) + split +  (j/3));
                    if (flag) {
                        return false;
                    }
                }

            }

        }
        return true;

    }
}
