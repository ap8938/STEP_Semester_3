import java.util.Scanner;

class FoodOrder {
    String studentName;
    String dishName;
    boolean delivered;

    FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid student name");
        }

        if (dishName == null || dishName.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid dish name");
        }

        this.studentName = studentName;
        this.dishName = dishName;
        delivered = false;
    }

    void markDelivered() {
        if (delivered) {
            System.out.println("Order was already delivered.");
        } else {
            delivered = true;
            System.out.println("Order delivered successfully.");
        }
    }

    static void processBatch(String[][] rawOrders) {
        int valid = 0;
        int rejected = 0;

        for (int i = 0; i < rawOrders.length; i++) {
            try {
                FoodOrder order = new FoodOrder(
                    rawOrders[i][0],
                    rawOrders[i][1]
                );

                valid++;
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid);
        System.out.println("Rejected: " + rejected);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of orders: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[][] orders = new String[n][2];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            orders[i][0] = sc.nextLine();

            System.out.print("Enter dish name: ");
            orders[i][1] = sc.nextLine();
        }

        processBatch(orders);
    }
}
