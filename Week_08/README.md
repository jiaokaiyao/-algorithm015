学习笔记

# 本周知识要点

## 位运算[0和1]

- 左移[<<]：所有二进制串向左移，低位补零

- 右移[>>]：所有二进制串向左移，高位补零

- 按位或[|]：有1为1，全0为0

- 按位与[&]：有0为0，全1为1

- 按位取反[~]：0变1，1变0

- 按位异或[^]:相同为0，不同为1

  特点：

  1. x^0=x
  2. x^x=0
  3. c=a^b=>b=a^c=>a=b^c // 两数交换
  4. a^b^c=a^(b^c)=(a^b)^c

  指定位置的位运算

  1. 将x的右边n位清零：x&(~0<<n)
  2. 获取x第n位的值（0或者1）：（x>>n)&1
  3. 获取x的第n位的幂值：x&(1<<n)
  4. 仅将第n位置为1：x|(1<<n)
  5. 仅将第n位置为0：x&(~(1<<n))
  6. 将x最高位至第n(含n)位清零：x&((1<<n)-1)

  实战要点：

  1. 判断奇偶

     奇数：x%2==1 => x&1==1

     偶数：x%2==0 => x&1==0

  2. x>>1 => x= x/2

  3. mid = (left+right)/2 => mid = (left+right) >> 1

  4. X=X&(X-1)清零最低位的1

  5. X&-X =>得到最低位的1

  6. x&(~x) = 0
  
  7. x^1s = ~x  // 1s = ~0
  
  8. x^(~x) = 1s 【1s表示全1】

## 布隆过滤器【Bloom Filter】

实现：实现一个超大的位数组和几个哈希函数，假设位数组的长度为m，哈希函数的个数为k

- 添加元素
  1. 将要添加的元素给k个hash函数
  2. 通过哈希函数得到m长度数组中的k个位置
  3. 将这k个位置置为1
- 查询元素
  1. 将要查询的元素给到k个hash函数
  2. 得到m长度数组中的k个位置
  3. 当k个位置中存在0，则这个元素一定不在集合中
  4. 当看个位置全为1，则这个元素可能存在于集合中【有一定的误判率】

## LRU Cache

实现：Hash Table + Double LinkedList

- 时间复杂度

  1. 查询：O(1)
  2. 修改、更新：O(n)

- 替换策略

  1. LFU: Least Frequently Used(最高频率使用优先)
  2. LRU: Least Recently Used(最近使用优先)

- java实现

  ```java
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
  ```

## 排序算法

### 比较类排序：

通过比较来决定元素间的相对次序，时间复杂度不能突破O(nlogn),所以也叫做非线性时间比较类排序

- 交换排序

  1. 冒泡排序Bubble Sort

     嵌套循环，每次查看相邻的元素如果逆序，则交换。

  2. 快速排序（Quick Sort）【O(nlogn)】高级

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
     
     ```c++
     //C/C++
     int random_partition(vector<int>& nums, int l, intr) {
       int random_pivot_index = rand() % (r - l +1) + l;  //随机选择pivot
       int pivot = nums[random_pivot_index];
       swap(nums[random_pivot_index], nums[r]);
       int i = l - 1;
       for (int j=l; j<r; j++) {
         if (nums[j] < pivot) {
           i++;
           swap(nums[i], nums[j]);
         }
       }
       int pivot_index = i + 1;
       swap(nums[pivot_index], nums[r]);
       return pivot_index;
     }
     void random_quicksort(vector<int>& nums, int l, int r) {
       if (l < r) {
         int pivot_index = random_partition(nums, l, r);
         random_quicksort(nums, l, pivot_index-1);
         random_quicksort(nums, pivot_index+1, r);
       }
     }
     ```
     
     ```python
     #Python
     
     def quick_sort(begin, end, nums):
         if begin >= end:
             return
         pivot_index = partition(begin, end, nums)
         quick_sort(begin, pivot_index-1, nums)
         quick_sort(pivot_index+1, end, nums)
         
     def partition(begin, end, nums):
         pivot = nums[begin]
         mark = begin
         for i in range(begin+1, end+1):
             if nums[i] < pivot:
                 mark +=1
                 nums[mark], nums[i] = nums[i], nums[mark]
         nums[begin], nums[mark] = nums[mark], nums[begin]
         return mark
     ```
     
     ```javascript
     // JavaScript
     const quickSort = (nums, left, right) => {
       if (nums.length <= 1) return nums
       if (left < right) {
         index = partition(nums, left, right)
         quickSort(nums, left, index-1)
         quickSort(nums, index+1, right)
       }
     }
           
     const partition = (nums, left, right) => {
       let pivot = left, index = left + 1
       for (let i = index; i <= right; i++) {
         if (nums[i] < nums[pivot]) {
           [nums[i], nums[index]] = [nums[index], nums[i]]
           index++
         }
       }
       [nums[pivot], nums[index-1]] = [nums[index-1], nums[pivot]]
       return index -1
     }
     ```
     
     

- 插入排序Insertion Sort

  从前到后逐步构建有序序列；对于未排序数据，在已排序序列中从后

  向前扫描，找到相应位置并插入

  1. 简单插入排序
  2. 希尔排序

