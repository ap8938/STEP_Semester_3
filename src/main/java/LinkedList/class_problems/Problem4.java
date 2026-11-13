public class Problem4 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    static int getLength(Node head) {
        int count = 0;

        while (head != null) {
            count++;
            head = head.next;
        }

        return count;
    }

    static Node findIntersection(Node a, Node b) {

        int lenA = getLength(a);
        int lenB = getLength(b);

        // Find the difference in lengths
        int diff = Math.abs(lenA - lenB);

        // Move the pointer of the longer list
        if (lenA > lenB) {
            for (int i = 0; i < diff; i++)
                a = a.next;
        } else {
            for (int i = 0; i < diff; i++)
                b = b.next;
        }

        // Traverse both lists together
        while (a != null && b != null) {

            if (a == b)
                return a;

            a = a.next;
            b = b.next;
        }

        return null;
    }

    public static void main(String[] args) {

        // Common part of both lists
        Node common = new Node(8);
        common.next = new Node(4);
        common.next.next = new Node(5);

        // First list: 4 -> 1 -> 8 -> 4 -> 5
        Node a = new Node(4);
        a.next = new Node(1);
        a.next.next = common;

        // Second list: 5 -> 6 -> 1 -> 8 -> 4 -> 5
        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(1);
        b.next.next.next = common;

        Node result = findIntersection(a, b);

        if (result != null)
            System.out.println("Intersection node: " + result.data);
        else
            System.out.println("No intersection");
    }
}
