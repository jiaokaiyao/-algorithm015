import java.util.ArrayList;
import java.util.List;

/**
 * @author kaiya
 * @Desc 括号生成
 * @date 2020/9/9 15:51
 */
public class GenerateParenthesis {
    /**
     * 暴力求解 时间复杂度O(N2^2n) 空间复杂度O(N)
     * @param n
     * @return
     */
   /* public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        char temp[] = new char[2*n];
        getParenthesis(0, temp, res);
        return res;
    }*/

    private void getParenthesis(int pos, char[] temp, List<String> res) {
        if (pos == temp.length) {
            // 暴力添加的需要判断有效性
            if (validParenthesis(temp)) {
                res.add(new String(temp));
            }
            return;
        }
        // current logic
        temp[pos] = '(';
        getParenthesis(pos + 1, temp, res);
        temp[pos] = ')';
        getParenthesis(pos + 1, temp, res);

    }

    private boolean validParenthesis(char[] temp) {
        int balance = 0;
        for (char c : temp) {
            if (c == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
    // ======================================分隔线======================

    /**
     * 回溯【深度优先遍历】 时间复杂度O(n) 空间复杂度O(n)
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // 边界情况考虑
        if (n == 0) return res;
        backTracing(n, n, "", res);
        return res;

    }

    /**
     *
     * @param left 可用的左括号数量
     * @param right 可用的右括号数量
     * @param current 当前的字符串
     * @param res 最终结果
     */
    private void backTracing(int left, int right, String current, List<String> res) {
        if (left == 0 && right == 0) {
            // 左右括号用完了，则假如结果中
            res.add(current);
        }
        // 剪枝： 如果左括号大于右括号的时候，那继续组成的括号肯定是无效的了  所以可以过滤掉
        if (left > right) {
            return;
        }
        // 左边还没有用完  就添加左括号， 添加的时候需要减少可用数量
        if (left > 0) {
            backTracing(left-1, right, current + "(", res);
        }
        // 右边没有用完  就添加右边， 添加的时候需要减少可用数量
        if (right > 0) {
            backTracing(left, right - 1, current + ")", res);
        }
    }
}
