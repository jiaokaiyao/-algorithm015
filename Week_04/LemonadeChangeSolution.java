/**
 * @author kaiya
 * @Desc 柠檬水找零
 * @date 2020/9/14 21:05
 */
public class LemonadeChangeSolution {
    /**
     * 直接一个一个问题找解
     * 时间复杂度O(N) N是数组的长度 空间复杂度O(1)
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        // 边界处理
        int len = (bills == null? 0 : bills.length);
        if (len == 0) return false;
        // 第一次如果不是收到的是5元 则都没有办法找零
        if (bills[0] != 5) {
            return false;
        }
        // 场景再现
        /**
         * 遍历记录5元与10元的数量  找零的钱只会是这两种 发生找零
         * 就减去相应的数量， 如果数量为0 则说明没有这个币种，则无法找零
         */

        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                // 没有5元，无法找零
                if (five < 0) return false;
                // 用掉了一张5元, 获得一张10元
                five--;
                ten++;
            } else {
                // 当前是20元，需要找零15  优先找零方案10+5 备选5*3
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    // 两种找零方案都不可行 则无法找零
                    return false;
                }
            }
        }

        return true;

    }
}
