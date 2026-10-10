public class Main5 {

    public static void maxSumSubarray(int[] sales, int k){
        int sum = 0;

        for(int i =0; i < k; i++){
            sum += sales[i];
        }
        int maxSum = sum;
        int start = 0;

        for(int i = k; i < sales.length; i++){
            sum += sales[i] - sales[i - k];

            if (sum > maxSum){
                maxSum = sum;
                start = i - k + 1;
            }
        }
        System.out.println("Maximum sum: " + maxSum);

        System.out.print("Subarray: [");
        for (int i = start; i < start + k; i++){
            System.out.print(sales[i]);
            if (i < start + k -1){
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] sales = {2, 5, 1, 3, 2};
        int k = 3;
        maxSumSubarray(sales, k);
    }
}