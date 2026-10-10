
import java.util.Scanner;

class Question {
    String question;
    String correctAnswer;

    Question(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Attempt {
    String studentName;
    String examName;
    String[] answers;
    boolean submitted;

    Attempt(String studentName, String examName, int size) {
        this.studentName = studentName;
        this.examName = examName;
        answers = new String[size];
        submitted = false;
    }

    void answerQuestion(int index, String answer) {
        if (submitted) {
            System.out.println("Cannot change submitted answers");
        } else {
            answers[index] = answer;
            System.out.println("Question " + (index + 1)
                    + " answered with " + answer);
        }
    }

    void submit() {
        submitted = true;
        System.out.println("Examination " + examName
                + " submitted successfully");
    }

    int calculateResult(Question[] questions) {
        int score = 0;

        for (int i = 0; i < questions.length; i++) {
            if (answers[i] != null
                    && questions[i].checkAnswer(answers[i])) {
                score++;
            }
        }

        return score;
    }
}

public class ExaminationDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Question[] questions = {
            new Question("Capital of France? A. Paris B. Rome", "A"),
            new Question("2 + 2? A. 5 B. 4", "B")
        };

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        String examName = "Math Quiz";
        Attempt attempt = new Attempt(name, examName, questions.length);

        System.out.println("Examination " + examName
                + " started by " + name);

        for (int i = 0; i < questions.length; i++) {
            System.out.println("Q" + (i + 1) + ": "
                    + questions[i].question);
            System.out.print("Enter answer (A/B): ");
            String answer = sc.nextLine();
            attempt.answerQuestion(i, answer);
        }

        System.out.print("Submit examination? (yes/no): ");
        String choice = sc.nextLine();

        if (choice.equalsIgnoreCase("yes")) {
            attempt.submit();
            System.out.println("Result for " + examName
                    + ": " + attempt.calculateResult(questions)
                    + "/" + questions.length + " correct");
        } else {
            System.out.println("Examination not submitted");
        }

        sc.close();
    }
}
