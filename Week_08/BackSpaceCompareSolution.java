/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 比较含退格的字符串
 * @date Date : 2020年10月19日 20:48
 */
public class BackSpaceCompareSolution {

    /**
     * 使用栈的思想 遇到退格 删除栈顶元素，否则，入栈
     * 时间复杂度O(len(S)len(T))
     * @param S
     * @param T
     * @return
     */
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }

    private String build(String s) {
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '#') {
                if (sb.length() > 0) {
                    // 如果遇到退格符，则删除上一次加入的字符
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(c);
            }

        }
        return sb.toString();
    }
}
