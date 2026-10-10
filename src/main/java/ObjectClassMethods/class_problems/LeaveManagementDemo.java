
import java.util.Scanner;

abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract int getLeaveLimit();
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    int getLeaveLimit() {
        return 24;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    int getLeaveLimit() {
        return 12;
    }
}

class LeaveRequest {
    Employee employee;
    String startDate;
    String endDate;
    String status = "Pending";

    LeaveRequest(Employee employee, String start, String end) {
        this.employee = employee;
        startDate = start;
        endDate = end;
    }

    void review(String decision) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change status: request already reviewed");
        } else if (decision.equalsIgnoreCase("approve")
                || decision.equalsIgnoreCase("reject")) {
            status = decision.equalsIgnoreCase("approve")
                    ? "Approved" : "Rejected";
            System.out.println("Leave request for " + employee.name
                    + " " + status);
        } else {
            System.out.println("Invalid decision");
        }
    }
}

public class LeaveManagementDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter employee name: ");
        String name = sc.nextLine();

        System.out.print("Employee type (1 Full-time, 2 Part-time): ");
        int type = sc.nextInt();
        sc.nextLine();

        Employee employee;
        if (type == 1) {
            employee = new FullTimeEmployee(name);
        } else {
            employee = new PartTimeEmployee(name);
        }

        System.out.print("Enter leave start date: ");
        String start = sc.nextLine();

        System.out.print("Enter leave end date: ");
        String end = sc.nextLine();

        LeaveRequest request = new LeaveRequest(employee, start, end);

        System.out.println("Leave request submitted by " + name
                + " for " + start + " to " + end
                + ". Status: " + request.status);

        System.out.print("Manager decision (approve/reject): ");
        request.review(sc.nextLine());

        System.out.println("Current status: " + request.status);

        System.out.print("Try another decision: ");
        request.review(sc.nextLine());

        sc.close();
    }
}
