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


class HostelRoom {

    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = 0;
    }

    void allot(String studentName) {

        if (occupied < beds) {
            occupied++;

            System.out.println(studentName
                    + " allotted to room "
                    + roomNo);
        } else {
            System.out.println("Room is full.");
        }
    }
}


class SrmStudent {

    String name;
    String regNo;

    FeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name,
               String regNo,
               FeeAccount feeAccount,
               HostelRoom room) {

        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = room;

        totalStudents++;
    }

    String fullStatus() {

        return name
                + " | Due: Rs "
                + feeAccount.getDue()
                + " | Room: "
                + room.roomNo;
    }
}


public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SrmStudent[] students =
                new SrmStudent[3];

        // Creating 3 students
        for (int i = 0; i < 3; i++) {

            System.out.println(
                    "\nEnter details for student "
                    + (i + 1));

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Registration number: ");
            String regNo = sc.nextLine();

            System.out.print("Total fee: ");
            double totalFee = sc.nextDouble();

            sc.nextLine();

            System.out.print("Room number: ");
            String roomNo = sc.nextLine();

            System.out.print("Number of beds: ");
            int beds = sc.nextInt();

            sc.nextLine();

            // Create FeeAccount
            FeeAccount fee =
                    new FeeAccount(regNo, totalFee);

            // Create HostelRoom
            HostelRoom room =
                    new HostelRoom(roomNo, beds);

            // Create SrmStudent
            students[i] =
                    new SrmStudent(
                            name,
                            regNo,
                            fee,
                            room
                    );

            // Allot student
            room.allot(name);
        }

        // Payment
        System.out.print(
                "\nEnter student number for payment (1-3): ");
        int studentNumber = sc.nextInt();

        System.out.print("Enter payment amount: ");
        double payment = sc.nextDouble();

        students[studentNumber - 1]
                .feeAccount.pay(payment);

        // Display status
        System.out.println("\nStudent Status:");

        for (SrmStudent student : students) {
            System.out.println(student.fullStatus());
        }

        System.out.println(
                "Total students: "
                + SrmStudent.totalStudents);

        sc.close();
    }
}