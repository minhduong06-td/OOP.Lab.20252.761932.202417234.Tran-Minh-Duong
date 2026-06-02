package hust.soict.vp.aims.screen;

import hust.soict.vp.aims.cart.Cart;
import hust.soict.vp.aims.store.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AddItemToStoreScreen extends JFrame {
    protected final Store store;
    protected final Cart cart;
    protected final StoreScreen storeScreen;
    protected final Map<String, JTextField> fields = new LinkedHashMap<String, JTextField>();
    private final JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));

    protected AddItemToStoreScreen(String title, Store store, Cart cart, StoreScreen storeScreen) {
        this.store = store;
        this.cart = cart;
        this.storeScreen = storeScreen;

        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(480, 320);
        setLocationRelativeTo(storeScreen);
        setLayout(new BorderLayout(8, 8));

        add(formPanel, BorderLayout.CENTER);
        add(createButtons(), BorderLayout.SOUTH);
    }

    protected void addField(String label) {
        JTextField textField = new JTextField();
        fields.put(label, textField);
        formPanel.add(new JLabel(label));
        formPanel.add(textField);
    }

    protected String text(String label) {
        return fields.get(label).getText().trim();
    }

    protected float parseFloat(String label) {
        return Float.parseFloat(text(label));
    }

    protected int parseInt(String label) {
        return Integer.parseInt(text(label));
    }

    protected void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message);
        storeScreen.refreshStore();
    }

    protected void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid input", JOptionPane.ERROR_MESSAGE);
    }

    protected abstract void addItem();

    private JPanel createButtons() {
        JPanel buttons = new JPanel();

        JButton add = new JButton("Add");
        add.addActionListener(e -> addItem());
        buttons.add(add);

        JButton back = new JButton("Back/View Store");
        back.addActionListener(e -> {
            storeScreen.refreshStore();
            dispose();
        });
        buttons.add(back);

        return buttons;
    }
}
