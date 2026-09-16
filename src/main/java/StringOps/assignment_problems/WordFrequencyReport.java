import java.util.HashMap;
import java.util.Scanner;

public class WordFrequencyReport {

    static void printFilteredWordFrequency(String feedback) {

        feedback = feedback.toLowerCase();

        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");

        String[] words = feedback.split("\\s+");

        String[] stopWords = {
            "the", "was", "and", "a", "is", "of", "in"
        };

        HashMap<String, Integer> frequency = new HashMap<>();

        for (int i = 0; i < words.length; i++) {

            boolean isStopWord = false;

            for (int j = 0; j < stopWords.length; j++) {

                if (words[i].equals(stopWords[j])) {
                    isStopWord = true;
                    break;
                }
            }

            if (!isStopWord) {

                if (frequency.containsKey(words[i])) {
                    frequency.put(words[i],
                            frequency.get(words[i]) + 1);
                } else {
                    frequency.put(words[i], 1);
                }
            }
        }

        String[] uniqueWords = new String[frequency.size()];
        int[] counts = new int[frequency.size()];

        int index = 0;

        for (String word : frequency.keySet()) {

            uniqueWords[index] = word;
            counts[index] = frequency.get(word);

            index++;
        }

        // Sort by frequency in descending order
        for (int i = 0; i < counts.length - 1; i++) {

            for (int j = i + 1; j < counts.length; j++) {

                if (counts[j] > counts[i]) {

                    int tempCount = counts[i];
                    counts[i] = counts[j];
                    counts[j] = tempCount;

                    String tempWord = uniqueWords[i];
                    uniqueWords[i] = uniqueWords[j];
                    uniqueWords[j] = tempWord;
                }
            }
        }

        for (int i = 0; i < uniqueWords.length; i++) {

            System.out.println(uniqueWords[i] + ": " + counts[i]);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter feedback paragraph: ");
        String feedback = sc.nextLine();

        printFilteredWordFrequency(feedback);

        sc.close();
    }
}
