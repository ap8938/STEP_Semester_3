import java.util.Scanner;

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        }
    }
}

public class ParkingDemo {

    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {

        for (ParkingSlot slot : slots) {
            if (slot.occupiedCount < slot.capacity) {
                return slot;
            }
        }

        return null;
    }

    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {

        ParkingSlot slot = findAvailableSlot(slots);

        if (slot != null) {
            slot.allot(vehicleNo);
        } else {
            System.out.println("No slots available for " + vehicleNo);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of parking slots: ");
        int n = sc.nextInt();
        sc.nextLine();

        ParkingSlot[] slots = new ParkingSlot[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nEnter details for slot " + (i + 1));

            System.out.print("Slot number: ");
            String slotNo = sc.nextLine();

            System.out.print("Capacity: ");
            int capacity = sc.nextInt();

            System.out.print("Occupied count: ");
            int occupiedCount = sc.nextInt();

            sc.nextLine();

            slots[i] = new ParkingSlot(slotNo, capacity, occupiedCount);
        }

        System.out.print("\nEnter vehicle number for first allotment: ");
        String vehicle1 = sc.nextLine();

        safeAllot(slots, vehicle1);

        System.out.print("\nEnter vehicle number for second allotment: ");
        String vehicle2 = sc.nextLine();

        safeAllot(slots, vehicle2);

        sc.close();
    }
}
