
import java.util.Scanner;

public class BudgetFriendlyStreak {

    static int[] longestStreak(int[] costs, long budget) {
        int left = 0;
        int bestLength = 0;
        int bestStart = -1;
        long sum = 0;

        for (int right = 0; right < costs.length; right++) {
            sum += costs[right];

            while (left <= right && sum > budget) {
                sum -= costs[left];
                left++;
            }

            int length = right - left + 1;

            if (length > bestLength) {
                bestLength = length;
                bestStart = left;
            }
        }

        return new int[]{bestLength, bestStart};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of days: ");
        int n = sc.nextInt();

        int[] costs = new int[n];
        for (int i = 0; i < n; i++) {
            costs[i] = sc.nextInt();
        }

        System.out.print("Budget: ");
        long budget = sc.nextLong();

        int[] result = longestStreak(costs, budget);
        System.out.println("(" + result[0] + ", " + result[1] + ")");

        sc.close();
    }
}
