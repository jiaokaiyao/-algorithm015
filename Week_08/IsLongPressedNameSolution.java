/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 长按键入
 * @date Date : 2020年10月21日 20:55
 */
public class IsLongPressedNameSolution {

    /**
     * 双指针
     * 时间复杂度 O(Len(typed))
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        int nameL = name.length(), typedL = typed.length();
        int i = 0, j = 0;
        while (j < typedL) {
            if (i < nameL && name.charAt(i) == typed.charAt(j)) {
                // 当前匹配 name的指针和typed的指针都移动
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                // 长按键 仅typed的指针移动
                j++;
            } else {
                // 既不是匹配到名字里面的字符  也不是长按键
                return false;
            }
        }
        return (i == nameL);
    }
}
