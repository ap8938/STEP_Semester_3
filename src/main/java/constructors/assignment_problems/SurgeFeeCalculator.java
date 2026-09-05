import java.util.Scanner;

final class SurgeFeeCalculator {

    private final double minimumSurgePercent;

    public SurgeFeeCalculator(double minimumSurgePercent) {
        this.minimumSurgePercent = minimumSurgePercent;
    }

    final double calculateSurgeFee(double orderValue, int delayMinutes) {

        if (orderValue < 0 || delayMinutes < 0) {
            throw new IllegalArgumentException("Values cannot be negative");
        }

        if (delayMinutes == 0) {
            return 0;
        }

        double fee = 0;

        // First 5 minutes
        int firstPart = Math.min(delayMinutes, 5);
        fee += firstPart * orderValue * 0.005;

        // Minutes 6 to 15
        if (delayMinutes > 5) {
            int secondPart = Math.min(delayMinutes - 5, 10);
            fee += secondPart * orderValue * 0.01;
        }

        // After 15 minutes
        if (delayMinutes > 15) {
            int thirdPart = delayMinutes - 15;
            fee += thirdPart * orderValue * 0.02;
        }

        // Minimum surge floor
        double minimumFee =
            orderValue * minimumSurgePercent / 100;

        if (fee < minimumFee) {
            fee = minimumFee;
        }

        return fee;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter minimum surge percentage: ");
        double minimum = sc.nextDouble();

        System.out.print("Enter order value: ");
        double value = sc.nextDouble();

        System.out.print("Enter delay in minutes: ");
        int delay = sc.nextInt();

        SurgeFeeCalculator calculator =
            new SurgeFeeCalculator(minimum);

        try {
            double fee = calculator.calculateSurgeFee(value, delay);

            System.out.println("Surge Fee: Rs " + fee);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
