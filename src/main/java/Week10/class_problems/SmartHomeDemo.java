
import java.util.Scanner;

interface Dimmable {
    void dim(int level);
}

interface Schedulable {
    void schedule(String time);
}

interface EnergyMonitor {
    void showEnergy();
}

abstract class Device {
    String id;
    boolean on;

    Device(String id) {
        this.id = id;
        on = false;
    }

    void turnOn() {
        on = true;
        System.out.println(id + " is ON");
    }
}

class Light extends Device implements Dimmable, Schedulable {
    Light(String id) {
        super(id);
    }

    public void dim(int level) {
        System.out.println(id + " dimmed to " + level);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Fan extends Device implements Schedulable {
    Fan(String id) {
        super(id);
    }

    public void schedule(String time) {
        System.out.println(id + " scheduled " + time);
    }
}

class Plug extends Device implements EnergyMonitor {
    double energy;

    Plug(String id, double energy) {
        super(id);
        this.energy = energy;
    }

    public void showEnergy() {
        System.out.println(id + " energy " + energy + " kWh");
    }
}

public class SmartHomeDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Light light = new Light("L1");
        Fan fan = new Fan("F1");
        Plug plug = new Plug("P1", 12);

        System.out.print("Enter command (ON, DIM, SCHEDULE, ENERGY): ");
        String command = sc.next().toUpperCase();

        System.out.print("Enter device ID (L1, F1, P1): ");
        String id = sc.next().toUpperCase();

        Device device = null;

        if (id.equals("L1")) {
            device = light;
        } else if (id.equals("F1")) {
            device = fan;
        } else if (id.equals("P1")) {
            device = plug;
        }

        if (device == null) {
            System.out.println("Unknown device");
        } else if (command.equals("ON")) {
            device.turnOn();
        } else if (command.equals("DIM")) {
            if (device instanceof Dimmable) {
                System.out.print("Enter dim level: ");
                int level = sc.nextInt();
                ((Dimmable) device).dim(level);
            } else {
                System.out.println(id + " rejected: DIM unsupported");
            }
        } else if (command.equals("SCHEDULE")) {
            if (device instanceof Schedulable) {
                System.out.print("Enter time: ");
                String time = sc.next();
                ((Schedulable) device).schedule(time);
            } else {
                System.out.println(id + " rejected: SCHEDULE unsupported");
            }
        } else if (command.equals("ENERGY")) {
            if (device instanceof EnergyMonitor) {
                ((EnergyMonitor) device).showEnergy();
            } else {
                System.out.println(id + " rejected: ENERGY unsupported");
            }
        } else {
            System.out.println("Unknown command");
        }

        sc.close();
    }
}
