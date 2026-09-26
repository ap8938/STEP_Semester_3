import java.util.Scanner;

abstract class StaffMember {

    private double baseSalary;
    protected double bonusRate;

    public StaffMember(double baseSalary) {

        this(baseSalary, 0.10);
    }

    public StaffMember(double baseSalary,
                       double bonusRate) {

        this.baseSalary = baseSalary;
        this.bonusRate = bonusRate;
    }

    public abstract double calculateBonus();

    public double getSalary() {

        return baseSalary;
    }

    public void setSalary(double baseSalary) {

        if (baseSalary >= 0) {
            this.baseSalary = baseSalary;
        } else {
            System.out.println(
                    "Invalid salary. Salary unchanged."
            );
        }
    }
}

interface Auditable {

    String auditRecord();
}

class TeamLead extends StaffMember
        implements Auditable {

    private int teamSize;

    public TeamLead(double baseSalary,
                    int teamSize) {

        super(baseSalary);
        this.teamSize = teamSize;
    }

    public TeamLead(double baseSalary,
                    double bonusRate,
                    int teamSize) {

        super(baseSalary, bonusRate);
        this.teamSize = teamSize;
    }

    @Override
    public double calculateBonus() {

        return getSalary() * bonusRate;
    }

    @Override
    public String auditRecord() {

        return "TeamLead audit: "
                + teamSize
                + " team members, salary $"
                + getSalary();
    }
}

public class BonusDemo {

    static String getAuditIfApplicable(StaffMember s) {

        if (s instanceof Auditable) {

            Auditable a = (Auditable) s;

            return a.auditRecord();
        }

        return "No audit required";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        System.out.print("Enter team size: ");
        int teamSize = sc.nextInt();

        System.out.print("Do you want to enter bonus rate? (yes/no): ");
        String choice = sc.next();

        TeamLead teamLead;

        if (choice.equalsIgnoreCase("yes")) {

            System.out.print("Enter bonus rate (example 0.20): ");
            double rate = sc.nextDouble();

            teamLead =
                    new TeamLead(salary, rate, teamSize);

        } else {

            teamLead =
                    new TeamLead(salary, teamSize);
        }

        System.out.println();
        System.out.println("Bonus: "
                + teamLead.calculateBonus());

        System.out.print(
                "Enter new salary to test setter: ");

        double newSalary = sc.nextDouble();

        teamLead.setSalary(newSalary);

        System.out.println("Current salary: "
                + teamLead.getSalary());

        // Upcasting
        StaffMember ref = teamLead;

        System.out.println();
        System.out.println(
                getAuditIfApplicable(ref));

        sc.close();
    }
}