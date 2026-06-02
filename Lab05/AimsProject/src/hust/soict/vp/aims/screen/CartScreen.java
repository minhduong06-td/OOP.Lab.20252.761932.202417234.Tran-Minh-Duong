package hust.soict.vp.aims.screen;

import hust.soict.vp.aims.cart.Cart;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.net.URL;

public class CartScreen extends JFrame {
    public CartScreen(Cart cart) {
        setTitle("AIMS Cart");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JFXPanel fxPanel = new JFXPanel();
        add(fxPanel);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loadCart(cart, fxPanel);
            }
        });

        setVisible(true);
    }

    private void loadCart(Cart cart, JFXPanel fxPanel) {
        try {
            URL fxml = CartScreen.class.getResource("cart.fxml");
            if (fxml == null) {
                throw new IllegalStateException("cart.fxml not found beside CartScreen.class");
            }

            FXMLLoader loader = new FXMLLoader(fxml);
            loader.setController(new CartScreenController(cart));
            Parent root = loader.load();
            fxPanel.setScene(new Scene(root));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Cannot load cart", JOptionPane.ERROR_MESSAGE);
        }
    }
}
