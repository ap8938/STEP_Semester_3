import java.util.Scanner;

abstract class HomeDevice {

    private static int counter = 1000;

    private final String serialNumber;

    public HomeDevice() {

        counter++;

        serialNumber =
                "HD-" + counter;
    }

    public abstract String activate();

    public String getSerialNumber() {

        return serialNumber;
    }
}

interface RemoteControllable {

    String connect(String appId);
}

interface EnergyTrackable {

    double getConsumptionWatts();
}

class WashingMachine
        extends HomeDevice
        implements RemoteControllable,
                   EnergyTrackable {

    private double consumptionWatts;

    public WashingMachine(
            double consumptionWatts) {

        super();

        this.consumptionWatts =
                consumptionWatts;
    }

    @Override
    public String activate() {

        return "Washing machine "
                + getSerialNumber()
                + " started a cycle";
    }

    @Override
    public String connect(String appId) {

        return getSerialNumber()
                + " connected to "
                + appId;
    }

    @Override
    public double getConsumptionWatts() {

        return consumptionWatts;
    }
}

class Refrigerator
        extends HomeDevice
        implements EnergyTrackable {

    private double consumptionWatts;

    public Refrigerator(
            double consumptionWatts) {

        super();

        this.consumptionWatts =
                consumptionWatts;
    }

    @Override
    public String activate() {

        return "Refrigerator "
                + getSerialNumber()
                + " activated";
    }

    @Override
    public double getConsumptionWatts() {

        return consumptionWatts;
    }
}

class MobileApp
        implements RemoteControllable {

    private String appName;

    public MobileApp(String appName) {

        this.appName = appName;
    }

    @Override
    public String connect(String appId) {

        return appName
                + " connected to "
                + appId;
    }
}

public class HomeControlDemo {

    static void connectAll(
            RemoteControllable[] items,
            String appId) {

        for (int i = 0;
             i < items.length;
             i++) {

            System.out.println(
                    items[i].connect(appId));
        }
    }

    static double getConsumptionIfTrackable(
            HomeDevice d) {

        if (d instanceof EnergyTrackable) {

            EnergyTrackable device =
                    (EnergyTrackable) d;

            return device.getConsumptionWatts();

        } else {

            return 0;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter washing machine consumption: ");

        double washingConsumption =
                sc.nextDouble();

        sc.nextLine();

        WashingMachine wm =
                new WashingMachine(
                        washingConsumption);

        System.out.print(
                "Enter refrigerator consumption: ");

        double fridgeConsumption =
                sc.nextDouble();

        sc.nextLine();

        Refrigerator fridge =
                new Refrigerator(
                        fridgeConsumption);

        System.out.print(
                "Enter mobile app name: ");

        String appName = sc.nextLine();

        MobileApp app =
                new MobileApp(appName);

        System.out.print(
                "Enter app ID: ");

        String appId = sc.nextLine();

        System.out.println();

        System.out.println(
                wm.activate());

        System.out.println(
                wm.connect(appId));

        System.out.println(
                fridge.activate());

        System.out.println(
                app.connect(appId));

        System.out.println();

        RemoteControllable[] items = {
                wm,
                app
        };

        System.out.println(
                "Connecting all:");

        connectAll(items, appId);

        System.out.println();

        // Upcasting
        HomeDevice ref = wm;

        System.out.println(
                "Washing machine consumption: "
                + getConsumptionIfTrackable(
                        ref));

        System.out.println(
                "Refrigerator consumption: "
                + getConsumptionIfTrackable(
                        fridge));

        sc.close();
    }
}