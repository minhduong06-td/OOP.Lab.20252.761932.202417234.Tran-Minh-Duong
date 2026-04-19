package hust.soict.vp.aims.store;

import hust.soict.vp.aims.disc.DigitalVideoDisc;

public class Store {
    public static final int MAX_ITEMS_IN_STORE = 100;

    private final DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[MAX_ITEMS_IN_STORE];
    private int qtyInStore = 0;

    public void addDVD(DigitalVideoDisc disc) {
        if (disc == null) {
            System.out.println("The disc is null.");
            return;
        }

        if (qtyInStore >= MAX_ITEMS_IN_STORE) {
            System.out.println("The store is full.");
            return;
        }

        itemsInStore[qtyInStore] = disc;
        qtyInStore++;
        System.out.println("The disc has been added to the store.");
    }

    public void removeDVD(DigitalVideoDisc disc) {
        int index = -1;

        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i] == disc) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("The disc was not found in the store.");
            return;
        }

        for (int i = index; i < qtyInStore - 1; i++) {
            itemsInStore[i] = itemsInStore[i + 1];
        }

        itemsInStore[qtyInStore - 1] = null;
        qtyInStore--;
        System.out.println("The disc has been removed from the store.");
    }

    public void printStore() {
        System.out.println("Items in store:");

        for (int i = 0; i < qtyInStore; i++) {
            System.out.println((i + 1) + ". " + itemsInStore[i]);
        }

    }

    public int getQtyInStore() {
        return qtyInStore;
    }

    public DigitalVideoDisc[] getItemsInStore() {
        return itemsInStore;
    }
}