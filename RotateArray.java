public class RotateArray {
    public void rotateArray(int[] nums, int k){
        int n = nums.length;
        int [] temp = new int[n] ;
        for(int i = 0; i< nums.length;i++){
            temp[(i + k) % n] = nums[i];
        }
//        for(int i= 0;i < n; i++){
//            nums[i] = temp[i];
//        }
        System.arraycopy(temp, 0, nums, 0, n);

    }
}
