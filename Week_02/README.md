学习笔记

# 树的面试题解法一般都是递归，为什么

因为树的定义决定了它的问题解法都是使用递归，叶子节点体现了子问题的重复性，而具有子问题重复性的问题都用递归求解。

# 本周要点

## 哈希表、映射、集合

### 哈希表相关概念[HashTable]

- 基本定义：又叫散列表，根据关键码值【key-value键值对的形式】访问数据的数据结构，key不允许重复
- 映射函数：用来计算key-value的映射，hash function， 底层实际是数组，映射函数计算要存储的索引位置
- 哈希碰撞：不同的值经过映射函数之后得到了同样的值，这种现象就叫做哈希碰撞
- 哈希碰撞解决办法：向后延展，形成链式存储

### 哈希表的工程实践[HashTable]

- 电话号码薄
- 用户信息表
- LRU Cache
- 键值存储【redis数据库】

### 时间复杂度[HashTable]

- O(1):完美hash table的插入、删除和访问【完美哈希表就是key和value是一一对应】
- O(n):由于hash冲突（碰撞）导致哈希表退化成链表结构的时候

### 映射基本定义[Map]

key-value存储， key不允许重复

### 时间复杂度[Map]

- HashMap:O（1）插入删除查询
- TreeMap:O（logN) 插入删除访问【利用红黑树实现的】

### HashMap小结[Map]

- HaspMap是基于Map接口实现的key value数据结构

- 允许Null key 和null value  

- 是线程不安全的

- 采用数组+链表的数据结构  数组：索引快    链表：插入和删除快  

- 底层其实就是数组，然后通过计算hashCode的方式来看插入的数据需要存在数组的哪一个位置 ,每个地方存储的就是一个链表，链表主要是用来解决hash冲突，因为有时候不同的值通过hash之后会得到相同的值，这样就会导致hash冲突

- 最常用的方法put、get  ，源码如下

  ```java
  /**
  * get 方法
  * 获取指定key在map中对应的值
  * 如果没有匹配到要查找的key，则返回null
  */
  public V get(Object key) {
          Node<K,V> e;
          return (e = getNode(hash(key), key)) == null ? null : e.value;
      }
  
  #put方法  存入一个key-value键值对，如果key存在于map中，则value保留的是最新的值
       public V put(K key, V value) {
          return putVal(hash(key), key, value, false, true);
      }
  ```

  ### 

### 集合[Set]

不重复元素的集合

### 时间复杂度[Set]

- HashSet:O（1）插入删除查询
- TreeSet:O（logN) 插入删除访问【利用红黑树实现的】

## 树、二叉树、二叉搜索树

### 树[Tree]

- 定义：根节点、儿子节点和兄弟节点 ，左子树和右子树

- 相当于在链表的基础进行了升维，一个节点有多个后续指针，每个指针也会再有多个后续指针，就好像多条链指向一个头结点

### 二叉树[Binary Tree]

每个节点最多只有两个叶子节点

- 后序遍历【Post-Order】:左子树、右子树、根

- 中序遍历【In-Order】:左子树、根、右子树

- 前序遍历【Pre-Order】:根、左子树、右子树

  ```java
  List<Object> list = new ArrayList<>();
  /**
  *前序遍历
  */
  private void preOrder(self, root) {
     if (root == null) return list;
     list.add(root.val);
     preOrder(root.left);
     preOrder(root.right);
  }
  /**
  *中序遍历
  */
  private void inOrder(self, root) {
     if (root == null) return list;
     inOrder(root.left);
     list.add(root.val);
     inOrder(root.right);
  }
  /**
  *后序遍历
  */
  private void postOrder(self, root) {
     if (root == null) return list;
     postOrder(root.left);
     postOrder(root.right);
     list.add(root.val);
  }
  
  ```

  

### 二叉搜索树[Binary Search Tree]

同二叉树，只不过它的元素是有序的，

#### 别称：

- 有序二叉树【Ordered Binary Tree】
- 排序二叉树【Sorted Binary Tree】
- 二叉排序树

#### 特点：

- 左子树上所有节点的值均小于它的根节点
- 右子树上所有节点的值均大于它的根节点

#### 推论：

- 空树也是二叉搜索树
- 所以分析得知，它的左右子树均为二叉搜索树【存在的重复性】
- 中序遍历：是一个升序排列

#### 时间复杂度:

原因：因为左子树节点、根、右子树节点的有序性，所以每比较一次都可以筛掉一半的节点

- 查询、插入、删除：O(logN)

  

# 堆、二叉堆、图

## 堆[Heap]

可以迅速找出一堆数据中的最大值或最小值的数据结构

大顶堆【大根堆】:根节点最大的堆

大顶堆【大根堆】时间复杂度：

- find-max:O（1）

- delete-max:O（1）

- insert(create):O（logN)

  

小顶堆【小根堆】:根节点最小的堆

- find-min:O（1）
- delete-min:O（1）
- insert(create):O（logN)

### 二叉堆Binary Heap【大顶堆】 

通过完全二叉树来实现【数组】

#### 实现

顶堆元素a[0]

索引为i的节点相关节点的位置

- 左儿子2i+1
- 右儿子2i+2
- 父节点floor((i+1)/2)

#### 性质

- 是一棵完全树【除叶子节点，其他节点都有左儿子和右儿子】
- 任意节点的值 >= 其子节点的值

#### 插入HeapifyUp【O(logN)】

- 先插入到二叉树的尾部，然后将该节点的值与父节点比较，如果比父节点大则交换，一直到比父节点小的时候停止

#### 删除堆顶元素HeapifyDown【O(logN)】

- 将堆尾元素替换到顶部
- 然后依次从根部调整堆的结构，直到堆尾【与子节点比较，比子节点小则交换】

#### 工程应用：

- priority queue  是最简单的实现，但不是最优的实现，严格的斐波拉契堆Fibonacci Heap才是最优的实现

### 图[Graph]

有还的树就称为图

#### 基本术语：

- 度：节点的边
- 有向无向：单行线
- 权重：边长
- 分类：无向无权图和、有向无权图、无向有权图、有向有权图

基于图的常见算法

- BFS（广度优先搜索）

  ```python
  def BFS(graph, start, end):
    queue = [] 
    queue.append([start]) 
    visited = set() # 和数中的BFS的最大区别
    while queue: 
       node = queue.pop() 
       visited.add(node)
       process(node) 
       nodes = generate_related_nodes(node) 
       queue.push(nodes)
  ```

  

- DFS(深度优先搜索)

- ```python
  def dfs(node, visited):
     if node in visited: # terminator
      # already visited 
     return 
     visited.add(node) 
     # process current node here. 
     ...
     for next_node in node.children(): 
        if not next_node in visited: 
           dfs(next_node, visited)
  ```

  

