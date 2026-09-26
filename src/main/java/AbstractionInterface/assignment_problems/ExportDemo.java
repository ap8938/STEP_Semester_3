import java.util.Scanner;

interface Exportable {

    String exportData();
}

class ExportCounter {

    private static int totalExports = 0;

    public static void increase() {

        totalExports++;
    }

    public static int getTotalExports() {

        return totalExports;
    }
}

class ReportGenerator implements Exportable {

    private String reportName;

    public ReportGenerator(String reportName) {

        this.reportName = reportName;
    }

    @Override
    public String exportData() {

        ExportCounter.increase();

        return "Exported report: "
                + reportName;
    }
}

class UserProfile implements Exportable {

    private String username;

    public UserProfile(String username) {

        this.username = username;
    }

    @Override
    public String exportData() {

        ExportCounter.increase();

        return "Exported profile: "
                + username;
    }
}

public class ExportDemo {

    static void exportAll(
            Exportable[] items) {

        for (int i = 0;
             i < items.length;
             i++) {

            System.out.println(
                    items[i].exportData());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print(
                "Enter report name: ");

        String reportName = sc.nextLine();

        System.out.print(
                "Enter username: ");

        String username = sc.nextLine();

        ReportGenerator report =
                new ReportGenerator(reportName);

        UserProfile profile =
                new UserProfile(username);

        System.out.println();

        // Interface references
        Exportable ref1 = report;
        Exportable ref2 = profile;

        System.out.println(
                ref1.exportData());

        System.out.println(
                ref2.exportData());

        System.out.println();

        Exportable[] items = {
                ref1,
                ref2
        };

        exportAll(items);

        System.out.println();

        System.out.println(
                "Total exports: "
                + ExportCounter.getTotalExports());

        sc.close();
    }
}
