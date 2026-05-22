package hust.soict.vp.aims.cart;

import hust.soict.vp.aims.media.DigitalVideoDisc;
import hust.soict.vp.aims.media.Media;
import hust.soict.vp.aims.media.Playable;

import java.util.ArrayList;
import java.util.Collections;

public class Cart {
    private ArrayList<Media> itemsOrdered = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("The media is null.");
            return;
        }

        if (itemsOrdered.contains(media)) {
            System.out.println("The media already exists in the cart.");
            return;
        }

        itemsOrdered.add(media);
        System.out.println("The media has been added to the cart.");
    }

    public void addMedia(Media... mediaList) {
        if (mediaList == null || mediaList.length == 0) {
            System.out.println("No media to add.");
            return;
        }

        for (Media media : mediaList) {
            addMedia(media);
        }
    }

    public void removeMedia(Media media) {
        if (!itemsOrdered.contains(media)) {
            System.out.println("The media was not found in the cart.");
            return;
        }

        itemsOrdered.remove(media);
        System.out.println("The media has been removed from the cart.");
    }

    public float totalCost() {
        float total = 0.0f;

        for (Media media : itemsOrdered) {
            total += media.getCost();
        }

        return total;
    }

    public void print() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");

        if (itemsOrdered.isEmpty()) {
            System.out.println("The cart is empty.");
        }

        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i));
        }

        System.out.printf("Total cost: %.2f $%n", totalCost());
        System.out.println("**************************************************");
    }

    public Media searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found: " + media);
                return media;
            }
        }

        System.out.println("No matching media found.");
        return null;
    }

    public Media searchByTitle(String title) {
        for (Media media : itemsOrdered) {
            if (media.isMatch(title)) {
                System.out.println("Found: " + media);
                return media;
            }
        }

        System.out.println("No matching media found.");
        return null;
    }

    public void filterById(int id) {
        boolean found = false;

        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println(media);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching media found.");
        }
    }

    public void filterByTitle(String title) {
        boolean found = false;

        for (Media media : itemsOrdered) {
            if (media.isMatch(title)) {
                System.out.println(media);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching media found.");
        }
    }

    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
    }

    public void playMedia(String title) {
        Media media = searchByTitle(title);

        if (media == null) {
            return;
        }

        if (!(media instanceof Playable)) {
            System.out.println("This media is not playable.");
            return;
        }

        ((Playable) media).play();
    }

    public void clear() {
        itemsOrdered.clear();
    }

    public int getQtyOrdered() {
        return itemsOrdered.size();
    }

    public int countDigitalVideoDiscs() {
        int count = 0;

        for (Media media : itemsOrdered) {
            if (media instanceof DigitalVideoDisc) {
                count++;
            }
        }

        return count;
    }

    public ArrayList<Media> getItemsOrdered() {
        return itemsOrdered;
    }

    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        addMedia(disc);
    }

    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        addMedia(dvd1, dvd2);
    }

    public void addDigitalVideoDisc(DigitalVideoDisc... dvdList) {
        addMedia(dvdList);
    }

    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        removeMedia(disc);
    }
}
