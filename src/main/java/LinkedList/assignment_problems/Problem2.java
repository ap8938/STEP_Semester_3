
import java.util.Stack;

public class Problem2 {

    public static String expandBanner(String code) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> textStack = new Stack<>();

        StringBuilder current = new StringBuilder();
        int number = 0;

        for (int i = 0; i < code.length(); i++) {
            char ch = code.charAt(i);

            if (Character.isDigit(ch)) {
                number = number * 10 + (ch - '0');
            }
            else if (ch == '[') {
                countStack.push(number);
                textStack.push(current);

                current = new StringBuilder();
                number = 0;
            }
            else if (ch == ']') {
                int count = countStack.pop();
                StringBuilder previous = textStack.pop();

                for (int j = 0; j < count; j++) {
                    previous.append(current);
                }

                current = previous;
            }
            else {
                current.append(ch);
            }
        }

        return current.toString();
    }

    public static void main(String[] args) {
        String code1 = "3[ab]2[c]";
        String code2 = "2[x3[y]]z";

        System.out.println(expandBanner(code1));
        System.out.println(expandBanner(code2));
    }
}
