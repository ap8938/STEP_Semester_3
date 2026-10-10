
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

interface Capability {
    boolean apply(int value);
}

class PowerCapability implements Capability {
    public boolean apply(int value) {
        if (value != 0 && value != 1) return false;
        System.out.println(value == 1 ? "Power ON" : "Power OFF");
        return true;
    }
}

class BrightnessCapability implements Capability {
    public boolean apply(int value) {
        if (value < 0 || value > 100) {
            System.out.println("Brightness must be 0 to 100");
            return false;
        }
        System.out.println("Brightness set to " + value + "%");
        return true;
    }
}

class TemperatureCapability implements Capability {
    public boolean apply(int value) {
        if (value < 16 || value > 30) {
            System.out.println("Temperature must be between 16 and 30");
            return false;
        }
        System.out.println("Temperature set to " + value + " C");
        return true;
    }
}

class Device {
    String name;
    Map<String, Capability> capabilities = new HashMap<>();

    Device(String name) {
        this.name = name;
    }

    void add(String name, Capability capability) {
        capabilities.put(name.toLowerCase(), capability);
        System.out.println(this.name + ": " + name + " capability added");
    }

    void apply(String name, int value) {
        Capability c = capabilities.get(name.toLowerCase());

        if (c == null) {
            System.out.println(this.name + ": " + name + " unsupported");
        } else {
            System.out.print(this.name + ": ");
            c.apply(value);
        }
    }
}

public class SmartLabDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Device ac = new Device("Lab AC");
        ac.add("Power", new PowerCapability());
        ac.add("Temperature", new TemperatureCapability());

        Device lights = new Device("Ceiling Lights");
        lights.add("Power", new PowerCapability());
        lights.add("Brightness", new BrightnessCapability());

        Device projector = new Device("Projector");
        projector.add("Power", new PowerCapability());

        System.out.print("Choose device (1 AC, 2 Lights, 3 Projector): ");
        int choice = sc.nextInt();

        Device device = choice == 1 ? ac : choice == 2 ? lights : projector;

        System.out.print("Capability (Power, Brightness, Temperature): ");
        String capability = sc.next();

        System.out.print("Value (Power: 0/1): ");
        int value = sc.nextInt();

        device.apply(capability, value);

        System.out.print("Add Brightness capability to projector? (1 Yes, 2 No): ");
        if (sc.nextInt() == 1) {
            projector.add("Brightness", new BrightnessCapability());
            System.out.print("Enter brightness: ");
            projector.apply("Brightness", sc.nextInt());
        }

        sc.close();
    }
}
