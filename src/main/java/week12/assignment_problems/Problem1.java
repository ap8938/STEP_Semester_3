
public class Problem1 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static String detectLoop(Node head) {
        Node slow = head;
        Node fast = head;

        // Detect whether a loop exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                break;
            }
        }

        // No loop found
        if (fast == null || fast.next == null) {
            return "Loop: no";
        }

        // Find the starting node of the loop
        Node start = head;

        while (start != slow) {
            start = start.next;
            slow = slow.next;
        }

        // Find the loop length
        int length = 1;
        Node current = start.next;

        while (current != start) {
            length++;
            current = current.next;
        }

        return "Loop: yes, starts at stop "
                + start.data + ", length " + length;
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);

        // Last node points to the node at index 2
        head.next.next.next.next.next = head.next.next;

        System.out.println(detectLoop(head));
    }
}
