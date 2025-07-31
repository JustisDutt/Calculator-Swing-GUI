package Calculator;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class CalculatorMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Try to use Nimbus (ships with Java)
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception ignored) {}

            CalculatorModel model = new CalculatorModel();
            CalculatorGUI view = new CalculatorGUI();
            new CalculatorController(model, view);
            view.setVisible(true);
        });
    }
}
