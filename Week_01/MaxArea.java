import java.util.Deque;
import java.util.Queue;

/**
 * @author kaiya
 * @Desc 最多水容器
 * @date 2020/8/28 18:56
 */
public class MaxArea {

    public static void main(String[] args) {
        int[] height = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = maxArea(height);
        System.out.println(maxArea);
    }

    public static int maxArea(int[] height) {
        // 双指针
        // 时间复杂度：O(n) n是数组的长度
        // 空间复杂度：O(1) 没有开辟大量的空间
        int left = 0, right = height.length - 1;
        int maxArea = 0;
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
        return maxArea;

    }
}
