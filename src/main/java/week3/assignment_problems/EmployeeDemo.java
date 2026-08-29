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

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(int empId, String empName, double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        if (getSalary() < stipendCap) {
            return getSalary();
        } else {
            return stipendCap;
        }
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plain employee ID: ");
        int id1 = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter plain employee name: ");
        String name1 = sc.nextLine();

        System.out.print("Enter plain employee salary: ");
        double salary1 = sc.nextDouble();

        System.out.print("Enter manager employee ID: ");
        int id2 = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter manager employee name: ");
        String name2 = sc.nextLine();

        System.out.print("Enter manager salary: ");
        double salary2 = sc.nextDouble();

        System.out.print("Enter manager team bonus: ");
        double bonus = sc.nextDouble();

        System.out.print("Enter intern employee ID: ");
        int id3 = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter intern employee name: ");
        String name3 = sc.nextLine();

        System.out.print("Enter intern salary: ");
        double salary3 = sc.nextDouble();

        System.out.print("Enter intern stipend cap: ");
        double cap = sc.nextDouble();

        Employee[] employees = new Employee[3];

        employees[0] = new Employee(id1, name1, salary1);
        employees[1] = new ManagerEmployee(id2, name2, salary2, bonus);
        employees[2] = new InternEmployee(id3, name3, salary3, cap);

        System.out.println("\nEmployee Pay:");

        for (Employee employee : employees) {

            if (employee instanceof ManagerEmployee) {
                ManagerEmployee manager = (ManagerEmployee) employee;

                System.out.println("Manager effective pay: Rs "
                        + manager.effectiveSalary());

            } else if (employee instanceof InternEmployee) {
                InternEmployee intern = (InternEmployee) employee;

                System.out.println("Intern effective pay: Rs "
                        + intern.effectiveSalary());

            } else {
                System.out.println("Plain employee pay: Rs "
                        + employee.getSalary());
            }
        }

        sc.close();
    }
}
