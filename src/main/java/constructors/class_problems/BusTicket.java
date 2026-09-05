import java.util.*;

class BusTicket {
    String passengerName;
    String destination;
    boolean checkedIn = false;

    BusTicket(String passengerName, String destination) {
        if (passengerName == null || passengerName.trim().equals("") ||
            destination == null || destination.trim().equals("") ||
            !passengerName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Invalid booking");
        }

        this.passengerName = passengerName;
        this.destination = destination;
    }

    void markCheckedIn() {
        if (checkedIn)
            System.out.println("Already checked in");
        else {
            checkedIn = true;
            System.out.println("Checked in successfully");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of bookings: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[][] bookings = new String[n][2];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter passenger name: ");
            bookings[i][0] = sc.nextLine();

            System.out.print("Enter destination: ");
            bookings[i][1] = sc.nextLine();
        }

        int valid = 0;
        int rejected = 0;
        int duplicate = 0;

        for (int i = 0; i < n; i++) {

            try {
                BusTicket ticket =
                    new BusTicket(bookings[i][0], bookings[i][1]);

                boolean isDuplicate = false;

                for (int j = 0; j < i; j++) {
                    if (bookings[i][0].equals(bookings[j][0]) &&
                        bookings[i][1].equals(bookings[j][1])) {
                        isDuplicate = true;
                        break;
                    }
                }

                if (isDuplicate)
                    duplicate++;
                else
                    valid++;

            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid);
        System.out.println("Rejected: " + rejected);
        System.out.println("Duplicates: " + duplicate);

        sc.close();
    }
}