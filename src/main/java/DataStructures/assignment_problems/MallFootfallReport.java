
import java.util.Arrays;
import java.util.Scanner;

public class MallFootfallReport {

    static int[] footfallReport(int[] visitors, int[][] queries) {
        int n = visitors.length;
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + visitors[i];
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int start = queries[i][0];
            int end = queries[i][1];
            answer[i] = (int) (prefix[end + 1] - prefix[start]);
        }

        return answer;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Number of hours: ");
        int n = sc.nextInt();

        int[] visitors = new int[n];
        for (int i = 0; i < n; i++) {
            visitors[i] = sc.nextInt();
        }

        System.out.print("Number of queries: ");
        int q = sc.nextInt();

        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }

        System.out.println(Arrays.toString(footfallReport(visitors, queries)));
        sc.close();
    }
}
