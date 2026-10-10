
import java.util.HashSet;
import java.util.Scanner;

class Customer {
    int id;
    String name;

    Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Customer)) {
            return false;
        }

        Customer other = (Customer) obj;

        return id == other.id && name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return 31 * id + name.hashCode();
    }
}

public class CustomerRegistry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashSet<Customer> customers = new HashSet<>();

        for (int i = 1; i <= 3; i++) {
            System.out.println("Customer " + i);

            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter name: ");
            String name = sc.nextLine();

            Customer c = new Customer(id, name);

            if (customers.add(c)) {
                System.out.println("Customer added");
            } else {
                System.out.println("Duplicate rejected");
            }
        }

        System.out.print("Enter ID to search: ");
        int searchId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter name to search: ");
        String searchName = sc.nextLine();

        Customer search = new Customer(searchId, searchName);

        System.out.println("Contains: " + customers.contains(search));
        System.out.println("Unique count: " + customers.size());

        sc.close();
    }
}
