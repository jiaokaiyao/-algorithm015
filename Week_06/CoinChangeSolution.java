/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 零钱兑换
 * @date Date : 2020年09月29日 21:51
 */
public class CoinChangeSolution {
    /**
     * 动态规划
     * 时间复杂度：O(MN) 其中M为需要兑换的钱的面值  N是硬币币种数量
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount < 1) return 0;
        int len = (coins == null ? 0 : coins.length);
        if (len == 0) return -1;
        return dp(coins, amount, new int[amount]);
    }


    /**
     *
     * @param coins 硬币仓库
     * @param remain 剩余待兑换
     * @param sub 子问题的解缓存
     * @return
     */
    private int dp(int[] coins, int remain, int[] sub) {
        // 如果用目标值减去硬币的值小于0 则说明不能凑齐 返回-1
        if (remain < 0) return -1;
        // 如果刚好等于0 则说明已经成功兑换
        if (remain == 0) return 0;
        if (sub[remain - 1] != 0) {
            return sub[remain - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = dp(coins, remain - coin, sub);
            if (res >= 0 && res < min) {
                min = res + 1;
            }
        }
        sub[remain - 1] = (min == Integer.MAX_VALUE ? -1 : min);
        return sub[remain -1];
        
    }

}
