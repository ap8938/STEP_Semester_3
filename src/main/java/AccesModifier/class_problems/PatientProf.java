package main.java.AccesModifier.class_problems;

import java.util.Scanner;

class PatientProfile {

    private String patientId;
    private String name;
    private boolean discharged;
    private String lockerPin;


    public PatientProfile() {
        this(null, null);
    }


    public PatientProfile(String name) {
        this(null, name);
    }


    public PatientProfile(String patientId, String name) {
        this.patientId = patientId;
        this.name = name;
    }


    public String getPatientId() {
        return patientId;
    }


    public void setPatientId(String id) {

        if (patientId == null)
            patientId = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public boolean isDischarged() {
        return discharged;
    }


    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }


    public void setLockerPin(String pin) {

        if (pin.matches("[0-9]{4,6}"))
            lockerPin = pin;
    }
}


public class PatientProf {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter patient name: ");
        String name = sc.nextLine();

        System.out.print("Enter patient ID: ");
        String id = sc.nextLine();

        System.out.print("Is patient discharged? (true/false): ");
        boolean discharged = sc.nextBoolean();
        sc.nextLine();

        System.out.print("Enter locker PIN: ");
        String pin = sc.nextLine();

        PatientProfile p =
            new PatientProfile(name);

        p.setPatientId(id);
        p.setDischarged(discharged);
        p.setLockerPin(pin);

        System.out.println("\nPatient Details:");
        System.out.println("Name: " + p.getName());
        System.out.println("Patient ID: " + p.getPatientId());
        System.out.println("Discharged: " + p.isDischarged());

        sc.close();
    }
}
