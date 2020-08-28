学习笔记

# 学习总结

1. ## 数组、链表、跳表

   ![image-20200828201558992](C:\Users\kaiya\AppData\Roaming\Typora\typora-user-images\image-20200828201558992.png)

2. ## 栈、队列、优先队列、双端队列

![image-20200828204801241](C:\Users\kaiya\AppData\Roaming\Typora\typora-user-images\image-20200828204801241.png)

## 实战题目

- [盛最多水的容器](https://leetcode-cn.com/problems/container-with-most-water/)

  解题方法：

  1. 暴力求解：双重循环

  2. 双指针法：

     思想：两个指针[左右指针]分别指向数组的首和尾，然后指针对应数值较小的指针移动，依次计算面积，找出最大面积【双指针】

     要点：面积一定是取决于较小的边

     核心代码：

     ```java
      while (left < right) {
                // 容器的面积取决于较小的边
                int currantArea = Math.min(height[left], height[right]) * (right - left);
                // 保存已经找到的最大的面积
                maxArea = Math.max(currantArea, maxArea);
                 // 那个指针对应的值小哪个指针变化【移动】
                 if (height[left] < height[right]) {
                     left++;
                 } else {
                     right--;
                 }
             }
     ```

- [爬楼梯](https://leetcode.com/problems/climbing-stairs/)

  解题思路：

  1. 动态规划 ，第n阶的方式是n-1阶与n-2阶的和，相当于一个斐波拉契数列求和，那么可以将前两阶的方式用一个中间变量存起来，方便后算算n阶的时候使用 【动态规划】

     核心代码

     ```
      int step_one = 1; // 上一阶
            int step_two = 2; // 下一阶
            int all_step = 0;
            // 循环n-2次 时间复杂度为O(n)
             for( int i = 3; i <= n ; i++) {
                 // 缓存中间结果  减少计算次数
                 all_step = step_one + step_two;
                 step_one = step_two;
                 step_two = all_step;
     
             }
     ```

     

  2. 矩阵快速幂和通项公式没有弄明白

- [合并两个有序链表](https://leetcode-cn.com/problems/merge-two-sorted-lists/)

  解题思路：  找出两个链表中头结点较小的那个链表，将此节点与余下的所有节点合并即可【递归思想】

  核心代码

  ```java
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
          // 边界处理
          if (l1 == null) {
             return l2;
          }
          if (l2 == null) {
              return l1;
          }
          // 递归处理
          // 找出两个链表中头结点较小的那个链表，将此节点与余下的所有节点合并即可
          // 时间复杂度：O(m+n) 其中m和n分别是两个链表的各自长度
          // 空间复杂度：O(m+n) 其中m和n分别是两个链表的各自长度
  
          if(l1.val < l2.val) {
              l1.next = mergeTwoLists(l1.next, l2);
              return l1;
          } else {
            l2.next = mergeTwoLists(l1, l2.next) ;
            return l2;
          }
  
      }
  ```

  ##    主要算法思想

  1.动态规划 :

  - 考虑子问题的重叠性，动态决策
  - 典型题目： 爬楼梯等

  2.双指针：

  - 两个指针移动，
  - 典型题目：盛最多水的容器、删除排序数组中的重复项等

  3.哈希表优化 ：

  -  空间换时间，hash表暂存，因为hash表的访问时间复杂度是O(1)的
  - 典型题目：两数和
