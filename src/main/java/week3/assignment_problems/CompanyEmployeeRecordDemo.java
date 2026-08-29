import java.util.Scanner;

class Employee {
    private int empId;
    private String empName;
    private double salary;

    Employee(int empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    private double teamBonus;

    ManagerEmployee(int empId, String empName, double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity, int occupiedCount) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = occupiedCount;
    }

    void allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            System.out.println(vehicleNo + " allotted to slot " + slotNo);
        }
    }
}

class CompanyEmployeeRecord {

    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee, ParkingSlot slot) {

        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = slot;

        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee) {
            ManagerEmployee manager = (ManagerEmployee) employee;
            pay = manager.effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        if (slot != null) {
            return name + " | Pay: Rs " + pay
                    + " | Slot: " + slot.slotNo;
        } else {
            return name + " | Pay: Rs " + pay
                    + " | Slot: no parking assigned";
        }
    }
}

public class CompanyEmployeeRecordDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first employee name: ");
        String name1 = sc.nextLine();

        System.out.print("Enter first employee ID: ");
        String id1 = sc.nextLine();

        System.out.print("Enter first employee salary: ");
        double salary1 = sc.nextDouble();

        System.out.print("Enter first employee team bonus: ");
        double bonus1 = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter second employee name: ");
        String name2 = sc.nextLine();

        System.out.print("Enter second employee ID: ");
        String id2 = sc.nextLine();

        System.out.print("Enter second employee salary: ");
        double salary2 = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter third employee name: ");
        String name3 = sc.nextLine();

        System.out.print("Enter third employee ID: ");
        String id3 = sc.nextLine();

        System.out.print("Enter third employee salary: ");
        double salary3 = sc.nextDouble();

        ManagerEmployee manager =
                new ManagerEmployee(1, name1, salary1, bonus1);

        Employee employee2 =
                new Employee(2, name2, salary2);

        Employee employee3 =
                new Employee(3, name3, salary3);

        ParkingSlot slot1 =
                new ParkingSlot("A1", 1, 0);

        ParkingSlot slot2 =
                new ParkingSlot("A2", 1, 0);

        slot1.allot("Vehicle1");
        slot2.allot("Vehicle2");

        CompanyEmployeeRecord record1 =
                new CompanyEmployeeRecord(name1, id1, manager, slot1);

        CompanyEmployeeRecord record2 =
                new CompanyEmployeeRecord(name2, id2, employee2, slot2);

        CompanyEmployeeRecord record3 =
                new CompanyEmployeeRecord(name3, id3, employee3, null);

        System.out.println("\nEmployee Profiles:");

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        System.out.println("Total records: "
                + CompanyEmployeeRecord.totalRecords);

        sc.close();
    }
}
