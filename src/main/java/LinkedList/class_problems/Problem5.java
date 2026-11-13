public class Problem5 {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            next = null;
        }
    }

    static Node reorderList(Node head){
        if (head == null || head.next == null)
            return head;

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        Node second = slow.next;
        slow.next = null;

        Node prev = null;
        Node current = second;

        while (current != null){
            Node newNode = current.next;
            current.next = prev;
            prev = current;
            current = newNode;
        }

        second = prev;

        Node first = head;

        while (second != null){
            Node temp1 = first.next;
            Node temp2 = second.next;

            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
        }

        return head;
    }

    static void display(Node head){
        while (head != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
    }

    public static void main(String[] args) {
        // Write your code here.
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        System.out.print("Original List: ");
        display(head);

        head = reorderList(head);

        System.out.print("\nReordered List: ");
        display(head);
    }
}