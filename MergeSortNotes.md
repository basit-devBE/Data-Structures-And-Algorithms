# MERGE SORT NOTES

## What is Merge Sort?

Merge Sort is a **divide-and-conquer** sorting algorithm that:
1. Divides the array into two halves
2. Recursively sorts each half
3. Merges the two sorted halves back together

## Key Characteristics

**Time Complexity:** O(n log n) - guaranteed (even worst case)
**Space Complexity:** O(n) - requires temporary arrays
**Stability:** Stable - maintains relative order of equal elements
**Type:** Divide and Conquer

## How Merge Sort Works

### Step 1: Divide
Split the array in half repeatedly until you have single elements

```
[5, 2, 6, 1]
    ↓
[5, 2]  [6, 1]
  ↓       ↓
[5] [2] [6] [1]
```

### Step 2: Conquer (Merge)
Merge pairs of sorted arrays back together

```
[5] [2] → [2, 5]
[6] [1] → [1, 6]
    ↓
[2, 5] [1, 6] → [1, 2, 5, 6]
```

## Basic Merge Sort Implementation

```java
void mergeSort(int[] arr, int left, int right) {
    if (left >= right) return;
    
    int mid = left + (right - left) / 2;
    
    mergeSort(arr, left, mid);        // Sort left half
    mergeSort(arr, mid + 1, right);   // Sort right half
    
    merge(arr, left, mid, right);     // Merge sorted halves
}

void merge(int[] arr, int left, int mid, int right) {
    // Create temp array
    int[] temp = new int[right - left + 1];
    int i = left, j = mid + 1, k = 0;
    
    // Compare and merge
    while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++];
        } else {
            temp[k++] = arr[j++];
        }
    }
    
    // Copy remaining elements
    while (i <= mid) temp[k++] = arr[i++];
    while (j <= right) temp[k++] = arr[j++];
    
    // Copy back to original array
    for (int idx = 0; idx < temp.length; idx++) {
        arr[left + idx] = temp[idx];
    }
}
```

---

# COUNTING INVERSIONS PROBLEM

## Problem Statement

Given an array `nums`, for each element, count how many elements to its **right** are **smaller** than it.

**Example:** `[5, 2, 6, 1]`
- 5: has 2 smaller elements to right (2, 1) → count = 2
- 2: has 1 smaller element to right (1) → count = 1
- 6: has 1 smaller element to right (1) → count = 1
- 1: has 0 smaller elements to right → count = 0

**Output:** `[2, 1, 1, 0]`

---

## Thought Process

### Naive Approach (Brute Force)
```java
for (int i = 0; i < n; i++) {
    int count = 0;
    for (int j = i + 1; j < n; j++) {
        if (nums[j] < nums[i]) count++;
    }
    counts[i] = count;
}
```
**Time:** O(n²) - too slow for large inputs

### Why Merge Sort?

**Key Insight:** During the merge step, when we choose an element from the **left subarray** over elements from the **right subarray**, those right elements are smaller and appear after it!

When merging `[2, 5]` and `[1, 6]`:
- We pick `1` from right → doesn't count for anyone
- We pick `2` from left → `1` was already picked, so `2` has 1 smaller element to its right
- We pick `5` from left → `1` was already picked, so `5` has 1 smaller element to its right
- We pick `6` from right → doesn't count

---

## Solution Strategy

### 1. Track Original Indices
Since we're sorting, we need to remember where each element came from:
```java
class Element {
    int value;           // The actual value
    int originalIndex;   // Its position in original array
}
```

### 2. Count During Merge
During merge, track how many elements from the right half we've already used:
```java
int rightCount = 0;  // Elements from right subarray already placed
```

### 3. When to Count
When we pick an element from the **left** subarray:
- All elements already picked from right subarray are smaller
- Add `rightCount` to this element's count

```java
if (arr[i].value > arr[j].value) {
    // Pick from left
    counts[arr[i].originalIndex] += rightCount;
    temp[k++] = arr[i++];
} else {
    // Pick from right (it's smaller)
    temp[k++] = arr[j++];
    rightCount++;  // One more small element on the right
}
```

