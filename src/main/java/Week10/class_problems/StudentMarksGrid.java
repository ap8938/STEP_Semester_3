
import java.util.Scanner;

public class StudentMarksGrid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] subjects = {"Math", "Sci", "Eng"};

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] names = new String[n];
        int[][] marks = new int[n][3];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            names[i] = sc.nextLine();

            for (int j = 0; j < 3; j++) {
                System.out.print("Enter " + subjects[j]
                        + " marks (-1 if missing): ");
                marks[i][j] = sc.nextInt();
            }
            sc.nextLine();
        }

        System.out.println("\nStudent totals:");

        for (int i = 0; i < n; i++) {
            int total = 0;

            for (int j = 0; j < 3; j++) {
                if (marks[i][j] != -1) {
                    total += marks[i][j];
                }
            }

            System.out.println(names[i] + ": " + total);
        }

        System.out.println("\nSubject toppers:");

        for (int j = 0; j < 3; j++) {
            int highest = -1;
            String topper = "No marks available";

            for (int i = 0; i < n; i++) {
                if (marks[i][j] != -1
                        && marks[i][j] > highest) {
                    highest = marks[i][j];
                    topper = names[i];
                }
            }

            System.out.println(subjects[j] + ": "
                    + topper + " (" + highest + ")");
        }

        sc.close();
    }
}
