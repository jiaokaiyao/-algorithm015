# 期末总结

## 数据与结构脑图总结

https://www.liuchengtu.com/swdt/#Ra18a6417c2a016d9e997bb4ef3abb90a

## 常见算法思维

### 枚举算法

暴力求解 ， 枚举每一种情况

```java
// 求1到100的和
int sum = 0;
for (int i = 1; i<= 100; i++) {
    sum += i;
}
```



### 贪心算法

贪心算法是一种在每一步选择中都采取当前状态下最优的选择，从而希望得到全局最优的算法

与动态规划的区别在于贪心算法对每个子问题的解决方案都做选择，不能回退，动态规划则会保存以前的运算结果，并根据以前的结果对当前问题进行选择，可以回退

- 适用场景：问题能够划分为子问题，且子问题的最优解能够递推到最终问题的最优解，这种子问题最优解称为最优子结构

### 递归算法

自己调用自己，直到达到递归终止条件

```java
public void recur(int level, int param) { 
  // terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 
  process(level, param); 
  // drill down 
  recur( level + 1, newParam); 
  // restore current status if needed
}
```



#### 分治

递归的另一种特殊形式，本质上就是找问题的重复性，分解成若干子问题

1. ###### 分解问题

2. 解决子问题

3. 合并解

   ```java
   
   private static int divide_conquer(Problem problem, ) {
     
     if (problem == NULL) {
       int res = process_last_result();
       return res;     
     }
     subProblems = split_problem(problem)
     
     res0 = divide_conquer(subProblems[0])
     res1 = divide_conquer(subProblems[1])
     
     result = process_result(res0, res1);
     
     return result;
   }
   ```

   

#### 回溯

- 尝试分步解决问题

- 每一层尝试找到解

- 如果这一次没有解决问题，就回退几步然后重试其他解法

- 一棵树上的深度优先算法

- 何时使用：需要寻找所有可行解的问题，都可以使用回溯

  ```java
  // 回溯【深度优先遍历】 + 剪枝
      public List<List<Integer>> combine(int n, int k) {
          List<List<Integer>> res = new ArrayList<>();
          if (k <= 0 || n < k) return res;
          Deque<Integer> stack = new ArrayDeque<>();
          dfs(n, 1, k, stack, res);
          // 返回
          return res;
      }
      private void dfs(int n, int index, int k, Deque<Integer> stack, List<List<Integer>> res) {
          // 已经添加到看K个元素的时候 就将其加入结果中
          if (stack.size() == k) {
              res.add(new ArrayList<>(stack));
              return;
          }
          // n- (k-stack.size()) + 1 剪枝
          // n- (k-stack.size()) + 1 剪枝
          for (int i = index; i <= n - (k- stack.size()) + 1; i++) {
              stack.addLast(i);
              // 下探 往下遍历
              dfs(n, i + 1, k, stack, res);
              // 深度优先遍历可能回退， 即递归之前做了什么，递归之后要逆向操作
              stack.removeLast();
          }
  
      }
  ```

  

#### 动态规划

（分治、回溯）+最优子结构

1.最优子结构

2.存储子问题的解【存储中间状态】-一般使用数组存储中间状态

3.递推公式【状态转移方程（DP方程）】

**难点**：状态转移方程，一般找状态转移方程是通过案列推导

- ### 最长公共子序列问题

思路：穷举+剪枝【动态规划】，两个指针分别遍历两个字符串，用二维数组存储子问题的解【状态数组】

代码：

```java
/**
     * 动态规划法 【穷举+剪枝】
     * 相同的时候就在之前的状态上加上新的值
     * 不同的时候需要考虑之前的状态，将最优解转移过来
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = (text1 == null ? 0 : text1.length());
        int len2 = (text2 == null ? 0 : text2.length());
        if (len1 == 0 || len2 == 0) return 0;
        // 状态数组 防止索引越界 所以加1
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                // 获取当前位置的字符
                char c1 = text1.charAt(i), c2 = text2.charAt(j);
                if (c1 == c2) {
                    // 相等的时候公共子序列加1  当前位置是前一个位置的值+1
                    dp[i+1][j+1] = dp[i][j] + 1;
                } else {
                    // 如果不相等 则公共子序列是text1往前退一格或text2往前退一格的最大值
                    dp[i + 1][j+1] = Math.max(dp[i+1][j], dp[i][j+1]);
                }
                
            }
            
        }
        return dp[len1][len2];
    }
```



### 二分查找

- 目标函数存在单调性，要么递增要么递减

- 存在上下界，即有边界【bounded】

- 能够通过索引访问的数据结构[index acessible]

  ```java
  private int binarySearch(int[] nums, int target) {
      int left = 0, right = nums.length - 1, mid;
      while (left <= right) {
          mid = (right - left) / 2 + left;
          if (nums[mid] == target) {
              return mid;
          } else if (nums[mid] > target) {
              right = mid - 1;   
          } else {
              left = mid + 1;
          }
      }
      return -1;
  }
  ```

  

### 双指针

两个指针

- 反转字符串

```java
/**
     * 双指针求解
     * 时间复杂度：O(N)
     * @param s
     */
    public void reverseString(char[] s) {
        int len = (s == null ? 0 : s.length);
        if (len == 0) return;
        int left = 0, right = len -1;
        // 双指针往中间访问 逐一交换
        while (left <= right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            // 左指针右移  右指针左移
            left++;
            right--;
        }
    }
```

## 常见算法

### 排序算法

#### 比较类排序：

通过比较来决定元素间的相对次序，时间复杂度不能突破O(nlogn),所以也叫做非线性时间比较类排序

##### 交换排序

