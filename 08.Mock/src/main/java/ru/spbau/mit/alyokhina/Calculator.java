package ru.spbau.mit.alyokhina;


import org.jetbrains.annotations.NotNull;

/** By arithmetic expression receives a reverse Polish notation and computes the value */
public class Calculator {
    public static void main(String[] argv) {
        if (argv.length == 0) {
            System.out.println("Expected expression");
        } else {
            Stack<String> expr = new Stack<String>();
            for (int i = argv[0].length() - 1; i >= 0; i--) {
                if ((argv[0].charAt(i) >= '0' && argv[0].charAt(i) <= '9') || (argv[0].charAt(i) == '.')) {
                    StringBuilder sb = new StringBuilder();
                    while (i < argv[0].length() && ((argv[0].charAt(i) >= '0' && argv[0].charAt(i) <= '9') || (argv[0].charAt(i) == '.'))) {
                        sb.insert(0, argv[0].charAt(i));
                        i--;
                    }
                    i++;
                    expr.push(sb.toString());
                } else if (argv[0].charAt(i) == '+' || argv[0].charAt(i) == '-' || argv[0].charAt(i) == '*' ||
                        argv[0].charAt(i) == '/' || argv[0].charAt(i) == '(' || argv[0].charAt(i) == ')') {
                    expr.push(argv[0].substring(i, i + 1));
                }
            }
            Calculator calculator = new Calculator(expr);
            System.out.println(calculator.getRPN());
            System.out.println(calculator.calculate());
        }
    }
    /** Stack for storing the expression */
    private Stack<String> expression = new Stack<String>();

    /** Stack for storing current operands */
    private Stack<String> operand = new Stack<String>();

    /** Stack for storing reverse Polish notation */
    private Stack<String> rpn = new Stack<String>();

    /**
     * Constructor
     * @param newExpression an expression that needs to be translated into a reverse Polish notation
     */
    public Calculator(@NotNull Stack<String> newExpression) {
        expression = newExpression;
        rpn = new Stack<String>();
        operand = new Stack<String>();
    }

    /**
     * Constructor
     * @param newExpression an expression that needs to be translated into a reverse Polish notation
     * @param forOperand stack for storing the operands
     * @param forRPN stack for storing reverse Polish notation
     */
    public Calculator(@NotNull Stack<String> newExpression, @NotNull Stack<String> forOperand, @NotNull Stack<String> forRPN) {
        expression = newExpression;
        rpn = forRPN;
        operand = forOperand;
    }

    /**
     * Get reverse Polish notation
     * @return reverse Polish notation as a string. All elements are separated by a space. The same value is stored in rpn
     */
    public String getRPN() {
        StringBuilder stringBuilder = new StringBuilder();

        while (!expression.isEmpty()) {
            String cur = expression.pop();
            if (cur.equals("(")) {
                operand.push("(");
            } else if (cur.equals(")")) {
                while (!operand.isEmpty() && !operand.peek().equals("(")) {
                    String str = operand.pop();
                    rpn.push(str);
                    stringBuilder.append(" ");
                    stringBuilder.append(str);
                }
                if (!operand.isEmpty()) {
                    operand.pop();
                }
            } else if (isOperand(cur)) {
                while (!operand.isEmpty() && !operand.peek().equals("(")
                        && (priority(operand.peek()) >= priority(cur))) {
                    String str = operand.pop();
                    rpn.push(str);
                    stringBuilder.append(" ");
                    stringBuilder.append(str);
                }
                operand.push(cur);
            } else {
                rpn.push(cur);
                stringBuilder.append(" ");
                stringBuilder.append(cur);
            }
        }
        while (!operand.isEmpty()) {
            String str = operand.pop();
            rpn.push(str);
            stringBuilder.append(" ");
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }

    /**
     * Calculates the value of the expression
     * @return The result of calculation of type Double
     */
    public Double calculate() {
        if (!rpn.isEmpty()) {
            if (isOperand(rpn.peek())) {
                String op = rpn.pop();
                return apply(calculate(), calculate(), op);
            } else {
                return (Double.parseDouble(rpn.pop()));
            }
        } else {
            return 0.0;
        }
    }

    /**
     * Whether the string is an operand
     * @param str string, which we want to check
     * @return true, if this string is operand, else - false
     */
    private boolean isOperand(String str) {
        return (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/"));
    }

    /**
     * Application of operations
     * @param a,b these values will be applied to the operation
     * @param op the operation to be applied
     * @return result of type Double
     */
    @NotNull
    private Double apply(Double a, Double b, String op) {
        if (op.equals("+")) {
            return a + b;
        } else if (op.equals("-")) {
            return a - b;
        } else if (op.equals("*")) {
            return a * b;
        } else {
            return a / b;
        }
    }

    /**
     * the priority of an operation
     * @param operand the operand, the priority of which will be issued
     * @return priority
     */
    private static int priority(@NotNull String operand) {
        if (operand.equals("+") || operand.equals("-")) {
            return 1;
        } else {
            return 2;
        }
    }
}
