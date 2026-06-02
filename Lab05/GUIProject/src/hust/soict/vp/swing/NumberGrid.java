package hust.soict.vp.swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class NumberGrid extends JFrame {
    private final JTextField display = new JTextField();

    public NumberGrid() {
        super("Number Grid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        display.setEditable(false);
        add(display, BorderLayout.NORTH);
        add(createButtons(), BorderLayout.CENTER);

        setSize(320, 360);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createButtons() {
        JPanel panel = new JPanel(new GridLayout(4, 3, 6, 6));
        String[] labels = {
                "1", "2", "3",
                "4", "5", "6",
                "7", "8", "9",
                "DEL", "0", "C"
        };

        for (String label : labels) {
            JButton button = new JButton(label);
            button.addActionListener(e -> handleButton(label));
            panel.add(button);
        }

        return panel;
    }

    private void handleButton(String label) {
        if ("DEL".equals(label)) {
            String text = display.getText();
            if (!text.isEmpty()) {
                display.setText(text.substring(0, text.length() - 1));
            }
        } else if ("C".equals(label)) {
            display.setText("");
        } else {
            display.setText(display.getText() + label);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGrid();
            }
        });
    }
}
