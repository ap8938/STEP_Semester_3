import java.util.Scanner;

interface Alertable {

    String sendAlert(String message);
}

class SecuritySensor {

    private String zoneName;

    public SecuritySensor(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return zoneName;
    }
}

class MotionSensor extends SecuritySensor
        implements Alertable {

    public MotionSensor(String zoneName) {
        super(zoneName);
    }

    @Override
    public String sendAlert(String message) {

        return "[" + getZoneName() + "] " + message;
    }
}

class DualZoneMotionSensor extends MotionSensor {

    private String secondZoneName;

    public DualZoneMotionSensor(String zoneName,
                                String secondZoneName) {

        super(zoneName);
        this.secondZoneName = secondZoneName;
    }

    @Override
    public String sendAlert(String message) {

        return super.sendAlert(message)
                + " [also covering " + secondZoneName + "]";
    }
}

class SmokeDetector implements Alertable {

    private String deviceId;

    public SmokeDetector(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String sendAlert(String message) {

        return "[" + deviceId + "] " + message;
    }
}

public class AlertDemo {

    static void broadcastAll(Alertable[] devices,
                             String message) {

        for (int i = 0; i < devices.length; i++) {

            System.out.println(devices[i].sendAlert(message));
        }
    }

    static String getZoneIfMotionSensor(Alertable a) {

        if (a instanceof MotionSensor) {

            MotionSensor m = (MotionSensor) a;

            return m.getZoneName();
        }

        return "Not a motion sensor";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter motion sensor zone: ");
        String zone = sc.nextLine();

        System.out.print("Enter second zone: ");
        String secondZone = sc.nextLine();

        System.out.print("Enter smoke detector ID: ");
        String deviceId = sc.nextLine();

        MotionSensor motion =
                new MotionSensor(zone);

        DualZoneMotionSensor dual =
                new DualZoneMotionSensor(zone, secondZone);

        SmokeDetector smoke =
                new SmokeDetector(deviceId);

        Alertable[] devices = {
            motion,
            dual,
            smoke
        };

        System.out.print("Enter alert message: ");
        String message = sc.nextLine();

        System.out.println();
        System.out.println("----- ALERTS -----");

        broadcastAll(devices, message);

        System.out.println();
        System.out.println("----- ZONE CHECK -----");

        System.out.println("Motion Sensor: "
                + getZoneIfMotionSensor(motion));

        System.out.println("Smoke Detector: "
                + getZoneIfMotionSensor(smoke));

        sc.close();
    }
}
