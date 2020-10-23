import java.util.Arrays;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 任务调度器
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 * @date Date : 2020年10月23日 20:13
 */
public class LeastIntervalSolution {

    /**
     * 时间复杂度：O(1)
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval(char[] tasks, int n) {
        // 用于记录26个字母出现的次数
        int record[] = new int[26];
        for (char task : tasks) {
            record[task - 'A']++;
        }
        // 排序
        Arrays.sort(record);
        int max = record[25] - 1;
        /**
         * 为什么是最多出现的次数减1
         * 因为假如我们这个任务列表都是任务A,比如AAAA ， n=2 ,那我们执行完成这些任务需要
         * 10个片段， 就是 4 + 3 * 2，4个执行 6个空闲，因为除了最后一个任务之外
         */
        int space = max * n;
        for (int i = 24; i >= 0 && record[i] > 0 ; i--) {
           space -= Math.min(max, record[i]);
        }
        if (space > 0) {
            return tasks.length + space;
        } else {
            return tasks.length;
        }
    }
}
