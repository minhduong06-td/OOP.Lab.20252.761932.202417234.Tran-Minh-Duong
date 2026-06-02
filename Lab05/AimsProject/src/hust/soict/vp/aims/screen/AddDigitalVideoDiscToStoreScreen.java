package hust.soict.vp.aims.screen;

import hust.soict.vp.aims.cart.Cart;
import hust.soict.vp.aims.media.DigitalVideoDisc;
import hust.soict.vp.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart, StoreScreen storeScreen) {
        super("Add DVD", store, cart, storeScreen);
        addField("Title");
        addField("Category");
        addField("Director");
        addField("Length");
        addField("Cost");
        setVisible(true);
    }

    @Override
    protected void addItem() {
        try {
            DigitalVideoDisc dvd = new DigitalVideoDisc(
                    text("Title"),
                    text("Category"),
                    text("Director"),
                    parseInt("Length"),
                    parseFloat("Cost")
            );
            store.addMedia(dvd);
            showSuccess("DVD added to store.");
        } catch (Exception e) {
            showError(e);
        }
    }
}
