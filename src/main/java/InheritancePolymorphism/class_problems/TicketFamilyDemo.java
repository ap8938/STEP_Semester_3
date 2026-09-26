import java.util.Scanner;

class EventTicket {

    protected String attendeeId;
    protected double basePrice;
    protected double balanceDue;

    public EventTicket(String attendeeId,
                       double basePrice) {

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

    public void printTicket() {

        System.out.println(
                "Standard Event Ticket | Balance Due: "
                + balanceDue);
    }
}

class WorkshopTicket extends EventTicket {

    protected String track;

    public WorkshopTicket(String attendeeId,
                          double basePrice,
                          String track) {

        super(attendeeId, basePrice);

        this.track = track;
    }

    @Override
    public void printTicket() {

        System.out.println(
                "Workshop Ticket | Track: "
                + track
                + " | Balance Due: "
                + balanceDue);
    }
}

class PremiumWorkshopTicket extends WorkshopTicket {

    private double kitFee;

    public PremiumWorkshopTicket(String attendeeId,
                                 double basePrice,
                                 String track,
                                 double kitFee) {

        super(attendeeId, basePrice, track);

        this.kitFee = kitFee;
        balanceDue = balanceDue + kitFee;
    }

    @Override
    public void printTicket() {

        System.out.println(
                "Premium Workshop Ticket | Track: "
                + track
                + " | Kit Fee: "
                + kitFee
                + " | Balance Due: "
                + balanceDue);
    }
}

class HackathonTicket extends EventTicket {

    private String teamName;

    public HackathonTicket(String attendeeId,
                           double basePrice,
                           String teamName) {

        super(attendeeId, basePrice);

        this.teamName = teamName;
    }

    @Override
    public void printTicket() {

        System.out.println(
                "Hackathon Ticket | Team: "
                + teamName
                + " | Balance Due: "
                + balanceDue);
    }
}

public class TicketFamilyDemo {

    static String classifyGeneration(EventTicket ticket) {

        if (ticket instanceof PremiumWorkshopTicket) {

            return "Multilevel descendant (3 generations deep)";

        } else if (ticket instanceof HackathonTicket) {

            return "Hierarchical sibling (independent branch)";

        } else {

            return "Base or direct child";
        }
    }

    static double getTotalBalanceDue(EventTicket[] tickets) {

        double total = 0;

        for (int i = 0; i < tickets.length; i++) {

            total = total + tickets[i].getBalanceDue();
        }

        return total;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter regular ticket attendee ID: ");
        String id1 = sc.nextLine();

        System.out.print("Enter regular ticket price: ");
        double price1 = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter workshop attendee ID: ");
        String id2 = sc.nextLine();

        System.out.print("Enter workshop price: ");
        double price2 = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter workshop track: ");
        String track = sc.nextLine();

        System.out.print("Enter premium attendee ID: ");
        String id3 = sc.nextLine();

        System.out.print("Enter premium ticket price: ");
        double price3 = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter premium track: ");
        String premiumTrack = sc.nextLine();

        System.out.print("Enter kit fee: ");
        double kitFee = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter hackathon attendee ID: ");
        String id4 = sc.nextLine();

        System.out.print("Enter hackathon price: ");
        double price4 = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter team name: ");
        String teamName = sc.nextLine();

        EventTicket standard =
                new EventTicket(id1, price1);

        WorkshopTicket workshop =
                new WorkshopTicket(id2, price2, track);

        PremiumWorkshopTicket premium =
                new PremiumWorkshopTicket(
                        id3,
                        price3,
                        premiumTrack,
                        kitFee);

        HackathonTicket hackathon =
                new HackathonTicket(
                        id4,
                        price4,
                        teamName);

        System.out.println();

        standard.printTicket();
        workshop.printTicket();
        premium.printTicket();
        hackathon.printTicket();

        System.out.println();

        System.out.println(
                classifyGeneration(premium));

        System.out.println(
                classifyGeneration(hackathon));

        EventTicket[] tickets = {
                standard,
                workshop,
                premium,
                hackathon
        };

        System.out.println(
                "Total Balance Due: "
                + getTotalBalanceDue(tickets));

        sc.close();
    }
}
