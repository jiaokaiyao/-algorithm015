学习笔记

# 本周核心

## 动态规划【Dynamic Programming】

1.最优子结构

2.存储子问题的解【存储中间状态】-一般使用数组存储中间状态

3.递推公式【状态转移方程（DP方程）】

**难点**：状态转移方程，一般找状态转移方程是通过案列推导

## 典型实战题目

### 最长公共子序列问题

思路：穷举+剪枝【动态规划】，两个指针分别遍历两个字符串，用二维数组存储子问题的解【状态数组】

代码：

```java
/**
     * 动态规划法 【穷举+剪枝】
     * 相同的时候就在之前的状态上加上新的值
     * 不同的时候需要考虑之前的状态，将最优解转移过来
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = (text1 == null ? 0 : text1.length());
        int len2 = (text2 == null ? 0 : text2.length());
        if (len1 == 0 || len2 == 0) return 0;
        // 状态数组 防止索引越界 所以加1
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                // 获取当前位置的字符
                char c1 = text1.charAt(i), c2 = text2.charAt(j);
                if (c1 == c2) {
                    // 相等的时候公共子序列加1  当前位置是前一个位置的值+1
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    // 如果不相等 则公共子序列是text1往前退一格或text2往前退一格的最大值
                    dp[i + 1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
                
            }
            
        }
        return dp[len1][len2];
    }
```

