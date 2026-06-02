package hust.soict.vp.aims.screen;

import hust.soict.vp.aims.cart.Cart;
import hust.soict.vp.aims.media.CompactDisc;
import hust.soict.vp.aims.media.Track;
import hust.soict.vp.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    public AddCompactDiscToStoreScreen(Store store, Cart cart, StoreScreen storeScreen) {
        super("Add Compact Disc", store, cart, storeScreen);
        addField("Title");
        addField("Category");
        addField("Director");
        addField("Artist");
        addField("Cost");
        addField("Tracks (title:length, comma separated)");
        setVisible(true);
    }

    @Override
    protected void addItem() {
        try {
            CompactDisc cd = new CompactDisc(
                    text("Title"),
                    text("Category"),
                    text("Director"),
                    text("Artist"),
                    parseFloat("Cost")
            );

            String tracks = text("Tracks (title:length, comma separated)");
            if (!tracks.isEmpty()) {
                String[] parts = tracks.split(",");
                for (String part : parts) {
                    String[] tokens = part.trim().split(":");
                    if (tokens.length == 2) {
                        cd.addTrack(new Track(tokens[0].trim(), Integer.parseInt(tokens[1].trim())));
                    }
                }
            }

            store.addMedia(cd);
            showSuccess("CD added to store.");
        } catch (Exception e) {
            showError(e);
        }
    }
}
