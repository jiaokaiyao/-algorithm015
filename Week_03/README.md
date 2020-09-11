学习笔记

# 本周知识点

## 泛型递归、树的递归

### 特点

以盗梦空间为例

- 向下进入不同的梦境中，向上有回到原来的一层
- 通过声音同步回到上一层【递归中的参数传递】
- 每一层周围的环境和人都是上一层的一份拷贝、主角几个人穿越不同层级的梦境【发生和携带变化，即在上一层的基础上创造出新的东西，结果的变化】

### 递归代码模板

```java
// Java
public void recur(int level, int param) { 
  // terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 
  process(level, param); 
  // drill down 
  recur( level: level + 1, newParam); 
  // restore current status 
 
}
```

```python
# Python
def recursion(level, param1, param2, ...): 
    # recursion terminator 
    if level > MAX_LEVEL: 
	   process_result 
	   return 
    # process logic in current level 
    process(level, data...) 
    # drill down 
    self.recursion(level + 1, p1, ...) 
    # reverse the current level status if needed
```

```JavaScript
// JavaScript
const recursion = (level, params) =>{
   // recursion terminator
   if(level > MAX_LEVEL){
     process_result
     return 
   }
   // process current level
   process(level, params)
   //drill down
   recursion(level+1, params)
   //clean current level status if needed
   
}
```

```C++
// C、C++
void recursion(int level, int param) {
    // terminator
    if (level > MAX_LEVEL) {
        // process result
        return;
    }
    // process current logic
    process(level, param);
    // drill down 
    recursion(level + 1, param);
    // reverse the current level status if needed
}
```



### 思维要点

- 不要人肉递归，直接从函数本身开始写
- 找到最近最简方法，将其拆解成可重复解决的子问题【子问题重复性】
- 数学归纳法思维

## 分治、回溯

是一种特殊的递归，本质上就是找问题的重复性，分解成若干子问题

### 分治【Divide & Conquer】

- 分：拆分子问题
- 治：解决子问题
- 合并

### 分治代码模板

```java
// java
private static int divideConquer(Problem problem, ... args) {
    // terminator
    if (problem == null) {
        int res = process_last_result();
        return res;
    }
    // current logic
    subProblem = split_problem(problem);
    // drill down
    res0 = divideConquer(subProblem[0]);
    ...
    resn = divideConquer(subProblem[subProblem.length - 1]);
    // merge
    result = process_result(res0...resn);
    // revert the  current level status
}
```



```Python
#Python
def divideConquer(Problem problem, param1, ...) :
    # terminator
    if problem is None:
        print_result
        return
    # prepare data
    data = prepare_data(problem)
    subProblems = splitProblem(problem, data);
    # conquer subProblem
    subResult1 = self.divideConquer(subProblems[0], p1, ...)
    subResult2 = self.divideConquer(subProblems[1], p1, ...)
    ...
    # process and generate the final result
    result = process_result(subResult1, subResult2, ...)
    # revert the current level status
```

```JavaScript
# JavaScript
const divideConquer = (problem, params) => {
   // terminator
   if (problem == null) {
     process_result;
     return;
   }
    // process current problem
    subProblems = split_problem(problem);
    subResult1 = deivideConquer(subProblem[0], p1s);
    subResult2 = deivideConquer(subProblem[1], p1s);
    ...
    // merge result
    result = process_result(subResult1, subResult2, ...);
    // revert the current level status
}
```

```C++
// c++
int divideConquer(Problem *problem, int params) {
     // terminator
    if (problem == nullptr) {
        process_result();
        return result;
    }
    // process current problem
    subProblems = split_problem(problem);
    subResult1 = divideConquer(subProblems[0], p1s);
    subResult2 = divideConquer(subProblems[1], p2s);
    ...
    // merge
    result = process_result(subResult1, subResult2, ...);
    // revert the current level status
    return 0
}
```

### 回溯【Backtracing】树的深度优先遍历

- 尝试分步解决问题
- 每一层尝试找到解
- 如果这一次没有解决问题，就回退几步然后重试其他解法
- 一棵树上的深度优先算法
- 何时使用：需要寻找所有可行解的问题，都可以使用回溯

## 典型例题

### 回溯 

#### 组合问题  

`当n = 4, k = 2`，分析如下

选定一个搜索起点

考虑组合的唯一性

如过选定1作为搜索起点，则只能在【2,3,4】中找另外一个数

2座位搜索起点，则只用在【3,4】中找另外一个数

3座位起点，就只能和4匹配了，按照这样的递归方式，就不会产生重复的组合，且能够匹配到所有的组合

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

#### 全排列问题

要找出所有可能的解用回溯

```java
/**
     * 回溯
     * 假如有1,2,3 则所有的全排列 是1 + [2,3]的全排列
     * 2 + [1,3]的全排列
     * 3 + [1,2]的全排列
     * 由上可知, 这是一个树形结构，可以按照树的解法来解决此题
     * 时间复杂度：O(N * N!)
     * 空间复杂度：O(N * N!)
     * @param nums
     * @return
     */
public List<List<Integer>> permute(int[] nums) {
        // 结果
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        // 边界
        if (nums == null || length == 0) return res;
        // 记录有没有被选过， 同一个元素不能使用两次
        boolean used[] = new boolean[length];
        Deque<Integer> cur = new ArrayDeque<>();
        backTracing(length,0, nums, cur, res, used);
        return res;
    }
    private void backTracing(int length, int depth, int[] nums
            , Deque<Integer> cur , List<List<Integer>> res, boolean used[] ) {
        // 得到需要长度的排列的时候 将其假如结果中， 然后终止递归
        if (depth == length) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < length; i++) {
            // 使用的元素不能再使用
            if (!used[i]) {
                // 没有使用过加入当前路径中并将这个元素使用状态设置为已使用
                cur.addLast(nums[i]);
                used[i] = true;
                backTracing(length, depth + 1, nums, cur, res, used);
                // 需要回退递归之前执行过的操作，这是回溯的特征
                cur.removeLast();
                used[i] = false;
            }
        }
    }
```

### 分治

#### POW(x, n)问题

通过降幂合并可以将大问题细分为小问题，解决小问题后，将每个小问题的结果进行合并就得到最终的解

```java
// 分治  时间复杂度O(LOGn)
    public double myPow(double x, int n) {
        // 边界情况
        if (x == 0) return 0.0;
        if (n == 0) return 1.0;
        return ((n > 0)? quickPow(x, n) :  1.0/quickPow(x, -n));
    }

    private double quickPow(double x, int n) {
        // 递归终止
        if (n == 0) return 1.0;
        // 问题分解
        double temp = quickPow(x, n / 2);
        // 解合并
        return  (n % 2 == 0) ?  temp * temp :  temp * temp * x;
    }
```

## 本周感悟

- 找最优解选择动态规划方式递归
- 找所有解用回溯式递归，回溯相当于树的深度优先遍历，回溯的要点是递归之前的操作与递归之后的操作互为逆操作【即递归之前做了什么，则递归之后要将这些操作都撤销掉】
- 找不重复的解需要用到回溯+剪枝【剪枝的作用就是避免产生重复解， 优化时间复杂度】
- 当问题可以分解的时候，就分解，分而治之