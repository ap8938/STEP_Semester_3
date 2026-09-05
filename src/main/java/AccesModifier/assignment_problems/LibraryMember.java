package main.java.AccesModifier.assignment_problems;

import java.util.Scanner;
import java.security.MessageDigest;

public class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    // No-argument constructor
    public LibraryMember() {
        this(null, null);
    }

    // Name-only constructor
    public LibraryMember(String name) {
        this(null, name);
    }

    // Main constructor
    public LibraryMember(
        String membershipId,
        String name) {

        this.membershipId = membershipId;
        this.name = name;
        this.premiumMember = false;
    }

    // Membership ID
    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {

        // Write once
        if (membershipId == null) {
            membershipId = id;
        }
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Premium
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        premiumMember = premium;
    }

    // Write-only security answer
    public void setSecurityAnswer(String answer) {

        try {

            MessageDigest md =
                MessageDigest.getInstance("SHA-256");

            byte[] bytes =
                md.digest(answer.getBytes());

            String result = "";

            for (int i = 0; i < bytes.length; i++) {
                result += String.format(
                    "%02x", bytes[i]
                );
            }

            securityAnswer = result;

        } catch (Exception e) {
            securityAnswer = null;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter member name: ");
        String name = sc.nextLine();

        System.out.print("Enter membership ID: ");
        String id = sc.nextLine();

        LibraryMember member =
            new LibraryMember(id, name);

        System.out.println(
            "Membership ID: " +
            member.getMembershipId()
        );

        System.out.print("Make premium? (1/0): ");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 1) {
            member.setPremiumMember(true);
        }

        System.out.println(
            "Premium: " +
            member.isPremiumMember()
        );

        System.out.print("Enter security answer: ");
        String answer = sc.nextLine();

        member.setSecurityAnswer(answer);

        System.out.println(
            "Security answer saved."
        );

        // Testing write-once property
        System.out.print(
            "Enter another membership ID: "
        );

        String newId = sc.nextLine();

        member.setMembershipId(newId);

        System.out.println(
            "Final membership ID: " +
            member.getMembershipId()
        );
    }
}
//EXIT
