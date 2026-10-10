
import java.util.Scanner;

interface ScoringRule {
    double calculate(double idea, double execution, double presentation);
}

class InnovationRule implements ScoringRule {
    public double calculate(double idea, double execution, double presentation) {
        return idea * 0.5 + execution * 0.3 + presentation * 0.2;
    }
}

class OpenRule implements ScoringRule {
    public double calculate(double idea, double execution, double presentation) {
        return (idea + execution + presentation) / 3;
    }
}

class Team {
    String name;
    int members;
    String project;
    double score;
    boolean scored;

    Team(String name, int members) {
        this.name = name;
        this.members = members;
    }
}

class Hackathon {
    boolean published;
    ScoringRule rule;

    Hackathon(ScoringRule rule) {
        this.rule = rule;
    }

    boolean register(Team team) {
        if (team.members < 2 || team.members > 4) {
            System.out.println("Registration failed: A team must have 2 to 4 members");
            return false;
        }
        System.out.println("Team " + team.name + " registered");
        return true;
    }

    void submit(Team team, String project) {
        if (team.project == null) {
            team.project = project;
            System.out.println("Project '" + project + "' submitted by " + team.name);
        }
    }

    void score(Team team, double idea, double execution, double presentation) {
        if (published) {
            System.out.println("Rescore rejected: Results have already been published");
            return;
        }
        team.score = rule.calculate(idea, execution, presentation);
        team.scored = true;
        System.out.println("Score recorded. Final score: "
                + String.format("%.2f", team.score));
    }

    void publish() {
        published = true;
        System.out.println("Results published");
    }
}

public class HackathonDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Team name: ");
        String name = sc.nextLine();

        System.out.print("Number of team members: ");
        int members = sc.nextInt();
        sc.nextLine();

        System.out.print("Track (1 Innovation, 2 Open): ");
        int track = sc.nextInt();

        ScoringRule rule = track == 1
                ? new InnovationRule() : new OpenRule();

        Hackathon hackathon = new Hackathon(rule);
        Team team = new Team(name, members);

        if (hackathon.register(team)) {
            sc.nextLine();
            System.out.print("Project name: ");
            String project = sc.nextLine();
            hackathon.submit(team, project);

            System.out.print("Enter idea, execution, presentation scores: ");
            double idea = sc.nextDouble();
            double execution = sc.nextDouble();
            double presentation = sc.nextDouble();

            hackathon.score(team, idea, execution, presentation);
            hackathon.publish();

            hackathon.score(team, 10, 10, 10);
        }

        sc.close();
    }
}
