import java.util.Arrays;

public class QuickSortPartition {
    
    public int minSubarraySumDifference(int[] nums, int k) {
        int n = nums.length;
        
        int pivot = quickSelect(nums, 0, n - 1, k - 1);
        
        int sumA = 0;
        int sumB = 0;
        
        for (int num : nums) {
            if (num < pivot) {
                sumA += num;
            } else if (num > pivot) {
                sumB += num;
            }
        }
        
        if (sumA > sumB) {
            sumA += pivot;
        } else {
            sumB += pivot;
        }
        
        return Math.abs(sumA - sumB);
    }
    
    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        
        int pivotIndex = partition(nums, left, right);
        
        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }
    
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left;
        
        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        
        swap(nums, i, right);
        return i;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static void main(String[] args) {
        QuickSortPartition solution = new QuickSortPartition();
        
        System.out.println("=== QUICK SELECT: MINIMUM SUBARRAY SUM DIFFERENCE ===\n");
        
        int[] nums1 = {8, 3, 1, 5, 2};
        int k1 = 3;
        int result1 = solution.minSubarraySumDifference(nums1.clone(), k1);
        System.out.println("Example 1:");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", k = " + k1);
        System.out.println("Sorted: [1, 2, 3, 5, 8]");
        System.out.println("3rd smallest (pivot) = 3");
        System.out.println("A (< 3): [1, 2], Sum = 3");
        System.out.println("B (> 3) + pivot: [5, 8, 3], Sum = 16");
        System.out.println("Difference: |3 - 16| = " + result1);
        System.out.println();
        
        int[] nums2 = {5, 2, 8, 1, 9};
        int k2 = 2;
        int result2 = solution.minSubarraySumDifference(nums2.clone(), k2);
        System.out.println("Example 2:");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", k = " + k2);
        System.out.println("Sorted: [1, 2, 5, 8, 9]");
        System.out.println("2nd smallest (pivot) = 2");
        System.out.println("Output: " + result2);
        System.out.println();
        
        int[] nums3 = {10, 5, 3, 7, 2};
        int k3 = 4;
        int result3 = solution.minSubarraySumDifference(nums3.clone(), k3);
        System.out.println("Example 3:");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", k = " + k3);
        System.out.println("Sorted: [2, 3, 5, 7, 10]");
        System.out.println("4th smallest (pivot) = 7");
        System.out.println("Output: " + result3);
    }
}
