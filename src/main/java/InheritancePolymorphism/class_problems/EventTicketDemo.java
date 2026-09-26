import java.util.Scanner;

class EventTicket {

    protected String attendeeId;
    protected double basePrice;
    protected double balanceDue;

    public EventTicket(String attendeeId, double basePrice) {

        if (attendeeId == null
                || attendeeId.trim().isEmpty()
                || attendeeId.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "Invalid attendee ID");
        }

        this.attendeeId = attendeeId;
        this.basePrice = basePrice;
        this.balanceDue = basePrice;
    }

    public void pay(double amount) {

        balanceDue = balanceDue - amount;

        if (balanceDue < 0) {
            balanceDue = 0;
        }
    }

    public double getBalanceDue() {

        return balanceDue;
    }
}

class WorkshopTicket extends EventTicket {

    private String track;

    public WorkshopTicket(String attendeeId,
                          double basePrice,
                          String track) {

        super(attendeeId, basePrice);

        this.track = track;
    }
}

public class EventTicketDemo {

    static String registerBatch(String[] attendeeIds,
                                double basePrice) {

        int registered = 0;
        int rejected = 0;

        for (int i = 0; i < attendeeIds.length; i++) {

            try {

                EventTicket ticket =
                        new EventTicket(
                                attendeeIds[i],
                                basePrice);

                registered++;

            } catch (IllegalArgumentException e) {

                rejected++;
            }
        }

        return "Registered: " + registered
                + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter attendee ID: ");
        String id = sc.nextLine();

        System.out.print("Enter base price: ");
        double price = sc.nextDouble();

        try {

            EventTicket ticket =
                    new EventTicket(id, price);

            System.out.print("Enter amount to pay: ");
            double amount = sc.nextDouble();

            ticket.pay(amount);

            System.out.println(
                    "Balance Due: "
                    + ticket.getBalanceDue());

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Construction rejected");
        }

        System.out.println();
        System.out.print(
                "Enter number of registrations for batch: ");

        int n = sc.nextInt();
        sc.nextLine();

        String[] ids = new String[n];

        for (int i = 0; i < n; i++) {

            System.out.print(
                    "Enter attendee ID " + (i + 1) + ": ");

            ids[i] = sc.nextLine();
        }

        System.out.print("Enter batch base price: ");
        double batchPrice = sc.nextDouble();

        System.out.println(
                registerBatch(ids, batchPrice));

        sc.close();
    }
}