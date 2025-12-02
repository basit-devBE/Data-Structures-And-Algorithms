public class MaximumSubArray {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];      // Overall maximum
        int maxEndingHere = nums[0]; // Max sum ending at current position

        for(int i = 1; i < nums.length; i++) {
            // Either extend existing subarray or start fresh
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);
            // Update overall maximum
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }

    public  int maxSumOfaPositiveArray(int[] nums){
        int maxSum = 0;
        for(int i = 0; i < nums.length;i++){
            if(nums[i] > 0){
                maxSum += nums[i];
            }
        }
        return maxSum;
    }
}


