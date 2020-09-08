/**
 * @author kaiya
 * @Desc 替换空格
 * @date 2020/9/8 17:00
 */
public class ReplaceSpaceSolution {
/*
    库函数 */
   /* public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        String s1 = s.replace(" ", "%20");
        return s1;

    } */

    /**
     * 遍历替换  时间复杂度O(N)N是字符串的长度， 空间复杂度O(N)
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                sb.append("%20");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();

    }
}
