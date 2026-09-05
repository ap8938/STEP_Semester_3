package main.java.AccesModifier.assignment_problems;

import java.util.Scanner;

class LoanReceipt {

    static String systemName;

    final String memberId;
    final String[] bookIds;

    static {
        systemName = "Library Circulation System";
    }

    public LoanReceipt(
        String memberId,
        String[] bookIds) {

        if (bookIds == null) {
            throw new IllegalArgumentException(
                "Book IDs cannot be null"
            );
        }

        for (int i = 0; i < bookIds.length; i++) {

            if (!isValidBookId(bookIds[i])) {
                throw new IllegalArgumentException(
                    "Invalid book ID"
                );
            }
        }

        this.memberId = memberId;

        // Defensive copy
        this.bookIds = bookIds.clone();
    }

    static boolean isValidBookId(String id) {

        if (id == null || id.length() != 6) {
            return false;
        }

        if (!id.startsWith("BK-")) {
            return false;
        }

        for (int i = 3; i < 6; i++) {

            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    String[] getBookIds() {

        // Defensive copy
        return bookIds.clone();
    }

    LoanReceipt withCorrectedBookId(
        int index,
        String newId) {

        if (index < 0 || index >= bookIds.length) {
            throw new IllegalArgumentException(
                "Invalid index"
            );
        }

        if (!isValidBookId(newId)) {
            throw new IllegalArgumentException(
                "Invalid book ID"
            );
        }

        String[] newBookIds = bookIds.clone();

        newBookIds[index] = newId;

        // Return a NEW object
        return new LoanReceipt(
            memberId,
            newBookIds
        );
    }

    static String processNightlyCirculation(
        LoanReceipt[] receipts) {

        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        for (int i = 0; i < receipts.length; i++) {

            if (receipts[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (receipts[i] instanceof
                ReferenceOnlyLoanReceipt) {

                referenceOnly++;

            } else {

                regular++;
            }
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               referenceOnly + " reference-only | " +
               regular + " regular";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of receipts: ");
        int n = sc.nextInt();
        sc.nextLine();

        LoanReceipt[] receipts =
            new LoanReceipt[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nReceipt " + (i + 1));

            System.out.println("1. Regular");
            System.out.println("2. Reference Only");
            System.out.println("3. Null");

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 3) {
                receipts[i] = null;
                continue;
            }

            System.out.print("Enter member ID: ");
            String memberId = sc.nextLine();

            System.out.print("Enter number of books: ");
            int count = sc.nextInt();
            sc.nextLine();

            String[] books = new String[count];

            for (int j = 0; j < count; j++) {
                System.out.print(
                    "Enter book ID (BK-XXX): "
                );

                books[j] = sc.nextLine();
            }

            try {

                if (choice == 1) {

                    receipts[i] =
                        new LoanReceipt(
                            memberId,
                            books
                        );

                } else if (choice == 2) {

                    System.out.print(
                        "Enter room number: "
                    );

                    String room = sc.nextLine();

                    receipts[i] =
                        new ReferenceOnlyLoanReceipt(
                            memberId,
                            books,
                            room
                        );

                } else {

                    System.out.println(
                        "Invalid choice"
                    );

                    i--;
                }

            } catch (Exception e) {

                System.out.println(
                    "construction rejected"
                );

                i--;
            }
        }

        System.out.println("\n--- Result ---");

        System.out.println(
            LoanReceipt.processNightlyCirculation(
                receipts
            )
        );
    }
}


class ReferenceOnlyLoanReceipt
    extends LoanReceipt {

    String roomNumber;

    public ReferenceOnlyLoanReceipt(
        String memberId,
        String[] bookIds,
        String roomNumber) {

        super(memberId, bookIds);

        this.roomNumber = roomNumber;
    }
}
//EXIT