###### 冒泡排序Bubble Sort

嵌套循环，每次查看相邻的元素如果逆序，则交换。

###### 快速排序（Quick Sort）【O(nlogn)】高级

**[先调配出左右子数组，然后对于左右子数组进行排序]**

数组取标杆 pivot，将小元素放 pivot左边，大元素放右侧，然后依

次对右边和右边的子数组继续快排；以达到整个序列有序

quickSort(a, 0, a.length - 1)

```java
public static void quickSort(int[] array, int begin, int end) {
    if (end <= begin) return;
    int pivot = partition(array, begin, end);
    quickSort(array, begin, pivot - 1);
    quickSort(array, pivot + 1, end);
}
static int partition(int[] a, int begin, int end) {
    //pivot:标杆位置， counter:小于pivot位置元素的个数
    int pivot = end, count = begin;
    for (int i = begin; i < end; i++) {
        if (a[i] < a[pivot]) {
            int temp = a[counter] ;
            a[counter] = a[i];
            a[i] = temp;
            counter++;
        }
    }
    int temp = a[pivot];
    a[pivot] = a[counter];
    a[counter] = temp;
    return counter;
}
```

##### 插入排序Insertion Sort

从前到后逐步构建有序序列；对于未排序数据，在已排序序列中从后

向前扫描，找到相应位置并插入

###### 简单插入排序

###### 希尔排序

##### 选择排序Selection Sort

###### 简单选择排序 【O(n^2)】

每次找最小值，然后放到待排序数组的起始位置。

###### 堆排序【O(nlogn)】— 

堆插入 O(logN)，取最大/小值 O(1)

数组元素依次建立小顶堆

依次取堆顶元素并删除

```java
// java
public static void heapSort(int[] array) {
    int len = array == null ? 0 : array.length;
    if (len == 0) return;
    for (int i = len / 2 - 1; i >= 0; i--) {
        heapify(array, length, i);
    }
    for (int i = len - 1; i >= 0; i--) {
        int temp = array[0]; 
        array[0] = array[i];
        array[i] = temp;
        heapify(array, i, 0);
    }
}
static void heapify(int[] array, int length, int i) {
    int left = 2 * i + 1, right = 2 * i + 2;
    int largest = i;
    if (left < length && array[left] < array[largest]) {
        largest = left;
    }
    if (right < length && array[right] > array[largest]) {
        largest = right;
    }
    if (largest != i) {
        int temp = array[i];
        array[i] = array[largest];
        array[largest] = temp;
        heapify(array, length, largest);
    }
}
```



##### 归并排序Merge Sort【O(nlogn)】——分治

**[先排序左右子数组，然后合并两个有序子数组]**

把长度为n的输入序列分成两个长度为n/2的序列

对这两个子序列分别采用归并排序

将排序好的两个子序列合并成一个最终的排序序列

```java
public static void mergeSort(int[] array, int left, int roght) {
    if (right <= left) return;
    int mid = (left + right) >> 1;
    mergeSort(array, left, mid);
    mergeSort(array, mid + 1, right);
    merge(array, left, mid , right);
}
public static void merge(int[] arr, int left, int mid, int right) {
    // 中间数组
    int[] temp = new int[right - left + 1];
    int i = left, j = mid + 1, k = 0;
    while (i <= mid && j <= right) {
        temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
    }
    while (i <= mid) {
        temp[k++] = arr[i++];
    }
    while (j <= right) {
        temp[k++] = arr[j++];
    }
    for (int p = 0; p < temp.length; p++) {
        arr[left + p] = temp[p];
    }
    
}
```

###### 二路归并排序

###### 多路归并排序

#### 非比较类排序

不通过比较决定元素的相对次序，以线性时间运行，也叫做线性时间比较类排序

##### 计数排序【Counting Sort】

输入的数据必须是确定范围的整数，将输入的数据值转化为键值存在在新开辟的数组空间中，然后依次把计数大于1的填充回原数组

##### 桶排序 【bucket Sort】

假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序【有可能再使用的别的算法或是以递归方式继续使用桶排序进行排序】

##### 基数排序

按低位先排序，然后收集，再按照高位排序，然后再收集，依次类推，直到最高位，有时候有些属性是有优先级顺序的，先按低优先级再按高优先级进行排序

### 搜索

#### DFS深度优先搜索（Depth First Search）

```java
public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if(root==null){
            return allResults;
        }
        travel(root,0,allResults);
        return allResults;
    }


    private void travel(TreeNode root,int level,List<List<Integer>> results){
        if(results.size()==level){
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        if(root.left!=null){
            travel(root.left,level+1,results);
        }
        if(root.right!=null){
            travel(root.right,level+1,results);
        }
    }
```



#### BFS广度优先搜索（Breadth First Search） 

```java
//Java
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if (root == null) {
        return allResults;
    }
    Queue<TreeNode> nodes = new LinkedList<>();
    nodes.add(root);
    while (!nodes.isEmpty()) {
        int size = nodes.size();
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = nodes.poll();
            results.add(node.val);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        allResults.add(results);
    }
    return allResults;
}
```



#### A*启发式搜索

### 查找

#### 线性表查找

#### 散列表查找

#### 树结构查找

## 其他

### Trie树实现

trie树实际就是多叉树，节点上没有存储实际的数据信息

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
        // 前缀匹配  根节点的下一个节点开始匹配  只要存在空 即没有匹配到  否则匹配到
        for (char c : words) {
            node = node.next[c - 'a'];
            if (node == null) {
                return false;
            }
        }
        return true;

    }
```



### LRU Cache实现

最近使用替换      哈希表+双链表实现

```java
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
```

