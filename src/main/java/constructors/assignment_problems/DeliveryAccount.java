import java.util.Scanner;

class DeliveryAccount {

    static String systemName;

    String studentId;
    double orderValue;

    // Static block
    static {
        systemName = "Campus Delivery System";
    }

    DeliveryAccount(String studentId, double orderValue) {
        this.studentId = studentId;
        this.orderValue = orderValue;
    }

    DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    final double calculateSurgeFee(int delayMinutes) {

        if (delayMinutes < 0) {
            throw new IllegalArgumentException("Delay cannot be negative");
        }

        double fee = 0;

        int firstPart = Math.min(delayMinutes, 5);
        fee += firstPart * orderValue * 0.005;

        if (delayMinutes > 5) {
            int secondPart = Math.min(delayMinutes - 5, 10);
            fee += secondPart * orderValue * 0.01;
        }

        if (delayMinutes > 15) {
            int thirdPart = delayMinutes - 15;
            fee += thirdPart * orderValue * 0.02;
        }

        return fee;
    }

    void processAccount(DeliveryAccount account,
                        double amount,
                        int delayMinutes) {

        double fee = account.calculateSurgeFee(delayMinutes);

        // Assumption: Premium gets 50% surge fee
        if (account instanceof Premium) {
            fee = fee * 0.5;
            System.out.println(
                account.studentId +
                " Premium | Surge Fee: Rs " + fee
            );
        } else {
            System.out.println(
                account.studentId +
                " Regular | Surge Fee: Rs " + fee
            );
        }
    }

    static void processBatch(
        DeliveryAccount[] accounts,
        double[] amounts,
        int[] delayMinutesArray) {

        if (accounts.length != amounts.length ||
            accounts.length != delayMinutesArray.length) {

            System.out.println("Error: Array lengths do not match.");
            return;
        }

        int processed = 0;
        int nullSkipped = 0;
        int premium = 0;
        int regular = 0;

        double totalSurge = 0;

        for (int i = 0; i < accounts.length; i++) {

            if (accounts[i] == null) {
                nullSkipped++;
                continue;
            }

            try {
                double fee =
                    accounts[i].calculateSurgeFee(
                        delayMinutesArray[i]
                    );

                if (accounts[i] instanceof Premium) {
                    fee = fee * 0.5;
                    premium++;
                } else {
                    regular++;
                }

                totalSurge += fee;
                processed++;

            } catch (Exception e) {
                System.out.println(
                    "Error processing " +
                    accounts[i].studentId
                );
            }
        }

        System.out.println("\n--- Summary ---");
        System.out.println("Processed: " + processed);
        System.out.println("Null skipped: " + nullSkipped);
        System.out.println("Premium: " + premium);
        System.out.println("Regular: " + regular);
        System.out.println("Grand total surge fees: Rs " + totalSurge);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of accounts: ");
        int n = sc.nextInt();
        sc.nextLine();

        DeliveryAccount[] accounts =
            new DeliveryAccount[n];

        double[] amounts = new double[n];
        int[] delays = new int[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nAccount " + (i + 1));

            System.out.println("1. Regular");
            System.out.println("2. Premium");
            System.out.println("3. Null");

            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 3) {
                accounts[i] = null;
                amounts[i] = 0;
                delays[i] = 0;
                continue;
            }

            System.out.print("Enter student ID: ");
            String id = sc.nextLine();

            System.out.print("Enter order value: ");
            double value = sc.nextDouble();

            System.out.print("Enter delay minutes: ");
            int delay = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                accounts[i] =
                    new DeliveryAccount(id, value);
            } else if (choice == 2) {
                accounts[i] =
                    new Premium(id, value);
            } else {
                System.out.println("Invalid choice.");
                i--;
                continue;
            }

            amounts[i] = value;
            delays[i] = delay;
        }

        processBatch(accounts, amounts, delays);
    }
}


// Premium account
class Premium extends DeliveryAccount {

    Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }
}
