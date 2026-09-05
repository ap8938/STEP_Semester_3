class BusTicketAccount {

    static String depotName;

    static {
        depotName = "Central Depot";
    }

    String bookingId;
    double ticketFare;

    BusTicketAccount(String bookingId, double ticketFare) {
        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
    }

    BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    final double calculatePenalty(int minutesLate) {

        if (minutesLate < 0) {
            throw new IllegalArgumentException("Invalid minutes");
        }

        return ticketFare * 0.01 * minutesLate;
    }

    void processAccount(BusTicketAccount account,
                        double amount,
                        int minutesLate) {

        double penalty = calculatePenalty(minutesLate);

        System.out.println("Booking ID: " + bookingId);
        System.out.println("Amount: " + amount);
        System.out.println("Penalty: " + penalty);
    }

    static void processBatch(BusTicketAccount[] accounts,
                             double[] amounts,
                             int[] minutesLateArray) {

        int processed = 0;
        int nullSkipped = 0;
        int sleeperCount = 0;
        int regularCount = 0;
        double totalPenalty = 0;

        int n = Math.min(accounts.length,
                Math.min(amounts.length, minutesLateArray.length));

        for (int i = 0; i < n; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            accounts[i].processAccount(
                    accounts[i],
                    amounts[i],
                    minutesLateArray[i]
            );

            totalPenalty +=
                    accounts[i].calculatePenalty(minutesLateArray[i]);

            processed++;

            if (accounts[i] instanceof Sleeper) {
                sleeperCount++;
            } else {
                regularCount++;
            }
        }

        System.out.println("\n--- Summary ---");
        System.out.println("Processed: " + processed);
        System.out.println("Null skipped: " + nullSkipped);
        System.out.println("Sleeper: " + sleeperCount);
        System.out.println("Regular: " + regularCount);
        System.out.println("Grand total penalties: " + totalPenalty);
    }
}


class Sleeper extends BusTicketAccount {

    Sleeper(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    Sleeper(String bookingId) {
        super(bookingId);
    }
}


public class BusTicketAccs {

    public static void main(String[] args) {

        BusTicketAccount[] accounts = {
            new Sleeper("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {
            1200,
            900,
            700
        };

        int[] minutesLateArray = {
            10,
            5,
            0
        };

        BusTicketAccount.processBatch(
                accounts,
                amounts,
                minutesLateArray
        );
    }
}
