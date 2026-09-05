package main.java.AccesModifier.class_problems;

import java.util.Scanner;

class AccessRuleEngine {

    static String classifyAccess(String modifier, String context) {

        if (modifier.equals("public"))
            return "ALLOWED";

        if (modifier.equals("private"))
            return context.equals("SAME_CLASS")
                   ? "ALLOWED" : "DENIED";

        if (modifier.equals("default"))
            return (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE"))
                   ? "ALLOWED" : "DENIED";

        if (modifier.equals("protected"))
            return (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE") ||
                    context.equals(
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                   ? "ALLOWED" : "DENIED";

        return "DENIED";
    }


    static String describeContext(String context) {

        String[] words = context.split("_");
        String result = "";

        for (String word : words) {
            result += word.substring(0, 1).toUpperCase()
                    + word.substring(1).toLowerCase()
                    + " ";
        }

        return result.trim();
    }
}


public class AccessRuleMod {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter modifier: ");
        String modifier = sc.nextLine();

        System.out.print("Enter context: ");
        String context = sc.nextLine();

        System.out.println(
            AccessRuleEngine.classifyAccess(modifier, context)
        );

        System.out.println(
            AccessRuleEngine.describeContext(context)
        );

        sc.close();
    }
}
