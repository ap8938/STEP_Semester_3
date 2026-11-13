public class Problem2 {

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            next = null;
        }
    }

    static boolean isPalindrome(Node head){
        if(head == null || head.next == null)
            return true;
        Node slow = head;
        Node fast = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        Node prev = null;
        Node current = slow.next;

        while(current != null){
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        Node first = head;
        Node second = prev;

        while (second != null){
            if (first.data != second.data)
                return false;
            first = first.next;
            second = second.next;
        }
        return true;    
    }

    public static void main(String[] args) {
        // Write your code here.
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);

        if(isPalindrome(head))
            System.out.println("Palindrome");
        else
            System.out.println("Not a Palindrome");
    }
}
