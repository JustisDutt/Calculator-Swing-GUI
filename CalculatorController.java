package Calculator;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class CalculatorController implements ActionListener, KeyListener {

    private final CalculatorModel model;
    private final CalculatorGUI view;

    public CalculatorController(CalculatorModel model, CalculatorGUI view) {
        this.model = model;
        this.view = view;

        // Connect all button actions to this controller
        bindButtons(view.getAllButtons());

        // Keyboard input on frame and display
        view.addKeyListener(this);
        view.getDisplayField().addKeyListener(this);

        // Initialize display
        view.setDisplayText(model.getDisplayText());
    }

    private void bindButtons(List<JButton> buttons) {
        for (JButton b : buttons) {
            // Safety: skip any accidental nulls
            if (b == null) continue;
            b.setActionCommand(b.getText());
            b.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        handleCommand(cmd);
    }

    private void handleCommand(String cmd) {
        switch (cmd) {
            // Digits
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                model.inputDigit(cmd.charAt(0));
                break;

            // Operators
            case "+": case "-": case "*": case "/":
                model.applyOperator(cmd.charAt(0));
                break;

            case ".":
                model.inputDot();
                break;

            case "=":
                model.equalsPress();
                break;

            case "C":
                model.clearAll();
                break;

            case "←":
                model.backspace();
                break;

            case "±":
                model.toggleSign();
                break;

            default:
                // no-op
        }
        view.setDisplayText(model.getDisplayText());
    }

    /* === Keyboard support === */
    @Override public void keyTyped(KeyEvent e) { /* not used */ }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        char ch = e.getKeyChar();

        if (Character.isDigit(ch)) {
            handleCommand(String.valueOf(ch));
        } else if (ch == '.') {
            handleCommand(".");
        } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
            handleCommand(String.valueOf(ch));
        } else if (code == KeyEvent.VK_ENTER || (code == KeyEvent.VK_EQUALS && e.isShiftDown())) {
            handleCommand("=");
        } else if (code == KeyEvent.VK_BACK_SPACE) {
            handleCommand("←");
        } else if (code == KeyEvent.VK_ESCAPE) {
            handleCommand("C");
        } else if (code == KeyEvent.VK_F9) {
            handleCommand("±");
        }
    }

    @Override public void keyReleased(KeyEvent e) { /* not used */ }
}
