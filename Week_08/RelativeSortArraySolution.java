/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 数组的相对排序
 * 给你两个数组，arr1 和 arr2，
 *
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 * @date Date : 2020年10月23日 9:07
 */
public class RelativeSortArraySolution {

    /**
     * 计数排序
     * 时间复杂度：O(1001) => O（1）
     * 空间复杂度：O(1001) => O（1）
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int len1 = arr1.length, len2 = arr2.length;
        int[] count = new int[1001];
        // 记录arr1的元素的个数 数据作为下标，相当于把数据排序了
        for (int i : arr1) {
            count[i]++;
        }
        // 排序arr2中的数据  由于arr2的元素一定在arr1中  所以直接从计数数组中获取即可
        int i = 0;
        for (int num : arr2) {
            while (count[num] > 0 ) {
               arr1[i++] = num;
               // 用一次减一次计数
               count[num]--;
            }
        }
        // 排序剩下的数据
        for (int j = 0; j < count.length; j++) {
            while (count[j] > 0) {
                arr1[i++] = j;
                count[j]--;
            }

        }
        return arr1;

    }
}
