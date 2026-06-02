package hust.soict.vp.aims.screen;

import hust.soict.vp.aims.cart.Cart;
import hust.soict.vp.aims.media.Media;
import hust.soict.vp.aims.store.Store;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class StoreScreen extends JFrame {
    private final Store store;
    private final Cart cart;
    private JPanel centerPanel;

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;

        setTitle("AIMS Store");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(createNorth(), BorderLayout.NORTH);
        centerPanel = new JPanel();
        add(new JScrollPane(centerPanel), BorderLayout.CENTER);
        refreshStore();

        setVisible(true);
    }

    private JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());

        JLabel title = new JLabel("AIMS STORE", SwingConstants.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 28));
        title.setBorder(BorderFactory.createEmptyBorder(16, 0, 16, 0));
        title.setAlignmentX(CENTER_ALIGNMENT);
        north.add(title);

        return north;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu options = new JMenu("Options");
        JMenu updateStore = new JMenu("Update Store");

        JMenuItem addBook = new JMenuItem("Add Book");
        addBook.addActionListener(e -> new AddBookToStoreScreen(store, cart, this));

        JMenuItem addCD = new JMenuItem("Add CD");
        addCD.addActionListener(e -> new AddCompactDiscToStoreScreen(store, cart, this));

        JMenuItem addDVD = new JMenuItem("Add DVD");
        addDVD.addActionListener(e -> new AddDigitalVideoDiscToStoreScreen(store, cart, this));

        JMenuItem viewStore = new JMenuItem("View Store");
        viewStore.addActionListener(e -> refreshStore());

        JMenuItem viewCart = new JMenuItem("View Cart");
        viewCart.addActionListener(e -> new CartScreen(cart));

        updateStore.add(addBook);
        updateStore.add(addCD);
        updateStore.add(addDVD);
        options.add(updateStore);
        options.add(viewStore);
        options.add(viewCart);
        menuBar.add(options);
        return menuBar;
    }

    public void refreshStore() {
        centerPanel.removeAll();
        centerPanel.setLayout(new GridLayout(0, 3, 12, 12));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        for (Media media : store.getItemsInStore()) {
            centerPanel.add(new MediaStore(media, cart));
        }

        centerPanel.revalidate();
        centerPanel.repaint();
    }
}
