package main.java.AccesModifier.assignment_problems;

import java.util.Scanner;

class LibraryMember {

    private String membershipId;
    String branchCode;              // default
    protected double finesOwed;
    public String displayName;

    LibraryMember(String membershipId, String branchCode,
                  double finesOwed, String displayName) {

        if (membershipId == null ||
            membershipId.trim().length() < 4) {

            throw new IllegalArgumentException(
                "Invalid membership ID"
            );
        }

        this.membershipId = membershipId;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

public class AccessChecker {

    static String classifyAccess(
        String fieldModifier,
        String accessorContext) {

        switch (fieldModifier) {

            case "private":
                if (accessorContext.equals("SAME_CLASS"))
                    return "ALLOWED";
                else
                    return "DENIED";

            case "default":
                if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                    return "ALLOWED";
                else
                    return "DENIED";

            case "protected":
                if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                    return "ALLOWED";
                else
                    return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    static String summarizeByModifier(String[][] attempts) {

        int privateAllowed = 0;
        int privateDenied = 0;

        int defaultAllowed = 0;
        int defaultDenied = 0;

        int protectedAllowed = 0;
        int protectedDenied = 0;

        int publicAllowed = 0;
        int publicDenied = 0;

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result = classifyAccess(modifier, context);

            if (modifier.equals("private")) {
                if (result.equals("ALLOWED"))
                    privateAllowed++;
                else
                    privateDenied++;
            }

            else if (modifier.equals("default")) {
                if (result.equals("ALLOWED"))
                    defaultAllowed++;
                else
                    defaultDenied++;
            }

            else if (modifier.equals("protected")) {
                if (result.equals("ALLOWED"))
                    protectedAllowed++;
                else
                    protectedDenied++;
            }

            else if (modifier.equals("public")) {
                if (result.equals("ALLOWED"))
                    publicAllowed++;
                else
                    publicDenied++;
            }
        }

        return "private: " + privateAllowed + " allowed / "
             + privateDenied + " denied | "
             + "default: " + defaultAllowed + " allowed / "
             + defaultDenied + " denied | "
             + "protected: " + protectedAllowed + " allowed / "
             + protectedDenied + " denied | "
             + "public: " + publicAllowed + " allowed / "
             + publicDenied + " denied";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose field modifier:");
        System.out.println("1. private");
        System.out.println("2. default");
        System.out.println("3. protected");
        System.out.println("4. public");

        int choice = sc.nextInt();
        sc.nextLine();

        String modifier;

        switch (choice) {
            case 1:
                modifier = "private";
                break;
            case 2:
                modifier = "default";
                break;
            case 3:
                modifier = "protected";
                break;
            case 4:
                modifier = "public";
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        System.out.println("Enter context:");
        System.out.println("1. SAME_CLASS");
        System.out.println("2. SAME_PACKAGE");
        System.out.println("3. DIFFERENT_PACKAGE");

        int contextChoice = sc.nextInt();

        String context;

        switch (contextChoice) {
            case 1:
                context = "SAME_CLASS";
                break;
            case 2:
                context = "SAME_PACKAGE";
                break;
            case 3:
                context = "DIFFERENT_PACKAGE";
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        System.out.println(
            classifyAccess(modifier, context)
        );

        System.out.print(
            "Enter membership ID to test: "
        );
        sc.nextLine();

        String id = sc.nextLine();

        try {
            LibraryMember member =
                new LibraryMember(
                    id, "BR1", 0, "Priya Nair"
                );

            System.out.println("LibraryMember created.");
        }
        catch (Exception e) {
            System.out.println("construction rejected");
        }
    }
}
