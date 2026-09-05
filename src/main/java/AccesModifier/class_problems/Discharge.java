package main.java.AccesModifier.class_problems;

import java.util.Scanner;
class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    static String hospitalName;

    static {
        hospitalName = "MediTrack";
    }


    public DischargeSummary(String patientId,
                            String[] medicationCodes) {

        for (String code : medicationCodes) {

            if (!code.matches("MED-[A-Z]"))
                throw new IllegalArgumentException(
                    "Invalid medication code"
                );
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }


    public String[] getMedicationCodes() {
        return medicationCodes.clone();
    }


    public DischargeSummary withCorrectedMedication(
            int index, String newCode) {

        if (!newCode.matches("MED-[A-Z]"))
            throw new IllegalArgumentException(
                "Invalid medication code"
            );

        String[] newCodes = medicationCodes.clone();

        newCodes[index] = newCode;

        return new DischargeSummary(patientId, newCodes);
    }
}


class CriticalCareDischargeSummary
        extends DischargeSummary {

    private int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
}


public class Discharge {

    static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;

        for (DischargeSummary s : summaries) {

            if (s == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (s instanceof CriticalCareDischargeSummary)
                critical++;
            else
                routine++;
        }

        return processed + " processed | " +
               nullSkipped + " null skipped | " +
               critical + " critical-care | " +
               routine + " routine";
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter patient ID: ");
        String id = sc.nextLine();

        System.out.print("Enter number of medicines: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] medicines = new String[n];

        for (int i = 0; i < n; i++) {

            System.out.print(
                "Enter medicine code " + (i + 1) + ": "
            );

            medicines[i] = sc.nextLine();
        }


        try {

            DischargeSummary d =
                new DischargeSummary(id, medicines);

            System.out.println("Summary created.");

            System.out.println("Medication codes:");

            String[] codes = d.getMedicationCodes();

            for (String code : codes)
                System.out.println(code);

        }
        catch (IllegalArgumentException e) {

            System.out.println(
                "Invalid medication code. Summary rejected."
            );
        }

        sc.close();
    }
}
