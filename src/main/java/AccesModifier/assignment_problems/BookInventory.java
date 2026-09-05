package main.java.AccesModifier.assignment_problems;

import java.util.Scanner;

class BookInventory {

    private int copiesTotal;
    private int copiesAvailable;

    BookInventory(int copiesTotal) {

        if (copiesTotal <= 0) {
            throw new IllegalArgumentException(
                "Invalid number of copies"
            );
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    void checkOut() {

        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    void checkIn() {

        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total copies: ");
        int total = sc.nextInt();

        try {

            BookInventory book =
                new BookInventory(total);

            System.out.println("\nChoose operation:");
            System.out.println("1. Checkout");
            System.out.println("2. Check-in");

            int choice = sc.nextInt();

            System.out.print("How many times? ");
            int times = sc.nextInt();

            switch (choice) {

                case 1:
                    for (int i = 0; i < times; i++) {
                        book.checkOut();
                    }
                    break;

                case 2:
                    for (int i = 0; i < times; i++) {
                        book.checkIn();
                    }
                    break;

                default:
                    System.out.println("Invalid choice");
                    return;
            }

            System.out.println(
                "Available copies: " +
                book.getCopiesAvailable()
            );

        } catch (Exception e) {
            System.out.println("construction rejected");
        }
    }
}
//EXIT
