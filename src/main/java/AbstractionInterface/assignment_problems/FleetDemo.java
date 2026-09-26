import java.util.Scanner;

abstract class ServiceableVehicle {

    private double mileage;

    public ServiceableVehicle() {

        mileage = 0;
    }

    public abstract String performMaintenance();

    public double getMileage() {

        return mileage;
    }

    public void addMileage(double km) {

        if (km < 0) {

            throw new IllegalArgumentException(
                    "Mileage cannot be negative");
        }

        mileage = mileage + km;
    }
}

interface Insurable {

    String getInsuranceInfo();
}

class Forklift
        extends ServiceableVehicle
        implements Insurable {

    protected String assetTag;

    public Forklift(String assetTag) {

        super();

        this.assetTag = assetTag;
    }

    @Override
    public String performMaintenance() {

        return "Forklift "
                + assetTag
                + ": hydraulic and fork inspection complete";
    }

    @Override
    public String getInsuranceInfo() {

        return "Insured under fleet policy - Asset "
                + assetTag;
    }
}

class HeavyDutyForklift extends Forklift {

    public HeavyDutyForklift(String assetTag) {

        super(assetTag);
    }

    @Override
    public String performMaintenance() {

        return super.performMaintenance()
                + " | high-pressure hydraulic check complete";
    }
}

public class FleetDemo {

    static String getInsuranceIfApplicable(
            ServiceableVehicle v) {

        if (v instanceof Insurable) {

            Insurable insured =
                    (Insurable) v;

            return insured.getInsuranceInfo();

        } else {

            return "No insurance record exists";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter Forklift asset tag: ");

        String forkliftTag = sc.nextLine();

        Forklift forklift =
                new Forklift(forkliftTag);

        System.out.print(
                "Enter kilometers to add: ");

        double km = sc.nextDouble();

        try {

            forklift.addMileage(km);

            System.out.println(
                    "Mileage: "
                    + forklift.getMileage());

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid mileage: "
                    + e.getMessage());
        }

        System.out.println(
                forklift.performMaintenance());

        System.out.println(
                getInsuranceIfApplicable(
                        forklift));

        sc.nextLine();

        System.out.print(
                "Enter Heavy Duty Forklift tag: ");

        String heavyTag = sc.nextLine();

        HeavyDutyForklift heavy =
                new HeavyDutyForklift(heavyTag);

        System.out.println(
                heavy.performMaintenance());

        System.out.println(
                getInsuranceIfApplicable(
                        heavy));

        sc.close();
    }
}