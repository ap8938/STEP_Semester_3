
public class Problem5 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // Reverse a linked list
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;

            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    public static boolean isPalindrome(Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        // Find the end of the first half
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse the second half
        Node secondHalf = reverse(slow.next);

        Node first = head;
        Node second = secondHalf;

        boolean palindrome = true;

        // Compare both halves
        while (second != null) {
            if (first.data != second.data) {
                palindrome = false;
                break;
            }

            first = first.next;
            second = second.next;
        }

        // Restore the original list
        slow.next = reverse(secondHalf);

        return palindrome;
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
        Node head = new Node(4);
        head.next = new Node(7);
        head.next.next = new Node(9);
        head.next.next.next = new Node(7);
        head.next.next.next.next = new Node(4);

        boolean result = isPalindrome(head);

        System.out.println("Palindrome: " + result);
        System.out.print("List after check: ");
        printList(head);
    }
}
