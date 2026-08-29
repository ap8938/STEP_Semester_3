import java.util.Scanner;

class SrmStudent {

    String name;
    String regNo;

    static String university =
            "SRM Institute of Science and Technology";

    static int admissionCount = 0;

    SrmStudent(String name) {

        this.name = name;

        admissionCount++;

        regNo = "RA23110030"
                + admissionCount;
    }

    void printIdCard() {
        System.out.println(name + " | "
                + regNo + " | "
                + university);
    }

    static void printTotalAdmissions() {
        System.out.println(
                "Students admitted so far: "
                + admissionCount);
    }
}

public class SRMStudent {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SrmStudent[] students =
                new SrmStudent[3];

        for (int i = 0; i < 3; i++) {

            System.out.print(
                    "Enter student " + (i + 1)
                    + " name: ");

            String name = sc.nextLine();

            students[i] =
                    new SrmStudent(name);
        }

        System.out.println("\nID Cards:");

        for (SrmStudent student : students) {
            student.printIdCard();
        }

        SrmStudent.printTotalAdmissions();

        sc.close();
    }
}