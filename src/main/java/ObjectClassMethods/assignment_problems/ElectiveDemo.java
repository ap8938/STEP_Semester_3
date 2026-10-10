
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Student {
    String name;
    String type;
    int credits;

    Student(String name, String type, int credits) {
        this.name = name;
        this.type = type;
        this.credits = credits;
    }

    int limit() {
        if (type.equalsIgnoreCase("Honors")) return 28;
        if (type.equalsIgnoreCase("Exchange")) return 20;
        return 24;
    }
}

class Elective {
    String name;
    int credits;
    int capacity;
    ArrayList<Student> enrolled = new ArrayList<>();
    Queue<Student> waitlist = new LinkedList<>();

    Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    boolean known(Student s) {
        if (enrolled.contains(s) || waitlist.contains(s)) {
            System.out.println(s.name + " already registered");
            return true;
        }
        return false;
    }

    boolean eligible(Student s) {
        if (s.credits + credits > s.limit()) {
            System.out.println("Enrollment failed: " + s.name
                    + " would exceed the " + s.type + " credit limit");
            return false;
        }
        return true;
    }

    void enroll(Student s) {
        if (known(s) || !eligible(s)) return;

        if (enrolled.size() < capacity) {
            enrolled.add(s);
            s.credits += credits;
            System.out.println(s.name + " enrolled in " + name
                    + " (credits: " + s.credits + "/" + s.limit() + ")");
        } else {
            waitlist.add(s);
            System.out.println(name + " is full. " + s.name
                    + " added to waitlist (position " + waitlist.size() + ")");
        }
    }

    void drop(Student s) {
        if (!enrolled.remove(s)) {
            System.out.println(s.name + " is not enrolled");
            return;
        }

        s.credits -= credits;
        System.out.println(s.name + " dropped " + name
                + " (credits: " + s.credits + "/" + s.limit() + ")");

        int size = waitlist.size();
        for (int i = 0; i < size; i++) {
            Student next = waitlist.remove();

            if (eligible(next)) {
                enrolled.add(next);
                next.credits += credits;
                System.out.println(next.name + " promoted from waitlist and enrolled");
                break;
            } else {
                waitlist.add(next);
            }
        }
    }
}

public class ElectiveDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Elective elective = new Elective("Cloud Computing", 4, 2);

        for (int i = 0; i < 4; i++) {
            System.out.print("Student name: ");
            String name = sc.next();

            System.out.print("Type (Regular, Honors, Exchange): ");
            String type = sc.next();

            System.out.print("Current semester credits: ");
            int credits = sc.nextInt();

            elective.enroll(new Student(name, type, credits));
        }

        System.out.print("Name of enrolled student dropping: ");
        String dropName = sc.next();

        for (Student s : new ArrayList<>(elective.enrolled)) {
            if (s.name.equalsIgnoreCase(dropName)) {
                elective.drop(s);
                break;
            }
        }

        sc.close();
    }
}
