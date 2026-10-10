
import java.util.Scanner;

public class ProductPriceFinder {

    static int lowerBound(int[] prices, int target) {
        int low = 0;
        int high = prices.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    static int upperBound(int[] prices, int target) {
        int low = 0;
        int high = prices.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        int[] prices = new int[n];

        System.out.println("Enter prices in ascending order:");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        System.out.print("Enter lower price: ");
        int lower = sc.nextInt();

        System.out.print("Enter upper price: ");
        int upper = sc.nextInt();

        if (lower > upper) {
            System.out.println("Invalid range");
        } else {
            int first = lowerBound(prices, lower);
            int afterLast = upperBound(prices, upper);

            System.out.println("Lower bound index: " + first);
            System.out.println("Upper bound index: " + afterLast);
            System.out.println("Count: " + (afterLast - first));
        }

        sc.close();
    }
}
