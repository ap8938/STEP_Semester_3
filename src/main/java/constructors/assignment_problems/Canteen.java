import java.util.Scanner;

class Canteen {
    String canteenCode;
    String canteenName;
    int trustScore;

    Canteen(String canteenCode, String canteenName, int trustScore) {
        this.canteenCode = canteenCode;
        this.canteenName = canteenName;
        this.trustScore = trustScore;
    }

    Canteen(String canteenCode, String canteenName) {
        this(canteenCode, canteenName, 3);
    }

    int compareTo(Canteen other) {

        // Higher score should come first
        if (this.trustScore != other.trustScore) {
            return other.trustScore - this.trustScore;
        }

        // If score is same, compare code ignoring case
        int result = this.canteenCode.compareToIgnoreCase(other.canteenCode);

        if (result != 0) {
            return result;
        }

        // If code is also same, shorter name comes first
        return this.canteenName.length() - other.canteenName.length();
    }

    static Canteen[] rankCanteens(Canteen[] canteens) {

        for (int i = 0; i < canteens.length - 1; i++) {

            for (int j = 0; j < canteens.length - 1 - i; j++) {

                if (canteens[j].compareTo(canteens[j + 1]) > 0) {

                    Canteen temp = canteens[j];
                    canteens[j] = canteens[j + 1];
                    canteens[j + 1] = temp;
                }
            }
        }

        return canteens;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of canteens: ");
        int n = sc.nextInt();
        sc.nextLine();

        Canteen[] canteens = new Canteen[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter canteen code: ");
            String code = sc.nextLine();

            System.out.print("Enter canteen name: ");
            String name = sc.nextLine();

            System.out.print("Enter trust score (or -1 for default 3): ");
            int score = sc.nextInt();
            sc.nextLine();

            if (score == -1) {
                canteens[i] = new Canteen(code, name);
            } else {
                canteens[i] = new Canteen(code, name, score);
            }
        }

        rankCanteens(canteens);

        System.out.println("\nRanked Canteens:");

        for (int i = 0; i < n; i++) {
            System.out.println(
                canteens[i].canteenCode + " - " +
                canteens[i].canteenName + " - " +
                canteens[i].trustScore
            );
        }
    }
}
