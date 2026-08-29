import java.util.Scanner;

class BookIssue {
    String title;
    String borrowerName;
    int daysOverdue;

    BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    double fineAmount() {
        if (daysOverdue > 0) {
            return daysOverdue * 5;
        } else {
            return 0;
        }
    }

    boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    static double totalFineCollected(BookIssue[] issues) {
        double total = 0;

        for (BookIssue issue : issues) {
            total += issue.fineAmount();
        }

        return total;
    }
}

public class BookIssueDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookIssue[] issues = new BookIssue[5];

        for (int i = 0; i < 5; i++) {

            System.out.println("Enter details for book " + (i + 1) + ":");

            System.out.print("Title: ");
            String title = sc.nextLine();

            System.out.print("Borrower name: ");
            String borrowerName = sc.nextLine();

            System.out.print("Days overdue: ");
            int daysOverdue = sc.nextInt();

            sc.nextLine();

            issues[i] = new BookIssue(title, borrowerName, daysOverdue);
        }

        System.out.println("\nBook Details:");

        for (BookIssue issue : issues) {

            if (issue.isSeverelyOverdue()) {
                System.out.println(issue.title + " - "
                        + issue.daysOverdue + " days - Severely overdue");
            } else {
                System.out.println(issue.title + " - "
                        + issue.daysOverdue + " days - OK");
            }
        }

        System.out.println("\nTotal fine collected: Rs "
                + BookIssue.totalFineCollected(issues));

        sc.close();
    }
}
