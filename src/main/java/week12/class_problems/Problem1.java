
import java.util.Scanner;

public class Problem1 {

    static class LineItem {
        String productName;
        int quantity;
        double unitPrice;

        LineItem(String productName, int quantity, double unitPrice) {
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
        }

        double total() {
            return quantity * unitPrice;
        }
    }

    LineItem[] items;
    int count;

    Problem1(int size) {
        items = new LineItem[size];
        count = 0;
    }

    void addItem(String name, int quantity, double price) {
        items[count] = new LineItem(name, quantity, price);
        count++;
    }

    class Cursor {
        int position = 0;

        boolean hasNext() {
            return position < count;
        }

        LineItem next() {
            return items[position++];
        }

        void reset() {
            position = 0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Problem1 order = new Problem1(n);

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int quantity = sc.nextInt();
            double price = sc.nextDouble();

            order.addItem(name, quantity, price);
        }

        Cursor cursor = order.new Cursor();
        double grandTotal = 0;

        while (cursor.hasNext()) {
            LineItem item = cursor.next();

            System.out.println(item.productName + " " + item.total());
            grandTotal += item.total();
        }

        System.out.println("Total " + grandTotal);

        System.out.println("Cursor: inner (reads the order's array)");
        System.out.println("LineItem: static nested (needs no order object)");

        sc.close();
    }
}
