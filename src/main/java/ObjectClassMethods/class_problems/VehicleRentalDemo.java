
import java.util.Scanner;

abstract class Vehicle {
    String name;
    boolean available;

    Vehicle(String name) {
        this.name = name;
        available = true;
    }

    abstract double calculateCharge(int days);
}

class StandardCar extends Vehicle {
    StandardCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 50;
    }
}

class LuxuryCar extends Vehicle {
    LuxuryCar(String name) {
        super(name);
    }

    double calculateCharge(int days) {
        return days * 100;
    }
}

class RentalService {
    void rent(Vehicle vehicle, int days) {
        if (!vehicle.available) {
            System.out.println(vehicle.name + " is unavailable");
            return;
        }

        vehicle.available = false;
        System.out.printf("%s rented for %d days. Total charge: $%.2f%n",
                vehicle.name, days, vehicle.calculateCharge(days));
    }

    void returnVehicle(Vehicle vehicle) {
        vehicle.available = true;
        System.out.println(vehicle.name + " returned. Now available.");
    }
}

public class VehicleRentalDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StandardCar standard = new StandardCar("Standard Car B");
        LuxuryCar luxury = new LuxuryCar("Luxury Car A");
        RentalService service = new RentalService();

        System.out.println("1. Standard Car");
        System.out.println("2. Luxury Car");
        System.out.print("Choose vehicle: ");
        int choice = sc.nextInt();

        System.out.print("Enter rental days: ");
        int days = sc.nextInt();

        if (days <= 0) {
            System.out.println("Days must be positive");
        } else if (choice == 1) {
            service.rent(standard, days);
        } else if (choice == 2) {
            service.rent(luxury, days);
        } else {
            System.out.println("Invalid choice");
        }

        System.out.print("Return which vehicle? (1 Standard, 2 Luxury): ");
        int returned = sc.nextInt();

        if (returned == 1) {
            service.returnVehicle(standard);
        } else if (returned == 2) {
            service.returnVehicle(luxury);
        } else {
            System.out.println("Invalid choice");
        }

        sc.close();
    }
}
