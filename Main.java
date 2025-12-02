import java.util.Arrays;

public  class Main {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9};
        MaximumSubArray msa = new MaximumSubArray();
//        int result = msa.maxSumOfaPositiveArray(nums);
//        System.out.println("Maximum sum of a positive array: " + result);

        MoveZeroes mz = new MoveZeroes();
        int[] bernicebreast = {1,2,4,0,2,0,8,0,3,5,6,7};
        int[] results = mz.moveZeroes(bernicebreast);
        System.out.println(Arrays.toString(results));

    }
}