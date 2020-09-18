学习笔记

# 本周要点

## 深度优先搜索、广度优先搜索

- 深度优先从起点开始，先访问与起点有路径相同的节点，一直到路径结束，然后再返回去访问另一条路径。类似于树的前序遍历

- 广度优先是按层进行访问节点的，距离开始节点最近的那些节点首先被访问，类似于树的层序遍历

### 深度优先搜索代码模板

- 递归写法

```Python
# Python
visited = set()
def dfs(node ,visited): 
     if node in visited: 
        # terminator already visited
        return;
     visited.add(node)
     # process current node here
     ...
     for nextNode in node.children():
         if nextNode not in visited:
         dfs(nextNode, visited)
          
        
```

```C++
// C++
map<int, int>  visited;
void dfs(Node* node) {
    // terminator
    if (!root) return;
    if (visited.count(node->val)) {
        // already visited
        return;
    }
    visited[root->val] = 1;
    // process the current node here
    // ...
    for (int i =0; i < node->children.size(); ++i) {
        dfs(root->children);
    }
    return;   
}
```

```java
// java
public List<List<Integer>> levelOrder(TreeNode node) {
    List<List<Integer>> res = new ArrayList<>();
    if (node == null) {
        return res;
    }
    travel(0, node, res);
    return res;
}

private void travel(int level, TreeNode root, List<List<Integer>> res) {
    if (res.size() == level) {
        res.add(new ArrayList<>());
    }
    results.get(level).add(root.val);
    if (root.left != null) {
        travel(level+1, root.left, res);
    }
    if (root.right != null) {
        travel(level + 1, root.right, res);
    }
}
```

```javascript
// javascript
const visited = new Set();
const dfs = node => {
    if (visited.has(node)) return
    visited.add(node)
    dfs(node.left)
    dfs(node.right)
}
```

- 非递归写法

```python
#Python
def dfs(self, tree):
    if tree.root is None:
        return []
    visited, stack = [], [tree.root]
    while stack:
        node = stack.pop()
        visited.add(node)
        nodes = generate_related_nodes(node)
        stack.push(nodes)
    #other processing work
    ...
```

```c++
// C++
void dfs(Node* node) {
    map<int,int> visited;
    if (!node) return;
    stack<Node*> stackN;
    stackN.push(node);
    while (!stackN.empty()) {
        Node* root = stackN.top();
        stack.pop();
        if (visited.count(root->val)) continue;
        visited[root->val] = 1;
        for (int i = root-> children.size() - 1; i >= 0; --i) {
            stackN.push(node->children[i]);
        }  
    }
    return
}
```



### 广度优先遍历代码模板

```python
# python
def bfs(graph, start, end):
    visited = set()
    queue = []
    queue.append([start])
    while queue:
        node = queue.pop()
        visited.add(node)
        process(node)
        nodes = generate_related_nodes(node)
        queue.push(nodes)
   # other processing work
   ...
```

```java
// java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
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
                nodes.add(node.right)
            }
        }
        res.add(results);
    }
    return res;
}
```

```c++
// C++
void bfs(Node* root) {
    map<int, int> visited;
    if (!root) return;
    queue<Node*> queueN;
    queueN.push(root);
    while (!queueN.empty()) {
        Node* node = queueN.top();
        queueN.pop();
        if (visited.count(node->val)) continue;
        visited[node->val] = 1;
        for (int i = 0; i < node->children.size(); ++i) {
            queueN.push(node->children[i]);
        }
    }
    return;
    
}
```

```javascript
// javascript
const bfs = (root) => {
    let result = [], queue = [root]
    while (queue.length > 0) {
        let level = [], n = queue.length
        for (let i = 0; i < n; i++) {
            let node = queue.pop();
            level.push(node.val)
            if (node.left) queue.unshift(node.left);
            if (node.right) queue.unshift(node.right); 
        }
        result.push(level);
    }
    return result;
}
```

## 贪心算法

贪心算法是一种在每一步选择中都采取当前状态下最优的选择，从而希望得到全局最优的算法

与动态规划的区别在于贪心算法对每个子问题的解决方案都做选择，不能回退，动态规划则会保存以前的运算结果，并根据以前的结果对当前问题进行选择，可以回退

- 适用场景：问题能够划分为子问题，且子问题的最优解能够递推到最终问题的最优解，这种子问题最优解称为最优子结构

## 二分查找

### 特点

- 目标函数存在单调性，要么递增要么递减
- 存在上下界，即有边界【bounded】
- 能够通过索引访问的数据结构[index acessible]

### 代码模板

```python
# python
left, right = 0 , length(array) - 1;
while left <= right:
    mid = (left + right) / 2
    if array[mid] == target:
        # find the target  break or return the result
        return mid;
    elseif array[mid] < target:
        left = mid + 1;
    else:
        right = mid - 1;
        
```

```c++
# C++
int binarySearch(const vector<int>& nums, int target) {
    int left = 0, right = (int)nums.size()-1;
    while(left <= right) {
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) return mid;
        else if (nums[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}
```

```java
// java
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

```javascript
// javascript
let left = 0, right = len(nums) - 1
while  (left <= right) {
    let mid  = (left + right) >> 1;
    if (nums[mid] == target){
        return mid;
    } else if (nums[mid] < target) {
        left = mid + 1;
    } else {
        right = mid -1;
    }
    return -1;
    
}
```

### 实战

使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

思路：

因为数组是半有序的，意思就是说存在一个元素，
当先升后降的时候它同时大于它的前一个元素和后一个元素，
当先降后升的时候它同时小于它的前一个元素和后一个元素
所以二分查找符合这样条件的位置就可以了

```java
public static void main(String[] args) {
//        int[] nums = new int[]{4, 5, 6, 7, 8, 0, 1, 2};
        int[] nums = new int[]{8, 7, 6, 5, 4, 9, 10, 11};
        int len = nums.length;
        int left = 0, right = len;
        while (left <= right) {
            int mid = (right - left) / 2 -left;
            // 先升后降
            if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid + 1]) {
                System.out.println("无序的位置为：：" + mid + "、" + mid+1);
//                System.out.println("无序的元素为：：" + nums[mid] + "、" + nums[mid+1]);
                break;
            }
            // 先降后升
            if (nums[mid] < nums[mid-1] && nums[mid] < nums[mid + 1]) {
                System.out.println("无序的位置为：：" + (mid) + "、" + (mid + 1));
//                System.out.println("无序的元素为：：" + nums[mid] + "、" + nums[mid+1]);
                break;
            }
            left = mid + 1;
            right = mid -1;
        }
    }
```

