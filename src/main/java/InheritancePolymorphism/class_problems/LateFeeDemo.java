import java.util.Scanner;
import java.util.Arrays;

class EventTicket {

    protected double basePrice;
    protected double balanceDue;

    private double[] lateFeeHistory;
    private int feeCount;

    public EventTicket(double basePrice) {

        this.basePrice = basePrice;
        this.balanceDue = basePrice;

        lateFeeHistory = new double[10];
        feeCount = 0;
    }

    public void pay(double amount) {

        balanceDue = balanceDue - amount;

        if (balanceDue < 0) {
            balanceDue = 0;
        }
    }

    protected void applyLateFee(double amount) {

        balanceDue = balanceDue + amount;

        if (feeCount < lateFeeHistory.length) {

            lateFeeHistory[feeCount] = amount;
            feeCount++;
        }
    }

    public double getBalanceDue() {

        return balanceDue;
    }

    public double[] getLateFeeHistory() {

        return Arrays.copyOf(
                lateFeeHistory,
                feeCount);
    }
}

class WorkshopTicket extends EventTicket {

    public WorkshopTicket(double basePrice) {

        super(basePrice);
    }

    @Override
    protected void applyLateFee(double amount) {

        super.applyLateFee(amount * 2);
    }
}

public class LateFeeDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ticket base price: ");
        double price = sc.nextDouble();

        WorkshopTicket ticket =
                new WorkshopTicket(price);

        System.out.print("Enter payment amount: ");
        double payment = sc.nextDouble();

        ticket.pay(payment);

        System.out.print("Enter late fee: ");
        double lateFee = sc.nextDouble();

        ticket.applyLateFee(lateFee);

        System.out.println(
                "Balance Due: "
                + ticket.getBalanceDue());

        double[] history =
                ticket.getLateFeeHistory();

        System.out.println(
                "Late Fee History: "
                + Arrays.toString(history));

        // Testing defensive copy
        if (history.length > 0) {

            history[0] = 999;

            System.out.println(
                    "After changing returned array:");

            System.out.println(
                    "Actual History: "
                    + Arrays.toString(
                            ticket.getLateFeeHistory()));
        }

        sc.close();
    }
}