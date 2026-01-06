 # QUICK SORT & QUICK SELECT NOTES

## What is Quick Sort?

Quick Sort is a **divide-and-conquer** sorting algorithm that:
1. Chooses a **pivot** element
2. **Partitions** the array: elements < pivot go left, elements > pivot go right
3. Recursively sorts the left and right partitions

## Key Characteristics

**Time Complexity:** 
- Average: O(n log n)
- Worst: O(n²) - when pivot is always min/max
- Best: O(n log n)

**Space Complexity:** O(log n) - recursion stack

**Stability:** NOT stable - relative order may change

**Type:** Divide and Conquer, In-place sorting

## How Quick Sort Works

### Step 1: Choose Pivot
Select an element as pivot (commonly last element, first element, or random)

```
[8, 3, 1, 5, 2]  → Pick pivot = 2 (last element)
```

### Step 2: Partition
Rearrange array so:
- Elements ≤ pivot are on the left
- Pivot is in its final sorted position
- Elements > pivot are on the right

```
[8, 3, 1, 5, 2]  → Partition around 2
      ↓
[1, 2, 8, 5, 3]
    ↑
  pivot is now at correct position
```

### Step 3: Recursively Sort
Apply same process to left and right partitions

```
Left: [1]  (single element, sorted)
Right: [8, 5, 3]  → Continue partitioning
```

## Partition Algorithm (Lomuto Scheme)

```java
int partition(int[] arr, int left, int right) {
    int pivot = arr[right];  // Choose last element as pivot
    int i = left;            // Position for next small element
    
    for (int j = left; j < right; j++) {
        if (arr[j] < pivot) {
            swap(arr, i, j);  // Move smaller elements to left
            i++;
        }
    }
    
    swap(arr, i, right);  // Place pivot in correct position
    return i;             // Return pivot's final index
}
```

### Visual Example of Partition

```
Array: [8, 3, 1, 5, 2]  pivot = 2
       
i=0, j=0: arr[0]=8 > 2, skip
i=0, j=1: arr[1]=3 > 2, skip
i=0, j=2: arr[2]=1 < 2, swap(0,2), i=1
          [1, 3, 8, 5, 2]
i=1, j=3: arr[3]=5 > 2, skip
Done: swap(i=1, right=4)
      [1, 2, 8, 5, 3]
           ↑
         pivot at index 1
```

---

## What is QuickSelect?

QuickSelect finds the **k-th smallest element** WITHOUT fully sorting the array.

**Key Insight:** After partitioning, the pivot is at its correct sorted position!

### QuickSelect Algorithm

```java
int quickSelect(int[] nums, int left, int right, int k) {
    if (left == right) return nums[left];
    
    int pivotIndex = partition(nums, left, right);
    
    if (k == pivotIndex) {
        return nums[k];  // Found it!
    } else if (k < pivotIndex) {
        return quickSelect(nums, left, pivotIndex - 1, k);  // Search left
    } else {
        return quickSelect(nums, pivotIndex + 1, right, k);  // Search right
    }
}
```

**Time Complexity:** 
- Average: O(n)
- Worst: O(n²)

**Advantage over sorting:** O(n) vs O(n log n)

---

# MINIMUM SUBARRAY SUM DIFFERENCE PROBLEM

## Problem Statement

Given an array `nums` and integer `k`:
1. Find the k-th smallest element (pivot P)
2. Partition into:
   - A: elements < P
   - B: elements > P
3. Assign P to the subarray with larger sum
4. Return |Sum(A) - Sum(B)|

---

## Thought Process

### Why QuickSelect?

The problem asks for:
1. **k-th smallest element** → QuickSelect is perfect for this
2. **Partition around pivot** → QuickSelect does this naturally
3. **Don't need full sort** → QuickSelect is O(n) vs O(n log n)

### Naive Approach
```java
Arrays.sort(nums);           // O(n log n)
int pivot = nums[k - 1];     // Get k-th smallest
// Then partition and calculate
```
Works, but unnecessary sorting!

### Optimized Approach
```java
int pivot = quickSelect(nums, 0, n-1, k-1);  // O(n) average
// Then partition and calculate
```

---

## Solution Strategy

### Step 1: Find k-th Smallest (QuickSelect)
Use partitioning to locate the k-th smallest element without full sort

### Step 2: Partition into A and B
- A: all elements < pivot
- B: all elements > pivot
- Pivot: separate (will be assigned later)

### Step 3: Calculate Initial Sums
```java
int sumA = 0, sumB = 0;
for (int num : nums) {
    if (num < pivot) sumA += num;
    else if (num > pivot) sumB += num;
}
```

### Step 4: Assign Pivot
```java
if (sumA > sumB) {
    sumA += pivot;  // Add to larger sum
} else {
    sumB += pivot;
}
```

### Step 5: Return Difference
```java
return Math.abs(sumA - sumB);
```

---

## Complete Walkthrough: [8, 3, 1, 5, 2], k=3

### Goal
Find 3rd smallest element and minimize sum difference

### Step 1: QuickSelect to Find 3rd Smallest

**Initial:** `[8, 3, 1, 5, 2]`, looking for index k-1 = 2

**Iteration 1:** Partition around 2 (last element)
```
[8, 3, 1, 5, 2]
       
i=0, j=0: 8 > 2, skip
i=0, j=1: 3 > 2, skip  
i=0, j=2: 1 < 2, swap(0,2), i=1
          [1, 3, 8, 5, 2]
i=1, j=3: 5 > 2, skip

Place pivot: swap(1, 4)
Result: [1, 2, 8, 5, 3]
             ↑
        pivotIndex = 1
```

