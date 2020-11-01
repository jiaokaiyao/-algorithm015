/**
 * @author kaiya
 * @title: MaxProfitSimpleSolution
 * @projectName -algorithm015
 * @description: TODO 买卖股票的最佳时机
 *  * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *  *
 *  * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *  *
 *  * 注意：你不能在买入股票前卖出股票。
 *  *
 *  *  
 *  *
 *  * 示例 1:
 *  *
 *  * 输入: [7,1,5,3,6,4]
 *  * 输出: 5
 *  * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *  *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * @date 2020/10/28  21:52
 */
public class MaxProfitSimpleSolution {
    /**
     * 最低点买入  最高点卖出
     * 时间复杂度O(N)
     * 空间复杂度：O(1)
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        int len = prices.length;
        for (int i = 0; i < len; i++) {
            if (prices[i] < minPrice) {
                // 记录一个最低点
                minPrice = prices[i];
            } else if ((prices[i] - minPrice) > maxProfit){
                // 最大收益
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;

    }
}
