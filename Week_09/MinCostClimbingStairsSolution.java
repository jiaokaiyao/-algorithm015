/**
 * @author kaiya
 * @title: MinCostClimbingStairsSolution
 * @projectName -algorithm015
 * @description: TODO 使用最小花费爬楼梯
 * 数组的每个索引作为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 *
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 *
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 * 示例 1:
 *
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
 * @date 2020/10/29  12:43
 */
public class MinCostClimbingStairsSolution {

    /**
     * 动态规划
     * DP方程：f(i) = cost(i) + Math.min(f(i + 1) ,f(i + 2)) 每次可以爬一个或两个阶梯
     * 从后往前计算
     * 时间复杂度：O(length(cost))
     * 空间复杂度：O(1)
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int f1 = 0, f2 = 0;
        for (int i = len - 1; i >= 0 ; i--) {
            int temp = Math.min(f1, f2) + cost[i];
            f2 = f1;
            f1 = temp;
        }
        return Math.min(f1, f2);
    }
}
