package Calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CalculatorGUI extends JFrame {

    private final JTextField display = new JTextField("0");
    private final List<JButton> allButtons = new ArrayList<>();

    // Public references for convenience if you want them later
    public JButton btnClear, btnBack, btnSign, btnDiv, btnMul, btnSub, btnAdd, btnEq, btnDot;
    public JButton[] digitBtns = new JButton[10]; // digitBtns[0]..digitBtns[9] (0 is CREATED here!)

    public CalculatorGUI() {
        super("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 520);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Display
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(display.getFont().deriveFont(Font.PLAIN, 32f));
        display.setBorder(new EmptyBorder(16, 16, 16, 16));
        display.setBackground(new Color(250, 250, 250));
        add(display, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(panel, BorderLayout.CENTER);

        Font btnFont = display.getFont().deriveFont(Font.PLAIN, 22f);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // Row 0: C, ←, ±, /
        btnClear = createButton("C", btnFont);
        btnBack  = createButton("←", btnFont);
        btnSign  = createButton("±", btnFont);
        btnDiv   = createButton("/", btnFont);

        addAt(panel, btnClear, gbc, 0, 0);
        addAt(panel, btnBack,  gbc, 1, 0);
        addAt(panel, btnSign,  gbc, 2, 0);
        addAt(panel, btnDiv,   gbc, 3, 0);

        // Row 1: 7 8 9 *
        addAt(panel, digitBtns[7] = createButton("7", btnFont), gbc, 0, 1);
        addAt(panel, digitBtns[8] = createButton("8", btnFont), gbc, 1, 1);
        addAt(panel, digitBtns[9] = createButton("9", btnFont), gbc, 2, 1);
        btnMul = createButton("*", btnFont);
        addAt(panel, btnMul, gbc, 3, 1);

        // Row 2: 4 5 6 -
        addAt(panel, digitBtns[4] = createButton("4", btnFont), gbc, 0, 2);
        addAt(panel, digitBtns[5] = createButton("5", btnFont), gbc, 1, 2);
        addAt(panel, digitBtns[6] = createButton("6", btnFont), gbc, 2, 2);
        btnSub = createButton("-", btnFont);
        addAt(panel, btnSub, gbc, 3, 2);

        // Row 3: 1 2 3 +
        addAt(panel, digitBtns[1] = createButton("1", btnFont), gbc, 0, 3);
        addAt(panel, digitBtns[2] = createButton("2", btnFont), gbc, 1, 3);
        addAt(panel, digitBtns[3] = createButton("3", btnFont), gbc, 2, 3);
        btnAdd = createButton("+", btnFont);
        addAt(panel, btnAdd, gbc, 3, 3);

        // Row 4: 0 . =
        digitBtns[0] = createButton("0", btnFont);   // <-- create 0 here to avoid nulls
        btnDot       = createButton(".", btnFont);
        btnEq        = createButton("=", btnFont);

        // Make 0 span two columns
        gbc.gridwidth = 2; addAt(panel, digitBtns[0], gbc, 0, 4); gbc.gridwidth = 1;
        addAt(panel, btnDot, gbc, 2, 4);
        addAt(panel, btnEq,  gbc, 3, 4);

        // Collect all for bulk listener binding & consistent style (NO NULLS)
        for (int i = 0; i <= 9; i++) allButtons.add(digitBtns[i]);
        allButtons.add(btnDot); allButtons.add(btnEq);
        allButtons.add(btnAdd); allButtons.add(btnSub); allButtons.add(btnMul); allButtons.add(btnDiv);
        allButtons.add(btnClear); allButtons.add(btnBack); allButtons.add(btnSign);

        // Slightly rounded look
        getRootPane().setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
    }

    private JButton createButton(String text, Font font) {
        JButton b = new JButton(text);
        b.setFont(font);
        b.setFocusPainted(false);
        b.putClientProperty("JButton.buttonType", "roundRect");
        return b;
    }

    private void addAt(JPanel panel, JComponent comp, GridBagConstraints gbc, int x, int y) {
        GridBagConstraints g = (GridBagConstraints) gbc.clone();
        g.gridx = x; g.gridy = y;
        panel.add(comp, g);
    }

    /* === Public API used by the Controller === */
    public void setDisplayText(String value) { display.setText(value); }
    public String getDisplayText() { return display.getText(); }
    public List<JButton> getAllButtons() { return allButtons; }
    public JTextField getDisplayField() { return display; }
}
