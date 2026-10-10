
import java.util.Scanner;
import java.util.ArrayList;

interface ShippingType {
    double charge(double weight);
}

class StandardShipping implements ShippingType {
    public double charge(double weight) {
        return 40 + 10 * weight;
    }
}

class ExpressShipping implements ShippingType {
    public double charge(double weight) {
        return 80 + 15 * weight;
    }
}

class FragileShipping implements ShippingType {
    public double charge(double weight) {
        return 40 + 10 * weight + 50;
    }
}

interface NotificationChannel {
    void notify(String message);
}

class SmsChannel implements NotificationChannel {
    public void notify(String message) {
        System.out.println("[SMS] " + message);
    }
}

class EmailChannel implements NotificationChannel {
    public void notify(String message) {
        System.out.println("[Email] " + message);
    }
}

class Parcel {
    String id;
    String status = "BOOKED";
    ShippingType shipping;
    double weight;
    ArrayList<NotificationChannel> channels = new ArrayList<>();

    Parcel(String id, double weight, ShippingType shipping) {
        this.id = id;
        this.weight = weight;
        this.shipping = shipping;
    }

    void update(String next) {
        String[] states = {
            "BOOKED", "PICKED_UP", "IN_TRANSIT",
            "OUT_FOR_DELIVERY", "DELIVERED"
        };

        int current = -1;
        for (int i = 0; i < states.length; i++) {
            if (states[i].equals(status)) current = i;
        }

        if (current < 0 || current + 1 >= states.length
                || !states[current + 1].equals(next)) {
            System.out.println("Invalid transition: "
                    + status + " -> " + next + " is not allowed");
            return;
        }

        status = next;
        for (NotificationChannel channel : channels) {
            channel.notify(id + " is now " + status);
        }
    }

    void cancel() {
        if (status.equals("BOOKED")) {
            status = "CANCELLED";
            System.out.println("Parcel cancelled");
        } else {
            System.out.println("Cancellation failed: "
                    + id + " can be cancelled only while BOOKED");
        }
    }
}

public class ParcelTrackerDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Parcel ID: ");
        String id = sc.next();

        System.out.print("Weight in kg: ");
        double weight = sc.nextDouble();

        System.out.print("Shipping (1 Standard, 2 Express, 3 Fragile): ");
        int choice = sc.nextInt();

        ShippingType shipping;
        if (choice == 1) shipping = new StandardShipping();
        else if (choice == 2) shipping = new ExpressShipping();
        else shipping = new FragileShipping();

        Parcel parcel = new Parcel(id, weight, shipping);

        parcel.channels.add(new SmsChannel());
        parcel.channels.add(new EmailChannel());

        System.out.printf("Charge: ₹%.2f%n", shipping.charge(weight));

        for (NotificationChannel channel : parcel.channels) {
            channel.notify(id + " is now BOOKED");
        }

        System.out.print("Next status (PICKED_UP, IN_TRANSIT, OUT_FOR_DELIVERY, DELIVERED): ");
        parcel.update(sc.next());

        System.out.print("Cancel parcel? (1 Yes, 2 No): ");
        if (sc.nextInt() == 1) parcel.cancel();

        sc.close();
    }
}
