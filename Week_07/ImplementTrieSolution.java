import sun.text.normalizer.Trie;

/**
 * @author : jiaokaiyao
 * @version V1.0
 * @Description: TODO 实现前缀树【Trie】 Trie实际就是多叉树 节点上没有存实际的数据信息
 * @date Date : 2020年10月16日 10:52
 */
public class ImplementTrieSolution {
    // Trie树数据结构
    class TrieNode {
        private boolean isEnd;
        private TrieNode[] next;
        public TrieNode() {
            // 是否是字符串的结束节点
            this.isEnd = false;
            // 字母映射表 对当前结点而言下一个可能出现的所有字符的链接
            next = new TrieNode[26];
        }
    }
    // 根节点
    private TrieNode root;
    /** Initialize your data structure here. */
    public ImplementTrieSolution() {
        root = new TrieNode();

    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        char[] words = word.toCharArray();
        // 从根节点与Trie树匹配，一直匹配到没有前缀链 然后不断开辟新的节点 直到word的最后一个字符被插入，
        // 并标记当前节点为word字符串的结尾
        for (char c : words) {
            if (node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new TrieNode();
            }
            node = node.next[c - 'a'];
        }
        node.isEnd = true;


    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        char[] words = word.toCharArray();
        // 从根节点的下一个节点开始匹配  如果匹配到空 则没有查找到想要查找的单词
        // 当所有都匹配完的时候，只需要判断当前节点是否是单词的结尾节点
        for (char c : words) {
            node = node.next[c - 'a'];
            if (node == null) {
                return false;
            }
        }
        return node.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        char[] words = prefix.toCharArray();
        // 前缀匹配  根节点的下一个节点开始匹配  只要存在空 即没有匹配到  否则匹配到
        for (char c : words) {
            node = node.next[c - 'a'];
            if (node == null) {
                return false;
            }
        }
        return true;

    }
}
