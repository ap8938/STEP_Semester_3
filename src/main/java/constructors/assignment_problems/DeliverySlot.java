import java.util.Scanner;

class DeliverySlot {
    String orderId;
    String timeSlot;

    DeliverySlot(String orderId, String timeSlot) {
        this.orderId = orderId;
        this.timeSlot = timeSlot;
    }

    DeliverySlot(String orderId) {
        this(orderId, "ASAP");
    }

    boolean isPeakHour() {
        switch (timeSlot) {
            case "12:00-13:00":
            case "13:00-14:00":
            case "19:00-20:00":
            case "20:00-21:00":
                return true;

            default:
                return false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter order ID: ");
        String id = sc.nextLine();

        System.out.print("Choose slot:\n");
        System.out.println("1. ASAP");
        System.out.println("2. 12:00-13:00");
        System.out.println("3. 13:00-14:00");
        System.out.println("4. 19:00-20:00");
        System.out.println("5. 20:00-21:00");

        int choice = sc.nextInt();

        DeliverySlot slot;

        switch (choice) {
            case 1:
                slot = new DeliverySlot(id);
                break;

            case 2:
                slot = new DeliverySlot(id, "12:00-13:00");
                break;

            case 3:
                slot = new DeliverySlot(id, "13:00-14:00");
                break;

            case 4:
                slot = new DeliverySlot(id, "19:00-20:00");
                break;

            case 5:
                slot = new DeliverySlot(id, "20:00-21:00");
                break;

            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Time Slot: " + slot.timeSlot);
        System.out.println("Peak Hour: " + slot.isPeakHour());
    }
}
