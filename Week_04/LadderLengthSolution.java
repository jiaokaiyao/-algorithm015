import javafx.util.Pair;

import java.util.*;

/**
 * @author kaiya
 * @Desc
 * @date 2020/9/17 14:31
 */
public class LadderLengthSolution {

    /** ============================== 会超时 ==================
     * ========================深度优先搜索 =============================
     *
     * */
    /**
     * 深度优先搜索
     * 时间复杂度：O(N * M) N：单词库中的单词个数M单词的长度
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    int minSteps = Integer.MAX_VALUE;
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> temp = new HashSet<>();
        dfs(1, beginWord, endWord, wordList, temp);
        return (minSteps == Integer.MAX_VALUE) ? 0 : minSteps;
    }


    private void dfs(int steps, String beginWord, String endWord, List<String> wordList, HashSet<String> temp) {
        if (steps > minSteps) {
            return;
        }
        if (beginWord.equals(endWord)) {
            minSteps = Math.min(steps, minSteps);
            return;
        }
        // 从单词库中变化来找最短变化
        for (String s : wordList) {
            int diff = 0;
            for (int i = 0; i < s.length(); i++) {
                // 找到一个位置不匹配  当前单词就作为变化一位后的beginWord使用  继续下一次查找
                if (s.charAt(i) != beginWord.charAt(i)) {
                    if (++diff > 1) {
                        break;
                    }
                }
            }
            if (diff == 1 && !temp.contains(s)) {
                temp.add(s);
                dfs(steps + 1, s, endWord, wordList, temp);
                temp.remove(s);
            }
        }
    }


    /**
     * ============================ 广度优先搜索 ======================
     */

    /**
     * 广度优先搜索  有向无环图
     * 时间复杂度：O(N * M) N：单词库中的单词个数M单词的长度
     * 空间复杂度：O(N * M) N：单词库中的单词个数M单词的长度
     * 找到每个单词的变化形态需要访问M次（单词每个位都要变化一次） 广度优先搜索最坏情况下需要访问完整个单词表的单词
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLengthWithBFS(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> temp = new HashSet<>();
        int len = beginWord.length();
        // 记录每一个单词形态所在的层
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        Map<String, List<String>> allStateWords = new HashMap<>();
        // 初始化单词的所有形态
        wordList.forEach(word -> {
            for (int i = 0; i < len; i++) {
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, len);
                List<String> allStateWordsList = allStateWords.getOrDefault(newWord, new ArrayList<>());
                allStateWordsList.add(word);
                allStateWords.put(newWord, allStateWordsList);
            }
        });
        // 避免产生环，记录已经访问过得单词
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);
        queue.add(new Pair<>(beginWord, 1));
        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.remove();
            String word = node.getKey();
            int level = node.getValue();
            for (int i = 0; i < len; i++) {
                // 从散列表中取出当前单词对应的所有形态
                String newWord = word.substring(0, i) + "*" + word.substring(i + 1, len);
                for(String changeWord : allStateWords.getOrDefault(newWord, new ArrayList<>())) {
                    // 如果其中一个形态等于endWord 说明已经找到了从源变化到目标需要的次数
                    if (endWord.equals(changeWord)) {
                        level += 1;
                        return level;
                    }
                    // 如果没有，则将当前单词加入已经访问过的列表中
                    if (!visited.containsKey(changeWord)) {
                        visited.put(changeWord, true);
                        queue.add(new Pair<>(changeWord, level + 1));
                    }
                }
            }

        }
        return 0;
    }
}
