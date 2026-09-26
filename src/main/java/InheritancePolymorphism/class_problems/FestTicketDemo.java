import java.util.Scanner;

class EventTicket {

    private static int ticketsIssued = 1000;

    private final String ticketId;

    protected double balanceDue;

    public EventTicket(double basePrice) {

        ticketsIssued++;

        ticketId = "TCK-" + ticketsIssued;

        balanceDue = basePrice;
    }

    public void pay(double amount) {

        balanceDue = balanceDue - amount;

        if (balanceDue < 0) {
            balanceDue = 0;
        }
    }

    public void pay(double amount, String mode) {

        System.out.println("Paying via " + mode);

        pay(amount);
    }

    public double getBalanceDue() {

        return balanceDue;
    }

    public String getTicketId() {

        return ticketId;
    }

    public static boolean isValidPromoCode(
            String code) {

        if (code == null || code.length() != 5) {
            return false;
        }

        if (code.charAt(0) != 'F') {
            return false;
        }

        for (int i = 1; i <= 3; i++) {

            if (!Character.isDigit(code.charAt(i))) {
                return false;
            }
        }

        if (!Character.isUpperCase(code.charAt(4))) {
            return false;
        }

        return true;
    }

    public static int getTicketsIssued() {

        return ticketsIssued - 1000;
    }
}

class GroupTicket extends EventTicket {

    private int groupSize;

    public GroupTicket(double basePrice,
                       int groupSize) {

        super(basePrice);

        this.groupSize = groupSize;
    }

    public int getGroupSize() {

        return groupSize;
    }
}

public class FestTicketDemo {

    static String processNightlySettlement(
            EventTicket[] tickets) {

        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        StringBuilder result =
                new StringBuilder();

        for (int i = 0; i < tickets.length; i++) {

            if (tickets[i] == null) {

                nullSkipped++;
                continue;
            }

            processed++;

            if (tickets[i] instanceof GroupTicket) {

                groupCount++;

            } else {

                individualCount++;
            }
        }

        result.append(processed);
        result.append(" processed | ");
        result.append(nullSkipped);
        result.append(" null skipped | ");
        result.append(groupCount);
        result.append(" group | ");
        result.append(individualCount);
        result.append(" individual");

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ticket base price: ");
        double price = sc.nextDouble();

        EventTicket ticket =
                new EventTicket(price);

        System.out.println(
                "Ticket ID: "
                + ticket.getTicketId());

        System.out.print(
                "Enter amount to pay: ");

        double amount = sc.nextDouble();

        sc.nextLine();

        System.out.print(
                "Enter payment mode: ");

        String mode = sc.nextLine();

        ticket.pay(amount, mode);

        System.out.println(
                "Balance Due: "
                + ticket.getBalanceDue());

        System.out.print(
                "Enter promo code: ");

        String promo = sc.nextLine();

        System.out.println(
                "Valid Promo Code: "
                + EventTicket.isValidPromoCode(promo));

        System.out.print(
                "Enter group ticket price: ");

        double groupPrice = sc.nextDouble();

        System.out.print(
                "Enter group size: ");

        int groupSize = sc.nextInt();

        GroupTicket group =
                new GroupTicket(
                        groupPrice,
                        groupSize);

        EventTicket[] tickets = {
                ticket,
                null,
                group,
                new EventTicket(500)
        };

        System.out.println();

        System.out.println(
                processNightlySettlement(tickets));

        System.out.println(
                "Tickets Issued: "
                + EventTicket.getTicketsIssued());

        sc.close();
    }
}