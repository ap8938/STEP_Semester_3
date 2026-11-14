
import java.util.Scanner;

class Candidate {
    String name;
    int[] scores;
    int total;
    int bestClimb;
    int startTest;
    int endTest;

    Candidate(String name, int[] scores) {
        this.name = name;
        this.scores = scores;

        total = 0;

        for (int score : scores) {
            total += score;
        }

        findBestClimb();
    }

    void findBestClimb() {
        int currentSum = 0;
        int bestSum = Integer.MIN_VALUE;

        int currentStart = 0;

        for (int i = 0; i < 5; i++) {
            int change = scores[i + 1] - scores[i];

            if (currentSum < 0) {
                currentSum = 0;
                currentStart = i;
            }

            currentSum += change;

            if (currentSum > bestSum) {
                bestSum = currentSum;
                startTest = currentStart + 1;
                endTest = i + 2;
            }
        }

        bestClimb = bestSum;
    }
}

public class Problem5 {

    // Stable merge sort: highest total first
    public static void mergeSort(Candidate[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    public static void merge(
            Candidate[] arr, int left, int mid, int right) {

        Candidate[] temp = new Candidate[right - left + 1];

        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {

            // >= keeps original order when totals are equal
            if (arr[i].total >= arr[j].total) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // Count candidates with total >= cutoff
    public static int cutoff(Candidate[] arr, int target) {
        int left = 0;
        int right = arr.length;

        // Find first candidate with total < target
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid].total >= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    // Find two different candidates with required total
    public static void findPair(Candidate[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left].total + arr[right].total;

            if (sum == target) {
                System.out.println("PAIR " + target + ": "
                        + arr[left].name + " + " + arr[right].name);
                return;
            }
            else if (sum > target) {
                left++;
            }
            else {
                right--;
            }
        }

        System.out.println("PAIR " + target + ": no pair");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Candidate[] candidates = new Candidate[n];

        for (int i = 0; i < n; i++) {
            String name = sc.next();

            int[] scores = new int[6];

            for (int j = 0; j < 6; j++) {
                scores[j] = sc.nextInt();
            }

            candidates[i] = new Candidate(name, scores);
        }

        mergeSort(candidates, 0, n - 1);

        System.out.println("Shortlist:");

        for (int i = 0; i < n; i++) {
            Candidate c = candidates[i];

            System.out.println((i + 1) + ". " + c.name + " "
                    + c.total + " | best climb +" + c.bestClimb
                    + " (test " + c.startTest + " to " + c.endTest + ")");
        }

        // Read CUTOFF and PAIR queries until input ends
        while (sc.hasNext()) {
            String command = sc.next();
            int target = sc.nextInt();

            if (command.equalsIgnoreCase("CUTOFF")) {
                int count = cutoff(candidates, target);

                System.out.println("CUTOFF " + target + ": "
                        + count + " candidates");
            }
            else if (command.equalsIgnoreCase("PAIR")) {
                findPair(candidates, target);
            }
        }

        sc.close();
    }
}
