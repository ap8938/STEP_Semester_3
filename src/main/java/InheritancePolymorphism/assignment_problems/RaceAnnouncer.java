import java.util.Scanner;

class RaceEntry {

    protected String bibNumber;
    protected double balanceDue;

    public RaceEntry(String bibNumber,
                     double entryFee) {

        this.bibNumber = bibNumber;
        this.balanceDue = entryFee;
    }

    public String announce() {

        return "Runner Entry | Bib: "
                + bibNumber
                + " | Balance: "
                + balanceDue;
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

    @Override
    public String announce() {

        return "Relay Team | Bib: "
                + bibNumber
                + " | Team Size: "
                + teamSize
                + " | Balance: "
                + balanceDue;
    }

    public int getTeamSize() {

        return teamSize;
    }
}

public class RaceAnnouncer {

    static String announceAll(
            RaceEntry[] entries) {

        StringBuilder report =
                new StringBuilder();

        for (int i = 0;
             i < entries.length;
             i++) {

            report.append(
                    entries[i].announce());

            if (entries[i] instanceof RelayTeamEntry) {

                RelayTeamEntry relay =
                        (RelayTeamEntry) entries[i];

                report.append(
                        " [Team size via downcast: "
                        + relay.getTeamSize()
                        + "]");
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter runner bib number: ");

        String runnerBib = sc.nextLine();

        System.out.print(
                "Enter runner fee: ");

        double runnerFee = sc.nextDouble();

        sc.nextLine();

        System.out.print(
                "Enter relay bib number: ");

        String relayBib = sc.nextLine();

        System.out.print(
                "Enter relay fee: ");

        double relayFee = sc.nextDouble();

        System.out.print(
                "Enter relay team size: ");

        int teamSize = sc.nextInt();

        RaceEntry runner =
                new RaceEntry(
                        runnerBib,
                        runnerFee);

        RelayTeamEntry relay =
                new RelayTeamEntry(
                        relayBib,
                        relayFee,
                        teamSize);

        RaceEntry[] fleet = {
                runner,
                relay
        };

        System.out.println();

        System.out.println(
                announceAll(fleet));

        sc.close();
    }
}