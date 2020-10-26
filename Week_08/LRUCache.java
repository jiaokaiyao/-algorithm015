import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO LRU Cache
 * 使用双链表+散列表实现
 * @date Date : 2020年10月19日 22:55
 */
public class LRUCache {
    private Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        map = new LinkedCappedHashMap<>(capacity);
    }

    public int get(int key) {
        return map.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        map.put(key, value);
    }


    private static class LinkedCappedHashMap<K, V> extends LinkedHashMap<K, V> {
        int maximumCapacity;

        LinkedCappedHashMap(int maximumCapacity) {
            super(16, 0.75f, true);
            this.maximumCapacity = maximumCapacity;
        }

        /**
         * 删除最少使用元素
         * @param eldest
         * @return
         */
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > maximumCapacity;
        }
    }
}
