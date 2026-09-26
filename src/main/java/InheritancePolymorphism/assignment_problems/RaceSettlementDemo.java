import java.util.Scanner;

class RaceEntry {

    private static int bibCounter = 0;

    private final String entryCode;

    protected double balanceDue;

    public RaceEntry(String bibNumber,
                     double entryFee) {

        bibCounter++;

        entryCode = "ENT-" + bibCounter;

        balanceDue = entryFee;
    }

    public void pay(double amount) {

        balanceDue = balanceDue - amount;

        if (balanceDue < 0) {
            balanceDue = 0;
        }
    }

    public void pay(double amount,
                    String mode) {

        System.out.println(
                "Paying via " + mode);

        pay(amount);
    }

    public static boolean isValidDiscountCode(
            String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'M') {
            return false;
        }

        for (int i = 1; i <= 3; i++) {

            if (!Character.isDigit(
                    code.charAt(i))) {

                return false;
            }
        }

        if (!Character.isUpperCase(
                code.charAt(4))) {

            return false;
        }

        return true;
    }

    public static int getBibCounter() {

        return bibCounter;
    }

    public String getEntryCode() {

        return entryCode;
    }

    public double getBalanceDue() {

        return balanceDue;
    }
}

class RelayTeamEntry extends RaceEntry {

    private int teamSize;

    public RelayTeamEntry(String bibNumber,
                          double entryFee,
                          int teamSize) {

        super(bibNumber, entryFee);

        this.teamSize = teamSize;
    }

    public int getTeamSize() {

        return teamSize;
    }
}

public class RaceSettlementDemo {

    static String settleNight(
            RaceEntry[] entries) {

        int processed = 0;
        int nullSkipped = 0;
        int relayCount = 0;
        int individualCount = 0;

        StringBuilder result =
                new StringBuilder();

        for (int i = 0;
             i < entries.length;
             i++) {

            if (entries[i] == null) {

                nullSkipped++;
                continue;
            }

            processed++;

            if (entries[i]
                    instanceof RelayTeamEntry) {

                relayCount++;

            } else {

                individualCount++;
            }
        }

        result.append(processed);
        result.append(" processed | ");

        result.append(nullSkipped);
        result.append(" null skipped | ");

        result.append(relayCount);
        result.append(" relay | ");

        result.append(individualCount);
        result.append(" individual");

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter bib number: ");

        String bib = sc.nextLine();

        System.out.print(
                "Enter entry fee: ");

        double fee = sc.nextDouble();

        sc.nextLine();

        RaceEntry entry =
                new RaceEntry(bib, fee);

        System.out.println(
                "Entry Code: "
                + entry.getEntryCode());

        System.out.print(
                "Enter payment amount: ");

        double amount = sc.nextDouble();

        sc.nextLine();

        System.out.print(
                "Enter payment mode: ");

        String mode = sc.nextLine();

        entry.pay(amount, mode);

        System.out.println(
                "Balance Due: "
                + entry.getBalanceDue());

        System.out.print(
                "Enter discount code: ");

        String code = sc.nextLine();

        System.out.println(
                "Valid Discount Code: "
                + RaceEntry.isValidDiscountCode(
                        code));

        System.out.print(
                "Enter relay entry fee: ");

        double relayFee = sc.nextDouble();

        System.out.print(
                "Enter relay team size: ");

        int teamSize = sc.nextInt();

        RelayTeamEntry relay =
                new RelayTeamEntry(
                        "RELAY",
                        relayFee,
                        teamSize);

        RaceEntry[] entries = {
                entry,
                null,
                relay
        };

        System.out.println();

        System.out.println(
                settleNight(entries));

        System.out.println(
                "Bib Counter: "
                + RaceEntry.getBibCounter());

        sc.close();
    }
}