package softwaretechnik;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private String operator;
    private double firstOperand;
    private StringBuilder expression;
    private boolean operatorPressed; // Flag, um zu verfolgen, ob ein Operator gedrückt wurde

    public Calculator() {
        setTitle("Taschenrechner");
        setSize(1080, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        add(display, BorderLayout.NORTH);

        int panelWidth = 400;
        int panelHeight = (int) (panelWidth * 9.0 / 16.0);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        buttonPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "Löschen", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 30));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        expression = new StringBuilder();
        operatorPressed = false; // Initialisiere das Flag
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Löschen":
                clear();
                break;
            case "=":
                calculateResult();
                break;
            case "/":
            case "*":
            case "-":
            case "+":
                setOperator(command);
                break;
            default:
                appendToExpression(command);
                break;
        }
    }

    private void clear() {
        display.setText("");
        expression.setLength(0);
        firstOperand = 0;
        operator = null;
        operatorPressed = false; // Setze das Flag zurück
    }

    private void appendToExpression(String value) {
        if (operatorPressed) {
            display.setText(""); // Leere das Display, wenn der letzte Druck ein Operator war
            operatorPressed = false; // Setze das Flag zurück
        }
        display.setText(display.getText() + value);
        expression.append(value);
    }

    private void setOperator(String op) {
        if (display.getText().isEmpty()) return; // Verhindere Berechnungen ohne Eingabe

        if (operator != null) {
            double interimResult = calculate(firstOperand, Double.parseDouble(display.getText()), operator);
            display.setText(String.valueOf(interimResult));
            expression.append(" = ").append(interimResult);
            firstOperand = interimResult;
        } else {
            firstOperand = Double.parseDouble(display.getText());
        }

        operator = op;
        expression.append(" ").append(operator).append(" ");
        operatorPressed = true; // Setze das Flag, um anzuzeigen, dass der Operator gedrückt wurde
    }

    private void calculateResult() {
        if (operator == null || display.getText().isEmpty()) return; // Verhindere Berechnungen ohne Operator oder Eingabe

        double secondOperand = Double.parseDouble(display.getText());
        double result = calculate(firstOperand, secondOperand, operator);
        expression.append(secondOperand).append(" = ").append(result);
        display.setText(expression.toString());
        firstOperand = result; // Setze den ersten Operanden auf das Ergebnis
        operator = null; // Setze den Operator zurück
        operatorPressed = false; // Setze das Flag zurück
    }

    private double calculate(double first, double second, String op) {
        switch (op) {
            case "/": return first / second;
            case "*": return first * second;
            case "-": return first - second;
            case "+": return first + second;
            default: throw new UnsupportedOperationException("Unbekannter Operator");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}