k=2, pivotIndex=1 → k > pivotIndex, search right

**Iteration 2:** Partition right side [8, 5, 3] around 3
```
Relative indices: [8, 5, 3]
Partition around 3:

[3, 5, 8]
 ↑
pivotIndex = 2 (in original array)
```

k=2, pivotIndex=2 → Found! Pivot = 3

### Step 2: Partition into A and B

Original array: `[8, 3, 1, 5, 2]`
Pivot P = 3

```
Elements < 3: [1, 2]      → A
Elements = 3: [3]         → Pivot (assign later)
Elements > 3: [8, 5]      → B
```

### Step 3: Calculate Sums

```
sumA = 1 + 2 = 3
sumB = 8 + 5 = 13
pivot = 3
```

### Step 4: Assign Pivot to Larger Sum

```
sumA = 3
sumB = 13
sumB > sumA, so add pivot to B

sumB = 13 + 3 = 16
```

### Step 5: Calculate Difference

```
|sumA - sumB| = |3 - 16| = 13
```

**Answer: 13** ✓

---

## Visual Summary of QuickSelect Process

```
Finding 3rd smallest in [8, 3, 1, 5, 2]:

Round 1: Partition around 2
[8, 3, 1, 5, 2] → [1, 2, 8, 5, 3]
             ↑                ↑
           pivot          position 1
        k=2 > 1, search right →

Round 2: Partition [8, 5, 3] around 3  
[8, 5, 3] → [3, 5, 8]
         ↑       ↑
       pivot  position 2
        k=2 == 2, found! ✓
        
Result: 3rd smallest = 3
```

---

## Another Example: [5, 2, 8, 1, 9], k=2

### QuickSelect for 2nd smallest (k-1 = 1)

```
[5, 2, 8, 1, 9]  Partition around 9
↓
[5, 2, 8, 1, 9]  (9 goes to position 4)
Continue with left side...

Eventually find: 2nd smallest = 2
```

### Partition & Calculate

```
Elements < 2: [1]           sumA = 1
Pivot: 2
Elements > 2: [5, 8, 9]     sumB = 22

sumB > sumA, add pivot to B
sumB = 24

Difference: |1 - 24| = 23
```

---

## Key Differences: QuickSort vs QuickSelect

| Feature | QuickSort | QuickSelect |
|---------|-----------|-------------|
| Goal | Sort entire array | Find k-th element |
| Recursion | Both sides | One side only |
| Time | O(n log n) avg | O(n) avg |
| Output | Sorted array | Single element |
| Use case | Need all sorted | Need specific rank |

---

## Why This Approach is Optimal

### 1. Efficient k-th Element Finding
- **QuickSelect:** O(n) average
- **Full Sort:** O(n log n)
- **For k=3, n=1000:** QuickSelect is ~10x faster

### 2. Natural Partitioning
QuickSelect already partitions the array, so we get:
- Elements < pivot
- Pivot location
- Elements > pivot

### 3. Single Pass Sum Calculation
After finding pivot, one pass through array calculates both sums: O(n)

### 4. Overall Complexity
- QuickSelect: O(n) average
- Sum calculation: O(n)
- **Total: O(n) average**

Compare to naive:
- Sort: O(n log n)
- Find k-th: O(1)
- Sum calculation: O(n)
- **Total: O(n log n)**

---

## Complexity Analysis

### Time Complexity

**Best/Average Case:** O(n)
- QuickSelect average: O(n)
- Sum calculation: O(n)
- Total: O(n)

**Worst Case:** O(n²)
- QuickSelect worst (bad pivot choices): O(n²)
- Rare with good pivot selection

### Space Complexity

**O(log n)** - recursion stack for QuickSelect

### Optimization: Randomized Pivot
```java
int randomPivot = left + random.nextInt(right - left + 1);
swap(nums, randomPivot, right);
// Then partition as usual
```
This makes worst case O(n²) very unlikely!

---

## Common Pitfalls & Tips

### Pitfall 1: Off-by-One Errors
```java
// k is 1-indexed in problem, 0-indexed in array
int pivot = quickSelect(nums, 0, n-1, k-1);  // Note: k-1!
```

### Pitfall 2: Modifying Original Array
```java
// QuickSelect modifies the array!
int[] copy = nums.clone();  // Make copy if needed
int pivot = quickSelect(copy, 0, n-1, k-1);
```

### Pitfall 3: Handling Duplicates
If pivot value appears multiple times, count all occurrences:
```java
for (int num : nums) {
    if (num < pivot) sumA += num;
    else if (num == pivot) pivotSum += num;
    else sumB += num;
}
```

---

## When to Use QuickSelect

✅ **Use QuickSelect when:**
- Need k-th smallest/largest element
- Don't need full sorted array
- Average O(n) is acceptable
- Can modify input array

❌ **Don't use QuickSelect when:**
- Need multiple k-th elements (sort once instead)
- Need stable selection (use other methods)
- Cannot modify input array
- Worst case O(n²) is unacceptable

---

## Real-World Applications

1. **Finding Median:** Select k = n/2
2. **Top K elements:** Select k-th largest, then filter
3. **Database Queries:** "Find 95th percentile"
4. **Load Balancing:** Partition tasks by size
5. **Network Routing:** Select optimal path threshold

This problem beautifully combines partition logic with sum optimization!
