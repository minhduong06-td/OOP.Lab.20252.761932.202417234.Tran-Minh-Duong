package hust.soict.vp.aims.screen;

import hust.soict.vp.aims.cart.Cart;
import hust.soict.vp.aims.exception.PlayerException;
import hust.soict.vp.aims.media.Media;
import hust.soict.vp.aims.media.Playable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class MediaStore extends JPanel {
    private final Media media;
    private final Cart cart;

    public MediaStore(Media media, Cart cart) {
        this.media = media;
        this.cart = cart;

        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));

        JLabel title = new JLabel(media.getTitle());
        JLabel cost = new JLabel(String.format("%.2f $", media.getCost()));
        add(title, BorderLayout.NORTH);
        add(cost, BorderLayout.CENTER);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private JPanel createButtons() {
        JPanel buttons = new JPanel(new GridLayout(1, 0, 8, 8));

        JButton addToCart = new JButton("Add to cart");
        addToCart.addActionListener(e -> {
            cart.addMedia(media);
            JOptionPane.showMessageDialog(this, "Media added to cart.");
        });
        buttons.add(addToCart);

        if (media instanceof Playable) {
            JButton play = new JButton("Play");
            play.addActionListener(e -> playMedia());
            buttons.add(play);
        }

        return buttons;
    }

    private void playMedia() {
        try {
            ((Playable) media).play();
        } catch (PlayerException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Player error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
