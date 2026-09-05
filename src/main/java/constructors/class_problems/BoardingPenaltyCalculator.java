class BoardingPenaltyCalculator {

    final double minimumPenaltyPercent;

    BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    final double calculatePenalty(double ticketFare, int minutesLate) {

        if (ticketFare < 0 || minutesLate < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (minutesLate == 0) {
            return 0;
        }

        double penalty = 0;

        // First 5 minutes
        if (minutesLate <= 5) {
            penalty = ticketFare * 0.005 * minutesLate;
        }

        // Minutes 6 to 15
        else if (minutesLate <= 15) {
            penalty = ticketFare * 0.005 * 5;
            penalty += ticketFare * 0.01 * (minutesLate - 5);
        }

        // After 15 minutes
        else {
            penalty = ticketFare * 0.005 * 5;
            penalty += ticketFare * 0.01 * 10;
            penalty += ticketFare * 0.02 * (minutesLate - 15);
        }

        // Minimum penalty floor
        double minimumPenalty = ticketFare * minimumPenaltyPercent / 100;

        if (penalty < minimumPenalty) {
            penalty = minimumPenalty;
        }

        return penalty;
    }

    public static void main(String[] args) {

        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = BusRoute.rankRoutes(routes);

        System.out.println("Ranked Routes:");

        for (BusRoute r : ranked) {
            System.out.println(r.routeCode + " - "
                    + r.routeName + " - "
                    + r.priority);
        }
    }
}
