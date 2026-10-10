
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NetBalancePeriodCounter {

    static long countPeriods(int[] transactions, long k) {
        Map<Long, Integer> frequency = new HashMap<>();
        frequency.put(0L, 1);

        long prefix = 0;
        long count = 0;

        for (int value : transactions) {
            prefix += value;

            count += frequency.getOrDefault(prefix - k, 0);

            frequency.put(prefix,
                    frequency.getOrDefault(prefix, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of transactions: ");
        int n = sc.nextInt();

        int[] transactions = new int[n];
        for (int i = 0; i < n; i++) {
            transactions[i] = sc.nextInt();
        }

        System.out.print("Target sum: ");
        long k = sc.nextLong();

        System.out.println(countPeriods(transactions, k));
        sc.close();
    }
}
