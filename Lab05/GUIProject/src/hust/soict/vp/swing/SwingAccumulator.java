package hust.soict.vp.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.GridLayout;

public class SwingAccumulator extends JFrame {
    private final JTextField inputField = new JTextField("0", 10);
    private final JTextField sumField = new JTextField("0", 10);
    private int sum = 0;

    public SwingAccumulator() {
        super("Swing Accumulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Enter an integer:"));
        panel.add(inputField);
        panel.add(new JLabel("The accumulated sum is:"));
        sumField.setEditable(false);
        panel.add(sumField);

        inputField.addActionListener(e -> accumulate());

        setContentPane(panel);
        setSize(420, 120);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void accumulate() {
        try {
            sum += Integer.parseInt(inputField.getText().trim());
            sumField.setText(String.valueOf(sum));
        } catch (NumberFormatException e) {
            inputField.setText("0");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SwingAccumulator();
            }
        });
    }
}