- 选择排序Selection Sort

  1. 简单选择排序 【O(n^2)】

     每次找最小值，然后放到待排序数组的起始位置。

  2. 堆排序【O(nlogn)】— 

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

     ```python
     #Python
     
     def heapify(parent_index, length, nums):
         temp = nums[parent_index]
         child_index = 2*parent_index+1
         while child_index < length:
             if child_index+1 < length and nums[child_index+1] > nums[child_index]:
                 child_index = child_index+1
             if temp > nums[child_index]:
                 break
             nums[parent_index] = nums[child_index]
             parent_index = child_index
             child_index = 2*parent_index + 1
         nums[parent_index] = temp
     
     
     def heapsort(nums):
         for i in range((len(nums)-2)//2, -1, -1):
             heapify(i, len(nums), nums)
         for j in range(len(nums)-1, 0, -1):
             nums[j], nums[0] = nums[0], nums[j]
             heapify(0, j, nums)
     ```

     ```c++
     C/C++
     
     void heapify(vector<int> &array, int length, int i) {
         int left = 2 * i + 1, right = 2 * i + 2;
         int largest = i;
     
         if (left < length && array[left] > array[largest]) {
             largest = left;
         }
         if (right < length && array[right] > array[largest]) {
             largest = right;
         }
     
         if (largest != i) {
             int temp = array[i]; array[i] = array[largest]; array[largest] = temp;
             heapify(array, length, largest);
         }
     
     
         return ;
     }
     
     void heapSort(vector<int> &array) {
         if (array.size() == 0) return ;
     
         int length = array.size();
         for (int i = length / 2 - 1; i >= 0; i--) 
             heapify(array, length, i);
     
         for (int i = length - 1; i >= 0; i--) {
             int temp = array[0]; array[0] = array[i]; array[i] = temp;
             heapify(array, i, 0);
         }
     
         return ;
     }
     ```

     ```javascript
     // Javascript
     function heapSort(arr) {
       if (arr.length === 0) return;
       let len = arr.length;
       // 建堆
       for (let i = Math.floor(len / 2) - 1; i >= 0; i--) {
         heapify(arr, len, i);
       }
       // 排序
       for (let i = len - 1; i >= 0; i--) {
         // 堆顶元素与最后一个互换
         [arr[0], arr[i]] = [arr[i], arr[0]];
         // 对 0 ～ i 的数组建堆
         heapify(arr, i, 0);
       }
     }
     function heapify(arr, len, i) {
       let left = i * 2 + 1;
       let right = i * 2 + 2;
       let largest = i;
       if (left < len && arr[left] > arr[largest]) {
         largest = left;
       }
       if (right < len && arr[right] > arr[largest]) {
         largest = right;
       }
       if (largest !== i) {
         [arr[i], arr[largest]] = [arr[largest], arr[i]];
         heapify(arr, len, largest);
       }
     }
     ```

     

- 归并排序Merge Sort【O(nlogn)】——分治

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
  
  ```c++
  C/C++
  
  void mergeSort(vector<int> &nums, int left, int right) {
  	if (left >= right) return;
  	
  	int mid = left + (right - left) / 2;
  	mergeSort(nums, left, mid);
  	mergeSort(nums, mid+1, right);
  	
  	merge(nums, left, mid, right);
  }
  
  
  void merge(vector<int> &nums, int left, int mid, int right) {
  	vector<int> tmp(right-left+1);
  	int i = left, j = mid+1, k = 0;
  	
  	while (i <= mid && j <= right) {
  		tmp[k++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
  	}
  	while (i <= mid) tmp[k++] = nums[i++];
  	while (j <= right) tmp[k++] = nums[j++];
  	
  	for (i = left, k = 0; i <= right;) nums[i++] = tmp[k++];
  }
  
  ```
  
  ```python
  #Python
  
  def mergesort(nums, left, right):
      if right <= left:
          return
      mid = (left+right) >> 1
      mergesort(nums, left, mid)
      mergesort(nums, mid+1, right)
      merge(nums, left, mid, right)
  
  def merge(nums, left, mid, right):
      temp = []
      i = left
      j = mid+1
      while i <= mid and j <= right:
          if nums[i] <= nums[j]:
              temp.append(nums[i])
              i +=1
          else:
              temp.append(nums[j])
              j +=1
      while i<=mid:
          temp.append(nums[i])
          i +=1
      while j<=right:
          temp.append(nums[j])
          j +=1
      nums[left:right+1] = temp
  ```
  
  ```javascript
  // JavaScript
  const mergeSort = (nums) => {
    if (nums.length <= 1) return nums
    let mid = Math.floor(nums.length/2), 
        left = nums.slice(0, mid), 
        right = nums.slice(mid)
    return merge(mergeSort(left), mergeSort(right))
  }
  
  const merge(left, right) => {
    let result = []
    while(left.length && right.length) {
      result.push(left[0] <= right[0] ? left.shift() : right.shift()
    }
    while(left.length) result.push(left.shift())
    while(right.length) result.push(right.shift())
    return result
  }
  ```
  
  
  
  1. 二路归并排序
  2. 多路归并排序

### 非比较类排序

不通过比较决定元素的相对次序，以线性时间运行，也叫做线性时间比较类排序

- 计数排序【Counting Sort】

  输入的数据必须是确定范围的整数，将输入的数据值转化为键值存在在新开辟的数组空间中，然后依次把计数大于1的填充回原数组

- 桶排序 【bucket Sort】

  假设输入数据服从均匀分布，将数据分到有限数量的桶里，每个桶再分别排序【有可能再使用的别的算法或是以递归方式继续使用桶排序进行排序】

- 基数排序

  按低位先排序，然后收集，再按照高位排序，然后再收集，依次类推，直到最高位，有时候有些属性是有优先级顺序的，先按低优先级再按高优先级进行排序

  

