
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

class Room {
    String roomName;
    double pricePerNight;

    Room(String roomName, double pricePerNight) {
        this.roomName = roomName;
        this.pricePerNight = pricePerNight;
    }
}

class Reservation {
    Room room;
    String customer;
    LocalDate start;
    LocalDate end;
    LocalDate cancellationDeadline;
    boolean cancelled;

    Reservation(Room room, String customer, LocalDate start,
                LocalDate end, LocalDate deadline) {
        this.room = room;
        this.customer = customer;
        this.start = start;
        this.end = end;
        this.cancellationDeadline = deadline;
        cancelled = false;
    }

    boolean overlaps(LocalDate newStart, LocalDate newEnd) {
        return !cancelled && newStart.isBefore(end)
                && newEnd.isAfter(start);
    }
}

class Hotel {
    ArrayList<Reservation> reservations = new ArrayList<>();

    void book(Room room, String customer,
               LocalDate start, LocalDate end,
               LocalDate deadline) {
        if (!start.isBefore(end)) {
            System.out.println("Invalid date range");
            return;
        }

        for (Reservation r : reservations) {
            if (r.room.roomName.equals(room.roomName)
                    && r.overlaps(start, end)) {
                System.out.println("Booking failed: "
                        + room.roomName + " is unavailable");
                return;
            }
        }

        Reservation r = new Reservation(
                room, customer, start, end, deadline);
        reservations.add(r);

        long nights = java.time.temporal.ChronoUnit.DAYS
                .between(start, end);

        System.out.printf("%s booked from %s to %s. Total price: $%.2f%n",
                room.roomName, start, end, nights * room.pricePerNight);
    }

    void cancel(String roomName, String customer, LocalDate today) {
        for (Reservation r : reservations) {
            if (r.room.roomName.equals(roomName)
                    && r.customer.equals(customer)
                    && !r.cancelled) {
                if (today.isBefore(r.cancellationDeadline)) {
                    r.cancelled = true;
                    System.out.println("Reservation cancelled successfully");
                } else {
                    System.out.println("Cancellation deadline has passed");
                }
                return;
            }
        }

        System.out.println("Active reservation not found");
    }
}

public class HotelBookingDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        Room deluxe = new Room("Deluxe Room 101", 200);
        Room standard = new Room("Standard Room 205", 150);

        System.out.print("Enter customer name: ");
        String customer = sc.nextLine();

        System.out.print("Enter room (1 Deluxe, 2 Standard): ");
        int choice = sc.nextInt();
        sc.nextLine();

        Room room = choice == 1 ? deluxe : standard;

        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        LocalDate start = LocalDate.parse(sc.nextLine());

        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        LocalDate end = LocalDate.parse(sc.nextLine());

        System.out.print("Enter cancellation deadline (YYYY-MM-DD): ");
        LocalDate deadline = LocalDate.parse(sc.nextLine());

        hotel.book(room, customer, start, end, deadline);

        System.out.print("Enter today's date to attempt cancellation: ");
        LocalDate today = LocalDate.parse(sc.nextLine());

        hotel.cancel(room.roomName, customer, today);

        sc.close();
    }
}
