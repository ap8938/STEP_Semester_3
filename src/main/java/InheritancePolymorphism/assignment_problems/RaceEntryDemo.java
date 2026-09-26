import java.util.Scanner;

class RaceEntry {

    protected String bibNumber;
    protected double entryFee;
    protected double balanceDue;

    public RaceEntry(String bibNumber,
                     double entryFee) {

        if (bibNumber == null
                || bibNumber.trim().isEmpty()
                || bibNumber.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "Invalid bib number");
        }

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
    }

    public void pay(double amount) {

        balanceDue = balanceDue - amount;

        if (balanceDue < 0) {
            balanceDue = 0;
        }
    }

    public double getBalanceDue() {

        return balanceDue;
    }
}

class RunnerEntry extends RaceEntry {

    private String category;

    public RunnerEntry(String bibNumber,
                       double entryFee,
                       String category) {

        super(bibNumber, entryFee);

        this.category = category;
    }
}

public class RaceEntryDemo {

    static String registerBatch(
            String[] bibNumbers,
            double entryFee) {

        int registered = 0;
        int rejected = 0;

        for (int i = 0;
             i < bibNumbers.length;
             i++) {

            try {

                RaceEntry entry =
                        new RaceEntry(
                                bibNumbers[i],
                                entryFee);

                registered++;

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        return "Registered: "
                + registered
                + " | Rejected: "
                + rejected;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bib number: ");
        String bib = sc.nextLine();

        System.out.print("Enter entry fee: ");
        double fee = sc.nextDouble();

        try {

            RaceEntry entry =
                    new RaceEntry(bib, fee);

            System.out.print(
                    "Enter payment amount: ");

            double amount = sc.nextDouble();

            entry.pay(amount);

            System.out.println(
                    "Balance Due: "
                    + entry.getBalanceDue());

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Construction rejected");
        }

        System.out.print(
                "Enter number of batch registrations: ");

        int n = sc.nextInt();

        sc.nextLine();

        String[] bibNumbers = new String[n];

        for (int i = 0; i < n; i++) {

            System.out.print(
                    "Enter bib number "
                    + (i + 1) + ": ");

            bibNumbers[i] = sc.nextLine();
        }

        System.out.print(
                "Enter batch entry fee: ");

        double batchFee = sc.nextDouble();

        System.out.println(
                registerBatch(
                        bibNumbers,
                        batchFee));

        sc.close();
    }
}