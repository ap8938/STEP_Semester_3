import java.util.Scanner;

abstract class LibraryItem {

    private static int counter = 1000;

    private final String itemId;

    public LibraryItem() {

        counter++;
        itemId = "LIB-" + counter;
    }

    public abstract int getLoanPeriodDays();

    public String getItemId() {

        return itemId;
    }
}

interface Renewable {

    String renew();
}

interface Reservable {

    String reserve();
}

class Textbook extends LibraryItem
        implements Renewable, Reservable {

    private String title;

    public Textbook(String title) {

        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {

        return 14;
    }

    @Override
    public String renew() {

        return title + " renewed";
    }

    @Override
    public String reserve() {

        return title + " reserved";
    }
}

class Magazine extends LibraryItem
        implements Renewable {

    private String title;

    public Magazine(String title) {

        this.title = title;
    }

    @Override
    public int getLoanPeriodDays() {

        return 7;
    }

    @Override
    public String renew() {

        return title + " renewed";
    }
}

class DigitalPass implements Renewable {

    private String resourceName;

    public DigitalPass(String resourceName) {

        this.resourceName = resourceName;
    }

    @Override
    public String renew() {

        return resourceName + " renewed";
    }
}

public class LibraryDemo {

    static void processCheckouts(
            LibraryItem[] items) {

        for (int i = 0; i < items.length; i++) {

            System.out.println(
                    items[i].getLoanPeriodDays()
                    + " days");
        }
    }

    static String reserveIfSupported(Object o) {

        if (o instanceof Reservable) {

            Reservable r =
                    (Reservable) o;

            return r.reserve();
        }

        return "Reservation not supported";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter textbook title: ");
        String textbookTitle = sc.nextLine();

        System.out.print("Enter magazine title: ");
        String magazineTitle = sc.nextLine();

        System.out.print("Enter digital resource name: ");
        String resourceName = sc.nextLine();

        Textbook textbook =
                new Textbook(textbookTitle);

        Magazine magazine =
                new Magazine(magazineTitle);

        DigitalPass digitalPass =
                new DigitalPass(resourceName);

        System.out.println();
        System.out.println("----- TEXTBOOK -----");

        System.out.println(
                "Loan period: "
                + textbook.getLoanPeriodDays()
                + " days");

        System.out.println(
                textbook.renew());

        System.out.println(
                textbook.reserve());

        System.out.println();
        System.out.println("----- MAGAZINE -----");

        System.out.println(
                "Loan period: "
                + magazine.getLoanPeriodDays()
                + " days");

        System.out.println(
                magazine.renew());

        System.out.println(
                reserveIfSupported(magazine));

        System.out.println();
        System.out.println("----- DIGITAL PASS -----");

        System.out.println(
                digitalPass.renew());

        System.out.println(
                reserveIfSupported(digitalPass));

        // Upcasting
        LibraryItem ref = textbook;

        System.out.println();
        System.out.println("Through LibraryItem reference:");

        System.out.println(
                reserveIfSupported(ref));

        System.out.println();
        System.out.println("----- ALL LIBRARY ITEMS -----");

        LibraryItem[] items = {
            textbook,
            magazine
        };

        processCheckouts(items);

        sc.close();
    }
}
