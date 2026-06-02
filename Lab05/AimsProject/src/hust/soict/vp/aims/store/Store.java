package hust.soict.vp.aims.store;

import hust.soict.vp.aims.media.DigitalVideoDisc;
import hust.soict.vp.aims.media.Media;

import java.util.ArrayList;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<Media>();

    public void addMedia(Media media) {
        if (media == null) {
            System.out.println("The media is null.");
            return;
        }

        if (itemsInStore.contains(media)) {
            System.out.println("The media already exists in the store.");
            return;
        }

        itemsInStore.add(media);
        System.out.println("The media has been added to the store.");
    }

    public void removeMedia(Media media) {
        if (!itemsInStore.contains(media)) {
            System.out.println("The media was not found in the store.");
            return;
        }

        itemsInStore.remove(media);
        System.out.println("The media has been removed from the store.");
    }

    public Media getMediaByTitle(String title) {
        for (Media media : itemsInStore) {
            if (media.isMatch(title)) {
                return media;
            }
        }

        return null;
    }

    public void printStore() {
        System.out.println("***********************STORE**********************");
        System.out.println("Items in store:");

        if (itemsInStore.isEmpty()) {
            System.out.println("The store is empty.");
        }

        for (int i = 0; i < itemsInStore.size(); i++) {
            System.out.println((i + 1) + ". " + itemsInStore.get(i));
        }

        System.out.println("**************************************************");
    }

    public int getQtyInStore() {
        return itemsInStore.size();
    }

    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

    public void addDVD(DigitalVideoDisc disc) {
        addMedia(disc);
    }

    public void removeDVD(DigitalVideoDisc disc) {
        removeMedia(disc);
    }
}
