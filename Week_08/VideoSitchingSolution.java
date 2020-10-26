/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 视频拼接
 * @date Date : 2020年10月24日 21:16
 */
public class VideoSitchingSolution {

    /**
     * 贪心算法
     * 时间复杂度：O(length(子区的数量) + T)
     * 空间复杂度：O(T) 对于每一个位置，需要记录以其为左端点的子区间的最右端点
     * @param clips
     * @param T
     * @return
     */
    public int videoStitching(int[][] clips, int T) {
        int[] maxn = new int[T];
        int last = 0, res = 0, pre = 0;
        for (int[] clip : clips) {
            if (clip[0] < T) {
                // 对于左端点相同的子区间，右端点越大的为越优的选择
                maxn[clip[0]] = Math.max(maxn[clip[0]], clip[1]);
            }
            
        }
        /**
         * 枚举每一个位置，如当前位置i,左端点不大于i的所有子区间的最远右端为last,即切分到的子区间的右端点
         * 每枚举一个位置，用maxn来记录新的last
         * last == i, 则下一个位置没法被覆盖，既不能解决此问题，返回-1
         * pre:上一个子区间的结束位置, 跨越一个子区间就会要开始一个新的子区间，新子区间的右端位置为last
         * 所以 pre == i的时候说明已经使用完一个空间,答案【区间数】+1，且更新区间的结束位置为last
         */

        for (int i = 0; i < T; i++) {
            last = Math.max(last, maxn[i]);
            if (i == last) {
                return -1;
            }
            if (i == pre) {
                res++;
                pre = last;
            }

        }
        return res;
    }
}
