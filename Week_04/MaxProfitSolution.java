/**
 * @author kaiya
 * @Desc
 * @date 2020/9/15 21:50
 */
public class MaxProfitSolution {
    /**
     * 峰谷合并 连续的升就可以计算收益，最低点买入  最高点卖出
     * 时间复杂度O(N)
     * 空间复杂度：O(1)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int len = prices.length;
        for (int i = 1; i < len; i++) {
            if (prices[i] > prices[i-1]) {
                maxProfit += prices[i] - prices[i-1];
            }
        }
        return maxProfit;

    }
}
