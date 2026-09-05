import java.util.*;

class BusRoute {

    String routeCode;
    String routeName;
    int priority;

    BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    int compareTo(BusRoute other) {

        if (this.priority != other.priority)
            return this.priority - other.priority;

        return this.routeCode.compareToIgnoreCase(other.routeCode);
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {

        for (int i = 0; i < routes.length - 1; i++) {

            for (int j = 0; j < routes.length - 1 - i; j++) {

                if (routes[j].compareTo(routes[j + 1]) > 0) {

                    BusRoute temp = routes[j];
                    routes[j] = routes[j + 1];
                    routes[j + 1] = temp;
                }
            }
        }

        return routes;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of routes: ");
        int n = sc.nextInt();
        sc.nextLine();

        BusRoute[] routes = new BusRoute[n];

        for (int i = 0; i < n; i++) {

            System.out.println("\nRoute " + (i + 1));

            System.out.print("Enter route code: ");
            String code = sc.nextLine();

            System.out.print("Enter route name: ");
            String name = sc.nextLine();

            System.out.print("Enter priority: ");
            int priority = sc.nextInt();
            sc.nextLine();

            routes[i] = new BusRoute(code, name, priority);
        }

        rankRoutes(routes);

        System.out.println("\nRanked Routes:");

        for (int i = 0; i < n; i++) {
            System.out.println(
                routes[i].routeCode + " " +
                routes[i].routeName + " " +
                routes[i].priority
            );
        }

        sc.close();
    }
}
