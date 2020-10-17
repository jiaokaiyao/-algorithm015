学习笔记

# 本周知识点

## 字典树和并查集

### 字典树【Trie树】

定义：一种树形结构，又称单词查找树或键树

基本特点：

- 结点本身不存完整单词
- 从根结点到某一结点，路径上经过的字符连接起来，为该结点对应的字符串
- 每个结点的所有子结点路径代表的字符都不相同

核心思想：

- 空间换时间
- 利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的

典型应用：

- 统计字符串

- 排序大量的字符串

  

工程应用：

- 搜索引擎文本词频统计

  

优点：

- 最大限度减少无谓的字符串比较，查询效率比哈希表高

  
  
  

Trie树实现

```java
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
        // 前缀匹配  根节点的下一个节点开始匹配  只要存在空 即没有匹配到  否则匹配到[已经匹配到最后一个字符，都已经匹配上了
        // 说明一定存在以这个前缀为前缀的单词]
        for (char c : words) {
            node = node.next[c - 'a'];
            if (node == null) {
                return false;
            }
        }
        return true;

    }
```



### 并查集【Disjoint Set】

适用场景：

- 组团、配对问题

操作核心

- makeSet(s):建立包含s个单元数集合的并查集
- unionSet(x,y):把元素x和元素y所在的集合合并【集合没有交集才合并】
- find(x):找到元素x所在的集合的代表【可判断两个元素是不是属于同一个集合，比较所在集合的代表就可以了】



## 高级搜索

### 剪枝

- #### 剪掉不必要的状态数分支【重复问题解缓存，减少不必要计算】】通常会与回溯算法一起使用

- 典型题目：

  1. 解数独
  
     只要行中、列中、九宫格中存在重复元素，就不是有效的的数独，通过散列集合来存储已经访问过的位置，下次判断的时候只需要O(1)的时间复杂度就能判断是否存在重复的数字出现在同行、同列、同一个九宫格中
  
     ```
     /**
          * 时间复杂度O(1)只遍历了81次
          * hashset保存已经存在的元素 ，如果存在多次就不是有效的数独
          * @param board
          * @return
          */
         public boolean isValidSudoku(char[][] board) {
             Set<String> valid = new HashSet();
             String column = " column:";
             String row = " row:";
             String box = " box:";
             String split = "-";
             for (int i = 0; i < 9; i++) {
                 for (int j = 0; j < 9; j++) {
                     if (board[i][j] != '.') {
                         char num = board[i][j];
                         // 如果已经存在的值 添加会返回false 则通过此可以判断行、列、九宫格中是否有重复数字
                         boolean flag = !valid.add(num + row + i) || !valid.add(num + column + j)
                                  || !valid.add(num + box + (i/3) + split +  (j/3));
                         if (flag) {
                             return false;
                         }
                     }
     
                 }
     
             }
             return true;
     
         }
     ```
  
     

### 双向BFS【Two-ended BFS】

- 同BFS ，不同点在于两边同时向中间搜索，当两边访问到同一顶点【即双向搜索的交点】的时候结束搜索
- 典型题目
  
  1. 单词接龙
  
     ```java
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
     ```
  
     

### 启发式搜索Heuristic Search (A*)

- 基于BFS，结合估价函数

- 估价函数又叫启发式函数，用来评价哪些结点最有可能是我们要查找的结点，返回一个非负实数，h(n)从结点n的目标结点路径的估计成本，是一种告知搜索方向的方法，提供了一种明智的方法来猜测那个邻居结点会导向一个目标

- 典型题目

  1. #### 二进制矩阵中的最短路径【看别人的题解写的 】
  
     想象成图 按图的广度优先遍历搜索，可走路径长度加一【八个方向（上下左右及两条对角线上）】
  
     ```java
     // 每个方向上的索引变化
         private static int[][] directions = {{0,1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
         private static int row, col;
     
         /**
          * BFS搜索
          * 时间复杂度：O(N)
          * @param grid
          * @return
          */
         public static int shortestPathBinaryMatrix(int[][] grid) {
             row = grid.length;
             col = grid[0].length;
             // 边界处理 当左上顶点和右下顶点为不可行时  一定没有路径到达
             if (grid[0][0] == 1 || grid[row - 1][col - 1] == 1) {
                 return -1;
             }
             // 记录当前位置的索引
             Queue<int[]> pos = new LinkedList<>();
             // 用原数组记录起点到当前位置的最短路径长度 第一个位置为1【可走】
             grid[0][0] = 1;
             pos.add(new int[]{0, 0});
             // 走一个位置  就把那个位置标记为1
             while (!pos.isEmpty() && grid[row - 1][col - 1] == 0) {
                 int[] pL = pos.remove();
                 // 当前位置路径长度
                 int currentL = grid[pL[0]][pL[1]];
                 for (int i = 0; i < 8; i++) {
                     int newRow = pL[0] + directions[i][0];
                     int newCol = pL[1] + directions[i][1];
                     // 索引有效且当前位置可走的情况下  路径长度+1
                     if (inGrid(newRow, newCol) && grid[newRow][newCol] == 0) {
                         pos.add(new int[]{newRow, newCol});
                         // 下一个位置的路径长度
                         grid[newRow][newCol] = currentL + 1;
                     }
                 }
             }
             // 如果最终右下顶点值为0 则说明没有路径通往右下顶点
             return grid[row - 1][col - 1] == 0 ? -1 : grid[row - 1][col - 1];
         }
     
         /**
          * 索引不能越界
          * @param newRow
          * @param newCol
          * @return
          */
         private static boolean inGrid(int newRow, int newCol) {
             return (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col);
         }
     ```
  
     

## 高级树

### AVL树【平衡二叉搜索树】

- 它的左子树的高度减去它的右子树的高度不能超过1【平衡因子：balance factor = {-1, 0, 1}】
- 结点除了自身的值外，还存储平衡因子
- 不足是因为要存储额外信息，调整次数频繁
- 通过旋转操作来做到平衡
  1. 左旋【右右子树（只有右结点）】
  2. 右旋【左左子树（只有左结点）】
  3. 左右旋【左右子树（左结点->右结点）】
  4. 右左旋【右左子树（右结点->左结点）】

红黑树【Red-Black Tree】

- 近似平衡的二叉搜索树，任何一个结点的左右子树高度差小于两倍

- 特点：

  1. 每个结点要么是红色，要么是黑色
  2. 根结点是黑色
  3. 每个叶子结点【空结点，NIL结点】是黑色的
  4. 不能有相邻的两个结点是红色的
  5. 从任一结点到其每个叶子结点的所有路径都包含相同数目的黑色结点

- 关键性质

  从根到叶子的最长的可能路径不多于最短路径的可能路径的两倍长











