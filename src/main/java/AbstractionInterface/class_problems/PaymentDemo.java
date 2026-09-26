import java.util.Scanner;

abstract class PaymentMethod {

    private static int counter = 1000;

    private final String transactionId;

    public PaymentMethod() {
        counter++;
        transactionId = "TXN-" + counter;
    }

    public abstract String processPayment(double amount);

    // Overloaded method
    public String processPayment(double amount, String note) {

        return processPayment(amount) + " (" + note + ")";
    }

    public String getTransactionId() {
        return transactionId;
    }
}

class CreditCardPayment extends PaymentMethod {

    private String cardNumberLastFour;

    public CreditCardPayment(String cardNumberLastFour) {
        this.cardNumberLastFour = cardNumberLastFour;
    }

    @Override
    public String processPayment(double amount) {

        return "Charged $" + amount
                + " to card ending " + cardNumberLastFour
                + " - Txn " + getTransactionId();
    }
}

class CashPayment extends PaymentMethod {

    public CashPayment() {
    }

    @Override
    public String processPayment(double amount) {

        return "Received $" + amount
                + " in cash - Txn " + getTransactionId();
    }
}

public class PaymentDemo {

    static void printConfirmation(PaymentMethod payment, double amount) {

        System.out.println(payment.processPayment(amount));
    }

    static void testUpcasting() {

        CreditCardPayment cc = new CreditCardPayment("4471");

        // Upcasting: child object stored in parent reference
        PaymentMethod ref = cc;

        printConfirmation(ref, 250.0);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Credit Card Payment");
        System.out.println("2. Cash Payment");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();

        sc.nextLine();

        if (choice == 1) {

            System.out.print("Enter last 4 digits of card: ");
            String cardNumber = sc.nextLine();

            CreditCardPayment cc =
                    new CreditCardPayment(cardNumber);

            System.out.println(cc.processPayment(amount));

            System.out.print("Enter a note: ");
            String note = sc.nextLine();

            System.out.println(cc.processPayment(amount, note));

        } else if (choice == 2) {

            CashPayment cash = new CashPayment();

            System.out.println(cash.processPayment(amount));

            System.out.print("Enter a note: ");
            String note = sc.nextLine();

            System.out.println(cash.processPayment(amount, note));

        } else {

            System.out.println("Invalid choice.");
        }

        System.out.println();
        System.out.println("Testing upcasting:");
        testUpcasting();

        sc.close();
    }
}
