package main.java.AccesModifier.class_problems;

import java.util.Scanner;

class AccessRuleEngine {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (fieldModifier.equals("public"))
            return "ALLOWED";

        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE"))
                return "ALLOWED";
            else
                return "DENIED";
        }

        return "DENIED";
    }
}


class PatientRecord {

    private String patientId;
    String wardCode;
    protected double vitalsScore;
    public String facilityName;

    public PatientRecord(String patientId, String wardCode,
                         double vitalsScore, String facilityName) {

        patientId = patientId.trim();

        if (patientId.length() < 4)
            throw new IllegalArgumentException("Invalid patient ID");

        this.patientId = patientId;
        this.wardCode = wardCode;
        this.vitalsScore = vitalsScore;
        this.facilityName = facilityName;
    }
}


public class AccessRule {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter field modifier: ");
        String modifier = sc.nextLine();

        System.out.print("Enter accessor context: ");
        String context = sc.nextLine();

        System.out.println(
            AccessRuleEngine.classifyAccess(modifier, context)
        );


        System.out.print("Enter patient ID: ");
        String id = sc.nextLine();

        System.out.print("Enter ward code: ");
        String ward = sc.nextLine();

        System.out.print("Enter vitals score: ");
        double vitals = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter facility name: ");
        String facility = sc.nextLine();

        try {
            PatientRecord p =
                new PatientRecord(id, ward, vitals, facility);

            System.out.println("Patient record created.");
        }
        catch (IllegalArgumentException e) {
            System.out.println("Invalid patient ID.");
        }

        sc.close();
    }
}