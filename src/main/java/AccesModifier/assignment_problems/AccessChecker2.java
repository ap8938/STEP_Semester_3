package main.java.AccesModifier.assignment_problems;

import java.util.Scanner;

public class AccessChecker2 {

    static String classifyAccess(
        String fieldModifier,
        String accessorContext) {

        switch (fieldModifier) {

            case "private":
                if (accessorContext.equals("SAME_CLASS"))
                    return "ALLOWED";
                return "DENIED";

            case "default":
                if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE"))
                    return "ALLOWED";
                return "DENIED";

            case "protected":

                if (accessorContext.equals("SAME_CLASS") ||
                    accessorContext.equals("SAME_PACKAGE") ||
                    accessorContext.equals(
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                    return "ALLOWED";

                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    static String describeContext(String context) {

        String[] words = context.split("_");

        String result = "";

        for (int i = 0; i < words.length; i++) {

            result += words[i].substring(0, 1).toUpperCase()
                   + words[i].substring(1).toLowerCase();

            if (i < words.length - 1)
                result += " ";
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Choose modifier:");
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

        System.out.println("\nChoose context:");
        System.out.println("1. SAME_CLASS");
        System.out.println("2. SAME_PACKAGE");
        System.out.println("3. DIFFERENT_PACKAGE");
        System.out.println("4. SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE");
        System.out.println("5. SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE");

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
            case 4:
                context =
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE";
                break;
            case 5:
                context =
                    "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE";
                break;
            default:
                System.out.println("Invalid choice");
                return;
        }

        System.out.println(
            "Access: " +
            classifyAccess(modifier, context)
        );

        System.out.println(
            "Context: " +
            describeContext(context)
        );
    }
}

//EXIT
