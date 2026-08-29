import java.util.Scanner;

class SrmStudent {
    String name;
    String regNo;
    int attendance;

    SrmStudent(String name, String regNo, int attendance) {
        this.name = name;
        this.regNo = regNo;
        this.attendance = attendance;
    }

    void addAttendanceUpdate(int newAttendance) {
        attendance = newAttendance;
    }

    boolean isEligible() {
        return attendance >= 75;
    }

    static double classAverage(SrmStudent[] students) {
        int sum = 0;

        for (SrmStudent student : students) {
            sum += student.attendance;
        }

        return (double) sum / students.length;
    }
}

public class SRM_Student {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SrmStudent[] students = new SrmStudent[5];

        for (int i = 0; i < 5; i++) {

            System.out.println("Enter student " + (i + 1) + " details:");

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Registration number: ");
            String regNo = sc.nextLine();

            System.out.print("Attendance: ");
            int attendance = sc.nextInt();

            sc.nextLine();

            students[i] =
                new SrmStudent(name, regNo, attendance);
        }

        System.out.println("\nStudent Details:");

        for (SrmStudent student : students) {

            if (student.isEligible()) {
                System.out.println(student.name + " - "
                        + student.attendance + "% - Eligible");
            } else {
                System.out.println(student.name + " - "
                        + student.attendance + "% - Detained");
            }
        }

        System.out.println("Class average: "
                + SrmStudent.classAverage(students) + "%");

        sc.close();
    }
}