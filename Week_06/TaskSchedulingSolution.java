import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 任务调度器
 * @date Date : 2020年10月07日 20:51
 */
public class TaskSchedulingSolution {

    /**
     * 优先队列
     * 时间复杂度：O(times)
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        // 优先队列存储所有任务 ，每次从队列中选择n+1个任务执行
        int[] temp = new int[26];
        // 先将所有任务需要执行的次数置为1
        for (char task : tasks) {
            temp[task - 'A']++;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
        for (int i : temp) {
            if (i > 0) {
                queue.add(i);
            }
        }
        // 调度的次数
        int times = 0;
        while (!queue.isEmpty()) {
            int k = 0;
            List<Integer> helper = new ArrayList<>();
            while (k <= n) {
                if (!queue.isEmpty()) {
                    if (queue.peek() > 1) {
                        // 如果选择调度当前任务 则当前任务需要减1
                        helper.add(queue.poll() - 1);
                    } else {
                        // 无需执行减1操作
                        queue.poll();
                    }
                }
                times++;
                // 如果队列中没有任务需要调度 则退出
                if (queue.isEmpty() && helper.size() == 0) {
                    break;
                }
                k++;

            }
            for (Integer task : helper) {
                queue.add(task);
            }
        }
        return times;
    }
}
