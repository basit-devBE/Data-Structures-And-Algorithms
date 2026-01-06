import java.util.Arrays;

public class Merge {
    
    private static class Element {
        int value;
        int originalIndex;
        
        Element(int value, int originalIndex) {
            this.value = value;
            this.originalIndex = originalIndex;
        }
    }
    
    private int[] counts;
    
    public int[] countSmaller(int[] nums) {
        int n = nums.length;
        counts = new int[n];
        
        Element[] elements = new Element[n];
        for (int i = 0; i < n; i++) {
            elements[i] = new Element(nums[i], i);
        }
        
        mergeSort(elements, 0, n - 1);
        
        return counts;
    }
    
    private void mergeSort(Element[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        
        int mid = left + (right - left) / 2;
        
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        
        merge(arr, left, mid, right);
    }
    
    private void merge(Element[] arr, int left, int mid, int right) {
        Element[] temp = new Element[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        int rightCount = 0;
        
        while (i <= mid && j <= right) {
            if (arr[j].value < arr[i].value) {
                temp[k++] = arr[j++];
                rightCount++;
            } else {
                counts[arr[i].originalIndex] += rightCount;
                temp[k++] = arr[i++];
            }
        }
        
        while (i <= mid) {
            counts[arr[i].originalIndex] += rightCount;
            temp[k++] = arr[i++];
        }
        
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        
        for (int idx = 0; idx < temp.length; idx++) {
            arr[left + idx] = temp[idx];
        }
    }
    
    public static void main(String[] args) {
        Merge solution = new Merge();
        
        System.out.println("=== MERGE SORT: COUNTING INVERSIONS ===\n");
        
        int[] nums1 = {5, 2, 6, 1};
        int[] result1 = solution.countSmaller(nums1);
        System.out.println("Input: nums = " + Arrays.toString(nums1));
        System.out.println("Output: counts = " + Arrays.toString(result1));
        System.out.println("Explanation:");
        System.out.println("  5: 2 smaller elements to the right (2, 1)");
        System.out.println("  2: 1 smaller element to the right (1)");
        System.out.println("  6: 1 smaller element to the right (1)");
        System.out.println("  1: 0 smaller elements to the right\n");
        
        solution = new Merge();
        int[] nums2 = {-1, -2};
        int[] result2 = solution.countSmaller(nums2);
        System.out.println("Input: nums = " + Arrays.toString(nums2));
        System.out.println("Output: counts = " + Arrays.toString(result2));
        System.out.println();
        
        solution = new Merge();
        int[] nums3 = {3, 2, 1};
        int[] result3 = solution.countSmaller(nums3);
        System.out.println("Input: nums = " + Arrays.toString(nums3));
        System.out.println("Output: counts = " + Arrays.toString(result3));
    }
}
