import java.util.Scanner;

class RaceEntry {

    protected String bibNumber;
    protected double entryFee;
    protected double balanceDue;

    public RaceEntry(String bibNumber,
                     double entryFee) {

        this.bibNumber = bibNumber;
        this.entryFee = entryFee;
        this.balanceDue = entryFee;
    }

    public double getBalanceDue() {

        return balanceDue;
    }

    public void announce() {

        System.out.println(
                "Race Entry | Bib: "
                + bibNumber
                + " | Balance: "
                + balanceDue);
    }
}

class RunnerEntry extends RaceEntry {

    protected String category;

    public RunnerEntry(String bibNumber,
                       double entryFee,
                       String category) {

        super(bibNumber, entryFee);

        this.category = category;
    }

    @Override
    public void announce() {

        System.out.println(
                "Runner Entry | Bib: "
                + bibNumber
                + " | Category: "
                + category
                + " | Balance: "
                + balanceDue);
    }
}

class EliteRunnerEntry extends RunnerEntry {

    private double sponsorBonus;

    public EliteRunnerEntry(String bibNumber,
                            double entryFee,
                            String category,
                            double sponsorBonus) {

        super(bibNumber, entryFee, category);

        this.sponsorBonus = sponsorBonus;

        balanceDue =
                balanceDue + sponsorBonus;
    }

    @Override
    public void announce() {

        System.out.println(
                "Elite Runner | Bib: "
                + bibNumber
                + " | Category: "
                + category
                + " | Sponsor Bonus: "
                + sponsorBonus
                + " | Balance: "
                + balanceDue);
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
    public void announce() {

        System.out.println(
                "Relay Team | Bib: "
                + bibNumber
                + " | Team Size: "
                + teamSize
                + " | Balance: "
                + balanceDue);
    }
}

public class RaceFamilyDemo {

    static String classifyGeneration(
            RaceEntry entry) {

        if (entry instanceof EliteRunnerEntry) {

            return "Multilevel descendant (3 generations deep)";

        } else if (entry instanceof RelayTeamEntry) {

            return "Hierarchical sibling (independent branch)";

        } else {

            return "Base or direct child";
        }
    }

    static double getTotalBalanceDue(
            RaceEntry[] entries) {

        double total = 0;

        for (int i = 0;
             i < entries.length;
             i++) {

            total = total
                    + entries[i].getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter runner bib number: ");

        String bib1 = sc.nextLine();

        System.out.print(
                "Enter runner entry fee: ");

        double fee1 = sc.nextDouble();

        sc.nextLine();

        System.out.print(
                "Enter runner category: ");

        String category = sc.nextLine();

        System.out.print(
                "Enter elite bib number: ");

        String bib2 = sc.nextLine();

        System.out.print(
                "Enter elite entry fee: ");

        double fee2 = sc.nextDouble();

        sc.nextLine();

        System.out.print(
                "Enter elite category: ");

        String eliteCategory = sc.nextLine();

        System.out.print(
                "Enter sponsor bonus: ");

        double bonus = sc.nextDouble();

        sc.nextLine();

        System.out.print(
                "Enter relay bib number: ");

        String bib3 = sc.nextLine();

        System.out.print(
                "Enter relay entry fee: ");

        double fee3 = sc.nextDouble();

        System.out.print(
                "Enter team size: ");

        int teamSize = sc.nextInt();

        RunnerEntry runner =
                new RunnerEntry(
                        bib1,
                        fee1,
                        category);

        EliteRunnerEntry elite =
                new EliteRunnerEntry(
                        bib2,
                        fee2,
                        eliteCategory,
                        bonus);

        RelayTeamEntry relay =
                new RelayTeamEntry(
                        bib3,
                        fee3,
                        teamSize);

        runner.announce();
        elite.announce();
        relay.announce();

        System.out.println();

        System.out.println(
                classifyGeneration(elite));

        System.out.println(
                classifyGeneration(relay));

        RaceEntry[] entries = {
                runner,
                elite,
                relay
        };

        System.out.println(
                "Total Balance Due: "
                + getTotalBalanceDue(entries));

        sc.close();
    }
}