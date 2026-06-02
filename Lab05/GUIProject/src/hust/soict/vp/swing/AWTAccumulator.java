package hust.soict.vp.swing;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AWTAccumulator extends Frame {
    private final TextField inputField = new TextField("0", 10);
    private final TextField sumField = new TextField("0", 10);
    private int sum = 0;

    public AWTAccumulator() {
        super("AWT Accumulator");
        setLayout(new GridLayout(2, 2));

        add(new Label("Enter an integer:"));
        add(inputField);
        add(new Label("The accumulated sum is:"));
        sumField.setEditable(false);
        add(sumField);

        inputField.addActionListener(e -> accumulate());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

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
        new AWTAccumulator();
    }
}
