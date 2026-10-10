
import java.util.ArrayList;
import java.util.Scanner;

interface PaymentMethod {
    boolean pay(double amount);
    String getName();
}

class CreditCardPayment implements PaymentMethod {
    public boolean pay(double amount) {
        return true;
    }

    public String getName() {
        return "Credit Card";
    }
}

class WalletPayment implements PaymentMethod {
    public boolean pay(double amount) {
        return false; // Simulated failure
    }

    public String getName() {
        return "Digital Wallet";
    }
}

class LineItem {
    String name;
    int quantity;
    double price;

    LineItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    double subtotal() {
        return quantity * price;
    }
}

class FoodOrder {
    ArrayList<LineItem> items = new ArrayList<>();
    String status = "Created";

    void addItem(String name, int quantity, double price) {
        items.add(new LineItem(name, quantity, price));
        System.out.println("Added " + name + " (Qty " + quantity + ")");
    }

    double total() {
        double sum = 0;
        for (LineItem item : items) {
            sum += item.subtotal();
        }
        return sum;
    }

    void place(PaymentMethod payment) {
        if (items.isEmpty()) {
            System.out.println("Cannot place order: Order must contain at least one item");
            return;
        }

        System.out.println("Order placed successfully");

        if (payment.pay(total())) {
            status = "Paid";
            System.out.println("Payment via " + payment.getName() + " successful");
            System.out.println("Notification: Order placed and paid");
        } else {
            status = "Pending Payment";
            System.out.println("Payment via " + payment.getName() + " failed");
            System.out.println("Notification: Order placed, awaiting payment");
        }

        System.out.println("Order status: " + status);
    }
}

public class FoodOrderDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FoodOrder order = new FoodOrder();

        System.out.print("How many items? ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Item name: ");
            String name = sc.nextLine();

            System.out.print("Quantity: ");
            int quantity = sc.nextInt();

            System.out.print("Price per item: ");
            double price = sc.nextDouble();
            sc.nextLine();

            if (quantity > 0 && price >= 0) {
                order.addItem(name, quantity, price);
            } else {
                System.out.println("Invalid quantity or price");
            }
        }

        System.out.print("Payment (1 Card, 2 Wallet): ");
        int choice = sc.nextInt();

        PaymentMethod payment;
        if (choice == 1) {
            payment = new CreditCardPayment();
        } else {
            payment = new WalletPayment();
        }

        order.place(payment);
        sc.close();
    }
}
