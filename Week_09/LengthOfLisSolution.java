/**
 * @author kaiya
 * @title: LengthOfLisSolutin
 * @projectName -algorithm015
 * @description: TODO 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * @date 2020/10/30  20:50
 */
public class LengthOfLisSolution {
    /**
     * 动态规划
     * length(nums) = N
     * 时间复杂度：O（N*N)
     * 空间复杂度O(N)
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int len = (nums == null ? 0 : nums.length);
        if (len == 0) {
            return 0;
        }
        // dp状态数组：dp[i]表示第i位置上升子序列的长度，第i位置被选中
        int[] dp = new int[len];
        // 选定第一个作为起始位置
        dp[0] = 1;
        int maxRes = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    // 如果第i位置比第j位置的值大，那么选中第i位置放入队列组成更长的子序列
                   dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }
            maxRes = Math.max(maxRes, dp[i]);
        }
        return maxRes;

    }
}
