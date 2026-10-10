
import java.util.Scanner;

interface Reservable {
    void reserve(String memberId);
}

interface Downloadable {
    void download();
}

abstract class Resource {
    String id;

    Resource(String id) {
        this.id = id;
    }
}

class Book extends Resource implements Reservable {
    Book(String id) {
        super(id);
    }

    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }
}

class EBook extends Resource implements Reservable, Downloadable {
    EBook(String id) {
        super(id);
    }

    public void reserve(String memberId) {
        System.out.println(id + " reserved for " + memberId);
    }

    public void download() {
        System.out.println(id + " downloaded");
    }
}

public class LibraryPlatform {

    static int findResource(Resource[] resources, int count, String id) {
        int low = 0;
        int high = count - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int result = resources[mid].id.compareTo(id);

            if (result == 0) {
                return mid;
            } else if (result < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    static int addResource(Resource[] resources, int count,
                           Resource resource) {
        if (findResource(resources, count, resource.id) != -1) {
            System.out.println("duplicate rejected");
            return count;
        }

        int i = count - 1;

        while (i >= 0 &&
                resources[i].id.compareTo(resource.id) > 0) {
            resources[i + 1] = resources[i];
            i--;
        }

        resources[i + 1] = resource;
        System.out.println(resource.id + " added");

        return count + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Resource[] resources = new Resource[100];
        int count = 0;

        System.out.print("Enter number of commands: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter command: ");
            String command = sc.next().toUpperCase();

            if (command.equals("ADD")) {
                String type = sc.next();
                String id = sc.next();

                Resource resource;

                if (type.equalsIgnoreCase("Book")) {
                    resource = new Book(id);
                } else {
                    resource = new EBook(id);
                }

                count = addResource(resources, count, resource);

            } else if (command.equals("RESERVE")) {
                String id = sc.next();
                String memberId = sc.next();

                int index = findResource(resources, count, id);

                if (index == -1) {
                    System.out.println("Resource not found");
                } else if (resources[index] instanceof Reservable) {
                    ((Reservable) resources[index]).reserve(memberId);
                } else {
                    System.out.println(id + " rejected: reserve unsupported");
                }

            } else if (command.equals("DOWNLOAD")) {
                String id = sc.next();

                int index = findResource(resources, count, id);

                if (index == -1) {
                    System.out.println("Resource not found");
                } else if (resources[index] instanceof Downloadable) {
                    ((Downloadable) resources[index]).download();
                } else {
                    System.out.println(id + " rejected: download unsupported");
                }

            } else if (command.equals("FIND")) {
                String id = sc.next();

                int index = findResource(resources, count, id);

                if (index == -1) {
                    System.out.println(id + " not found");
                } else {
                    System.out.println(id + " found at index " + index);
                }
            }
        }

        sc.close();
    }
}
