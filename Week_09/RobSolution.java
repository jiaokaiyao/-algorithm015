/**
 * @author kaiya
 * @title: RobSolution
 * @projectName -algorithm015
 * @description: TODO 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * @date 2020/10/28  20:49
 */
public class RobSolution {

    /**
     * 动态规划
     * 时间复杂度：O(length(nums))
     * 空间复杂度：O(length(nums))
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int len = (nums == null ? 0 : nums.length);
        if (len == 0) return 0;
        // 边界条件
        if (len == 1) {
            return nums[0];
        }
        // 第i个位置能偷盗的最大金额
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            /**
             * 当前能偷盗的最大值是取两种方案中的最优值
             * 方案1：偷i位置 ，则不能偷盗i-1位置，只能偷i-2位置 dp[i] = nums[i] + dp[i - 2]
             * 方案2：不偷i位置，则目前得到的金额即为i-1位置得到的金额，即dp[i] = dp[i-1]
             */
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return  dp[len - 1];
    }

}
