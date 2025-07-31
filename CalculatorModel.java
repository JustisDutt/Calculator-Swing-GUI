package Calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public class CalculatorModel {
    private BigDecimal accumulator = BigDecimal.ZERO;
    private StringBuilder input = new StringBuilder();
    private Character pendingOp = null;
    private boolean startNewInput = true;
    private boolean errorState = false;

    private static final MathContext MC = new MathContext(16);

    public String getDisplayText() {
        if (errorState) return "Error";
        if (input.length() == 0) return "0";
        return input.toString();
    }

    public void clearAll() {
        accumulator = BigDecimal.ZERO;
        input.setLength(0);
        pendingOp = null;
        startNewInput = true;
        errorState = false;
    }

    public void clearEntry() {
        if (errorState) { clearAll(); return; }
        input.setLength(0);
        startNewInput = true;
    }

    public void backspace() {
        if (errorState) return;
        if (startNewInput) return;
        if (input.length() > 0) input.deleteCharAt(input.length() - 1);
    }

    public void inputDigit(char d) {
        if (errorState) return;
        if (startNewInput) {
            input.setLength(0);
            startNewInput = false;
        }
        if (d == '0' && input.length() == 0) {
            input.append('0');
            return;
        }
        if (input.length() == 1 && input.charAt(0) == '0' && d != '.') {
            input.setCharAt(0, d);
        } else {
            input.append(d);
        }
    }

    public void inputDot() {
        if (errorState) return;
        if (startNewInput) {
            input.setLength(0);
            input.append("0.");
            startNewInput = false;
            return;
        }
        if (input.indexOf(".") == -1) {
            if (input.length() == 0) input.append("0");
            input.append('.');
        }
    }

    public void toggleSign() {
        if (errorState) return;
        if (startNewInput) {
            accumulator = accumulator.negate(MC);
            input.setLength(0);
            input.append(stripTrailingZeros(accumulator));
            startNewInput = false;
            return;
        }
        if (input.length() == 0 || input.toString().equals("0")) return;
        if (input.charAt(0) == '-') {
            input.deleteCharAt(0);
        } else {
            input.insert(0, '-');
        }
    }

    public void applyOperator(char op) {
        if (errorState) return;
        try {
            BigDecimal current = parseInputOrAccumulator();
            if (pendingOp == null) {
                accumulator = current;
            } else {
                accumulator = compute(accumulator, current, pendingOp);
            }
            pendingOp = op;
            input.setLength(0);
            input.append(stripTrailingZeros(accumulator));
            startNewInput = true;
        } catch (ArithmeticException ex) {
            setError();
        }
    }

    public void equalsPress() {
        if (errorState) return;
        try {
            if (pendingOp == null) {
                BigDecimal current = parseInputOrAccumulator();
                input.setLength(0);
                input.append(stripTrailingZeros(current));
                startNewInput = true;
                return;
            }
            BigDecimal current = parseInputOrAccumulator();
            accumulator = compute(accumulator, current, pendingOp);
            pendingOp = null;
            input.setLength(0);
            input.append(stripTrailingZeros(accumulator));
            startNewInput = true;
        } catch (ArithmeticException ex) {
            setError();
        }
    }

    private BigDecimal parseInputOrAccumulator() {
        if (input.length() == 0) return accumulator;
        return new BigDecimal(input.toString(), MC);
    }

    private BigDecimal compute(BigDecimal a, BigDecimal b, char op) {
        switch (op) {
            case '+': return a.add(b, MC);
            case '-': return a.subtract(b, MC);
            case '*': return a.multiply(b, MC);
            case '/':
                if (b.compareTo(BigDecimal.ZERO) == 0) throw new ArithmeticException("Divide by zero");
                return a.divide(b, 12, BigDecimal.ROUND_HALF_UP).round(MC);
            default: throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    private String stripTrailingZeros(BigDecimal bd) {
        bd = bd.stripTrailingZeros();
        String s = bd.toPlainString();
        return s.equals("-0") ? "0" : s;
    }

    private void setError() {
        errorState = true;
        input.setLength(0);
    }
}

