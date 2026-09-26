import java.util.Scanner;

abstract class Shape {

    private static int counter = 0;

    private final String shapeId;

    public Shape() {

        counter++;

        shapeId = "SH-" + counter;
    }

    public abstract double calculateArea();

    public abstract void scaleShape(
            double xFactor,
            double yFactor);

    // One-argument overload
    public void scale(double factor) {

        scaleShape(factor, factor);
    }

    // Two-argument overload
    public void scale(double xFactor,
                      double yFactor) {

        scaleShape(xFactor, yFactor);
    }

    public String getShapeId() {

        return shapeId;
    }
}

class CircleShape extends Shape {

    private double radius;

    public CircleShape(double radius) {

        super();

        this.radius = radius;
    }

    @Override
    public double calculateArea() {

        return Math.PI * radius * radius;
    }

    @Override
    public void scaleShape(double xFactor,
                            double yFactor) {

        // To keep a circle a circle,
        // use the average of the two factors.
        double factor =
                (xFactor + yFactor) / 2;

        radius = radius * factor;
    }
}

class SquareShape extends Shape {

    private double side;

    public SquareShape(double side) {

        super();

        this.side = side;
    }

    @Override
    public double calculateArea() {

        return side * side;
    }

    @Override
    public void scaleShape(double xFactor,
                            double yFactor) {

        // For a square, equal scaling
        // keeps it a square.
        double factor =
                (xFactor + yFactor) / 2;

        side = side * factor;
    }
}

public class ShapeDemo {

    static void printArea(Shape s) {

        System.out.println(
                "Area: " + s.calculateArea());
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1. Circle");
        System.out.println("2. Square");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        Shape shape;

        if (choice == 1) {

            System.out.print("Enter radius: ");
            double radius = sc.nextDouble();

            shape = new CircleShape(radius);

        } else {

            System.out.print("Enter side: ");
            double side = sc.nextDouble();

            shape = new SquareShape(side);
        }

        System.out.println(
                "Shape ID: "
                + shape.getShapeId());

        printArea(shape);

        System.out.println();
        System.out.println("Scaling options:");
        System.out.println("1. Equal scaling");
        System.out.println("2. Uneven scaling");

        System.out.print("Enter choice: ");
        int scaleChoice = sc.nextInt();

        if (scaleChoice == 1) {

            System.out.print(
                    "Enter scale factor: ");

            double factor = sc.nextDouble();

            shape.scale(factor);

        } else {

            System.out.print(
                    "Enter X scale factor: ");

            double xFactor = sc.nextDouble();

            System.out.print(
                    "Enter Y scale factor: ");

            double yFactor = sc.nextDouble();

            shape.scale(xFactor, yFactor);
        }

        System.out.println(
                "Area after scaling:");

        printArea(shape);

        sc.close();
    }
}
