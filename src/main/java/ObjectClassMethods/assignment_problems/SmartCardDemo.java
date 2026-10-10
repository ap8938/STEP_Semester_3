
import java.util.ArrayList;
import java.util.Scanner;

interface PricingPlan {
    double price(double amount);
}

class DayScholarPlan implements PricingPlan {
    public double price(double amount) {
        return amount;
    }
}

class HostellerPlan implements PricingPlan {
    public double price(double amount) {
        return amount * 0.90;
    }
}

class StaffPlan implements PricingPlan {
    public double price(double amount) {
        return amount * 0.80;
    }
}

class Purchase {
    String item;
    double charged;
    boolean refunded;

    Purchase(String item, double charged) {
        this.item = item;
        this.charged = charged;
        refunded = false;
    }
}

class SmartCard {
    String id;
    double balance;
    boolean blocked;
    PricingPlan plan;
    ArrayList<Double> transactions = new ArrayList<>();
    ArrayList<Purchase> purchases = new ArrayList<>();

    SmartCard(String id, PricingPlan plan) {
        this.id = id;
        this.plan = plan;
    }

    void topUp(double amount) {
        if (blocked) {
            System.out.println("Card is blocked");
        } else if (amount < 100 || balance + amount > 5000) {
            System.out.println("Invalid top-up amount");
        } else {
            balance += amount;
            transactions.add(amount);
            System.out.printf("Balance: ₹%.2f%n", balance);
        }
    }

    void buy(String item, double price) {
        double charged = plan.price(price);

        if (blocked) {
            System.out.println("Card is blocked");
        } else if (balance < charged) {
            System.out.printf("Insufficient balance (required ₹%.2f, available ₹%.2f)%n",
                    charged, balance);
        } else {
            balance -= charged;
            transactions.add(-charged);
            purchases.add(new Purchase(item, charged));
            System.out.printf("%s purchased for ₹%.2f. Balance: ₹%.2f%n",
                    item, charged, balance);
        }
    }

    void refund(String item) {
        for (Purchase p : purchases) {
            if (p.item.equalsIgnoreCase(item)) {
                if (p.refunded) {
                    System.out.println("Refund rejected: already refunded");
                } else {
                    balance += p.charged;
                    transactions.add(p.charged);
                    p.refunded = true;
                    System.out.printf("Refund ₹%.2f processed. Balance: ₹%.2f%n",
                            p.charged, balance);
                }
                return;
            }
        }
        System.out.println("Purchase not found");
    }

    void statement() {
        System.out.print("Transactions: ");
        for (double t : transactions) {
            System.out.printf("%+.2f ", t);
        }
        System.out.printf("= ₹%.2f%n", balance);
    }
}

public class SmartCardDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Card ID: ");
        String id = sc.next();

        System.out.print("Plan (1 Day Scholar, 2 Hosteller, 3 Staff): ");
        int choice = sc.nextInt();

        PricingPlan plan;
        if (choice == 1) plan = new DayScholarPlan();
        else if (choice == 2) plan = new HostellerPlan();
        else plan = new StaffPlan();

        SmartCard card = new SmartCard(id, plan);

        System.out.print("Top-up amount: ₹");
        card.topUp(sc.nextDouble());

        System.out.print("Food item: ");
        String item = sc.next();

        System.out.print("Food price: ₹");
        card.buy(item, sc.nextDouble());

        System.out.print("Refund this item? (1 Yes, 2 No): ");
        if (sc.nextInt() == 1) card.refund(item);

        card.statement();
        sc.close();
    }
}
