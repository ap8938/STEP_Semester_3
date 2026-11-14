
public class Problem4 {

    static class Node {
        String title;
        int day;
        Node next;

        Node(String title, int day) {
            this.title = title;
            this.day = day;
        }
    }

    public static Node sortByDueDay(Node head) {

        // Base case: empty or one-node list
        if (head == null || head.next == null) {
            return head;
        }

        // Find the middle of the list
        Node slow = head;
        Node fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Divide the list into two halves
        Node secondHalf = slow.next;
        slow.next = null;

        // Sort both halves
        Node first = sortByDueDay(head);
        Node second = sortByDueDay(secondHalf);

        // Merge the sorted halves
        return merge(first, second);
    }

    public static Node merge(Node first, Node second) {
        Node dummy = new Node("", 0);
        Node tail = dummy;

        while (first != null && second != null) {

            // <= preserves the original order on equal days
            if (first.day <= second.day) {
                tail.next = first;
                first = first.next;
            } else {
                tail.next = second;
                second = second.next;
            }

            tail = tail.next;
        }

        // Attach remaining nodes
        if (first != null) {
            tail.next = first;
        } else {
            tail.next = second;
        }

        return dummy.next;
    }

    public static void printList(Node head) {
        Node current = head;

        while (current != null) {
            System.out.print(
                current.title + ":" + current.day
            );

            current = current.next;

            if (current != null) {
                System.out.print(" -> ");
            }
        }

        System.out.println(" -> null");
    }

    public static void main(String[] args) {
        Node head = new Node("Java", 15);
        head.next = new Node("DSA", 3);
        head.next.next = new Node("OS", 9);
        head.next.next.next = new Node("DBMS", 3);
        head.next.next.next.next = new Node("CN", 12);
        head.next.next.next.next.next = new Node("AI", 1);

        head = sortByDueDay(head);

        printList(head);
    }
}
