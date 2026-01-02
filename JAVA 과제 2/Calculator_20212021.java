import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

public class Calculator_20212021 extends Frame implements ActionListener
{
    private String currentInput = ""; 
    private TextField display;
    private double result = 0;
    public Calculator_20212021()
    {
        setLayout(new BorderLayout());

        display = new TextField("0");
        display.setEditable(false);
        add(display, "North");

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(5, 4));
        String[] buttons = {
                "x!", "(", ")", "%", "AC",
                "ln", "7", "8", "9", "/",
                "log", "4", "5", "6", "*",
                "√", "1", "2", "3", "-",
                "x^y", "0", ".", "=", "+"
        };

        for(int k = 0; k < buttons.length; k++)
        {
            Button button = new Button(buttons[k]);
            button.addActionListener(this);
            panel.add(button);
        }
        add(panel, "Center");

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                dispose();
                System.exit(0);
            }
        });

        setSize(400, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evt)
    {
        String command = evt.getActionCommand();
        if ("0123456789+-*/().^".contains(command))
        {
            treatInput(command);
        } else if (command.equals("="))
        {
            CalculateOutcome();
        } else if (command.equals("AC"))
        {
            restartCalculator();
        } else if (SpecificOperator(command))
        {
            treatSpecificOperation(command);
        }

    }
    private void treatInput(String command)
    {
        
        currentInput += command;
        UpdateDevice(currentInput);
    }
    private void CalculateOutcome()
    {
        result = estimateExpression(currentInput);
        if (Double.isNaN(result))
        {
            UpdateDevice("Error!!: Division by Zero");
        }
        else
        {
            UpdateDevice(String.valueOf(result));
        }
        currentInput = ""; 
    }
    private void UpdateDevice(String text) {
        display.setText(text);
    }

    private void restartCalculator()
    {
        currentInput = "";
        UpdateDevice("0");
    }

    private double estimateExpression(String expression)
    {
        List<String> tokens = tokenize(expression);
        List<String> rpn = convertToRPN(tokens);
        return calculateRPN(rpn);
    }

    private List<String> tokenize(String expression)
    {
        List<String> tokens = new ArrayList<>();
        StringBuilder numberBuffer = new StringBuilder();
        boolean lastWasOp = true;

        for (char ch : expression.toCharArray())
        {
            if (Character.isDigit(ch) || ch == '.')
            {
                numberBuffer.append(ch);
            }
            else
            {
                if (numberBuffer.length() > 0)
                {
                    tokens.add(numberBuffer.toString());
                    numberBuffer = new StringBuilder();
                }
                if (ch == '-' && lastWasOp)
                {
                    numberBuffer.append('-'); 
                }
                else
                {
                    tokens.add(String.valueOf(ch));
                    lastWasOp = (ch != ')'); 
                }
            }
        }
        if (numberBuffer.length() > 0)
        {
            tokens.add(numberBuffer.toString());
        }
        return tokens;
    }

    private List<String> convertToRPN(List<String> tokens)
    {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String token : tokens)
        {
            if (Numeric(token))
            {
                output.add(token);
            }
            else if (token.equals("("))
            {
                stack.push(token);
            }
            if (token.equals(")"))
            {
                while (!stack.isEmpty() && !stack.peek().equals("("))
                {
                    output.add(stack.pop());
                }
                stack.pop(); 
            }
            else
            { 
                while (!stack.isEmpty() && earnPrecedence(token) <= earnPrecedence(stack.peek()))
                {
                    output.add(stack.pop());
                }
                stack.push(token);
            }
        }

        while (!stack.isEmpty())
        {
            output.add(stack.pop());
        }

        return output;
    }

    private double calculateRPN(List<String> rpn)
    {
        Stack<Double> stack = new Stack<>();

        for (String token : rpn)
        {
            if (Numeric(token))
            {
                stack.push(Double.parseDouble(token));
            }
            else
            {
                double secondOperand = stack.pop();
                double firstOperand = stack.pop();
                double result = 0;

                switch (token)
                {
                    case "+":
                        result = firstOperand + secondOperand;
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        break;
                    case "*":
                        result = firstOperand * secondOperand;
                        break;
                    case "/":
                        if (secondOperand == 0)
                        {
                            return Double.NaN;
                        }
                        result = firstOperand / secondOperand;
                        break;
                    case "^":
                        result = Math.pow(firstOperand, secondOperand);
                        break;
                }

                stack.push(result);
            }
        }

        return stack.isEmpty() ? 0 : stack.pop();
    }
    private boolean Numeric(String str)
    {
        try
        {
            Double.parseDouble(str);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }

    private int earnPrecedence(String operator)
    {
        switch (operator)
        {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }

    private double calculateSpecificOperation(String operation, double operand)
    {
        switch (operation)
        {
            case "%":
                return operand / 100.0;
            case "x!":
                return factorial((int) operand);
            case "log":
                return Math.log10(operand);
            case "ln":
                return Math.log(operand);
            case "√":
                return Math.sqrt(operand);
            default:
                return 0;
        }
    }

    private void treatSpecificOperation(String operation)
    {
        if (operation.equals("x^y"))
        {
            treatInput("^");
        }
        else
        {
            double operand = Double.parseDouble(currentInput);
            double result = calculateSpecificOperation(operation, operand);
            currentInput = String.valueOf(result);
            UpdateDevice(String.valueOf(result));
        }
    }

    private double factorial(int n)
    {
        double factor = 1;
        for (int j = 1; j <= n; j++)
        {
            factor *= j;
        }
        return factor;
    }

    private boolean SpecificOperator(String op)
    {
        return op.equals("%") || op.equals("x!") || op.equals("log") || op.equals("ln") || op.equals("√") || op.equals("x^y");
    }

    public static void main(String[] args)
    {
        new Calculator_20212021();
    }
}