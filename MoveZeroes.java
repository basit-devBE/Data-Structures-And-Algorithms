public class MoveZeroes {
    public int[] moveZeroes(int[] nums) {
        int lastNonZero = 0; // position to place the next non-zero

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // swap nums[i] with nums[lastNonZero] only if i != lastNonZero
                int temp = nums[i];
                nums[i] = nums[lastNonZero];
                nums[lastNonZero] = temp;
                lastNonZero++;
            }
        }
        return nums;
    }
}
