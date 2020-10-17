import javafx.util.Pair;

import java.util.*;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 单词接龙
 * @date Date : 2020年10月14日 22:24
 */
public class WordLadderLengthSolution {

    /**
     * 双向BFS搜索
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        // 如果单词库中本就没有这个单词  则直接返回0
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        // 搜索的起始单词组
        Set<String> begin = new HashSet<>(), end = new HashSet<>();
        int len = 1;
        begin.add(beginWord);
        end.add(endWord);
        // 双向BFS 两个单词同时搜索
        while (!begin.isEmpty()) {
            Set<String> temp = new HashSet<>();
            for (String word : begin) {
                char[] ch = word.toCharArray();
                for (int i = 0; i < ch.length; i++) {
                    char old = ch[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        ch[i] = c;
                        // 变换一位后判断是否与目标值匹配
                        String target = String.valueOf(ch);
                        if (end.contains(target)) {
                            return len + 1;
                        }
                        // 已经访问过的单词记录
                        if (wordSet.contains(target)) {
                            temp.add(target);
                            wordSet.remove(target);
                        }

                    }
                    ch[i] = old;

                }

            }
            // 选长度最小的作为下次搜索的单词组  减少遍历次数
            begin = temp.size() < end.size() ? temp : end;
            end = begin.size() < end.size() ? end : temp;
            len++;
        }

        return 0;
    }

    private int LEN;
    private Map<String, List<String>> dictMap;
    WordLadderLengthSolution() {
        this.LEN = 0;
        this.dictMap = new HashMap<>();
    }


    /**
     * 双向BFS搜索
     * 时间复杂度O(M*N)  N 是单词表中单词的总数 M是单词的长度
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        this.LEN = beginWord.length();
        wordList.forEach(word -> {
            for (int i = 0; i < LEN; i++) {
                // 同BFS  用特殊字符构造所有的单词形态
                String newWord = word.substring(0, i) + "*" + word.substring(i+1, LEN);
                List<String> allStates = dictMap.getOrDefault(newWord, new ArrayList<>());
                allStates.add(word);
                dictMap.put(newWord, allStates);
            }
        });
        // beginWord的队列
        Queue<Pair<String, Integer>> beginQueue = new LinkedList<>();
        // endWord的队列
        Queue<Pair<String, Integer>> endQueue = new LinkedList<>();
        beginQueue.add(new Pair<>(beginWord, 1));
        endQueue.add(new Pair<>(endWord, 1));
        // 已经访问过的单词记录 避免重复
        Map<String, Integer> beginVisited = new HashMap<>();
        Map<String, Integer> endVisited = new HashMap<>();
        beginVisited.put(beginWord, 1);
        endVisited.put(endWord, 1);
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            // begin从一边开始搜索
            int resBegin = visitWord(beginQueue, beginVisited, endVisited);
            if (resBegin > -1) {
                return resBegin;
            }
            // endWord从另一边开始搜索
            int resEnd = visitWord(endQueue, endVisited, beginVisited);
            if (resEnd > -1) {
                return resEnd;
            }
        }
        // 没有找到 返回0
        return 0;
    }


    /**
     * 单词搜索
     * @param wordQueue
     * @param oneSideVisited
     * @param otherSideVisited
     * @return
     */
    private int visitWord(Queue<Pair<String, Integer>> wordQueue, Map<String, Integer> oneSideVisited, Map<String, Integer> otherSideVisited) {
        Pair<String, Integer> wordNode = wordQueue.remove();
        String word = wordNode.getKey();
        int level = wordNode.getValue();
        for (int i = 0; i < LEN; i++) {
            String newWord = word.substring(0, i) + "*" + word.substring(i+1, LEN);
            // 匹配是否在所有单词形态中
            for(String adjacentWord : dictMap.getOrDefault(newWord, new ArrayList<>())) {
                // 匹配终极单词  如果存在 则返回
                if (otherSideVisited.containsKey(adjacentWord)) {
                    return level + otherSideVisited.get(adjacentWord);
                }
                if (!oneSideVisited.containsKey(adjacentWord)) {
                    // 没有匹配 加入已经访问列表 并记录所在的层级
                    oneSideVisited.put(adjacentWord, level + 1);
                    wordQueue.add(new Pair<>(adjacentWord, level + 1));
                }
            }
            
        }
        // 没有找到 返回 -1
        return -1;
    }
}
