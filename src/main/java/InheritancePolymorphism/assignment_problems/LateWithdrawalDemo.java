import java.util.Arrays;
import java.util.Scanner;

class RaceEntry {

    protected double entryFee;
    protected double balanceDue;

    private double[] lateFeeHistory;
    private int feeCount;

    public RaceEntry(double entryFee) {

        this.entryFee = entryFee;
        this.balanceDue = entryFee;

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

class RunnerEntry extends RaceEntry {

    private String bibNumber;
    private String category;

    public RunnerEntry(String bibNumber,
                       double entryFee,
                       String category) {

        super(entryFee);

        this.bibNumber = bibNumber;
        this.category = category;
    }

    @Override
    protected void applyLateFee(double amount) {

        super.applyLateFee(amount * 2);
    }
}

public class LateWithdrawalDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter entry fee: ");

        double fee = sc.nextDouble();

        sc.nextLine();

        System.out.print(
                "Enter bib number: ");

        String bib = sc.nextLine();

        System.out.print(
                "Enter category: ");

        String category = sc.nextLine();

        RunnerEntry runner =
                new RunnerEntry(
                        bib,
                        fee,
                        category);

        System.out.print(
                "Enter payment amount: ");

        double payment = sc.nextDouble();

        runner.pay(payment);

        System.out.print(
                "Enter late fee: ");

        double lateFee = sc.nextDouble();

        runner.applyLateFee(lateFee);

        System.out.println(
                "Balance Due: "
                + runner.getBalanceDue());

        double[] history =
                runner.getLateFeeHistory();

        System.out.println(
                "Late Fee History: "
                + Arrays.toString(history));

        // Test defensive copy
        if (history.length > 0) {

            history[0] = 999;

            System.out.println(
                    "Changed returned copy.");

            System.out.println(
                    "Actual History: "
                    + Arrays.toString(
                            runner.getLateFeeHistory()));
        }

        sc.close();
    }
}