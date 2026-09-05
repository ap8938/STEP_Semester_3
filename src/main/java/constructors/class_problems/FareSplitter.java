import java.util.*;

class FareSplitter {

    String tripId;
    double totalFare;
    int passengerCount;

    FareSplitter(String tripId, double totalFare, int passengerCount) {

        if (totalFare < 0 || passengerCount <= 0)
            throw new IllegalArgumentException("Invalid input");

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    FareSplitter(String tripId) {
        this(tripId, 0, 2);
    }

    double[] fareBreakdown() {

        double[] result = new double[passengerCount];

        double share = totalFare / passengerCount;

        for (int i = 0; i < passengerCount; i++) {
            result[i] = Math.round(share * 100) / 100.0;
        }

        // Adjust last person so total is exactly equal
        double sum = 0;

        for (int i = 0; i < passengerCount - 1; i++)
            sum += result[i];

        result[passengerCount - 1] =
            Math.round((totalFare - sum) * 100) / 100.0;

        return result;
    }

    boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    System.out.println("1. Enter trip ID, fare and passengers");
    System.out.println("2. Enter trip ID and fare");
    System.out.println("3. Enter only trip ID");

    System.out.print("Enter your choice: ");
    int choice = sc.nextInt();
    sc.nextLine();

    FareSplitter f;

    if (choice == 1) {

        System.out.print("Enter trip ID: ");
        String id = sc.nextLine();

        System.out.print("Enter total fare: ");
        double fare = sc.nextDouble();

        System.out.print("Enter number of passengers: ");
        int n = sc.nextInt();

        f = new FareSplitter(id, fare, n);

    } 
    else if (choice == 2) {

        System.out.print("Enter trip ID: ");
        String id = sc.nextLine();

        System.out.print("Enter total fare: ");
        double fare = sc.nextDouble();

        f = new FareSplitter(id, fare);

    } 
    else {

        System.out.print("Enter trip ID: ");
        String id = sc.nextLine();

        f = new FareSplitter(id);
    }

    double[] result = f.fareBreakdown();

    System.out.println("\nFare Breakdown:");

    for (int i = 0; i < result.length; i++) {
        System.out.printf("Passenger %d: %.2f%n",
                i + 1, result[i]);
    }

    sc.close();
}
}
