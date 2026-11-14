
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Job {
    String name;
    int remainingTime;

    Job(String name, int time) {
        this.name = name;
        this.remainingTime = time;
    }
}

public class Problem3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int quantum = sc.nextInt();
        int n = sc.nextInt();

        Queue<Job> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int time = sc.nextInt();

            queue.add(new Job(name, time));
        }

        int currentTime = 0;

        StringBuilder slices = new StringBuilder();
        StringBuilder finish = new StringBuilder();

        while (!queue.isEmpty()) {
            Job job = queue.remove();

            int start = currentTime;
            int runTime = Math.min(quantum, job.remainingTime);

            currentTime += runTime;
            job.remainingTime -= runTime;

            if (slices.length() > 0) {
                slices.append(" ");
            }

            slices.append(job.name)
                  .append("(")
                  .append(start)
                  .append("-")
                  .append(currentTime)
                  .append(")");

            if (job.remainingTime > 0) {
                queue.add(job);
            } else {
                if (finish.length() > 0) {
                    finish.append(", ");
                }

                finish.append(job.name)
                      .append(" ")
                      .append(currentTime);
            }
        }

        System.out.println(slices);
        System.out.println("finish " + finish);

        sc.close();
    }
}
