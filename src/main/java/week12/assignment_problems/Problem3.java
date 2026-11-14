
import java.util.HashMap;

public class Problem3 {

    static class Node {
        int key;
        String page;
        Node prev;
        Node next;

        Node(int key, String page) {
            this.key = key;
            this.page = page;
        }
    }

    static class RecentCache {
        int capacity;

        HashMap<Integer, Node> map;

        Node head;
        Node tail;

        int lastEvictedKey = -1;
        boolean wasUpdated = false;

        RecentCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();

            // Dummy nodes
            head = new Node(0, "");
            tail = new Node(0, "");

            head.next = tail;
            tail.prev = head;
        }

        // Remove a node from its current position
        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // Add a node just after the head
        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        // Move a node to the most recently used position
        void moveToFirst(Node node) {
            remove(node);
            addFirst(node);
        }

        public String get(int key) {
            if (!map.containsKey(key)) {
                return "-1";
            }

            Node node = map.get(key);

            // Mark this page as recently used
            moveToFirst(node);

            return node.page;
        }

        public void put(int key, String page) {
            lastEvictedKey = -1;
            wasUpdated = false;

            // If key already exists, update it
            if (map.containsKey(key)) {
                Node node = map.get(key);
                node.page = page;

                moveToFirst(node);
                wasUpdated = true;
                return;
            }

            // Remove least recently used page if full
            if (map.size() == capacity) {
                Node leastUsed = tail.prev;

                lastEvictedKey = leastUsed.key;

                remove(leastUsed);
                map.remove(leastUsed.key);
            }

            // Insert new page
            Node newNode = new Node(key, page);

            addFirst(newNode);
            map.put(key, newNode);
        }

        // Display cache from most to least recently used
        void display() {
            Node current = head.next;

            System.out.print("Cache (most recent first): ");

            while (current != tail) {
                System.out.print(
                    current.key + "=" + current.page
                );

                current = current.next;

                if (current != tail) {
                    System.out.print(", ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        RecentCache cache = new RecentCache(2);

        cache.put(1, "Home");
        System.out.println("put(1,Home)");

        cache.put(2, "Notes");
        System.out.println("put(2,Notes)");

        System.out.println("get(1) -> " + cache.get(1));

        cache.put(3, "Quiz");

        if (cache.lastEvictedKey != -1) {
            System.out.println(
                "put(3,Quiz) evicted " + cache.lastEvictedKey
            );
        }

        System.out.println("get(2) -> " + cache.get(2));

        cache.put(1, "Home2");

        if (cache.wasUpdated) {
            System.out.println("put(1,Home2) updated");
        }

        cache.put(4, "Mail");

        if (cache.lastEvictedKey != -1) {
            System.out.println(
                "put(4,Mail) evicted " + cache.lastEvictedKey
            );
        }

        System.out.println("get(3) -> " + cache.get(3));
        System.out.println("get(1) -> " + cache.get(1));

        cache.display();
    }
}