---

## Complete Walkthrough: [5, 2, 6, 1]

### Initial State
```
Elements: [(5, idx=0), (2, idx=1), (6, idx=2), (1, idx=3)]
counts = [0, 0, 0, 0]
```

### Step 1: Split into Singles
```
[(5, 0)]  [(2, 1)]  [(6, 2)]  [(1, 3)]
```

### Step 2: Merge [5] and [2]
```
Left:  [(5, 0)]
Right: [(2, 1)]
rightCount = 0

Compare: 5 > 2
  → Pick (2, 1) from right
  → rightCount = 1
  → Result: [(2, 1)]

No more right elements, pick remaining from left:
  → counts[0] += 1  → counts = [1, 0, 0, 0]
  → Result: [(2, 1), (5, 0)]
```

### Step 3: Merge [6] and [1]
```
Left:  [(6, 2)]
Right: [(1, 3)]
rightCount = 0

Compare: 6 > 1
  → Pick (1, 3) from right
  → rightCount = 1
  → Result: [(1, 3)]

No more right elements, pick remaining from left:
  → counts[2] += 1  → counts = [1, 0, 1, 0]
  → Result: [(1, 3), (6, 2)]
```

### Step 4: Merge [(2, 1), (5, 0)] and [(1, 3), (6, 2)]
```
Left:  [(2, 1), (5, 0)]
Right: [(1, 3), (6, 2)]
rightCount = 0

Compare: 2 > 1
  → Pick (1, 3) from right
  → rightCount = 1
  → Result: [(1, 3)]

Compare: 2 > 6? No, 2 < 6
  → Pick (2, 1) from left
  → counts[1] += 1  → counts = [1, 1, 1, 0]
  → Result: [(1, 3), (2, 1)]

Compare: 5 < 6
  → Pick (5, 0) from left
  → counts[0] += 1  → counts = [2, 1, 1, 0]
  → Result: [(1, 3), (2, 1), (5, 0)]

No more left elements:
  → Pick (6, 2) from right
  → Result: [(1, 3), (2, 1), (5, 0), (6, 2)]
```

### Final Answer
```
counts = [2, 1, 1, 0]
```

---

## Visual Representation of Key Insight

```
When merging:  [2, 5]  and  [1, 6]
                 ↑           ↑
                 i           j

Step 1: arr[j]=1 < arr[i]=2
  → Pick 1 (smaller, goes first)
  → rightCount = 1
  
Step 2: arr[i]=2 < arr[j]=6
  → Pick 2 from left
  → Element 2 has 'rightCount=1' smaller elements to its right
  → counts[2's original index] += 1

Step 3: arr[i]=5 < arr[j]=6
  → Pick 5 from left
  → Element 5 has 'rightCount=1' smaller elements to its right
  → counts[5's original index] += 1
```

**Key:** `rightCount` tracks how many elements from the right subarray we've already placed before the current left element. These are all smaller AND to the right in the original array!

---

## Time and Space Complexity

**Time Complexity:** O(n log n)
- Merge sort divides array log(n) times
- Each level processes n elements
- Total: O(n log n)

**Space Complexity:** O(n)
- Temporary arrays during merge: O(n)
- Recursion stack depth: O(log n)
- Element objects: O(n)
- Total: O(n)

**Comparison to Brute Force:**
- Brute Force: O(n²) - nested loops
- Merge Sort: O(n log n) - much faster for large inputs

For n = 100,000:
- Brute Force: ~10 billion operations
- Merge Sort: ~1.7 million operations

---

## Why This Works

1. **Merge Sort naturally processes pairs:** Every element eventually gets compared to elements on its right
2. **Sorted subarrays preserve order:** If element A is in the left subarray and B is in the right, B originally appeared after A
3. **rightCount tracks inversions:** When we pick from left, rightCount tells us how many smaller elements from right we've seen
4. **Original indices preserved:** We track where each element came from so counts go to the right position

This is a beautiful application of merge sort beyond just sorting!
