import java.util.HashMap;
import java.util.Map;

/**
 * @author kaiya
 * @Desc
 * @date 2020/9/10 21:40
 */
public class MajorityElementSolution {

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int len = nums.length / 2;
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > len) {
                res = entry.getKey();
                break;
            }
        }
        return res;
    }
}
