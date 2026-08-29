import java.util.Scanner;

class BrokenLibraryMember {
    static String name;
    static String memberId;
    static int booksIssued;

    BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class LibraryMember {
    String name;
    String memberId;
    int booksIssued;

    static String libraryName = "Central Library";
    static int memberCount = 0;

    LibraryMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;

        memberCount++;
        memberId = "LM-" + (1000 + memberCount);
    }

    void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }
}

public class LibraryMemberDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("BROKEN VERSION:");

        BrokenLibraryMember member1 =
                new BrokenLibraryMember("Aditi", "LM-1001", 2);

        BrokenLibraryMember member2 =
                new BrokenLibraryMember("Rohan", "LM-1002", 3);

        System.out.println(member1.name);
        System.out.println(member2.name);

        /*
         * The broken version uses static fields for name, memberId and booksIssued.
         * These fields belong to the class instead of individual objects.
         * Therefore, creating member2 overwrites the data stored by member1.
         *
         * name is wrong as static because every member has a different name.
         * memberId is wrong as static because every member needs a different ID.
         * booksIssued is wrong as static because each member can issue a different
         * number of books.
         */

        System.out.println("\nFIXED VERSION:");

        System.out.print("Enter first member name: ");
        String name1 = sc.nextLine();

        System.out.print("Enter books issued by first member: ");
        int books1 = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter second member name: ");
        String name2 = sc.nextLine();

        System.out.print("Enter books issued by second member: ");
        int books2 = sc.nextInt();

        LibraryMember fixedMember1 =
                new LibraryMember(name1, books1);

        LibraryMember fixedMember2 =
                new LibraryMember(name2, books2);

        fixedMember1.printMemberCard();
        fixedMember2.printMemberCard();

        LibraryMember.printTotalMembers();

        sc.close();
    }
}
