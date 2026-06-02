package hust.soict.vp.aims;

import hust.soict.vp.aims.cart.Cart;
import hust.soict.vp.aims.exception.PlayerException;
import hust.soict.vp.aims.media.Book;
import hust.soict.vp.aims.media.CompactDisc;
import hust.soict.vp.aims.media.DigitalVideoDisc;
import hust.soict.vp.aims.media.Media;
import hust.soict.vp.aims.media.Playable;
import hust.soict.vp.aims.media.Track;
import hust.soict.vp.aims.screen.StoreScreen;
import hust.soict.vp.aims.store.Store;

import javax.swing.SwingUtilities;
import java.util.Scanner;

public class Aims {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Store store = new Store();
    private static final Cart cart = new Cart();

    public static void main(String[] args) {
        seedStore();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StoreScreen(store, cart);
            }
        });
    }

    private static void seedStore() {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
                "The Lion King",
                "Animation",
                "Roger Allers",
                87,
                19.95f
        );

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
                "Star Wars",
                "Science Fiction",
                "George Lucas",
                87,
                24.95f
        );

        Book book = new Book("Clean Code", "Programming", 30.50f);
        book.addAuthor("Robert C. Martin");
        book.setContent("A handbook of agile software craftsmanship");

        CompactDisc cd = new CompactDisc(
                "Hybrid Theory",
                "Rock",
                "Don Gilmore",
                "Linkin Park",
                15.75f
        );
        cd.addTrack(new Track("Papercut", 184));
        cd.addTrack(new Track("One Step Closer", 157));
        cd.addTrack(new Track("In the End", 216));

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book);
        store.addMedia(cd);
    }

    public static void showMenu() {
        System.out.println("AIMS:");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3: ");
    }

    public static void storeMenu() {
        System.out.println("Options:");
        System.out.println("--------------------------------");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4: ");
    }

    public static void mediaDetailsMenu(Media media) {
        System.out.println("Options:");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");

        if (media instanceof Playable) {
            System.out.println("2. Play");
        }

        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2: ");
    }

    public static void cartMenu() {
        System.out.println("Options:");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.print("Please choose a number: 0-1-2-3-4-5: ");
    }

    private static void viewStore() {
        int choice;

        do {
            store.printStore();
            storeMenu();
            choice = readInt();

            switch (choice) {
                case 1:
                    seeMediaDetails();
                    break;
                case 2:
                    addMediaToCartFromStore();
                    break;
                case 3:
                    playMediaFromStore();
                    break;
                case 4:
                    seeCurrentCart();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void seeMediaDetails() {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();

        Media media = store.getMediaByTitle(title);
        if (media == null) {
            System.out.println("No matching media found in store.");
            return;
        }

        System.out.println(media);

        int choice;
        do {
            mediaDetailsMenu(media);
            choice = readInt();

            switch (choice) {
                case 1:
                    cart.addMedia(media);
                    if (media instanceof DigitalVideoDisc) {
                        System.out.println("Number of DVDs in cart: " + cart.countDigitalVideoDiscs());
                    }
                    break;
                case 2:
                    playMedia(media);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void addMediaToCartFromStore() {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();

        Media media = store.getMediaByTitle(title);
        if (media == null) {
            System.out.println("No matching media found in store.");
            return;
        }

        cart.addMedia(media);

        if (media instanceof DigitalVideoDisc) {
            System.out.println("Number of DVDs in cart: " + cart.countDigitalVideoDiscs());
        }
    }

    private static void playMediaFromStore() {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();

        Media media = store.getMediaByTitle(title);
        if (media == null) {
            System.out.println("No matching media found in store.");
            return;
        }

        playMedia(media);
    }

    private static void updateStore() {
        System.out.println("Update store:");
        System.out.println("1. Add DVD");
        System.out.println("2. Add Book");
        System.out.println("3. Add CD");
        System.out.println("4. Remove media by title");
        System.out.println("0. Back");
        System.out.print("Choose: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                store.addMedia(inputDVD());
                break;
            case 2:
                store.addMedia(inputBook());
                break;
            case 3:
                store.addMedia(inputCD());
                break;
            case 4:
                removeMediaFromStore();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static DigitalVideoDisc inputDVD() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Director: ");
        String director = scanner.nextLine();

        System.out.print("Length: ");
        int length = readInt();

        System.out.print("Cost: ");
        float cost = readFloat();

        return new DigitalVideoDisc(title, category, director, length, cost);
    }

    private static Book inputBook() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Cost: ");
        float cost = readFloat();

        Book book = new Book(title, category, cost);

        System.out.print("Number of authors: ");
        int numberOfAuthors = readInt();

        for (int i = 0; i < numberOfAuthors; i++) {
            System.out.print("Author " + (i + 1) + ": ");
            book.addAuthor(scanner.nextLine());
        }

        System.out.print("Content: ");
        book.setContent(scanner.nextLine());

        return book;
    }

    private static CompactDisc inputCD() {
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Category: ");
        String category = scanner.nextLine();

        System.out.print("Director: ");
        String director = scanner.nextLine();

        System.out.print("Artist: ");
        String artist = scanner.nextLine();

        System.out.print("Cost: ");
        float cost = readFloat();

        CompactDisc cd = new CompactDisc(title, category, director, artist, cost);

        System.out.print("Number of tracks: ");
        int numberOfTracks = readInt();

        for (int i = 0; i < numberOfTracks; i++) {
            System.out.print("Track title " + (i + 1) + ": ");
            String trackTitle = scanner.nextLine();

            System.out.print("Track length " + (i + 1) + ": ");
            int trackLength = readInt();

            cd.addTrack(new Track(trackTitle, trackLength));
        }

        return cd;
    }

    private static void removeMediaFromStore() {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();

        Media media = store.getMediaByTitle(title);
        if (media == null) {
            System.out.println("No matching media found in store.");
            return;
        }

        store.removeMedia(media);
    }

    private static void seeCurrentCart() {
        int choice;

        do {
            cart.print();
            cartMenu();
            choice = readInt();

            switch (choice) {
                case 1:
                    filterCart();
                    break;
                case 2:
                    sortCart();
                    break;
                case 3:
                    removeMediaFromCart();
                    break;
                case 4:
                    playMediaFromCart();
                    break;
                case 5:
                    placeOrder();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private static void filterCart() {
        System.out.println("Filter by:");
        System.out.println("1. Id");
        System.out.println("2. Title");
        System.out.print("Choose: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                System.out.print("Enter id: ");
                cart.filterById(readInt());
                break;
            case 2:
                System.out.print("Enter title: ");
                cart.filterByTitle(scanner.nextLine());
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void sortCart() {
        System.out.println("Sort by:");
        System.out.println("1. Title then cost");
        System.out.println("2. Cost then title");
        System.out.print("Choose: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                cart.sortByTitleCost();
                cart.print();
                break;
            case 2:
                cart.sortByCostTitle();
                cart.print();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private static void removeMediaFromCart() {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();

        Media media = cart.searchByTitle(title);
        if (media != null) {
            cart.removeMedia(media);
        }
    }

    private static void playMediaFromCart() {
        System.out.print("Enter media title: ");
        String title = scanner.nextLine();

        Media media = cart.searchByTitle(title);
        if (media == null) {
            return;
        }

        playMedia(media);
    }

    private static void placeOrder() {
        System.out.println("An order is created.");
        cart.clear();
        System.out.println("The cart is now empty.");
    }

    private static void playMedia(Media media) {
        if (!(media instanceof Playable)) {
            System.out.println("This media is not playable.");
            return;
        }

        try {
            ((Playable) media).play();
        } catch (PlayerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter an integer: ");
            }
        }
    }

    private static float readFloat() {
        while (true) {
            try {
                return Float.parseFloat(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a number: ");
            }
        }
    }
}
