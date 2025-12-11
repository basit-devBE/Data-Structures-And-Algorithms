public class GroceryReceipt {

    static int  grocery(String[] receipt,String target){
        for(int i =0;i < receipt.length; i++){
            if(receipt[i].equals(target)){
                System.out.println("Found the item: " + target);
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] receipts = {"Apple","Banana","Orange"};
        String target = "Orange";

        GroceryReceipt.grocery(receipts, target);
    }

}
