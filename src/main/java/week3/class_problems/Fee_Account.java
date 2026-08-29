import java.util.Scanner;

class FeeAccount {

    private String regNo;
    private double totalFee;
    private double amountPaid;

    FeeAccount(String regNo, double totalFee) {
        this.regNo = regNo;
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid payment");
        } else {
            amountPaid += amount;
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }

    void payInTwoInstallments(double amount) {
        pay(amount / 2);
        pay(amount / 2);
    }

    double effectiveDue(double scholarshipPercent) {
        double due = getDue();

        return due - (due * scholarshipPercent / 100);
    }
}

public class Fee_Account {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Account A
        System.out.print("Enter Account A registration number: ");
        String regNoA = sc.nextLine();

        System.out.print("Enter Account A total fee: ");
        double feeA = sc.nextDouble();

        FeeAccount accountA =
                new FeeAccount(regNoA, feeA);

        System.out.print("Enter amount to pay in two installments: ");
        double payment = sc.nextDouble();

        accountA.payInTwoInstallments(payment);

        System.out.println("Account A due: Rs "
                + accountA.getDue());


        // Account B
        sc.nextLine(); // consume newline

        System.out.print("\nEnter Account B registration number: ");
        String regNoB = sc.nextLine();

        System.out.print("Enter Account B total fee: ");
        double feeB = sc.nextDouble();

        FeeAccount accountB =
                new FeeAccount(regNoB, feeB);

        System.out.print("Enter scholarship percentage: ");
        double scholarship = sc.nextDouble();

        System.out.println("Account B effective due: Rs "
                + accountB.effectiveDue(scholarship));

        sc.close();
    }
}