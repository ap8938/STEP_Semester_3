package main.java.AccesModifier.class_problems;

import java.util.Scanner;

class PatientVitals {

    private double[] readings;
    private int count;

    PatientVitals(double[] initialReadings) {

        readings = new double[500];
        count = 0;

        for (double reading : initialReadings) {
            recordReading(reading);
        }
    }


    void recordReading(double reading) {

        if (reading > 0 && reading <= 45) {
            readings[count] = reading;
            count++;
        }
    }


    double getAverage() {

        if (count == 0)
            return 0;

        double sum = 0;

        for (int i = 0; i < count; i++) {
            sum += readings[i];
        }

        return sum / count;
    }


    double[] getAllReadings() {

        double[] result = new double[count];

        for (int i = 0; i < count; i++) {
            result[i] = readings[i];
        }

        return result;
    }

     public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of readings: ");
        int n = sc.nextInt();

        double[] initialReadings = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter reading " + (i + 1) + ": ");
            initialReadings[i] = sc.nextDouble();
        }

        PatientVitals v =
            new PatientVitals(initialReadings);

        double[] result = v.getAllReadings();

        System.out.println("Valid readings:");

        for (double x : result) {
            System.out.println(x);
        }

        System.out.println("Average: " + v.getAverage());

        sc.close();
    }
}
