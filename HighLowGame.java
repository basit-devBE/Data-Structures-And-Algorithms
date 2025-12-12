import java.util.Arrays;

public class HighLowGame {
    static int game(int[] arr, int target){
        Arrays.sort(arr);
        int low =0;
        int high = arr.length -1;
        int guesses =0;

        while(low <= high){
            guesses ++;
            int mid = (low + high) /2;
            if(arr[mid]== target){
                return guesses;
            }else if(arr[mid] < target){
                low = mid +1;
            }else{
                high = mid -1;
            }
        }
   return guesses;


    }

}
