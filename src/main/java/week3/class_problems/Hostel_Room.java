import java.util.Scanner;

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
                    + " allotted to room " + roomNo);
        } else {
            System.out.println("Room is full. "
                    + studentName
                    + " added to waiting list.");
        }
    }
}

public class Hostel_Room {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter room number: ");
        String roomNo = sc.nextLine();

        System.out.print("Enter number of beds: ");
        int beds = sc.nextInt();

        sc.nextLine();

        HostelRoom room =
                new HostelRoom(roomNo, beds);

        HostelRoom sameRoom = room;

        System.out.print("Enter first student name: ");
        String student1 = sc.nextLine();

        sameRoom.allot(student1);

        System.out.println("Occupied through room: "
                + room.occupied);

        HostelRoom separate =
                new HostelRoom(roomNo, beds);

        System.out.println("sameRoom == room: "
                + (sameRoom == room));

        System.out.println("separate == room: "
                + (separate == room));

        for (int i = 1; i < beds + 2; i++) {

            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            room.allot(name);
        }

        sc.close();
    }
}