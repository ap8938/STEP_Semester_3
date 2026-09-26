import java.util.Scanner;

class EventTicket {

    protected double balanceDue;

    public EventTicket(double price) {

        balanceDue = price;
    }

    public void printTicket() {

        System.out.println(
                "Standard | Balance: "
                + balanceDue);
    }

    public double getBalanceDue() {

        return balanceDue;
    }
}

class WorkshopTicket extends EventTicket {

    private String track;

    public WorkshopTicket(double price,
                          String track) {

        super(price);

        this.track = track;
    }

    @Override
    public void printTicket() {

        System.out.println(
                "Workshop | Track: "
                + track
                + " | Balance: "
                + balanceDue);
    }

    public String getTrack() {

        return track;
    }
}

public class TicketAnnouncer {

    static String batchPrint(EventTicket[] tickets) {

        StringBuilder report =
                new StringBuilder();

        for (int i = 0; i < tickets.length; i++) {

            // Polymorphic call
            if (tickets[i] instanceof WorkshopTicket) {

                WorkshopTicket workshop =
                        (WorkshopTicket) tickets[i];

                report.append(
                        "Workshop | Track: "
                        + workshop.getTrack()
                        + " | Balance: "
                        + workshop.getBalanceDue());

            } else {

                report.append(
                        "Standard | Balance: "
                        + tickets[i].getBalanceDue());
            }

            report.append(" | ");
        }

        return report.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter standard ticket price: ");

        double standardPrice = sc.nextDouble();

        sc.nextLine();

        System.out.print(
                "Enter workshop ticket price: ");

        double workshopPrice = sc.nextDouble();

        sc.nextLine();

        System.out.print(
                "Enter workshop track: ");

        String track = sc.nextLine();

        EventTicket standard =
                new EventTicket(standardPrice);

        WorkshopTicket workshop =
                new WorkshopTicket(
                        workshopPrice,
                        track);

        EventTicket[] tickets = {
                standard,
                workshop
        };

        System.out.println();
        System.out.println(
                batchPrint(tickets));

        sc.close();
    }
}