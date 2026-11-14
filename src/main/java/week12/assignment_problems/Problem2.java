
public class Problem2 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node reverseInGroups(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node groupPrev = dummy;

        while (true) {
            Node kth = groupPrev;

            // Check whether k nodes are available
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }

            // Fewer than k nodes remain
            if (kth == null) {
                break;
            }

            Node groupNext = kth.next;

            // Reverse the current group
            Node prev = groupNext;
            Node current = groupPrev.next;

            while (current != groupNext) {
                Node nextNode = current.next;
                current.next = prev;
                prev = current;
                current = nextNode;
            }

            // Connect the reversed group
            Node oldGroupHead = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = oldGroupHead;
        }

        return dummy.next;
    }

    public static void printList(Node head) {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);

        int k = 3;

        head = reverseInGroups(head, k);

        printList(head);
    }
}
