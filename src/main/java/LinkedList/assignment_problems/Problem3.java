
import java.util.Stack;

public class Problem3 {

    public static int precedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        }
        if (op == '*' || op == '/') {
            return 2;
        }
        return 0;
    }

    public static void applyOperation(
            Stack<Integer> values,
            Stack<Character> operators) {

        int b = values.pop();
        int a = values.pop();
        char op = operators.pop();

        int result = 0;

        switch (op) {
            case '+':
                result = a + b;
                break;

            case '-':
                result = a - b;
                break;

            case '*':
                result = a * b;
                break;

            case '/':
                result = a / b;
                break;
        }

        values.push(result);
    }

    public static int evaluate(String expr) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;

        while (i < expr.length()) {
            char ch = expr.charAt(i);

            if (ch == ' ') {
                i++;
            }
            else if (Character.isDigit(ch)) {
                int number = 0;

                // Read a complete multi-digit number
                while (i < expr.length() &&
                       Character.isDigit(expr.charAt(i))) {
                    number = number * 10 + (expr.charAt(i) - '0');
                    i++;
                }

                values.push(number);
            }
            else if (ch == '(') {
                operators.push(ch);
                i++;
            }
            else if (ch == ')') {
                while (!operators.isEmpty() &&
                       operators.peek() != '(') {
                    applyOperation(values, operators);
                }

                operators.pop(); // Remove '('
                i++;
            }
            else {
                // Apply operators with higher or equal precedence
                while (!operators.isEmpty() &&
                       operators.peek() != '(' &&
                       precedence(operators.peek()) >= precedence(ch)) {
                    applyOperation(values, operators);
                }

                operators.push(ch);
                i++;
            }
        }

        // Apply remaining operators
        while (!operators.isEmpty()) {
            applyOperation(values, operators);
        }

        return values.pop();
    }

    public static void main(String[] args) {
        String expr1 = "2 * (3 + 4) - 10 / 5";
        String expr2 = "(8 - 2) * (1 + 2) / 4";

        System.out.println(evaluate(expr1));
        System.out.println(evaluate(expr2));
    }
}
