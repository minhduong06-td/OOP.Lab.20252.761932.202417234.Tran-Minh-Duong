package hust.soict.vp.aims.screen;

import hust.soict.vp.aims.cart.Cart;
import hust.soict.vp.aims.media.Book;
import hust.soict.vp.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    public AddBookToStoreScreen(Store store, Cart cart, StoreScreen storeScreen) {
        super("Add Book", store, cart, storeScreen);
        addField("Title");
        addField("Category");
        addField("Cost");
        addField("Authors (comma separated)");
        addField("Content");
        setVisible(true);
    }

    @Override
    protected void addItem() {
        try {
            Book book = new Book(text("Title"), text("Category"), parseFloat("Cost"));
            String authors = text("Authors (comma separated)");
            if (!authors.isEmpty()) {
                String[] parts = authors.split(",");
                for (String author : parts) {
                    book.addAuthor(author.trim());
                }
            }
            book.setContent(text("Content"));
            store.addMedia(book);
            showSuccess("Book added to store.");
        } catch (Exception e) {
            showError(e);
        }
    }
}
